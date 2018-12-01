public class WildCardMatch {
    /**
     * Given an input string (s) and a pattern (p), implement wildcard pattern
     * matching with support for '?' and '*'.
     * 
     * '?' Matches any single character.
     * 
     * '*' Matches any sequence of characters (including the empty sequence).
     */

    public boolean isMatch(final String A, final String B) {

        int s = A.length();
        int p = B.length();

        boolean[][] dp = new boolean[p + 1][s + 1];

        dp[0][0] = true;

        for (int i = 1; i <= p; i++) {
            if (B.charAt(i - 1) == '*') {
                dp[i][0] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i <= p; i++) {
            for (int j = 1; j <= s; j++) {
                if (B.charAt(i - 1) == '*') {
                    // I can skip '*' or
                    // think the current string in the input string is the '*'
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else if (B.charAt(i - 1) == '?' || B.charAt(i - 1) == A.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[p][s] ? true : false;

    }
}