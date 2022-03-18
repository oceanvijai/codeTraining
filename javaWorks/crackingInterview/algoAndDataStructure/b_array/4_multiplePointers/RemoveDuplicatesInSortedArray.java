public class RemoveDuplicatesInSortedArray {
	
	/**

		Given a sorted array nums, remove the duplicates in-place such that each element appears only once and returns the new length.

		Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.




		Example 1:

		Input: nums = [1,1,2]
		Output: 2, nums = [1,2]
		Explanation: Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the returned length.
		Example 2:

		Input: nums = [0,0,1,1,1,2,2,3,3,4]
		Output: 5, nums = [0,1,2,3,4]
		Explanation: Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively. It doesn't matter what values are set beyond the returned length.


	**/


	/**
		Approach 1: two pointers
		Have j(first pointer) retaining the current valid element and i (second pointer) to move forward if duplicate is found
		If duplicate is not found, just replace j with i
	**/



	public int removeDuplicates(int[] nums) {
        
        int i = 0;
        int j = 0;
        
        while(i < nums.length){
            if(nums[j] == nums[i]){
                i++;
            }else{
                j++;
                nums[j] = nums[i];
                i++;
            }
        }
        
        return j+1;
    }



	/**
		Approach 2: bad ass

		just have one pointer retaining the next valid index and start putting them from the array
		increment the pointer only if the its not a duplicate
	**/


	public int removeDuplicates(int[] nums) {
	    int i = 0;
	    for(int n : nums)
	        if(i < 1 || n > nums[i - 1]) 
	            nums[i++] = n;
	    return i;
	}



	
}