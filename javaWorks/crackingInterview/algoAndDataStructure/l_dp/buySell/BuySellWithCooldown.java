public class BuySellWithCooldown {
    /**
     * Say you have an array for which the ith element is the price of a given stock
     * on day i.
     * 
     * Design an algorithm to find the maximum profit. You may complete as many
     * transactions as you like (ie, buy one and sell one share of the stock
     * multiple times) with the following restrictions:
     * 
     * You may not engage in multiple transactions at the same time (ie, you must
     * sell the stock before you buy again). After you sell your stock, you cannot
     * buy stock on next day. (ie, cooldown 1 day)
     * 
     * Input: [1,2,3,0,2]
     * 
     * Output: 3 Explanation:
     * 
     * transactions = [buy, sell, cooldown, buy, sell]
     */

    /**
     * Approach: when you are doing a transaction on that day, we need to take the
     * one state previous to the previous one.
     * 
     * i.e, when we want to buy, we need to use the previous sell (profit so far)
     * with the current price
     * 
     * When you want to sell, we need to do it with the previous buy
     */

    public int maxProfit(int[] prices) {
        int sell = 0;
        int prevSell = 0;

        int buy = Integer.MIN_VALUE;
        int prevBuy = 0;

        for (int price : prices) {
            prevBuy = buy;
            buy = Math.max(prevBuy, prevSell - price);

            prevSell = sell;
            sell = Math.max(prevSell, prevBuy + price);
        }

        return sell;
    }

    // The above one id derived from

    /**
     * The below one is not fully complete yet, need to verify corner cases
     * @param prices
     * @return
     */

    public int maxProfit_(int[] prices) {

        if (prices.length <= 1)
            return 0;
        int days = prices.length, buy[] = new int[days], sell[] = new int[days];
        buy[0] = -prices[0];
        for (int i = 1; i < days; i++) {
            if (i == 1) {
                buy[i] = prices[0] - prices[1];
            } else {
                buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            }

            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[days - 1];
    }
}