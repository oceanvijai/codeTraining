public class LongestIncreasingSubsequence2{


	/**

		Given an integer array nums, return true if there exists a triple of indices (i, j, k) 
		such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.

	**/

	/**

		Approach 1: Simple, have two auxilary array and fill one with min from left and other one with max from right
		Then at every point check the valid condition

	**/

	public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        int[] leftMin = new int[n];
        int[] rightMax = new int[n];
        leftMin[0] = nums[0];
        rightMax[n-1] = nums[n-1];
        for(int i = 1 ; i < n; i++){
            leftMin[i] = Math.min(nums[i],leftMin[i-1]);
            int j = n-i-1;
            rightMax[j] = Math.max(nums[j],rightMax[j+1]);
        }
        
        for(int i = 0 ; i < n; i++){
            if(leftMin[i] < nums[i] && nums[i] < rightMax[i]){
                return true;
            }
        }
        
        return false;
    }


    /**
		Approach 2: Same as the nlogn solution for LongestIncreasingSubsequence1

		Ex: input = [2,0,3,-1,4]

		var first = MAX, second = MAX
		If there is a number greater than these two, then return true.

		But, its a bit tricky, since we need to decide which sequence we want to maintain.
		Lets, take the example

		nums = [2,0,3,-1,4]

		i = 0, first = 2, second = MAX
		i = 1, first = 0, second = MAX
		i = 2, first = 0, second = 3 	// now if we see aything greate than second, return true
		i = 3, first =-1, second = 3	// even though -1 index (3) is greate than 3's index (2), we replace it with 0
										// reason is, if we want lets say, [0,3] didnt produce the third result, we also conside the -1 series
										   like lets say if someother number comes after this like [...,-1,4,2,7]
										   then, second will be replaced with 2 and the thrid one can be 7, so [-1,2,7]
										// Also, even if [0,3] series produce the third one, it will not get effected, since anyways 
										   the first two numbers are counted as follows
		i = 4, first =-1, second = 3	Now there is a third number, so return true for [0,3,4], not that I say "0" not "-1" even though 
										first current value is "-1".

    **/


	public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= first) { // this condition is not as simple as it looks
                first = n;
            } else if (n <= second) {
                second = n;
            } else {
                return true;
            }
            
            //System.out.println("f:"+first+ " s:"+second);
        }
    return false;
    }

}