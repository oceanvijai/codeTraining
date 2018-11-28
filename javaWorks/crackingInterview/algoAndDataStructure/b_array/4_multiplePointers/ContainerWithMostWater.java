public class ContainerWithMostWater {

    /**
     * Given n non-negative integers a1, a2, ..., an , where each represents a point
     * at coordinate (i, ai). n vertical lines are drawn such that the two endpoints
     * of line i is at (i, ai) and (i, 0). Find two lines, which together with
     * x-axis forms a container, such that the container contains the most water.
     * 
     * 
     * Input: [1,8,6,2,5,4,8,3,7] Output: 49
     */

    public int maxArea(int[] height) {
        int ans = 0;

        int i = 0;
        int j = height.length - 1;

        while (i < j) {
            int area = (Math.min(height[i], height[j])) * (j - i);
            ans = Math.max(ans, area);

            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return ans;

    }
}