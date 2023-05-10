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
      
      We can reverse it as well. For any index we need to know what is the index with the smallest index to the left.
      We can do this using a monotone stack.
      We start from zero index and add elements to the stack in such a way the stack contains indices which are in descreasing order. 
      This is allow us to check from the last index what is the smallest index to the left.
      
      In the second iteration, Lets say we get a number in the larger index and which is greater that the one in the top, we can consider it for the answer.
      If we can also try other elements in the stack with the same number. Reason being, 
      
      If we get a smaller number after that, that smaller number would be either in the stack or the larger number after it can produce a better ramp.
      input -> [6,0,8,2,1,5], stack -> [6,0]
      Now when i is 5 in the lower loop, we compare it with the first element in the stack and pop it out after computing the answer.
      Now when i moves to 4(value=1), it could not produced a better result that 5. So we are good. 
      
      If we get a larger number after that , that number will be  again can try poping out things from stack, but it will not affect the prvious computation.
      Ex, when i is 2(value=8) it can pop out 6, but still the previous answer was better. It make no difference to our previous answer.
  
  
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
