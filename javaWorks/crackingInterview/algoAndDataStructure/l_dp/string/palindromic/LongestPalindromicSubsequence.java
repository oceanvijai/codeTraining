public class LongestPalindromicSubsequence{

/**
  Given a string s, find the longest palindromic subsequence's length in s.

  A subsequence is a sequence that can be derived from another sequence by deleting some or no elements 
  without changing the order of the remaining elements.

 

    Example 1:

    Input: s = "bbbab"
    Output: 4
    Explanation: One possible longest palindromic subsequence is "bbbb".
    Example 2:

    Input: s = "cbbd"
    Output: 2
    Explanation: One possible longest palindromic subsequence is "bb".


**/
  
  /**
    Approach: This is an application of edit distance or better the longest common sub sequence.
    Only inside if two string for LCS we reverse the one give to get the string2
  **/
  
  
  
  
  public int longestPalindromeSubseq(String s) {
        String reversedS = new StringBuilder(s).reverse().toString();
        int n = s.length();
        int[][] dp = new int[n+1][n+1];
        

        for(int i =1; i <= n; i++){
            for(int j =1; j <= n; j++){
                if(reversedS.charAt(i-1) == s.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }


        return dp[n][n];
    }



}
