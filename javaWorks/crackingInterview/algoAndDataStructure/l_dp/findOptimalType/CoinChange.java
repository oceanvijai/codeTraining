public class CoinChange {

    /**
     * You are given coins of different denominations and a total amount of money
     * amount. Write a function to compute the fewest number of coins that you need
     * to make up that amount. If that amount of money cannot be made up by any
     * combination of the coins, return -1.
     * 
     * 
     * Input: coins = [1, 2, 5], amount = 11 
     * 
     * Output: 3 Explanation: 11 = 5 + 5 + 1
     * 
     */

    public int coinChange(int[] coins, int amount) {
        // we can use 2d DP but not required since we look back in the same row

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            for (int j = 1; j <= amount; j++) {
                if (coin <= j && dp[j - coin] != -1) {
                    dp[j] = dp[j] == -1 ? dp[j - coin] + 1 : Math.min(dp[j], dp[j - coin] + 1);
                }
            }
        }

        return dp[amount];
    }
}