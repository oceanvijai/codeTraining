public class RegularExpressionMatch {

    /**
     * Given an input string (s) and a pattern (p), implement regular expression
     * matching with support for '.' and '*'.
     */

    /**
     * '.' Matches any single character. It can accomadate any one char.
     * 
     * '*' Matches zero or more of the preceding element. Meaning if * come before char 'k' like k*, then we can conside "","k","kk..." but not other chars
     */

    public boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();

        boolean[][] dp = new boolean[pLen + 1][sLen + 1];

        dp[0][0] = true;

        for (int i = 1; i <= pLen; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[i][0] = dp[i - 2][0];
                ;
            }
        }

        for (int i = 1; i <= pLen; i++) {
            for (int j = 1; j <= sLen; j++) {

                if (p.charAt(i - 1) == '*') {
                    dp[i][j] = dp[i - 2][j];

                    if (s.charAt(j - 1) == p.charAt(i - 2) || p.charAt(i - 2) == '.') {
                        dp[i][j] = dp[i][j] || dp[i][j - 1];
                    }
                } else if (p.charAt(i - 1) == '.' || p.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[pLen][sLen];
    }
}
