public class MaxSubArraySum{

  /**
    Given an array of numbers find the max subarray sum

    Approach 1: Kadane O(n)

    Approach 2: Segment tree O(n)

    Approach 3: Kadane modified O(n) by finding minimum subArray sum

    here we can see Approach 2 & 3

  **/


  /**
      Approach 2: Segment tree O(n)
      Use this if we need to find max subarrays with modifications later on which can be done with O(log n)

  **/

  public int maxSubArray(int[] nums) {
        return buildTree(nums, 0, nums.length-1).maxSum;
    }

    private Segment buildTree(int[] nums, int startIndex, int endIndex){
        if(startIndex > endIndex){
            return new Segment(Integer.MIN_VALUE, startIndex, endIndex);
        }

        if(startIndex == endIndex){
            return new Segment(nums[startIndex], startIndex, endIndex);
        }

        int mid = (endIndex+startIndex)/2;
        Segment leftSegment = buildTree(nums, startIndex, mid);
        Segment rightSegment = buildTree(nums, mid+1, endIndex);

        Segment currentSegment = new Segment();
        currentSegment.startIndex = startIndex;
        currentSegment.endIndex = endIndex;
        // Total Sum (easy) to be used by segments higher while calcualting left or right sums
        currentSegment.totalSum = leftSegment.totalSum + rightSegment.totalSum;

        // max left to right sum
        currentSegment.bestLeftToRightSum = Math.max(leftSegment.bestLeftToRightSum, 
                                                    leftSegment.totalSum + rightSegment.bestLeftToRightSum);

        // max right to left sum
        currentSegment.bestRightToLeftSum = Math.max(rightSegment.bestRightToLeftSum, 
                                                    rightSegment.totalSum + leftSegment.bestRightToLeftSum);

        // Max sum
        // Either the left/right segemnt already has the max
        currentSegment.maxSum = (int)Math.max(leftSegment.maxSum, rightSegment.maxSum); 
        // Or we can see if its running accros two segments
        currentSegment.maxSum = (int)Math.max(currentSegment.maxSum, leftSegment.bestRightToLeftSum+ 
                                                                rightSegment.bestLeftToRightSum);

        return currentSegment;
    }

    private class Segment{
        long totalSum, bestLeftToRightSum, bestRightToLeftSum;
        int maxSum;
        int startIndex, endIndex; // Incase updates are needed to solve other problems

        public Segment(int value, int startIndex, int endIndex){
            // Used to calculate the prefix and sufix sums later in merged nodes
            this.totalSum = value; 
            // This mimics the best subarray sum starting from left index to right index of the segment
            this.bestLeftToRightSum = value; 
            // This mimics the best subarray sum starting from right index to left index of the segment
            this.bestRightToLeftSum = value;
            // Kind of store the ans for each segment
            this.maxSum = value;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        public Segment(){}
    }



   /**
      Approach 3: Kadane modified O(n) by finding minimum subArray sum

      As we go try and keep track of the min sub array sum so far and subract it from the running sum to get the max subarray

  **/

  public int maxSubArray(int[] nums) {
        int runningSum = 0; // Keep adding all nums all we iterate the sum
        int minSubArraySum = 0; // Track the min subarray sum
        // Defference of the above two will give us the max subarray sum
        int ans = Integer.MIN_VALUE;

        for(int i = 0; i < nums.length; i++){
            runningSum += nums[i];
            ans = Math.max(ans, runningSum - minSubArraySum);
            
            minSubArraySum = Math.min(minSubArraySum, runningSum);
        }

        return ans;
    }
  
}
