public class JumpGame2 {
    /**
     * Given an array of non-negative integers, you are initially positioned at the
     * first index of the array.
     * 
     * Each element in the array represents your maximum jump length at that
     * position.
     * 
     * Your goal is to reach the last index in the minimum number of jumps.
     */

    /**
     * Input: [2,3,1,1,4] Output: 2 Explanation: The minimum number of jumps to
     * reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the
     * last index.
     */

    /**
     * Approach: Is same as the JumpGame1 one, except we make a count
     * 
     * 1. get the first the value and see how far it can reach in the array 
     * 
     * 2. travel till the farest point found so far 
     * 
     * 3. On the way see if some other value is reaching further than the current reach 
     * 
     * 4. Also if it does reach the end, if so return true 
     * 
     * 5. When we arrive at the current reach, see if we have found something further 
     * 
     * 6. If so, reset the reach and increment count and repeate step 1
     */

    public int jump(int[] nums) {
        int count = 0;
        int farthestReach = 0;
        int reach = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthestReach = Math.max(farthestReach, i + nums[i]);
            if (reach == i) {
                reach = farthestReach;
                count++;
            }

            if (reach >= nums.length - 1) {
                break;
            }
        }

        return count;
    }
}