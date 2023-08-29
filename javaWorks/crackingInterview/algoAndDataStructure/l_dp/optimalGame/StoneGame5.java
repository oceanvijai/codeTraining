public class StoneGame5{
  /**
      There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.
    In each round of the game, Alice divides the row into two non-empty rows (i.e. left row and right row), then 
    Bob calculates the value of each row which is the sum of the values of all the stones in this row. Bob throws away the row 
    which has the maximum value, and Alice's score increases by the value of the remaining row. If the value of the two rows are equal, 
    Bob lets Alice decide which row will be thrown away. The next round starts with the remaining row.
    The game ends when there is only one stone remaining. Alice's is initially zero.

    Return the maximum score that Alice can obtain.

    Example 1:
    
    Input: stoneValue = [6,2,3,4,5,5]
    Output: 18
    Explanation: In the first round, Alice divides the row to [6,2,3], [4,5,5]. The left row has the value 11 and the right row has value 14. Bob throws away the right row and Alice's score is now 11.
    In the second round Alice divides the row to [6], [2,3]. This time Bob throws away the left row and Alice's score becomes 16 (11 + 5).
    The last round Alice has only one choice to divide the row which is [2], [3]. Bob throws away the right row and Alice's score is now 18 (16 + 2). The game ends because only one stone is remaining in the row.
    Example 2:
    
    Input: stoneValue = [7,7,7,7,7,7,7]
    Output: 28
    Example 3:
    
    Input: stoneValue = [4]
    Output: 0
  **/

  /**
    This is just like the other problems.
    When Alice makes a cut(decesion), bob makes a decesion. Further is based on the remaing array left.

    When alice divides the array, and if the left is larger. Bob discards it and vice versa.
    If both halves are same, then we need to try both the arrays. Why ? because based on the numbers in those arrays might produce different results.

    We DP it and we get a O(n^3) solution. N^2 states iterated n times.
  **/


  public int stoneGameV(int[] stoneValue) {
        int total = Arrays.stream(stoneValue).sum();
        int[][] dp = new int[stoneValue.length][stoneValue.length];
        Arrays.stream(dp).forEach(arr -> Arrays.fill(arr, -1));
        return solve(stoneValue, 0, stoneValue.length-1, total, dp);
    }

    private int solve(int[] stones, int start, int end, int total, int[][] dp){
        if(start >= stones.length){
            return 0;
        }

        if(dp[start][end] != -1){
            return dp[start][end];
        }

        int sunSum = 0, ans = 0;

        for(int i = start; i <=  end; i++){
            sunSum += stones[i];
            int secondSlice = total - sunSum;
            if(sunSum < secondSlice){
                ans = Math.max(ans, sunSum + solve(stones, start, i, sunSum, dp));
            }else if(sunSum > secondSlice){
                ans = Math.max(ans, secondSlice + solve(stones, i+1, end, secondSlice, dp));
            }else{
                ans = Math.max(ans, sunSum + solve(stones, start, i, sunSum, dp));
                ans = Math.max(ans, secondSlice + solve(stones, i+1, end, secondSlice, dp));
            }
        }

        dp[start][end] = ans;
        return dp[start][end];
    }


  /**
    bottom up solution.

  **/


  public int stoneGameV(int[] stoneValue) {
        int total = Arrays.stream(stoneValue).sum();
        int n = stoneValue.length;
        
        int[] prefixSum = new int[n+1];
        for(int i = 1; i <= n; i++){
            prefixSum[i] = prefixSum[i-1]+stoneValue[i-1];
        }

        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++){
            dp[i][i] = 0; // Need not do it, just for stating the base case
        }

        for(int l = 1; l < n; l++){
            for(int r = 0; r < n-l; r++){
                int start = r, end = start + l, ans = 0;
                for(int k = start; k < end; k++){
                    int leftSum = prefixSum[k+1] -  prefixSum[start];
                    int rightSum = prefixSum[end+1] -  prefixSum[k+1];
                    if(leftSum < rightSum){
                        ans = Math.max(ans, leftSum + dp[start][k]);
                    }else if(leftSum > rightSum){
                        ans = Math.max(ans, rightSum + dp[k+1][end]);
                    }else{
                        ans = Math.max(ans, leftSum + dp[start][k]);
                        ans = Math.max(ans, rightSum + dp[k+1][end]);
                    }
                }
                dp[start][end] = ans;
            }
        }
        return dp[0][n-1];
    }


  /**
    WE can further optimise it. We need to find the right K value which give us the optimal value for alice without iterating over all K for that range.
    How ?

  **/
  
}
