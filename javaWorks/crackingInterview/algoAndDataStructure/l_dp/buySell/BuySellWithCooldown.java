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
     * Approach: When we have 1D dp for buy and sell states, we might find that we need only few values which are
     * bestProfitWithCoolDown - best profit seen so far which does not include the cooldown day trans value
     * sellDay - The sell value of a day before (on the cool down day)
     * buyDay - The buy value of a day before (on the cool down day)
     * 
     * On the current day/iteration if we decide to buy, we need to take a best profile so far and
     * buy from that profit (bestProfitWithCoolDown-prices[i])
     * If we decide to sell that day, we need not worry about cooldown. So take the best buy we did so far and add the current price
     * Then we setup the next day accordingly
     */
    
    public int maxProfit(int[] prices) {
        if(prices.length <= 1){
            return 0;
        }
        
        // best profit seen so far which does not include the cooldown day trans value
        int bestProfitWithCoolDown=0; 
        
        // Sell value on the previous day
        int sellDay=0;
        
        // buy value on the previous day
        int buyDay=-prices[0];

        for(int i=1; i < prices.length; i++){
            // current day's transaction
            int currentDayBuy = bestProfitWithCoolDown-prices[i];
            int currentDaySell = buyDay + prices[i];
            
            // setup the next day
            // If today's buy is better or last buy was a better one
            buyDay =  Math.max(buyDay, currentDayBuy); 

            // Current cooldown was better or the previous day sell was better
            // Meaning either the profit before cooldown or the previous sell value is 
            // used as the bestProfitWithCoolDown for the next iteration
            bestProfitWithCoolDown = Math.max(bestProfitWithCoolDown, sellDay); 

            // currentDaySell becomes the sellDay for the next iteration
            sellDay = currentDaySell; 
        }
        
        return Math.max(bestProfitWithCoolDown,sellDay);
    }

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
}
