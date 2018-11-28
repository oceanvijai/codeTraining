public class LargestRectHistogram {
    public int largestRectangleArea(int[] heights) {
        LinkedList<Integer> stack = new LinkedList<>();

        int maxArea = 0;
        int i = 0;
        while (i < heights.length) {
            if (stack.isEmpty() || heights[i] >= heights[stack.peekFirst()]) {
                stack.addFirst(i);
                i++;
            } else {
                int top = stack.pollFirst();
                int area = 0;
                if (stack.isEmpty()) {
                    area = heights[top] * i;
                } else {
                    area = heights[top] * (i - stack.peekFirst() - 1);
                }
                maxArea = Math.max(maxArea, area);
            }
        }

        while (!stack.isEmpty()) {
            int top = stack.pollFirst();

            int area = 0;
            if (stack.isEmpty()) {
                area = heights[top] * i;
            } else {
                area = heights[top] * (i - stack.peekFirst() - 1);
            }
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;

    }
}