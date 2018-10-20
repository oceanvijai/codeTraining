public class Kadane {

    /**
     * Write an efficient program to find the sum of contiguous subarray within a
     * one-dimensional array of numbers which has the largest sum.
     */

    public void maxContiniousSubArray(int[] arr) {
        int maxSum = 0;
        int maxStart = 0;
        int maxEnd = 0;

        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
            
            if(sum < arr[i]){ // The current element is better than the sum so far
                sum = arr[i]; // start a new sum
                maxStart = i; // start is updated
            }

            if(sum > maxSum){
                maxSum = sum;
                maxEnd = i;
            }
        }

        System.out.println(maxSum);
        System.out.println(maxStart);
        System.out.println(maxEnd);
    }

}