public class LastStoneWeight2{
	/**
		You are given an array of integers stones where stones[i] is the weight of the ith stone.

		We are playing a game with the stones. On each turn, we choose any two stones and smash them together. Suppose the stones have weights x and y with x <= y. The result of this smash is:

		    If x == y, both stones are destroyed, and
		    If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.

		At the end of the game, there is at most one stone left.

		Return the smallest possible weight of the left stone. If there are no stones left, return 0.


		Example 1:

		Input: stones = [2,7,4,1,8,1]
		Output: 1
		Explanation:
		We can combine 2 and 4 to get 2, so the array converts to [2,7,1,8,1] then,
		we can combine 7 and 8 to get 1, so the array converts to [2,1,1,1] then,
		we can combine 2 and 1 to get 1, so the array converts to [1,1,1] then,
		we can combine 1 and 1 to get 0, so the array converts to [1], then that's the optimal value.

		Example 2:

		Input: stones = [31,26,33,21,40]
		Output: 5

		Example 3:

		Input: stones = [1,2]
		Output: 1
	**/


	/**
		The intution behind this is not straight forward
		Let say we sort all the stones and cancel the next smallest one, it will not provide a valid answer.
		
		We dont have to cancel and see. We just need to find the following.
		
		So we want to cancel the value of one stone with other and make it min
		So we can add all the values and divide it by 2
		Why is that, lets say one subset has only one number which can cancel all the other elements in the other subset.
		So if we get a subset which is half or close to half of the total, we can try and find the min we can achieve
		
		If we can achieve the target values with the available stones, then we are good
		Else find the closest achievable value and see how much is missingf from the total.

	**/

	public int lastStoneWeightII(int[] stones) {
        //get the total sum and divide by 2 as target sum
        // This will give us the sum we need to achieve 
        // using the given numbers
        // If not then find the best we can achieve and subract from the target
        
        int totalSum = 0;
        for(int stone : stones) totalSum += stone;
        int targetSum = totalSum/2;
        
        // Now we do DP and try and find how close we can achieve to the target
        boolean dp[][] = new boolean[stones.length+1][targetSum+1];
        for(int i = 0; i <= stones.length; i++) dp[i][0] = true;
        
        int bestSum = 0;
        for(int i = 1; i <= stones.length; i++){
            int stone = stones[i-1];
            for(int j = 1; j <=targetSum; j++){
                if(stone > j){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-stone];
                }
                
                
                if(dp[i][j] && j > bestSum){
                    bestSum = j;
                }
            }
        }
        
        // totalSum = bestSum + b --> b = totalSum - bestSum
        int b = totalSum - bestSum;
        int ans = Math.abs(bestSum - b);
        return ans;
    }

}
