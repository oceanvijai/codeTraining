public class NextLargetElement {
    /**
     * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s
     * elements are subset of nums2. Find all the next greater numbers for nums1's
     * elements in the corresponding places of nums2.
     * 
     * The Next Greater Number of a number x in nums1 is the first greater number to
     * its right in nums2. If it does not exist, output -1 for this number.
     */

    /**
     * Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
     * 
     * Output: [-1,3,-1]
     * 
     * Explanation: For number 4 in the first array, you cannot find the next
     * greater number for it in the second array, so output -1. For number 1 in the
     * first array, the next greater number for it in the second array is 3. For
     * number 2 in the first array, there is no next greater number for it in the
     * second array, so output -1.
     */

    /**
     * Approach:
     * 
     * we use stack to store every element we saw so far
     * 
     * We use hash map to remember the next greater element which we find from stack
     * 
     * Then we use the map to finally get the result
     * 
     */

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = nums2.length - 1; i >= 0; i--) {
            int num = nums2[i];
            if (!stack.isEmpty()) {
                while (!stack.isEmpty() && num > stack.peek()) { // remove anything lesser than num
                    stack.pop();
                }
                if (!stack.isEmpty() && num < stack.peek()) {
                    map.put(num, stack.peek());
                }
            }

            stack.push(num);
        }

        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.getOrDefault(nums1[i], -1);
        }

        return ans;
    }
}