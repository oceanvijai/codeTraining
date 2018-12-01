public class BuySellOneTrans {
    /**
     * If you were only permitted to complete at most one transaction (i.e., buy one
     * and sell one share of the stock), design an algorithm to find the maximum
     * profit.
     */

    // Approach 1: have two arrays onr for the min till that point
    // and other for the max after that point
    // The difference will give u the profit
    // time O(n) - 3 pass and space o(n)

    // Approach2: do it like we do kadane
    // have a minSoFar which will have a the min it has see so far
    // then caclulate the profit with the current price and the min found so far
    // update the ans if required


    /**
     * Important point here is, we are finding two numbers in a array with max difference
     */


    public int maxProfit(int[] prices) {

        int maxProfit = 0;
        int minSoFar = Integer.MAX_VALUE;
        int profit = 0;
        for (int p : prices) {
            minSoFar = Math.min(p, minSoFar);
            profit = p - minSoFar; // subract min found so far with the current price
            maxProfit = Math.max(maxProfit, profit);
        }

        return maxProfit;

    }
}