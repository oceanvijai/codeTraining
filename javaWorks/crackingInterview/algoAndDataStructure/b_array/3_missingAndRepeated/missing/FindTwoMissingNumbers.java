public class FindTwoMissingNumbers {

    /**
     * The array has all distinct elements and size of array is (n-2). Hence Two
     * numbers from the range are missing from this array. Find the two missing
     * numbers.
     */

    // First one is straight foward
    // count them and see which is missing using a array





    
    // Form a equation
    /**
     * like x + y = sumOfN - sumOfArray
     * Then some other way to get the equation solved
     * find xy = val / n!
     * 
     * then, we need x - y
     * 
     * So, first we do (a-b)^2 = a^2 + b^2 - 2ab;
     * To utilize a + b which we found above, instead of (a^2 + b^2) => (a + b)^2 - 2ab
     * 
     * So the above equaltion become, 
     * 
     * (a-b)^2 = ((a + b)^2) - 4ab;
     * then a - b = squarRT(((a + b)^2) - 4ab);
     * 
     * So we can susbtitue, a+b and a*b into this equation
     * 
      */





    /**
     * Bitwise
     * Algorith:
     * 
     * XOR elements from 1 to n
     * 
     * XOR element in the array
     * 
     * XOR of these two will give x XOR y
     * 
     * Now, find the position of the right most bit, why ? cause these two numbers 
     * differ in that set bit
     * 
     * Then XOR the numbers from 1 to n which has this bit set
     * Then XOR the number in the array which has the bit set, 
     * then we will get eithe x or y as the result
     * 
     * Why ? lets say, the numbers with that set bit are {1, 4, 7, 9}
     * Out of this , let say 4 is missing
     * 
     * then XOR from for the array {1, 4, 7, 9} and the XOR or the array {1, 7, 9}
     * will produce 4
     */
    
    static void findTwoMissingNumbers(int arr[], int n) {
        int XOR = arr[0];
        for (int i = 1; i < n - 2; i++)
            XOR ^= arr[i];

        for (int i = 1; i <= n; i++)
            XOR ^= i;

        // Now XOR will have x XOR y
        // So lets find the right most set bit in the XOR
        int set_bit_no = XOR & ~(XOR - 1); // find it in any other way also

        int x = 0, y = 0; // Initialize missing numbers

        for (int i = 0; i < n - 2; i++) {
            if ((arr[i] & set_bit_no) > 0)

                /* XOR of first set in arr[] */
                x = x ^ arr[i];
            else
                /* XOR of second set in arr[] */
                y = y ^ arr[i];
        }

        for (int i = 1; i <= n; i++) {
            if ((i & set_bit_no) > 0)

                /*
                 * XOR of first set in arr[] and {1, 2, ...n }
                 */
                x = x ^ i;
            else
                /*
                 * XOR of second set in arr[] and {1, 2, ...n }
                 */
                y = y ^ i;
        }

        System.out.println("Two Missing Numbers are ");
        System.out.println(x + " " + y);

    }

}