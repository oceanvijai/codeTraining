class SlidingWindowMaximum {


	/**
		You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

		Return the max sliding window.


		Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
		Output: [3,3,5,5,6,7]
		Explanation: 
		Window position                Max
		---------------               -----
		[1  3  -1] -3  5  3  6  7       3
		 1 [3  -1  -3] 5  3  6  7       3
		 1  3 [-1  -3  5] 3  6  7       5
		 1  3  -1 [-3  5  3] 6  7       5
		 1  3  -1  -3 [5  3  6] 7       6
		 1  3  -1  -3  5 [3  6  7]      7



	**/


	/**
		Approach 1: brute force O(nk)

		Approach 2: Use a linked list and do the following

		1.	Add elements to the front of the queue
		2.	On each iteration remove the elemets which belonged to the previous window
		3.	On each iteration, make sure the Max is at the end of queue
			And then scond max, which CAME AFTER the curent max is the next element
			i,e the queue should be in decending order always 
		4.	Get the max from of the window from the last element


		Time: O(N), since we add and remove each element in the queue only once


	**/



    public int[] maxSlidingWindow(int[] nums, int k) {
        LinkedList<Integer> indexQueue = new LinkedList<>();
        int n = nums.length;
        int[] ans = new int[n-k+1];
        
        for(int i = 0; i < n; i++){
            // Set the window
            if(!indexQueue.isEmpty() && indexQueue.peekLast() < i-k+1){
                indexQueue.pollLast();
            }
            
            // trim the queue
            while(!indexQueue.isEmpty() && nums[indexQueue.peekLast()] < nums[i]){
                indexQueue.pollLast();
            }
            
            // Add the current element to the queue
            indexQueue.addFirst(i);
            
            // get the max number in this window
            int max = nums[indexQueue.peekLast()];
            
            // Add it to the ans , if we achived window size
            if(i-(k-1) >= 0){
                ans[i-(k-1)] = max;
            }
        }
        
        return ans;
    }



    /**

		Approch 2: DP

    **/


		
}
