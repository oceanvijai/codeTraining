public class LargestRectHistogram {
    
    /**
        Approach: 
        1. Form a increasing stack
        2. When we need to pop, calculate the rectangle area
        3. For the poped element, the current stack peek is the left boundary, cause after that, there are only lesser elements which will contribute only lesser heights
        4. For the poped element, the current index is the right boundary. Since the poped 

    **/
    
    
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> increasingStack = new ArrayDeque<>();
        int maxArea = 0;
        int n = heights.length;

        for (int currentIndex = 0; currentIndex <= n; currentIndex++) {
    
            // current index height. If its taller than stack top, fine, It MAY be the right boundary for the current top of stock
            // We are setting 0, when the iteration goes after the array boundary
            int curHeight = (currentIndex != n) ? heights[currentIndex] : 0;
    
            // Else prune stack until its just taller. So the one left in the stack will form it left boundary for the rectangle.
            // Also, anthing bigger than this found previously, this will form the right boundary by pruning.
            while (!increasingStack.isEmpty() && curHeight < heights[increasingStack.peek()]) {
                // height, This is the rectangle we are considering, take this height and see where it start and ends
                int height = heights[increasingStack.pop()]; 
                
                // right, Either this poped index or one taller which just got poped previously in the loop might form the right boundary, so the 
                // right boundary just ended in the current index with the same of higher bar
                int right = currentIndex; 
                
                // left, The rectangle started just after this index, since the poped one started just after that left index
                int left   = increasingStack.isEmpty() ? -1 : increasingStack.peek(); 
                
                // width, the space between left to right
                int width  = right - left - 1;
                
                int newArea = width * height;
                maxArea = Math.max(maxArea, newArea);
            }

            // Add the new height to the increasing stack which will be resolved later
            increasingStack.push(currentIndex);
        }

        return maxArea;
    }

    
    
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
