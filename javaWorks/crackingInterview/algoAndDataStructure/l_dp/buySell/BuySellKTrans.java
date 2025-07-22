public class BuySellKTrans {

     /**
      * We have a O(Kn) solution
      *  odd are buy transactions, and even are sell transactions
      * https://www.youtube.com/watch?v=IV1dHbk5CDc&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=41
      */

        

      public int maxProfit(int k, int[] prices) {
        int[][] dp = new int[prices.length][(2*k)+1];
        for(int[] arr : dp){
            Arrays.fill(arr, -1);
        }
        return solve(0, prices, 1, k, dp);
    }

    private int solve(int index, int[] prices, int transCount, int k, int[][] dp){

        if(index == prices.length){
            return 0;
        }

        if(transCount > 2*k){
            return 0;
        }

        if(dp[index][transCount] != -1){
            return dp[index][transCount];
        }

        
        int ans = Integer.MAX_VALUE;
        if(transCount % 2 == 1){ // Buy available
            ans = Math.max( -prices[index]+solve(index+1, prices, transCount+1, k, dp), // buy
                            solve(index+1, prices, transCount, k, dp)); // dont buy
        }else{ // Sell available
            ans = Math.max( prices[index]+ solve(index+1, prices, transCount+1, k, dp), // sell
                            solve(index+1, prices, transCount, k, dp)); // dont sell
        }

        dp[index][transCount] = ans;
        return dp[index][transCount];
    }


        /**
         * Bottom up
         */

        public int maxProfit(int k, int[] prices) {
            int[][] dp = new int[prices.length+1][(2*k)+1];
    
            for(int i = prices.length-1; i >= 0; i--){
                for(int h = 2*k-1; h >= 0; h--){
                    if(h % 2 == 0){ // Buy available
                        dp[i][h] = Math.max( -prices[i]+dp[i+1][h+1], // buy
                                        dp[i+1][h]); // dont buy
                    }else{ // Sell available
                        dp[i][h] = Math.max( prices[i]+dp[i+1][h+1], // sell
                                            dp[i+1][h]); // dont sell
                    }
                }
            }
    
            return dp[0][0];
        }
      
}
