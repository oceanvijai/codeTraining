public class ReduceXToZero {
	
	/**
		You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. Note that this modifies the array for future operations.

		Return the minimum number of operations to reduce x to exactly 0 if it's possible, otherwise, return -1


		1 <= nums.length <= 105
		1 <= nums[i] <= 104
		1 <= x <= 109

	**/

	/**

		Example 1:

		Input: nums = [1,1,4,2,3], x = 5
		Output: 2
		Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
		Example 2:

		Input: nums = [5,6,7,8,9], x = 4
		Output: -1
		Example 3:

		Input: nums = [3,2,20,1,1,3], x = 10
		Output: 5
		Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.

	**/

	/**
		Note that, we cannot use TWO POINTERS, since the array is not sorted.
		So we got 3 approaches,

		Approach 1: brute force O(2^n)
		Approach 2: Memorisation O(n^2) - optimization problem, so we can use DP or memo
		Approach 3: Continious sum variation O(n), space O(n).  Variation 1
		Approach 4: Continious sum variation O(n), space O(n).	Variation 2 
		Approach 5: Sliding window O(n), space O(1)

	**/


	/**

		Approach 1: Time exceded
	**/

	public int minOperations(int[] nums, int x) {
        int ans = solve(nums,x,0,nums.length-1);
        return  ans == Integer.MAX_VALUE ? -1 : ans;
    }
    
    private int solve(int[] nums, int x, int start, int end){
        if(x == 0){
            return (start + (nums.length - end - 1));
        }
        
        if(x < 0 || start > nums.length-1 || end < 0){
            return Integer.MAX_VALUE;
        }
        
        return Math.min(solve(nums,x-nums[start],start+1,end),solve(nums,x-nums[end],start,end-1));
    }

    /**

		Approach 2 : time exceded O(n^2)
	**/

	public int minOperations(int[] nums, int x) {
        Map<String,Integer> map = new HashMap<>();
        int ans = solve(nums,x,0,nums.length-1, map);
        return  ans == Integer.MAX_VALUE ? -1 : ans;
    }
    
    private int solve(int[] nums, int x, int start, int end, Map<String,Integer> map){
        if(x == 0){
            return (start + (nums.length - end - 1));
        }
        
        String key = start+"*"+end;
        if(map.containsKey(key)){
            return map.get(key);
        }
        
        if(x < 0 || start > nums.length-1 || end < 0 || start > end){
            return Integer.MAX_VALUE;
        }
        
        int ops =  Math.min(solve(nums,x-nums[start],start+1,end,map),solve(nums,x-nums[end],start,end-1,map));
        map.put(key, ops);
        
        return ops;
    }

    /**

    	Approach 3: Continious Sum (Just for learing purpose)

		1. We iterate the array from start and save the index of the running sum at every step (prefixSum). 
		2. Then we iterate from reverse (This time dont update the map) and see if the (runningSum - x) is seen
		If seen, then it means, the reverseSum (sufflix) + somewhere in the prefixSum we get the items that add up to x.


    **/

	public int minOperations(int[] nums, int x) {
        int sum = 0, n = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0, -1);
        int i = 0;
        for(; i < n && sum <= x; i++){ // create a prefix sum till x
            sum += nums[i];
            map.put(sum, i);
        }
        
        // If we can get x, then return -1
        if(sum < x){
            return -1;
        }
        
        int revereSum = 0;
        int ans = Integer.MAX_VALUE;
        // First check if the entire sum is equal to x.
        for(int j = n-1; j >= 0 && revereSum <= x; j--){
            if(map.containsKey(x - revereSum)){ // With the current sum, see if the rest is present in the prefix
                int start = map.get(x - revereSum) + 1;
                ans = Math.min(ans, start+(n-j-1));
            }
            revereSum += nums[j];
        }
        return ans == Integer.MAX_VALUE ? -1: ans;
    }





    /**

    	Approach 4: Continious Sum

    	Instead of removing the prefix and suffix, we remove x from total sum. the remaining value should we produced by the elements 
    	in the middle/all.


    	so totalSum - x = target

    	Note that, all numbers will be positive, so the same target will not come twice.

    	Find if the subarray can form the target. 
    	But we have a problem. When we find the subarray with the target, we need to know where it starts and where it ends.


    	When we find the window size of the target, subract from n.

    **/


    	public int minOperations(int[] nums, int x) {
	        int totalSum = 0, n = nums.length;
	        for(int num : nums){
	            totalSum += num;
	        }
	        
	        int target = totalSum - x;
	        Map<Integer,Integer> map = new HashMap<>();
	        map.put(0, -1); // since we are doing the reverse, where we find the max window
	        int windowSize = -1;
	        int runningSum = 0;
	        for(int i = 0; i < n; i++){
	            runningSum += nums[i];
	            map.put(runningSum, i);
	            if(map.containsKey(runningSum-target)){
	                int size = i-map.get(runningSum-target);
	                windowSize = Math.max(windowSize, size);
	            }
	        }
	        
	        int ans = windowSize == -1 ? -1 : n - windowSize;
	        return ans;
    	}


    	/**

			Sliding window.

			Get the target for a subarray, which can be slided


    	**/


    	public int minOperations(int[] nums, int x) {
	        // Get the sub array target sum
	        int target = Arrays.stream(nums).sum() - x;
	        int n = nums.length, left = 0, right = 0;
	        
	        int windowSum = 0, windowSize = -1;
	        while(right < n){
	            windowSum += nums[right];
	            
	            while(windowSum > target && left <= right)
	            {
	                windowSum -= nums[left];
	                left++;
	            }
	            
	            if(windowSum == target){
	                windowSize = Math.max(windowSize, right - left + 1);
	            }
	            
	            right++;
	        }
	        
	        return windowSize == -1 ? -1 : n - windowSize;
	    }


}