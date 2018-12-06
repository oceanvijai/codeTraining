public class BuySellAnyTransWithFee {
    /**
     * Your are given an array of integers prices, for which the i-th element is the
     * price of a given stock on day i; and a non-negative integer fee representing
     * a transaction fee.
     * 
     * You may complete as many transactions as you like, but you need to pay the
     * transaction fee for each transaction. You may not buy more than 1 share of a
     * stock at a time (ie. you must sell the stock share before you buy again.)
     * 
     * Return the maximum profit you can make.
     */

    public int maxProfit(int[] prices, int fee) {

        if (prices.length <= 1)
            return 0;

        int n = prices.length;
        int[] buy = new int[n];
        int[] sell = new int[n];

        buy[0] = -prices[0];
        for (int i = 1; i < n; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i] - fee);
        }

        return sell[n - 1];
    }
}