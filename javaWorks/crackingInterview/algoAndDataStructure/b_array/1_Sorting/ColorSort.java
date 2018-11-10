public class ColorSort {

    /**
     * Given an array with n objects colored red, white or blue, sort them in-place
     * so that objects of the same color are adjacent, with the colors in the order
     * red, white and blue.
     * 
     * Here, we will use the integers 0, 1, and 2 to represent the color red, white,
     * and blue respectively.
     * 
     * Note: You are not suppose to use the library's sort function for this
     * problem.
     * 
     * 
     * Input: [2,0,2,1,1,0]
     * 
     * Output: [0,0,1,1,2,2]
     */

    /**
     * Follow up:
     * 
     * A rather straight forward solution is a two-pass algorithm using counting
     * sort. First, iterate the array counting number of 0's, 1's, and 2's, then
     * overwrite array with total number of 0's, then 1's and followed by 2's.
     * 
     * Could you come up with a one-pass algorithm using only constant space?
     * 
     */

    /**
     * Approach: This is a kind of partitioning We can have two points left and
     * right We have a current pointer
     * 
     * When we iterate over the array,
     * 
     * if we see 0 place it to the left og left pointer
     * 
     * if we see 2 we place it to the right of right pointer
     * 
     * if we see 1, we leave it in the middle
     */

    public void sortColors(int[] nums) {

        int l = 0; // everthing left of l is 0
        int i = 0;
        int r = nums.length - 1; // everythig right of r is 2

        if (nums.length < 2) {
            return;
        }

        while (i <= r) { // do it till we meet r where everthing right of r is 2's
            if (nums[i] == 0) {
                swap(nums, i, l); // 0's are to the left of l
                l++;
                i++;
            } else if (nums[i] == 1) {
                i++; // 1's are maintained in the middle
            } else {
                swap(nums, i, r); // 2's are to the right of r
                r--;
            }
        }
    }

    private void swap(int[] nums, int x, int y) {
        int t = nums[x];
        nums[x] = nums[y];
        nums[y] = t;
    }
}