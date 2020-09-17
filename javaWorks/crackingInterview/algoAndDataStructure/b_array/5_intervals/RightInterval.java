public class RightInterval{
	
	/*

	Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i, which can be called that j is on the "right" of i.

	For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum start point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.

	Note:

	You may assume the interval's end point is always bigger than its start point.
	You may assume none of these intervals have the same start point.


	Input: [ [1,2] ]

	Output: [-1]

	Explanation: There is only one interval in the collection, so it outputs -1.





		Input: [ [3,4], [2,3], [1,2] ]

		Output: [-1, 0, 1]

		Explanation: There is no satisfied "right" interval for [3,4].
		For [2,3], the interval [3,4] has minimum-"right" start point;
		For [1,2], the interval [2,3] has minimum-"right" start point.


		Input: [ [1,4], [2,3], [3,4] ]

		Output: [-1, 2, -1]

		Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
		For [2,3], the interval [3,4] has minimum-"right" start point.

	*/


	public int[] findRightInterval(int[][] intervals) {
        // store the indeces for later use
        int n = intervals.length;
        Map<Integer, Integer> indexMap = new HashMap<>();
        int[] startIntervals = new int[n];
        for(int i = 0; i <n; i++ ){
            indexMap.put(intervals[i][0], i);
            startIntervals[i] = intervals[i][0];
        }
        
        // Sort the start intervals alone
        Arrays.sort(startIntervals);
        
        // Iterate and find its right
        int[] ans = new int[n];
        for(int i= 0; i < n; i++){
            // need the right index of the current interval
            int rightIndex = binarySearch(startIntervals, intervals[i][1]);
            if(startIntervals[rightIndex] >= intervals[i][1]){
                ans[i] = indexMap.get(startIntervals[rightIndex]);
            }else{
                ans[i] = -1;
            }
        }
        return ans;
    }
    
    private int binarySearch(int[] startIntervals, int target){
        int start = 0, end = startIntervals.length-1, mid = 0;
        while(start < end){
            mid = start + (end - start)/2;
            if(startIntervals[mid] >= target){
                end = mid;
            }else{
                start = mid+1;
            }
        }
        return start;
    }

}