public class BuySellKTrans {
    

     /**
     * Important point here is, we are finding k pairs of numbers in a array with max difference
     */

     /**
      * We have a O(K * n ^ 2) solution and O(Kn) solution as well
      */

      public int maxProfit(int k, int[] prices) {
            int[][] dp = new int[k+1][prices.length+1];
            
            for(int t = 1; t <= k; t++){
                for(int d = 1; d < prices.length; d++){
                    int max_so_far = 0; 
                    for(int m = 0; m < d; m++){
                        max_so_far = Math.max(max_so_far,(prices[d]-prices[m]+dp[t-1][m])); 
                    }
                    
                    dp[t][d] = Math.max(dp[t][d - 1], max_so_far);
                    // System.out.println(dp[t][d]);
                }
            }
            
            return dp[k][prices.length-1]; 
            
        }


        /**
         * To optimize this we need to reduce the thired the nested loop
         * 
         * We can do this, by remembering the best we have seen so far as we progress
         * 
         * Also, check if the k is more, so we can do any number of trans
         */

        public int maxProfit_optimized(int k, int[] prices) {
            int len = prices.length;
            if (k >= len / 2) return quickSolve(prices);
            
            int[][] t = new int[k + 1][len];
            for (int i = 1; i <= k; i++) {
                int tmpMax =  -prices[0];
                for (int j = 1; j < len; j++) {
                    t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);
                    tmpMax =  Math.max(tmpMax, t[i - 1][j - 1] - prices[j]);
                }
            }
            return t[k][len - 1];   
        }
        
        private int quickSolve(int[] prices) {
            int len = prices.length, profit = 0;
            for (int i = 1; i < len; i++)
                // as long as there is a price gap, we gain a profit.
                if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
            return profit;
        }
      
}