public class SearchInRotatedArray {

    // In a sorted and rotated array
    // You are given a target value to search. If found in the array, return its
    // index

    /**
     * Here we have 3 options for the mid
     * 1.   Mid is equal to the target, then return its index
     * 2.   Mid is lesser than or equal to end
     *          Meaning, the mid is now in pivot or its right
     *          So, we have to decide left or right 
     *          If target is greater than mid then we can go left 
     *          else go right, 
     * 3.   Mid is greater than start
     *          Meaning we have landed on the left of the  pivot
     *          So, we have to go right or left 
     *          If the target is less than mid, go left
     *          else go right
     */

    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length -1;
        
        while (start <= end){
            int mid = (start + end )/ 2;
            
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] < nums[start]){ // we are in piovt or its right
                if(target > nums[mid] && target <=nums[end]){
                    start = mid+1;
                }else{
                    end = mid -1;
                }
            }else{
                if(target < nums[mid] && target >= nums[start]){
                    end = mid -1;
                }else{
                    start = mid+1;
                }
            }
        }
        
        if(start >= 0 && start < nums.length && nums[start] == target){
            return start;
        }
        
        return -1;
    }
}