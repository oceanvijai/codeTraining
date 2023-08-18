public class StoneGame{

    /**

      Alice and Bob play a game with piles of stones. There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
      The objective of the game is to end with the most stones. The total number of stones across all the piles is odd, so there are no ties.
      Alice and Bob take turns, with Alice starting first. Each turn, a player takes the entire pile of stones either 
      from the beginning or from the end of the row. This continues until there are no more piles left, at which point the person with the most stones wins.
      Assuming Alice and Bob play optimally, return true if Alice wins the game, or false if Bob wins.

    **/


    /**
        brute force, as explained in approach.txt first equaltion

    **/


    public boolean stoneGame(int[] piles) {
        int sum = Arrays.stream(piles).sum();
        int firstPlayerMaxSum = maxSum(piles, 0, piles.length-1, sum);

        return sum - firstPlayerMaxSum > 0;
    }

    private int maxSum(int[] piles, int l, int r, int sum){
        if(l > r){
            return 0;
        }
        // Pick from right side
        // sum - piles[l] is the sume from l+1 till r
        int a =  piles[l] + (sum - piles[l]) - maxSum (piles, l+1, r, sum - piles[l]);
        // Pick from left side
        // sum - piles[r] is the sume from l till r-1
        int b = piles[r] + (sum - piles[r]) - maxSum (piles, l, r-1, sum - piles[r]);

        return Math.max(a, b);
    }

    /**
        brute force, as explained in approach.txt equaltion two

    **/


    public boolean stoneGame(int[] piles) {
        int sum = Arrays.stream(piles).sum();
        int firstPlayerMaxSum = maxSum(piles, 0, piles.length-1);

        return sum - firstPlayerMaxSum > 0;
    }

    private int maxSum(int[] piles, int l, int r){
        if(l > r){
            return 0;
        }
        // Pick from right side
        int a = piles[l] + Math.min(maxSum(piles,l+2,r), maxSum(piles,l+1,r-1));
        // Pick from left side
        int b = piles[r] + Math.min(maxSum(piles,l+1,r-1), maxSum(piles,l,r-2));

        return Math.max(a, b);
    }

    /**
        Let do DP for this. Top down.
        Time:O(n^2), space:O(n)
    **/


    public boolean stoneGame(int[] piles) {
        int sum = Arrays.stream(piles).sum();
        int[][] dp = new int[piles.length][piles.length];
        Arrays.stream(dp).forEach(arr -> Arrays.fill(arr, -1));
        int firstPlayerMaxSum = maxSum(piles, 0, piles.length-1, dp);

        return sum - firstPlayerMaxSum > 0;
    }

    private int maxSum(int[] piles, int l, int r, int[][] dp){
        if(l > r){
            return 0;
        }

        if(dp[l][r] != -1){
            return dp[l][r];
        }

        // Pick from right side
        int a = piles[l] + Math.min(maxSum(piles,l+2,r,dp), maxSum(piles,l+1,r-1,dp));
        // Pick from left side
        int b = piles[r] + Math.min(maxSum(piles,l+1,r-1,dp), maxSum(piles,l,r-2,dp));

        dp[l][r] = Math.max(a, b);
        return dp[l][r];
    }

    /**
        Let do DP for this. bottom up.
        Time:O(n^2), space:O(n)
        We DP in a diagonal fashion as follows,
        input [1,2,5,1,9,3]
        We first calculate range is 2, 3 ...till we find the answer for range n.

        1, 2, 6, 6, 15, 15
        0, 2, 5, 3, 14, 6
        0, 0, 5, 5, 10, 14
        0, 0, 0, 1, 9,  4
        0, 0, 0, 0, 9,  9
        0, 0, 0, 0, 0,  0
    **/



    public boolean stoneGame(int[] piles) {
        int sum = Arrays.stream(piles).sum();
        int n = piles.length;
        int[][] dp = new int[piles.length+1][piles.length+1];

        for(int i = 1; i <= n; i++){
            dp[i][i] = piles[i-1];
        }

        for(int length = 2; length < n; length++){
            for(int row = 1; row <= n; row++){
                int l = row, r = l+length;
                if(r <= n){
                    int a = piles[l-1] + Math.min(l+2 > r ? 0: dp[l+2][r], l+1 > r-1 ? 0: dp[l+1][r-1]);
                    int b = piles[r-1] + Math.min(l+1 > r-1 ? 0: dp[l+1][r-1], l > r-2 ? 0: dp[l][r-2]);
                    dp[l][r] = Math.min(a,b);
                }
            }
        }

        int firstPlayerMaxSum = dp[1][n];

        return sum - firstPlayerMaxSum > 0;
    }
  
}
