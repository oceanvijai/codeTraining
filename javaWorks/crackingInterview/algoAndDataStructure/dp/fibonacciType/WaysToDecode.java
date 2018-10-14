public class WaysToDecode {

    /**
     * A message containing letters from A-Z is being encoded to numbers using the
     * following mapping:
     */

    /**
     * 'A' -> 1 'B' -> 2 ... 'Z' -> 26
     */

    /**
     * Example :
     * 
     * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
     * 
     * The number of ways decoding "12" is 2.
     */

     // this is very much fibinacci but a minor variation
     // here we only only if the second and first are within a range

    public int numDecodings(String A) {

        int n = A.length();
        int[] dp = new int[n + 1];

        dp[0] = 1;
        if (A.charAt(0) != '0') {
            dp[1] = 1;
        }

        for (int i = 2; i <= n; i++) {
            Integer first = Integer.parseInt(A.substring(i - 1, i));
            if ((first >= 1) && (first <= 9)) {
                dp[i] += dp[i - 1];
            }

            Integer second = Integer.parseInt(A.substring(i - 2, i));
            if ((second >= 10) && (second <= 26)) {
                dp[i] += dp[i - 2];
            }

        }

        return dp[n];
    }

}