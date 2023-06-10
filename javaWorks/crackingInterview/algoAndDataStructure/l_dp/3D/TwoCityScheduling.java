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
        int[][][] dp = new int[size+1][n+1][n+1];
        return solve(0, costs, n, 0,0,0,0);
    }

    private int solve(int i, int[][] costs, int n, int aCount, int aValue, int bCount, int bValue){
        if(aCount == n && bCount == n){
            return aValue + bValue;
        }

        if(aCount == n){
            return solve(i+1, costs, n, aCount, aValue, bCount+1, bValue+costs[i][1]);
        }else if(bCount == n){
            return solve(i+1, costs, n, aCount+1, aValue+costs[i][0], bCount, bValue);
        }else{
            return Math.min(solve(i+1, costs, n, aCount+1, aValue+costs[i][0], bCount, bValue),
                        solve(i+1, costs, n, aCount, aValue, bCount+1, bValue+costs[i][1]));
        }
    }
  
  
  /**
      Now lets convert the above recusrion into a 3D DP
      
  **/


}
