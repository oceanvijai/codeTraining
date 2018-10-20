public class SmallestDifference {

    /**
     * Given two arrays of integers, compute the pair of values (one value in each
     * array) with the smallest (non-negative) difference. Return the difference.
     */

    /**
     * EXAMPLE
     * 
     * Input
     * 
     * {1,3,15,11,2} {23,127,235,19,8}
     * 
     * Output 3. That is, the pair (11, 8).
     */

    // Approach : Sort both the array and move the pointers in the array producing
    // the smallest difference
    // So, here ArrayA has pointer to a
    // ArrayB has pointer to b

    /**
     * A: {1, 2, 11, IS}
     * 
     * B: {4, 12, 19, 23, 127, 235}
     */

    // a - b should be as less as possible
    // So, at each point, increment the array with the smallest element and make the
    // a as close as to b

    int findSmallestDifference(int[] arrayl, int[] array2) {
        Arrays.sort(arrayl);
        Arrays.sort(array2);
        int a = 0;
        int b = 0;
        int difference = Integer.MAX_VALUE;
        while (a < arrayl.length && b < array2.length) {
            if (Math.abs(arrayl[a] - array2[b]) < difference) {
                difference = Math.abs(arrayl[a] - array2[b]);
            }

            /* Move smaller value. */
            if (arrayl[a] < array2[b]) {
                a++;
            } else {
                b++;
            }
        }
        return difference;
    }
}