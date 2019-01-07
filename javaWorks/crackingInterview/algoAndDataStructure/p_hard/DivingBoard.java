package CrackCode;

import java.util.HashSet;

public class DivingBoard {

    /**
     * Diving Board: You are building a diving board by placing a bunch of planks of wood end-to-end.
     * There are two types of planks, one of length shorter and one of length longer.
     * <p>
     * You must use exactly K planks of wood.
     * <p>
     * Write a method to generate all possible lengths for the diving board.
     */

    // Here we are asked to find all possible lengths with k picks

    // Approach: recursively try first the small then in each step decide to take a smaller one or longer one

    // So take or not take

    // so the recurrence relation is f(i++,length+small) , f(i++,length+long)
    // base case is i == k;

    // time O(2^n)

    HashSet<Integer> allLengths(int k, int shorter, int longer) {
        HashSet<Integer> lengths = new HashSet<Integer>();
        getAllLengths(k, 0, shorter, longer, lengths);
        return lengths;
    }

    void getAllLengths(int k, int total, int shorter, int longer, HashSet<Integer> lengths) {
        if (k == 0) {
            lengths.add(total);
            return;
        }

        getAllLengths(k - 1, total + shorter, shorter, longer, lengths);
        getAllLengths(k - 1, total + longer, shorter, longer, lengths);
    }

    // Now we can see that this can be solved via DP also
    // Also, since there are only two lengths involved, we can say if we
    // pick short i times, then long is picked k - i times

    // so the DP table looks like this

    //  let say 1 to 6, lets say short is 2 and long is 4

    //   | 1 | 2 | 3 | 4 | 5 | 6 |
    // ---------------------------
    // 2 | 2 | 4 | 6 | 8 | 10| 12|
    // 4 | 4 | 8 |12 |16 | 20| 24|

    // We can not that i * 2 + (k-i) * 4
    // So, looks like we dint need the extra DP table

    HashSet<Integer> allLengths_DP(int k, int shorter, int longer) {
        HashSet<Integer> lengths = new HashSet<Integer>();
        for (int i = 0; i <= k; i++) {
            int nLonger = k - i;
            int length = i * shorter + nLonger * longer;
            lengths.add(length);
        }
        return lengths;
    }
}
