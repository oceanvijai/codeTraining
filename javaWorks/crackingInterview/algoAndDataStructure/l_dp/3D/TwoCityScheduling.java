public class TwoCityScheduling {

  /**
      A company is planning to interview 2n people. Given the array costs where costs[i] = [aCosti, bCosti], 
      the cost of flying the ith person to city a is aCosti, and the cost of flying the ith person to city b is bCosti.

      Return the minimum cost to fly every person to a city such that exactly n people arrive in each city.
      
      
      Example 1:

      Input: costs = [[10,20],[30,200],[400,50],[30,20]]
      Output: 110
      Explanation: 
      The first person goes to city A for a cost of 10.
      The second person goes to city A for a cost of 30.
      The third person goes to city B for a cost of 50.
      The fourth person goes to city B for a cost of 20.

      The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
  **/
  
  /**
    This is actually solved via greedy with nLogn solution. You can check that out.
    https://leetcode.com/problems/two-city-scheduling/description/
    
    
    But we wanted to show how it can use 3D DP as well
  **/
  
  /**
      First recursion: Simple, where we take to decesions for each item in the cost array
      Time: O(2^n)
      Space: O(n) - for the stack
  **/
  
  
  public int twoCitySchedCost(int[][] costs) {
        int size = costs.length;
        int n = size/2;
        int ans =  solve(0, costs, n, 0, 0);
        return ans;
    }

    private int solve(int index, int[][] costs, int n, int aCount, int bCount){
        if(index == costs.length){
            return 0;
        }

        int aPath = Integer.MAX_VALUE;
        int bPath = Integer.MAX_VALUE;
        if(aCount < n){
            aPath = costs[index][0] + solve(index+1, costs, n, aCount+1, bCount);
        }
        if(bCount < n){
            bPath = costs[index][1] + solve(index+1, costs, n, aCount, bCount+1);
        }

        return Math.min(aPath, bPath);
    }
  
  
  /**
      Now lets convert the above recusrion into a 3D DP.
      Time: O(2n(n+n)) = O(4n^2) = O(n^2)
      Space: O(n^3)+n => DP + stackSize
  **/
  
  
  public int twoCitySchedCost(int[][] costs) {
        int size = costs.length;
        int n = size/2;
        int[][][] dp = new int[size+1][n+1][n+1];
        Arrays.stream(dp).forEach(TwoDArray -> Arrays.stream(TwoDArray).forEach(oneDArray -> Arrays.fill(oneDArray, -1)));
        int ans =  solve(0, costs, n, 0, 0, dp);
        return ans;
    }

    private int solve(int index, int[][] costs, int n, int aCount, int bCount, int[][][] dp){
        if(index == costs.length){
            return 0;
        }

        if(dp[index][aCount][bCount] != -1){
            return dp[index][aCount][bCount];
        }

        int aPath = Integer.MAX_VALUE;
        int bPath = Integer.MAX_VALUE;
        if(aCount < n){
            aPath = costs[index][0] + solve(index+1, costs, n, aCount+1, bCount, dp);
        }
        if(bCount < n){
            bPath = costs[index][1] + solve(index+1, costs, n, aCount, bCount+1, dp);
        }

        dp[index][aCount][bCount] = Math.min(aPath, bPath);
        return dp[index][aCount][bCount];
    }
  
  /**
      Now lets Optimise the above solution for space
      Space: O(n^3)+n => DP + stackSize
      We cant do anything about stacksize. But we can do something about DP.
      We if see the states of DB[i][acount][bcount], i is almost always acount+bcount.
      Secondly, lets say i=5, acount=2 and bcount=3 or i=5, acount=3 and bcount=2 or i=5, acount=1 and bcount=4 etc.
      The rest of solution will come from the reset of the imput array which is min.
      
      So lets use only a 2d DP
  **/
  
  public int twoCitySchedCost(int[][] costs) {
        int size = costs.length;
        int n = size/2;
        int[][] dp = new int[n+1][n+1];
        Arrays.stream(dp).forEach(oneDArray -> Arrays.fill(oneDArray, -1));
        int ans =  solve(0, costs, n, 0, 0, dp);
        return ans;
    }

    private int solve(int index, int[][] costs, int n, int aCount, int bCount, int[][] dp){
        if(index == costs.length){
            return 0;
        }

        if(dp[aCount][bCount] != -1){
            return dp[aCount][bCount];
        }

        int aPath = Integer.MAX_VALUE;
        int bPath = Integer.MAX_VALUE;
        if(aCount < n){
            aPath = costs[index][0] + solve(index+1, costs, n, aCount+1, bCount, dp);
        }
        if(bCount < n){
            bPath = costs[index][1] + solve(index+1, costs, n, aCount, bCount+1, dp);
        }

        dp[aCount][bCount] = Math.min(aPath, bPath);
        return dp[aCount][bCount];
    }
  
  
  /**
      Now lets try tabluar. Since tabular can remove the space for stack.
  
  **/


  public int twoCitySchedCost(int[][] costs) {
        int size = costs.length;
        int n = size/2;
        int[][][] dp = new int[size+1][n+1][n+1];

        // base cases

        // If we have zero items then zero cost
        // like dp[0][a][b] are all zeros
        for(int i=0; i<=size; i++){
            dp[i][0][0] = 0;
        }

        // When I can considering only 'a' to pick from for size n from that item index
        for(int i=1; i<=size; i++){
            for(int a=1; a <= n; a++){
                dp[i][a][0] = costs[i-1][0]+dp[i-1][a-1][0]; // This addition is fully not so clear
            }
        }

        // When I can considering only 'b' to pick from for size n from that item index
        for(int i=1; i<=size; i++){
            for(int b=1; b<=n; b++){
                dp[i][0][b] = costs[i-1][1]+dp[i-1][0][b-1]; 
            }
        }
        
        for(int i=1; i<=size; i++){
            for(int a=1; a<=n; a++){
                for(int b=1; b <= n; b++){
                    int aPath =  costs[i-1][0]+dp[i-1][a-1][b];
                    int bPath =  costs[i-1][1]+dp[i-1][a][b-1];
                    dp[i][a][b] = Math.min(aPath,bPath);
                }
            }
        }
         
        return dp[size][n][n];
    }
  
  


}
