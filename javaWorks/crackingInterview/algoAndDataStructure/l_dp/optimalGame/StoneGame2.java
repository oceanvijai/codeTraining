public class StoneGame2{
  /**
    Alice and Bob continue their games with piles of stones.  There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].  
    The objective of the game is to end with the most stones. 
    Alice and Bob take turns, with Alice starting first.  Initially, M = 1.
    On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).
    The game continues until all the stones have been taken.
    Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.

    Example 1:
    
    Input: piles = [2,7,9,4,4]
    Output: 10
    Explanation:  If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again. Alice can get 2 + 4 + 4 = 10 piles in total. If Alice takes two piles at the beginning, then Bob can take all three piles left. In this case, Alice get 2 + 7 = 9 piles in total. So we return 10 since it's larger. 
    Example 2:
    
    Input: piles = [1,2,3,4,5,100]
    Output: 104


  **/


  /**
    Like stoneGame1 we dont want to complicate, since at each recursion we got a max of sum + further recursions.
    So we keep it simple and straight forward


  **/


  public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[][][] dp = new int[2][n][n];
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp[i].length; j++){
                for(int k = 0; k < dp[i][j].length; k++){
                    dp[i][j][k] = -1;
                }
            }
        }
        return solve(piles, 0, 0, 1, dp);
    }

    private int solve(int[]piles, int isAlicePlay, int currentIndex, int m, int[][][] dp){
        if(piles.length == 1){
            return piles[0];
        }

        if(currentIndex >= piles.length){
            return 0;
        }

        if(dp[isAlicePlay][currentIndex][m] != -1){
            return dp[isAlicePlay][currentIndex][m];
        }

        int ans = isAlicePlay == 0 ? -1 : Integer.MAX_VALUE, rangeSum = 0;

        for(int i = 1; i <= Math.min(2*m, piles.length-currentIndex); i++){
            rangeSum += piles[currentIndex+i-1];
            if(isAlicePlay == 0){
                ans = Math.max(ans, rangeSum + solve(piles, 1, currentIndex+i, Math.max(m,i), dp));
            }else{
                ans = Math.min(ans, solve(piles, 0, currentIndex+i, Math.max(m,i), dp));
            }
        }

        dp[isAlicePlay][currentIndex][m] = ans;
        return dp[isAlicePlay][currentIndex][m];
    }


  
}
