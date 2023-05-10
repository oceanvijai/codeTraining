public class MaximumWidthRamp{
  /**
      A ramp in an integer array nums is a pair (i, j) for which i < j and nums[i] <= nums[j]. The width of such a ramp is j - i.

      Given an integer array nums, return the maximum width of a ramp in nums. If there is no ramp in nums, return 0.

      Example 1:

      Input: nums = [6,0,8,2,1,5]
      Output: 4
      Explanation: The maximum width ramp is achieved at (i, j) = (1, 5): nums[1] = 0 and nums[5] = 5.
      Example 2:

      Input: nums = [9,8,1,0,1,9,4,0,4,1]
      Output: 7
      Explanation: The maximum width ramp is achieved at (i, j) = (2, 9): nums[2] = 1 and nums[9] = 1.
  
  **/
  
  
  /**
      Approach: For every index we need to know the farthest index which is equal or greater than the value in that current index.
      How to get that info ?
      
      We can start the other way 
  
  
  **/
  
  
  public int maxWidthRamp(int[] nums) {
        // create a momtone stack, with bigger elements in the bottom
        LinkedList<Integer> stack = new LinkedList<>();
        for(int i = 0; i < nums.length; i++){
            if(stack.isEmpty()){
                stack.addFirst(i);
            }else if(nums[i] < nums[stack.peekFirst()]){
                stack.addFirst(i);
            }
        }

        // Now use the stack for every elements in the array to see 
        // how big of a ramp we can create
        int ans = 0;
        for(int i = nums.length-1; i >= 0; i--){
            while(!stack.isEmpty() && nums[stack.peekFirst()] <= nums[i]){
                ans = Math.max(ans, i - stack.pollFirst());
            }
        }

        return ans;
    }


}
