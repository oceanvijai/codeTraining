public class PairSongs{
	/**

	You are given a list of songs where the ith song has a duration of time[i] seconds.

	Return the number of pairs of songs for which their total duration in seconds is divisible by 60. Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.


	Example 1:

	Input: time = [30,20,150,100,40]
	Output: 3
	Explanation: Three pairs have a total duration divisible by 60:
	(time[0] = 30, time[2] = 150): total duration 180
	(time[1] = 20, time[3] = 100): total duration 120
	(time[1] = 20, time[4] = 40): total duration 60
	Example 2:

	Input: time = [60,60,60]
	Output: 3
	Explanation: All three pairs have a total duration of 120, which is divisible by 60.

	**/


	/**

	Approach: We can create a dp[60] and store ONLY the reminders of 60 with all the numbers, and count them
	Then go through DP and find how may pairs we can find

	**/


	public int numPairsDivisibleBy60(int[] time) {
        int[] dp = new int[60];
        for(int i : time){
            dp[i % 60] = dp[i % 60] + 1;
        }
        
        int ans = 0;
        // For n%60 == 0
        ans = ans + ((dp[0]*(dp[0]-1))/2);
        // For 30
        ans = ans + ((dp[30]*(dp[30]-1))/2);
        
        for(int i = 1; i < 30; i++){
            ans = ans + (dp[i] * dp[60-i]);
        }
        return ans;
    }
}