public class SearchInRotatedArray2 {
    /**
     * Suppose an array sorted in ascending order is rotated at some pivot unknown
     * to you beforehand.
     * 
     * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
     * 
     * You are given a target value to search. If found in the array return true,
     * otherwise return false.
     * 
     * Input: nums = [2,5,6,0,0,1,2], target = 0 Output: true
     */


    public boolean search(int[] nums, int target) {
        int left = 0; 
        int right = nums.length-1;
        
        while(left < right){
            int mid = left + (right-left) / 2;
            if(nums[mid] == target){
                return true;
            }else if(nums[mid] > nums[left]){
                // we are in the right of the pivot and left side will be sorted
                // so, first search right and then left
                if(target < nums[mid] && target >= nums[left]){
                    right = mid;
                }else{
                    left = mid+1;
                }
                
            }else if(nums[mid] < nums[left]){
                // we are in the left of the pivot and right side will be sorted
                // so, first search in the right if not left
                if(target > nums[mid] && target <= nums[right]){
                    left = mid+1;
                }else{
                    right = mid;
                }

            }else{
                // now mid and start are same, so lets remove start form the search space
                // As we search it will take care when there are ducpicates in the middle
                left++;
            }
        }
        
        if(left >= 0 && left < nums.length && nums[left] == target){
            return true;
        }
                     
        return false;
    }




    

    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;

        while (left <= right) {
            mid = (left + right) >> 1;
            if (nums[mid] == target)
                return true;

            // the only difference from the first one, trickly case, just updat left and
            // right
            if ((nums[left] == nums[mid]) && (nums[right] == nums[mid])) {
                ++left;
                --right;
            }

            else if (nums[left] <= nums[mid]) {
                if ((nums[left] <= target) && (nums[mid] > target))
                    right = mid - 1;
                else
                    left = mid + 1;
            } else {
                if ((nums[mid] < target) && (nums[right] >= target))
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return false;

    }
}