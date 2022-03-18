public class FirstMissingPositive{
	/**

	Given an unsorted integer array, find the smallest missing positive integer.

		Example 1:

		Input: [1,2,0]
		Output: 3
		Example 2:

		Input: [3,4,-1,1]
		Output: 2
		Example 3:

		Input: [7,8,9,11,12]
		Output: 1
		Follow up:

		Your algorithm should run in O(n) time and uses constant extra space.

	**/

	/**
	The nums we are looking should be in range 1 to nums.length

	Approach: mark any -ve or numbers greater than nums.length as 1
	Also check if 1 ia already there, why we choose 1, because if its not there, just return as it is the smallest positive number
	**/


	public int firstMissingPositive(int[] nums) {
        boolean containsOne = false;
        for(int i =0; i < nums.length; i++){
            if(nums[i] == 1){
                containsOne = true;
            }
            if(nums[i] <= 0 || nums[i] > nums.length){
                nums[i] = 1;
            }
        }
        
        if(!containsOne){
            return 1;
        }
        
        for(int i =0; i < nums.length; i++){
            int val = Math.abs(nums[i]);
            nums[val-1] = -Math.abs(nums[val-1]);
        }
        
        for(int i =0; i < nums.length; i++){
            if(nums[i] >= 0){
                return i+1;
            }
        }
        
        return nums.length+1;
    }
}