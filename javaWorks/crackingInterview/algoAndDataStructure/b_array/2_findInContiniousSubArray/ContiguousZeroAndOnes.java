public class ContiguousZeroAndOnes{

	// Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
	/*
		Input: [0,1]
		Output: 2
		Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
	*/

	/*
		Input: [0,1,0]
		Output: 2
		Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
	*/

	public int findMaxLength(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                nums[i] = -1;
            }
        }
        
        Map<Integer,Integer> map = new HashMap<>();
        int sum = 0;
        int count = 0;
        for(int i =0; i < nums.length; i++){
            sum += nums[i];
            if(sum == 0){
                count = Math.max(count,i+1);
            }else if(map.containsKey(sum)){
                int start = map.get(sum)+1;
                int end = i;
                count = Math.max(count, (end-start+1));
            }else{
                map.put(sum,i);
            }
        }
        
        return count;
    }
	
}
