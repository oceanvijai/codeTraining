public class CoinChange2 {
    /**
     * You are given coins of different denominations and a total amount of money.
     * 
     * Write a function to compute the number of combinations that make up that
     * amount. You may assume that you have infinite number of each kind of coin.
     */

    /**
     * Approach it like knacksack, since we can either take the coin or not to make
     * this sum
     * 
     * Since we have infinite supply we just make sure, we use the same row even
     * when we decide to take it
     */

    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= coins.length; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j]; // meaning I didnt take this coin

                if (j >= coins[i - 1]) { // then add it if I consider taking this coin as well
                    dp[i][j] = dp[i][j] + dp[i][j - coins[i - 1]];
                }
            }
        }

        return dp[coins.length][amount];
    }
}