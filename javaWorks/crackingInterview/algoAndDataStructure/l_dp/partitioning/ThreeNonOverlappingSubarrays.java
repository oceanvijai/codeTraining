public class ThreeNonOverlappingSubarrays{
  /**
    Given an integer array nums and an integer k, 
    find three non-overlapping subarrays of length k with maximum sum and return them.

    Return the result as a list of indices representing the starting position 
    of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.
    
    Example 1:

    Input: nums = [1,2,1,2,6,7,5,1], k = 2
    Output: [0,3,5]
    Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
    We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
    Example 2:

    Input: nums = [1,2,1,2,1,2,1,2,1], k = 2
    Output: [0,2,4]
    
  **/
  
   /**
      Approach: We first do a sliding window and calcuate the windows sum
      Then this problem is reduced to finding pairs of 3 sum max, only constraint is the sub array sum should not have a overlapping indices.
      
      So we do these in order,
        1. Find the subarray sums at each index. Meaning each sub array starts form that index.
        2. So at every index we want to knwo the best of left and right non overlapping sub array sum, calculate it.
        3. Then find the best sum at each index
        
        Time: O(n)
        Space: O(n)
   
   
   **/
  
  
  public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        // Lets first do a sliding window and see what is the sum of each sub array
        int[] subarraySums = new int[nums.length-k+1];
        int subarraySum = 0;
        for(int i=0; i<nums.length; i++){
            if(i < k){
                subarraySum += nums[i];
            }else{
                subarraySum += nums[i] - nums[i-k];
            }

            if(i == k-1){
                subarraySums[0] = subarraySum;
            }else if(i > k-1){
                subarraySums[i-k+1] = subarraySum;
            }
        }

        // After that we do these two thing
        // 1. Start from left to right and find what is the best sub array sum to the left at any index
        // 2. Do the same from right to left
        // Also, make sure we save the index so we can find the smallest among them
        int[] bestLeftSubArrayIndex = new int[subarraySums.length];
        int bestIndex = 0;
        for(int i=0; i < bestLeftSubArrayIndex.length; i++){
            if(subarraySums[i] > subarraySums[bestIndex]){
                bestIndex = i;
            }
            bestLeftSubArrayIndex[i] = bestIndex;
        }

        int[] bestRightSubArrayIndex = new int[subarraySums.length];
        bestIndex = bestRightSubArrayIndex.length-1;
        for(int i=bestRightSubArrayIndex.length-1; i >= 0; i--){
            if(subarraySums[i] >= subarraySums[bestIndex]){
                bestIndex = i;
            }
            bestRightSubArrayIndex[i] = bestIndex;
        }

        // At every index, check if what is the best to the left and right
        // Make sure we avoid overlap by remoing overalping indices
        int[] ans = new int[]{-1,-1,-1};
        int bestAns = 0;
        for(int i = k; i < subarraySums.length-k; i++){
            int l = bestLeftSubArrayIndex[i-k], r = bestRightSubArrayIndex[i+k];
            if(subarraySums[l] + subarraySums[i] + subarraySums[r] > bestAns){
                bestAns = subarraySums[l] + subarraySums[i] + subarraySums[r];
                ans[0] = l;
                ans[1] = i;
                ans[2] = r;
            }
        }
        return ans;
    }
  
  
}
