public class LongestIncreasingSubsequence{

    // This is an N^2 alogithm

    public int longestSubsequenceLength(final List<Integer> A) {
        
        int[] inc = new int[A.size()];
        Arrays.fill(inc, 1);
        for(int i =1; i < A.size(); i++){
            for(int j =0; j < i; j++){
                if(A.get(j) < A.get(i) && inc[i] < inc[j] + 1){
                    inc[i] = inc[j]+1;
                }
            }
        }

        int max = 0;
        for(int i=0; i < A.size(); i++){
            if(inc[i] > max){
                max = inc[i];
            }
        }
        return max > 0 ? max : 0;


    }

    // NlogN algorithm

    // Approach : create a DP array
    /**
     * Now, our aim is to elements in the DP array, such that only the increasing oder is 
     * maintained in the DP.
     * 
     * How ?
     * 
     * Say, we have {10,100,5,9,200,25}
     * 
     * we create a DP array with 6 elements {0,0,0,0,0,0}
     * Have a pointer for the end of the valid placemet, say i.
     * Now take 10, 10 can be placed at any point now, so i = 0; {10,0,0,0,0,0}
     * Now take 100, Now 10 and 100 are increasing we, so i = 1; {10,100,0,0,0,0}
     * 
     * Now take 5, if you see it does not matter if we have 5 or 10 at index 0,
     * because both will give the same result.
     * But if we place 5, we might get a element lesser than 5, so we place the smallest  so i = 0; {5,100,0,0,0,0}
     * 
     * Now take 9, 9 can be placed at index 1 since it will give the same result, so i = 0; {5,9,0,0,0,0}
     * Now take 200, 200 at the last making it LIC 3, so i = 0; {5,9,200,0,0,0}
     * Now take 25, 25 can be placed at index 2 since it will ive the same result, so i = 0; {5,9,25,0,0,0}
     * 
     * Finally we can give the ans as 3, since we only were able to place only 3 which are increasing
     * 
     * So, the algo id simple
     * 
     * 1.   Iterate over the array (O(n))
     * 2.   For every element find a index in the DP using binary search (logn)
     * 3.   place the element in the index
     * 4.   if the index is the valid size so far, then increment it
     * 5.   return the size;
     * 
     */
    
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length]; 
        int validSize = 0;
        
        for(int n : nums){
            int start = 0;
            int end = validSize;
            while(start != end){
                int mid = (start+end) / 2;
                if(dp[mid] < n){
                    start = mid +1;
                }else{
                    end = mid;
                }
            }
            dp[start] = n;
            if(start == validSize){ // meaning we have place the element at the end
                validSize++;
            }
            
        }
        
        return validSize; 
    }


}