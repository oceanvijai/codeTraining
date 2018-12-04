public class TinyURLEncodeAndDecode {

    /**
     * TinyURL is a URL shortening service where you enter a URL such as
     * https://leetcode.com/problems/design-tinyurl and it returns a short URL such
     * as http://tinyurl.com/4e9iAk.
     * 
     * Design the encode and decode methods for the TinyURL service. There is no
     * restriction on how your encode/decode algorithm should work. You just need to
     * ensure that a URL can be encoded to a tiny URL and the tiny URL can be
     * decoded to the original URL.
     */

    static String base = "http://tinyurl.com/";

    int seed = 0; // increment for every seed
    int MAX_SEED = 10_000;
    int hashLength = 6;

    Map<String, String> store = new HashMap<>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        int div = longUrl.length() / hashLength;

        if (div == 0) {
            store.put(longUrl, longUrl);
            return longUrl;
        }

        String hash = "";
        // create a hash from the given URL
        while (div > 0) {
            if (div * 6 < longUrl.length() && longUrl.charAt(div * 6) != '/') {
                hash = hash + longUrl.charAt(div * 6);
            }
            div--;
        }

        // Add seed to hash
        hash = seed + hash;
        seed++;
        if (MAX_SEED < seed) {
            seed = 0;
        }

        // Store the tinyURL hash with long url
        store.put(hash, longUrl);

        // Add base URL for tiny URL
        hash = base + hash;

        return hash;

    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {

        String[] url = shortUrl.split("/");
        String hash = url[url.length - 1];

        return store.get(hash);
    }

}