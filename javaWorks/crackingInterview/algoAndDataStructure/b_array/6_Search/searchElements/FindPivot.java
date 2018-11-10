public class FindPivot {


    /**
     * the logic behind this is lets say an array which was origianlly as follows
     * 
     * 1, 2, 3, 4, 5, 6, 7, 8, 9
     * 
     * is rotated as 
     * 
     * 7, 8, 9, 1, 2, 3, 4, 5, 6
     * 
     * Now we need to find the min (pivot) around where the array was rotated
     * 
     * So, here pivot has a property where its left > pivot and right < pivot.
     * Only pivot has this property
     * 
     * So when we calculate mid, we end up in any one of these three places
     * 
     * 1. pivot - where pivot - 1 > mid and pivot +1 < mid
     * 2. We end up in before pivot, where mid > start, so we can go right
     * 3. We end up left of pivot, where mid < end, so we go left and search
     */

    public int findPvt(final List<Integer> a) {

        if (a == null || a.size() == 0) {
            return -1;
        }

        if (a.get(0) <= a.get(a.size() - 1)) {
            return a.get(0);
        }

        int start = 0, end = a.size() - 1;

        while (start <= end) {
            if (a.get(start) <= a.get(end))
                return a.get(start);

            int mid = (start + end) / 2;

            int next = (mid + 1) % a.size(); // To make sure we take care of overflow and rotate back
            int prev = ((mid - 1) + a.size()) % a.size();

            if (a.get(mid) < a.get(next) && a.get(mid) < a.get(prev)) {
                return a.get(mid);
            } else if (a.get(mid) >= a.get(start)) {
                start = mid + 1;

            } else {
                end = mid - 1;
            }
        }

        return -1;
    }



    public int findMin(int[] nums) {

        // If duplicates , then uncomment the below 
        /* while(i != j && nums[i] == nums[j]){
            i++;
        } */

        if(nums==null || nums.length==0)
            return -1;
     
        int i=0; 
        int j=nums.length-1;
     
        while(i<=j){
            if(nums[i]<=nums[j])
                return nums[i];
     
            int m=(i+j)/2;
     
            if(nums[m]>=nums[i]){
                i=m+1;
            }else{
                j=m;
            }
        }
     
        return -1;
    }

}