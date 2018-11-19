public class KEmptySlots {

    /**
     * 
     * There is a garden with N slots. In each slot, there is a flower. The N
     * flowers will bloom one by one in N days. In each day, there will be exactly
     * one flower blooming and it will be in the status of blooming since then.
     * 
     * Given an array flowers consists of number from 1 to N. Each number in the
     * array represents the place where the flower will open in that day.
     * 
     * For example, flowers[i] = x means that the unique flower that blooms at day i
     * will be at position x, where i and x will be in the range from 1 to N.
     * 
     * Also given an integer k, you need to output in which day there exists two
     * flowers in the status of blooming, and also the number of flowers between
     * them is k and these flowers are not blooming.
     * 
     * If there isn't such day, output -1.
     */

    public int kEmptySlots_solve(int[] flowers, int k) {

        int res = Integer.MAX_VALUE;
        int[] day = new int[flowers.length + 1];
        for (int i = 0; i < flowers.length; i++) {
            // day[i] is the day when the flower at position i blooms
            // day[0] is useless here
            day[flowers[i]] = i + 1;
        }

        // we now are supposed to find a subarray of day[left, right] where its length
        // is k+2
        // and all i that left < i < right, we have day[i] > day[left] and day[i] >
        // day[right]
        int left = 1, right = k + 2;
        for (int i = 2; right < day.length; i++) {
            if (i == right) {
                // found a sub array
                res = Math.min(res, Math.max(day[left], day[right]));
                left = i;
                right = left + k + 1;
            } else if (day[i] < day[left] || day[i] < day[right]) {
                left = i;
                right = left + k + 1;
            }
        }

        return (res == Integer.MAX_VALUE) ? -1 : res;
    }
}