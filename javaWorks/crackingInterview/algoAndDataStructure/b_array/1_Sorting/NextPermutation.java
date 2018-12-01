public class NextPermutation {
    /**
     * Implement next permutation, which rearranges numbers into the
     * lexicographically next greater permutation of numbers.
     * 
     * If such arrangement is not possible, it must rearrange it as the lowest
     * possible order (ie, sorted in ascending order).
     * 
     * The replacement must be in-place and use only constant extra memory.
     * 
     * Here are some examples. Inputs are in the left-hand column and its
     * corresponding outputs are in the right-hand column.
     * 
     *  1,2,3 → 1,3,2
     *  3,2,1 → 1,2,3
     *  1,1,5 → 1,5,1
     */


    public void nextPermutation(int[] nums) {
        // First find where/which index you are gona make the change
            // For this, start from end and see in which index the previous index is lesser than the current
            // This way, we are deciding which is the right place to do the change to get the next greater
            // How ? because when seeing the last, that index is a place where the produce the next least
        // Find an element which is just greater than the element in i and swap
            // This way, we are placing the next significant digit in place
        // sort the rest of the array, actually reverse the oder is just fine
            // This way, we are makig sure, the rest of the array is in decreasing order, so we get the next element
        
        // First step
        int i = nums.length-2;
        while(i >= 0 && nums[i] >= nums[i+1]){
            i--;
        }
        
        // new step 2
        if(i >= 0){
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }
    
     private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}