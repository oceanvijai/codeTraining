import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class RandomNumberInBound {

    /**
     * This is a reduced version of java RANDOM API
     * Not sure how this works 
     */

    private long seed;

    private static final long multiplier = 0x5DEECE66DL; // 25214903917
    private static final long addend = 0xBL; // 11
    private static final long mask = (1L << 48) - 1; // 65535 -> 1111111111111111

    public RandomNumberInBound() {
        this(System.nanoTime());
    }

    public RandomNumberInBound(long seed) {
            long scramble =  (seed ^ multiplier) & mask;
            this.seed = scramble;
    }

    public static void main(String[] args) {
        RandomNumberInBound r = new RandomNumberInBound();
        System.out.println(r.nextInt());
        for(int i = 0; i <= 10; i++){
            System.out.println(r.nextInt(10));
        }
        
    }

    /** 
    <p>The hedge "approximately" is used in the foregoing description only
     * because the next method is only approximately an unbiased source of
     * independently chosen bits.  If it were a perfect source of randomly
     * chosen bits, then the algorithm shown would choose {@code int}
     * values from the stated range with perfect uniformity.
     * <p>
     * The algorithm is slightly tricky.  It rejects values that would result
     * in an uneven distribution (due to the fact that 2^31 is not divisible
     * by n). The probability of a value being rejected depends on n.  The
     * worst case is n=2^30+1, for which the probability of a reject is 1/2,
     * and the expected number of iterations before the loop terminates is 2.
     * <p>
     * The algorithm treats the case where n is a power of two specially: it
     * returns the correct number of high-order bits from the underlying
     * pseudo-random number generator.  In the absence of special treatment,
     * the correct number of <i>low-order</i> bits would be returned.  Linear
     * congruential pseudo-random number generators such as the one
     * implemented by this class are known to have short periods in the
     * sequence of values of their low-order bits.  Thus, this special case
     * greatly increases the length of the sequence of values returned by
     * successive calls to this method if n is a small power of two.
     */
    
    public int nextInt(int bound) {
        if (bound <= 0)
            throw new IllegalArgumentException("bound must be positive");

        if ((bound & -bound) == bound) // i.e., bound is a power of 2
            return (int) ((bound * (long) next(31)) >> 31);
        int bits, val;
        do {
            bits = next(31);
            val = bits % bound;
        } while (bits - val + (bound - 1) < 0);
        return val;
    }

    protected int next(int bits) {
        long oldseed, nextseed;
        long seed = this.seed;
        
        oldseed = seed;
        nextseed = (multiplier * oldseed + addend) & mask;
        this.seed = nextseed;
        
        return (int) (nextseed >>> (48 - bits));
    }

    public int nextInt() {
        return next(32);
    }
}