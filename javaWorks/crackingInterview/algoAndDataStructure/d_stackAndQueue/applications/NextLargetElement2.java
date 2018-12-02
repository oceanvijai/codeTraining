public class NextLargetElement {
    /**
     * Given a circular array (the next element of the last element is the first
     * element of the array), print the Next Greater Number for every element. The
     * Next Greater Number of a number x is the first greater number to its
     * traversing-order next in the array, which means you could search circularly
     * to find its next greater number. If it doesn't exist, output -1 for this
     * number.
     * 
     * 
     * Input: [1,2,1]
     * 
     * Output: [2,-1,2]
     * 
     * Explanation: The first 1's next greater number is 2; The number 2 can't find
     * next greater number; The second 1's next greater number needs to search
     * circularly, which is also 2.
     */

    /**
     * Approach: It is same as the first except we do it twice
     * and store the index as the key in map, since duplicates are there
     */

    public int[] nextGreaterElements(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        LinkedList<Integer> stack = new LinkedList<>();
        int n = nums.length;
        for (int i = (n * 2) - 1; i >= 0; i--) {
            int num = nums[i % n];
            if (!stack.isEmpty()) {
                while (!stack.isEmpty() && num >= stack.peek()) { // remove anything lesser than num
                    stack.pop();
                }

                if (!stack.isEmpty() && num < stack.peek()) {
                    map.put((i % n), stack.peek());
                }
            }

            stack.push(num);
        }

        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = map.getOrDefault(i, -1);
        }

        return ans;
    }
}