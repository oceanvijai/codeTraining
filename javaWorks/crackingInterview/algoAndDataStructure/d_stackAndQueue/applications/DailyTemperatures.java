public class DailyTemperatures {
    /**
     * Given a list of daily temperatures T, return a list such that, for each day
     * in the input, tells you how many days you would have to wait until a warmer
     * temperature. If there is no future day for which this is possible, put 0
     * instead.
     * 
     * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76,
     * 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
     */

    /**
     * This is same as the next greater element, but only we need to get the next
     * greater element index and find the difference from the current index
     * 
     * 
     * 
     * 
     * See NextLargetElement before this
     */

    public int[] dailyTemperatures(int[] T) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = T.length - 1; i >= 0; i--) {
            int temperature = T[i];
            while (!stack.isEmpty() && temperature >= T[stack.peekFirst()]) {
                stack.pollFirst();
            }
            if (!stack.isEmpty() && temperature < T[stack.peek()]) {
                indexMap.put(i, stack.peek());
            }

            stack.addFirst(i);
        }

        int[] ans = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            if (indexMap.containsKey(i)) {
                ans[i] = indexMap.get(i) - i;
            }
        }

        return ans;
    }
}