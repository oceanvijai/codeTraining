# How many requests per second do you want a user to be allowed to do?
REPLENISH_RATE = 100

# How much bursting do you want to allow?
CAPACITY = 5 * REPLENISH_RATE

SCRIPT = File.read('request_rate_limiter.lua')

def check_request_rate_limiter(user)
  # Make a unique key per user.
  prefix = 'request_rate_limiter.' + user

  # You need two Redis keys for Token Bucket.
  keys = [prefix + '.tokens', prefix + '.timestamp']

  # The arguments to the LUA script. time() returns unixtime in seconds.
  args = [REPLENISH_RATE, CAPACITY, Time.new.to_i, 1]

  begin
    allowed, tokens_left = redis.eval(SCRIPT, keys, args)
  rescue RedisError => e
    # Fail open. We don't want a hard dependency on Redis to allow traffic.
    # Make sure to set an alert so you know if this is happening too much.
    # Our observed failure rate is 0.01%.
    puts 'Redis failed: ' + e
    return
  end

  if !allowed
    raise RateLimitError.new(status_code = 429)
  end
end
