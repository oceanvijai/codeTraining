public class FindMissingNumbers {

    // 1 -> n
    // You are given a list of n-1 integers and these integers are in the range of 1
    // to n

    /**
     * Approach 1: summation
     * 
     * 1.Get the sum of numbers total = n*(n+1)/2
     * 
     * 2. Subtract all the numbers from sum and you will get the missing number.
     */

    static int getMissingNo(int a[], int n) {
        int i, total;
        total = (n + 1) * (n + 2) / 2;
        for (i = 0; i < n; i++)
            total -= a[i];
        return total;
    }

    /**
     * Similarly, we can use XOR
     * 
     * We XOR all numbers from 1 to n 
     * 
     * then XOR all the elements in the array
     * 
     * then xor both result to get the missing one
     * 
     */

    // Function to find missing number

    static int getMissingNo_Bitwise(int a[], int n) {
        int x1 = a[0];
        int x2 = 1;

        /*
         * For xor of all the elements in array
         */
        for (int i = 1; i < n; i++)
            x1 = x1 ^ a[i];

        /*
         * For xor of all the elements from 1 to n+1
         */
        for (int i = 2; i <= n + 1; i++)
            x2 = x2 ^ i;

        return (x1 ^ x2);
    }

}
