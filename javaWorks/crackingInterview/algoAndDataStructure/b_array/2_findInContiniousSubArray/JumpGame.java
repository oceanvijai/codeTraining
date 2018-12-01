public class JumpGame {

    /**
     * Given an array of non-negative integers, you are initially positioned at the
     * first index of the array.
     * 
     * Each element in the array represents your maximum jump length at that
     * position.
     * 
     * Determine if you are able to reach the last index.
     */

    /**
     * Approach:
     * 
     * 1.   get the first the value and see how far it can reach in the array
     * 2.   travel till the farest point found so far
     * 3.   On the way see if some other value is reaching further than the current reach
     * 4.   Also if it does reach the end, if so return true
     * 5.   When we arrive at the current reach, see if we have found something further
     * 6.   If so, reset the reach and repeate step 1
     */

    public boolean canJump(int[] nums) {
        if (nums.length == 1 && nums[0] == 0) {
            return true;
        }

        int farthestReach = 0;
        int reach = 0;

        for (int i = 0; i < nums.length - 1; i++) {

            farthestReach = Math.max(i + nums[i], farthestReach); // how far can we go

            if (reach == i && farthestReach == i) { // we meet zero and so no futher to go
                return false;
            }
            if (reach == i) { // reset to how far we can go
                reach = farthestReach;
            }
            if (reach >= nums.length - 1 || farthestReach >= nums.length - 1) {
                return true;
            }
        }

        return farthestReach >= nums.length - 1;
    }
}