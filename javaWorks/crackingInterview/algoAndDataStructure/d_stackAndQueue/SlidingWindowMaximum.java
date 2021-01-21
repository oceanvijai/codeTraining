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

		This is not better than the above approach. Time and space remains the same. But just an alterante approach.
		Not very intutive, so try to stick to the first approach.

		So, lets say we have only one window instead of many.
		Ex: [7,6,5,4] and k=4
		Then to find the max, we simple wipe from LEFT to RIGHT and fid the max.

		Now, lets say we have two/may widows.
		So the problem now we face is, that we need to retain the effect of a number witin its widow range.
		Ex: [7,6,5,4,3,8,1,0] k=4, so we can keep of effect of 7[0] upto only 0+4 = 4 indices.


		For then we can do, something like this. If index % k == 0, then use the number in the index instead of propagating further.
		Ex: The DP for max from LEFT to RIGHT is as follows,
		[7,7,7,7,3,....], we kind of make BOUDARIES

		But this works only when my window current window fits correctly in the a window BOUDARY like |----|. So how will be take care of the window
		which are crossing the BOUDARIES.
		Whis is like  ---|- or -|--- or --|-- 

		So, we handle this by swiping from RIGHT to LEFT also with the same BOUNDARY condition.
		So, that way the max gets pushed either to the start or end of the window indices.
		Ex, it the above example, [7,6,5,4,3,8,1,0]
		Cosider window from 3 to 6 [....,5,4,3,8,....]
		The LEFT to RIGHT DP will be like [..,7,3,8,8,...]
		The RIGHT to LEFT DP will be like [..,4,3,8,1,...]
		
		Now you can see the 7, which had an effect from index 0, stopped at index 3 in the LEFT_TO_RIGHT DP.
		After this, a new series starts with boundary as 3, since index 4 % 4 = 0.
		Then 8, gets pushed as max to till the window's right end.

		Now, we can see, for RIGHT_TO_LEFT DP, 8 gets picked up as max at index 5. 
		But again, due to boudary condition at index 4, got drooped and didnt make it till the window's left end.

		But, since it made atleast to one of the end, we know what is the max for that window.

    **/


	public int[] maxSlidingWindow(int[] nums, int w) {
        
        if(nums.length == 0){
            return new int[0];
        }
        
        int[] max_left = new int[nums.length];
	    int[] max_right = new int[nums.length];

	    max_left[0] = nums[0];
	    max_right[nums.length - 1] = nums[nums.length - 1];

	    for (int i = 1; i < nums.length; i++) {
	        max_left[i] = (i % w == 0) ? nums[i] : Math.max(max_left[i - 1], nums[i]);

	        int j = nums.length - i - 1;
	        max_right[j] = (j % w == 0) ? nums[j] : Math.max(max_right[j + 1], nums[j]);
	    }

	    int[] sliding_max = new int[nums.length - w + 1];
	    for (int i = 0, j = 0; i + w <= nums.length; i++) {
	        sliding_max[j++] = Math.max(max_right[i], max_left[i + w - 1]);
	    }

	    return sliding_max;
    }

}
