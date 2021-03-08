public class TrappingRainWater {

	/**
		Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.


		Input: height = [4,2,0,3,2,5]
		Output: 9
	**/


	/**
		Approach 1: DP O(n) and O(n)

		Scan from left to right to find what is the max to the left of any index
		Scan from right to left to find which is the max to the right of any index

		using this info, find the min of the items to the left and right
		Then use that to get the trapped water by subracting from the current index height

	**/


	public int trap(int[] height) {
        
        if(height.length == 0){
            return 0;
        }
        
        int[] leftToRight = new int[height.length];
        int[] rightToLeft = new int[height.length];
        int[] delta = new int[height.length];
        
        leftToRight[0] = height[0];
        for(int i =1; i < height.length -1; i++){
            leftToRight[i] = Math.max(leftToRight[i-1],height[i]);
        }
        
        rightToLeft[height.length-1] = height[height.length-1];
        for(int i = height.length-2; i > 0; i--){
            rightToLeft[i] = Math.max(rightToLeft[i+1],height[i]);
            delta[i] = Math.min(leftToRight[i] , rightToLeft[i]);
        }
        
        
        int ans = 0;
        for(int i =1; i <= height.length-2; i++){
            int vol = Math.abs(height[i] - delta[i]);
            ans = ans + vol;
        }
        
        return ans;
        
    }

    /**
		Approach 2: stack O(n) and O(n)

		Iterate the elemets
		If the current index height is bigger than the top of the stack height
			then, find the cost to before the current index
			Pop the top index
			
			Calculate the distance from the current index to the top of the stack.
			This will give us the multiplier we need for this two towers
			Then select the min of the current index and stack top height. 

			Subract the poped height with the min of the above and multiply with the distance
			
		then add the current index to the stack

	**/


	public int trap(int[] height) {
        int ans = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        stack.addFirst(0);
        
        for(int i =1; i < height.length; i++){
            int currentHeight = height[i];
            while(!stack.isEmpty() && currentHeight > height[stack.peekFirst()]){
                int prevIndex = stack.pollFirst();
                if(stack.isEmpty()){
                    break;
                }
                int distance =  (i - stack.peekFirst()) - 1;
                ans += distance * (Math.min(currentHeight, height[stack.peekFirst()]) - height[prevIndex]);
            }
            stack.addFirst(i);
        }
        
        return ans;
    }
	
}