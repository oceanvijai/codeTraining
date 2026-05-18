public class BinarySearch{

  /**
      Upper bound

  **/

    public static int binarySearch(int searchNum, int[] nums) {
        int start = 0, end = nums.length - 1, ansIndex = 0;
      
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= searchNum) {
                start = mid+1;
                ansIndex = mid;
            }else {
                end = mid-1;
            }
        }

        return ansIndex;
    }


  /**
    Lower bound

  **/

    private static int binarySearch(int searchNum, int[] nums) {
        int start = 0, end = nums.length - 1, ansIndex = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < searchNum) {
                start = mid+1;
            }else {
                ansIndex = mid;
                end = mid-1;
            }
        }

        return ansIndex;
    }

  
}
