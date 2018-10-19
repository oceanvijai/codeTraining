public class SubSort {

     /**
     * Given an array of integers, write a method to find indices m and n such that
     * if you sorted elements m through n, the entire array would be sorted.
     * Minimize n - m (that is, find the smallest such sequence).
     */

    /**
     * EXAMPLE Input: 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19 
     * 
     * Output: (3, 9)
     */


     // Approach 1: 
     // Have two array 'min' placing the min item sor far and max having the max for far
     // Iterate from left to right, filling the max 
     // Iterate from right to left, filling the min

     // finally get the start by comparing the original array and max 
     // get the end by comparing original array and min

     // EX: for 1, 2, 4, 7, 10, 11, 7, 12,  6,  7,  16, 18, 19
     // max is  1, 2, 4, 7, 10, 11, 11, 12, 12, 12, 16, 18, 19
    //  min is  1, 2, 4, 6,  6,  6,  6,  6,  6,  7, 16, 18, 19

    public ArrayList<Integer> subUnsort(ArrayList<Integer> A) {
        
        int[] minArray = new int[A.size()];
        int[] maxArray = new int[A.size()];
        
        maxArray[0] = A.get(0);
        for(int i =1; i < A.size(); i++){
            maxArray[i] = Math.max(A.get(i), maxArray[i-1]);
        }
        
        minArray[A.size()-1] = A.get(A.size()-1);
        for(int i =A.size()-2; i >= 0; i--){
            minArray[i] = Math.min(A.get(i), minArray[i+1]);
        }
        
        // Get the start
        int start = 0;
        while(start < A.size() && A.get(start) == minArray[start]){
            start++;
        }
        
        // get the end
        int end = A.size() -1;
        while(end >= 0 && A.get(end) == maxArray[end]){
            end--;
        }
        
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(start == A.size()) result.add(new Integer(-1));
        else {
            result.add(new Integer(start));
            result.add(new Integer(end));
        }
        return result;
        
    }


    // Apporach 2: break the array into three parts

    /**
     *  left: 1, 2, 4, 7, 10, 11
        middle: 8, 12
        right: 5, 6, 16, 18, 19  
     *
     */

     /**
      * i.e left where the condition arr[i] < arr[i+1] is valid till
      *      right where there is a longest increasing subsequence like the above
      *     middle, what ever in the middle
      *      
      *  We are not at the answer yet, some number might appear at later than middle, which are much smaller
      */

      /**
       * So, then we find the smallest in the right and largest in the left
       * Then find the smallest's place int the left sub array and the largest's in the right
       * Sort everthing in the middle 
       */

    void findunsortedSequence(int[] array) {
        // find left subsequence
        int end_left = findEndOfLeftSubsequence(array);
        if (end_left >= array.length - 1)
            return; // Already sorted

        // find right subsequence
        int start_right = findStartOfRightSubsequence(array);

        // get min and max
        int max_index = end_left; // max of left side
        int min_index = start_right; // min of right side
        for (int i = end_left + 1; i < start_right; i++) {
            if (array[i] < array[min_index])
                min_index = i;
            if (array[i] > array[max_index])
                max_index = i;
        }

        // slide left until less than array[min_index]
        int left_index = shrinkLeft(array, min_index, end_left);

        // slide right until greater than array[max_index]
        int right_index = shrinkRight(array, max_index, start_right);

        System.out.println(left_index + "," + right_index);
    }

    int findEndOfLeftSubsequence(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1])
                return i - 1;
        }
        return array.length - 1;
    }

    int findStartOfRightSubsequence(int[] array) {
        for (int i = array.length - 2; i >= 0; i--) {
            if (array[i] > array[i + 1])
                return i + 1;
        }
        return 0;

    }

    int shrinkLeft(int[] array, int min_index, int start) {
        int camp = array[min_index];
        for (int i = start - 1; i >= 0; i--) {
            if (array[i] <= camp)
                return i + 1;
        }
        return 0;
    }

    int shrinkRight(int[] array, int max_index, int start) {
        int camp = array[max_index];
        for (int i = start; i < array.length; i++) {

            if (array[i] >= comp)
                return i - 1;
        }
        return array.length - 1;
    }
}