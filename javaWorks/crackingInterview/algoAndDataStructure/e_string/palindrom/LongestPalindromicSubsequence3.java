public class LongestPalindromicSubsequence3{

  /**
      Minimum Number of Moves to Make Palindrome
      
      
      You are given a string s consisting only of lowercase English letters.

      In one move, you can select any two adjacent characters of s and swap them.

      Return the minimum number of moves needed to make s a palindrome.

      Note that the input will be generated such that s can always be converted to a palindrome.



      Example 1:

      Input: s = "aabb"
      Output: 2
      Explanation:
      We can obtain two palindromes from s, "abba" and "baab". 
      - We can obtain "abba" from s in 2 moves: "aabb" -> "abab" -> "abba".
      - We can obtain "baab" from s in 2 moves: "aabb" -> "abab" -> "baab".
      Thus, the minimum number of moves needed to make s a palindrome is 2.
      Example 2:

      Input: s = "letelt"
      Output: 2
      Explanation:
      One of the palindromes we can obtain from s in 2 moves is "lettel".
      One of the ways we can obtain it is "letelt" -> "letetl" -> "lettel".
      Other palindromes such as "tleelt" can also be obtained in 2 moves.
      It can be shown that it is not possible to obtain a palindrome in less than 2 moves.
  **/
  
  /**
    This cannot be solved by a DP solution.
    
    We can use a two pointer like this.
    
    Start with left and right most.
    Then if the chars are same, increment both pointers
    Else find a char to the left of right pointer which is equal to the left char.
    Then move it to the right location and count along the way.
    
    If there is not char equal to the left char, it moslty belongs to the center of the palindrom.
    So we simple swap it one location to the right and deal with the current char again
    
  **/
  
  
  
  public int minMovesToMakePalindrome(String s) {
        int ans = 0;
        int n = s.length();
        int l = 0, r = n-1;
        char[] charArray = s.toCharArray();

        // Do two pointer
        while(l < r){
            // if the char is same in both the end, move both the pointers
            if(charArray[l] == charArray[r]){
                l++;
                r--;
            }
            // If they dont match
            else{
                // Find the place where we can swap
                int k = findSwapLocation(charArray,l,r);
                if(k == l){
                    // Swap left char to one side to right
                    swapOnceToRight(charArray,l);
                    ans++;
                }else{
                    // Move the char in Kth position to R
                    while(k < r){
                        swapOnceToRight(charArray,k);
                        ans++;
                        k++;
                    }
                }
            }
        }

        return ans;
    }

    private int findSwapLocation(char[] charArray,int l,int r){
        int k = r-1;
        while(k > l){
            if(charArray[l] == charArray[k]){
                return k; 
            }
            k--;
        }
        return k;
    }

    private void swapOnceToRight(char[] charArray, int l){
        char t = charArray[l];
        charArray[l] =  charArray[l+1];
        charArray[l+1] = t;
    }



}
