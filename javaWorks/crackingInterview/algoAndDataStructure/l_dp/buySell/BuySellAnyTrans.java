public class BuySellAnyTrans {
    /**
     * Say you have an array for which the ith element is the price of a given stock
     * on day i.
     * 
     * Design an algorithm to find the maximum profit. You may complete as many
     * transactions as you like (i.e., buy one and sell one share of the stock
     * multiple times).
     * 
     * Input: [7,1,5,3,6,4] Output: 7 Explanation: Buy on day 2 (price = 1) and sell
     * on day 3 (price = 5), profit = 5-1 = 4. Then buy on day 4 (price = 3) and
     * sell on day 5 (price = 6), profit = 6-3 = 3.
     */

    /**
     * Say the given array is:
     * 
     * [7, 1, 5, 3, 6, 4].
     * 
     * If we plot the numbers of the given array on a graph, we get:
     * 
     * If we analyze the graph, we notice that the points of interest are the
     * consecutive valleys and peaks.
     * 
     * The key point is we need to consider every peak immediately following a
     * valley to maximize the profit. In case we skip one of the peaks (trying to
     * obtain more profit), we will end up losing the profit over one of the
     * transactions leading to an overall lesser profit.
     */

    public int maxProfit(int[] prices) {

        if (prices.length < 2) {
            return 0;
        }

        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int ans = 0;

        while (i < prices.length - 1) {

            // First find a valley
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            valley = prices[i];

            // First find a peek
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            peak = prices[i];

            ans = ans + (peak - valley);
        }

        return ans;
    }


    // Or , if there is an increase then buy sell


    public int maxProfit_(int[] prices) {
        int total = 0;
        for (int i=0; i< prices.length-1; i++) {
            if (prices[i+1]>prices[i]) total += prices[i+1]-prices[i];
        }
        
        return total;
    }
}