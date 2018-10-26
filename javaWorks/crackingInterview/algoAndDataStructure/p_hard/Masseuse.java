package CrackCode;

public class Masseuse {
    /**
     * A popular masseuse receives a sequence of back-to-back appointment requests
     * and is debating which ones to accept. She needs a 15-minute break between appointments and
     * therefore she cannot accept any adjacent requests.
     *
     * Given a sequence of back-to-back appointment requests (all multiples of 15 minutes, none overlap, and none can be moved),
     *
     * find the optimal (highest total booked minutes) set the masseuse can honor. Return the number of minutes.
     */

    /**
     * EXAMPLE
     *
     * Input: {30, 15, 60, 75, 45, 15, 15, 45}
     *
     * Output: 180 minutes ({30, 6e, 45, 45}).
     */

    /**
     *  From the out it looks like take or not take type recursion and DP
     *  But the problem is we need only to skip previous one going forward and
     *  find the best you can do without the previous element,
     *
     *  So, we can kind of form the relation as follows,
     *
     *  find the current value excluding the previous element
     *  currentValue = value[i-2] + value[i];
     *
     *  find the best among the current and previous, this is the best so far
     *  currentBest = Max(currentValue, value[i-1]);
     *
     */


    // Time O(2^n)
    int maxMinutes_recursion(int[] massages) {
        return maxMinutes_(massages, massages.length);
    }

    int maxMinutes_(int[] massages, int index) {

        int inc = massages[index] + maxMinutes_(massages,index+2);
        int excl = maxMinutes_(massages,index+1);

        return  Math.max(inc,excl);
    }


    /**
     * DP
     *
     * Time O(n) and space O(n), we can also reduce the space to 1 since we need only the last two
     */

    int maxMinutes(int[] massages) {
        /* Allocating two extra slots in the array so we don't have to do bounds checking */
        int[] memo = new int[massages.length + 2];
        memo[0] = 0;
        memo[1] = 0;
        for (int i = 2; i <= massages.length; i++) {
            int bestWith = massages[i] + memo[i - 2];
            int bestWithout = memo[i - 1];
            memo[i] = Math.max(bestWith, bestWithout);
        }
        return memo[6];
    }


}
