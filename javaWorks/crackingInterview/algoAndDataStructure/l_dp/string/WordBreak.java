public class WordBreak {

    /**
     * Given a non-empty string s and a dictionary wordDict containing a list of
     * non-empty words, determine if s can be segmented into a space-separated
     * sequence of one or more dictionary words.
     */

    // Recursive with memorization

    /**
        Time: 
            recurence relation T(n) = T(n-1) + 1 + T(n-2) + 2 +  T(n-3) + 3 + T(n-4) + 4 ... T(0)
                                    = T(n-1) + T(n-2) + T(n-3) + T(n-4) ... T(0) + 1 + 2 + 3 + 4 ... n 
                                    = T(n-1) + T(n-2) + T(n-3) + T(n-4) ... T(0) + n^2
                                    = T(n-1) + T(n-1) + n^2 ,since T(n-2) + T(n-3) + T(n-4) ... T(0) is what we get for T(n-1) on LHS
                                    = 2T(n-1) + n^2
                                    = 2^n + n^2
                                    = 2^n // This is same via masters therom
    **/

    // Above for recursive solution


    /**
        Top down
        t(n)  = T(n-m) + T(n-2m) + T(n-3m)... + Km
              = 2T(n-m) + km
              = 2^f T(n-fm) + fkm
              f = (n+1)/m == n/m
              = 2^(n/m) + nk

              When m = 1, we have 2^n

              But if we look closer, we return when we got true from the reursion, which means, T(n) does not 
              go for T(n-m) , T(n-2m)... so on

              So in worst case, It goes like this
      T(n) = T(n-m) + km, km - for the dict check and only one goes for the recursion

      Which means we have, T(n) = nk
              

    **/

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
