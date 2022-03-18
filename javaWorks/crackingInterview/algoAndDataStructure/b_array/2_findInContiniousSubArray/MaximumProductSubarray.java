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



    public int maxProduct(int[] nums) {
       int pos = 1, neg = 1, product = Integer.MIN_VALUE;
        
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                // Reset
                pos = 1;
                neg = 1;
                product = Math.max(product, 0);
                continue;
            } else if(nums[i] > 0){
                // Continue extending the sub array
                pos = Math.max(nums[i],pos*nums[i]);
                neg = neg*nums[i];
            }else{
                int temp = neg;
                if(pos < 0){
                    // We need to start a new sub array for neg
                    neg = nums[i];
                }else{
                    // extend the existing sub array for neg
                    neg = pos * nums[i];
                }
                
                // Produce a new pos by multiplying with neg
                pos = Math.max(nums[i],temp*nums[i]);
            }
            
            product = Math.max(product, pos);
        }
        
        return product;
    }
}