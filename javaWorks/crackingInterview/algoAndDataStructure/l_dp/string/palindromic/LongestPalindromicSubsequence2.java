public class LongestPalindromicSubsequence2{

  /**
    Minimum Insertion Steps to Make a String Palindrome
    
    Given a string s. In one step you can insert any character at any index of the string.

    Return the minimum number of steps to make s palindrome.

    A Palindrome String is one that reads the same backward as well as forward.



    Example 1:

    Input: s = "zzazz"
    Output: 0
    Explanation: The string "zzazz" is already palindrome we do not need any insertions.
    Example 2:

    Input: s = "mbadm"
    Output: 2
    Explanation: String can be "mbdadbm" or "mdbabdm".
    Example 3:

    Input: s = "leetcode"
    Output: 5
    Explanation: Inserting 5 characters the string becomes "leetcodocteel".
  
  
  **/
  
  
  /**
      Approach: this is same as the LongestPalindromicSubsequence except one line change
      
      After finding the longest sub sequence, we subract it from the totol length
  **/
  
  
  public int minInsertions(String s) {
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

        return n-dp[n][n];
    }


}
