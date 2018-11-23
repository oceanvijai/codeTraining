class SildingWindow {

    /**
     * Use max queue if the nums are coming in streams
     * 
     * O(1) - armotozed for queue.add()
     */

    public int[] maxSlidingWindow(int[] nums, int k) {

        int[] r = new int[k];
        MaxQueue queue = new MaxQueue();

        for (int i = 0; i < k; i++) {
            queue.add(nums[i]);
        }

        r[0] = queue.getMax();

        for (int i = k + 1; i < nums.length; i++) {
            queue.add(nums[i]);
            queue.remove();
            r[i - k] = max.getMax();

        }

        return r;

    }

    public class MaxQueue {
        LinkedList<Integer> maxQueue = new LinkedList<>();
        LinkedList<Integer> queue = new LinkedList<>();

        public int add(int x) {
            queue.addLast(x);
            while (!maxQueue.isEmpty() && maxQueue.peekLast() < x) {
                maxQueue.pollLast();
            }

            maxQueue.addLast(x);
        }

        public void remove() {
            if (queue.isEmpty()) {
                return;
            }

            if (queue.peekFirst() == maxQueue.peekFirst()) {
                maxQueue.pollFirst();
            }

            queue.pollFirst();
        }

        public int getMax() {
            if (maxQueue.isEmpty()) {
                return -1;
            }

            return maxQueue.peekFirst();
        }

    }

    /**
     * Approach 2: DP the problem with the partition
     * 
     * For Example: A = [2,1,3,4,6,3,8,9,10,12,56], w=4
     * 
     * partition the array in blocks of size w=4. The last block may have less then
     * w.
     * 
     * 2, 1, 3, 4 | 6, 3, 8, 9 | 10, 12, 56|
     * 
     * Traverse the list from start to end and calculate max_so_far. Reset max after
     * each block boundary (of w elements).
     * 
     * left_max[] = 2, 2, 3, 4 | 6, 6, 8, 9 |10, 12, 56
     * 
     * Similarly calculate max in future by traversing from end to start.
     * right_max[] = 4, 4, 4, 4 | 9, 9, 9, 9 | 56, 56, 56
     * 
     * now, sliding max at each position i in current window, sliding-max(i) =
     * max{right_max(i), left_max(i+w-1)} sliding_max = 4, 6, 6, 8, 9, 10, 12, 56
     */

    public static int[] slidingWindowMax(final int[] in, final int w) {
        final int[] max_left = new int[in.length];
        final int[] max_right = new int[in.length];

        max_left[0] = in[0];
        max_right[in.length - 1] = in[in.length - 1];

        for (int i = 1; i < in.length; i++) {
            max_left[i] = (i % w == 0) ? in[i] : Math.max(max_left[i - 1], in[i]);

            final int j = in.length - i - 1;
            max_right[j] = (j % w == 0) ? in[j] : Math.max(max_right[j + 1], in[j]);
        }

        final int[] sliding_max = new int[in.length - w + 1];
        for (int i = 0, j = 0; i + w <= in.length; i++) {
            sliding_max[j++] = Math.max(max_right[i], max_left[i + w - 1]);
        }

        return sliding_max;
    }
}