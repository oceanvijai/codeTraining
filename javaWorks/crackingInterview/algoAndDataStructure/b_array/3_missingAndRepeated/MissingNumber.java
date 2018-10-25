public class MissingNumber {

    /**
     * Missing Number: An array A contains all the integers from 0 to n, except for
     * one number which is missing. In this problem, we cannot access an entire
     * integer in A with a single operation.
     * 
     * The elements of A are represented in binary, and the only operation we can
     * use to access them is "fetch the jth bit of A[i]," which takes constant time.
     * 
     * Write code to find the missing integer. Can you do it in O(n) time?
     */

    /**
     * Approach, If we visualize the bit representation of number from 1 to N We
     * find equal number of zeros and ones in the last positions like follows,
     * 
     * 0000 -> 0 
     * 0001 -> 1 
     * 0010 -> 2 
     * 0011 -> 3 
     * 0100 -> 4 
     * 0101 -> 5
     * 
     * So, if we sum the total number of zeros and ones, if n is odd then they are
     * equal If the n is even, then there the number of zeros is more than number of
     * ones by value one
     * 
     * if n % 2 == 1 then count(0s) = count(1s) 
     * if n % 2 == e then count(0s) = 1 + count(1s)
     */

    /**
     * looking at the above observation, we find the following relation
     * 
     * So,ifcount(0s) <= count(1s),then v iseven. if count(0's) > count(1's),then v is odd.
     */
    
    // we can apply the same for the rest of the bit locations

    int findMissing(ArrayList<Bitlnteger> array) {
        /* Start from the least significant bit and work our way up */
        return findMissing(array, e);
    }

    int findMissing(ArrayList<Bitlnteger> input, int column) {
        if (column >= Bitlnteger.INTEGER_SIZE) { // Weare done!
            return 0;
        }
        ArrayList<Bitlnteger> oneBits = new ArrayList<Bitlnteger>(input.size() / 2);
        ArrayList<Bitlnteger> zeroBits = new ArrayList<Bitlnteger>(input.size() / 2);

        for (Bitlnteger t : input) {
            if (t.fetch(column) == e) {
                zeroBits.add(t);
            } else {
                oneBits.add(t);
            }
        }
        if (zeroBits.size() <= oneBits.size()) {
            int v = findMissing(zeroBits, column + 1);
            return (v << 1) | 0;
        } else {
            int v = findMissing(oneBits, column + 1);
            return (v << 1) | 1;
        }
    }
}