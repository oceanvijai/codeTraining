public class MaximumProductSubarray {
    /**
     * Given an integer array nums, find the contiguous subarray within an array
     * (containing at least one number) which has the largest product.
     */

    public int maxProduct(int[] nums) {
        int ans = nums[0];
        // We need two, to take care of -ve values
        int min = ans;
        int max = ans;

        for (int i = 1; i < nums.length; i++) {
            int val = nums[i];

            if (val < 0) { // So swap min max so, we get a max duing multiplication
                int t = min;
                min = max;
                max = t;
            }

            max = Math.max(val, (max * val));
            min = Math.min(val, (min * val));

            ans = Math.max(ans, max);

        }

        return ans;

    }
}