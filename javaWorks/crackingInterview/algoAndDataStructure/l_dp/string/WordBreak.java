public class WordBreak {

    /**
     * Given a non-empty string s and a dictionary wordDict containing a list of
     * non-empty words, determine if s can be segmented into a space-separated
     * sequence of one or more dictionary words.
     */

    // Recursive with memorization

    public boolean wordBreak(String s, List<String> wordDict) {
        int[] dp = new int[s.length()];
        return solve(s, 0, wordDict, dp);
    }

    private boolean solve(String s, int startIndex, List<String> wordDict, int[] dp) {
        if (startIndex == s.length()) {
            return true;
        }

        if (dp[startIndex] != 0) {
            return dp[startIndex] == 1;
        }

        for (String word : wordDict) {
            if (matches(startIndex, word, s) && solve(s, startIndex + word.length(), wordDict, dp)) {
                dp[startIndex] = 1;
                return true;
            } else {
                dp[startIndex] = -1;
            }
        }

        return false;
    }

    private boolean matches(int index, String word, String s) {
        if ((index + word.length()) > s.length())
            return false;
        for (int i = 0; i < word.length(); i++)
            if (s.charAt(i + index) != word.charAt(i))
                return false;
        return true;
    }

    /**
     * Itervative approach: O(n^2)
     */

    public boolean wordBreak(String s, List<String> wordDict) {

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

}