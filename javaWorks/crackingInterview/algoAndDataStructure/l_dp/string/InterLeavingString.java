public class InterLeavingString {
    /**
     * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
     * 
     * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
     * 
     * Output: true
     * 
     * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
     * 
     * Output: false
     */

    /**
     * Approach 1:
     * 
     * 1. Find LongestCommonSubSequence of s1 and s3 2. Find
     * LongestCommonSubSequence of s2 and s3 3. The lcs of s1 and s2 put togther
     * should be same as length of s1 plus s2
     * 
     * n - s1, m - s2, s - s3
     * 
     * O(sn) + O(sm)
     */

    /**
     * Approach 2:
     * 
     * 1.
     * 
     * n - s1, m - s2, s - s3
     */

    public boolean isInterleave(String s1, String s2, String s3) {

        if ((s1.length() + s2.length()) != s3.length())
            return false;

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;

        // We assume the s3 is made only of s1
        for (int i = 1; i <= s1.length(); i++) {
            dp[i][0] = (dp[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1)));
        }

        // We assume the s3 is made only of s2
        for (int i = 1; i <= s2.length(); i++) {
            dp[0][i] = (dp[0][i - 1] && (s2.charAt(i - 1) == s3.charAt(i - 1)));
        }

        // both
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {

                dp[i][j] = (dp[i - 1][j] && (s1.charAt(i - 1) == s3.charAt(i + j - 1)))
                        || (dp[i][j - 1] && (s2.charAt(j - 1) == s3.charAt(i + j - 1)));
            }
        }

        return dp[s1.length()][s2.length()];
    }

}