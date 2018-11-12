public class TrappingRainWater {
    /**
     * Given n non-negative integers representing an elevation map where the width
     * of each bar is 1, compute how much water it is able to trap after raining.
     * 
     * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
     * 
     * Output: 6
     */

    public int trap(int[] height) {

        if (height.length == 0) {
            return 0;
        }

        int[] leftToRight = new int[height.length];
        int[] rightToLeft = new int[height.length];
        int[] delta = new int[height.length];

        leftToRight[0] = height[0];
        for (int i = 1; i < height.length - 1; i++) {
            leftToRight[i] = Math.max(leftToRight[i - 1], height[i]);
        }

        rightToLeft[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i > 0; i--) {
            rightToLeft[i] = Math.max(rightToLeft[i + 1], height[i]);
            // delta[i] = Math.abs(leftToRight[i] - rightToLeft[i]); // just reducing one
            // pass
            delta[i] = Math.min(leftToRight[i], rightToLeft[i]);
        }

        int ans = 0;
        for (int i = 1; i <= height.length - 2; i++) {
            int vol = Math.abs(height[i] - delta[i]);
            ans = ans + vol;
        }

        return ans;

    }
}