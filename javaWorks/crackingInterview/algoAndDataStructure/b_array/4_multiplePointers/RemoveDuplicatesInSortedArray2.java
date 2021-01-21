public class RemoveDuplicatesInSortedArray2 {
	
	/**

		Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.

		Do not allocate extra space for another array; you must do this by modifying the input array in-place with O(1) extra memory.




		Example 1:

		Input: nums = [1,1,1,2,2,3]
		Output: 5, nums = [1,1,2,2,3]
		Explanation: Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively. 
		It doesn't matter what you leave beyond the returned length.
		Example 2:

		Input: nums = [0,0,1,1,1,1,2,3,3]
		Output: 7, nums = [0,0,1,1,2,3,3]
		Explanation: Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively. 
		It doesn't matter what values are set beyond the returned length.
	**/


	/**
		Approach 1: two pointers
		Have i(first pointer) retaining the current valid element and i(second pointer) to move forward if duplicate is found. 
		Also keep a cout on how many duplicats are found
		If duplicate is not found, 
			1.	if we have 2 or more than 2 duplicate, just retian one duplicate and coutinue with the noraml flow
			2.	just replace i with j
	**/



	public int removeDuplicates(int[] nums) {
        int i = 0, j = 0;
        int n = nums.length;
        
        int duplicateCount = 0;
        while(j < n){
            if(nums[i] != nums[j]){
                if(duplicateCount >= 2){
                    i++;
                    nums[i] = nums[i-1];
                }
                duplicateCount = 0;
                i++;
                nums[i] = nums[j];
                continue;
            }else{
                duplicateCount++;
                j++;
            }
        }
        
        if(duplicateCount >= 2){
            i++;
            nums[i] = nums[i-1];
        }
        
        
        return i+1;
    }



	/**
		Approach 2: bad ass

		just have one pointer retaining the next valid index and start putting them from the array
		increment the pointer only if the its not a duplicate
	**/


	public int removeDuplicates(int[] nums) {
	   int i = 0;
	   for (int n : nums)
	      if (i < 2 || n > nums[i - 2])
	         nums[i++] = n;
	   return i;
	}



	
}