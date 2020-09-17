public class InsertInterval{
	
	/**

	Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

	You may assume that the intervals were initially sorted according to their start times.

	Example 1:

	Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
	Output: [[1,5],[6,9]]
	Example 2:

	Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
	Output: [[1,2],[3,10],[12,16]]
	Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

	**/


	public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList();
        for (int[] in : intervals) {
            if (in[1] < newInterval[0]) {
                list.add(in);
            } else if (newInterval[1] < in[0]) {
                list.add(newInterval);
                newInterval = in;
            } else {
                newInterval[0] = Math.min(newInterval[0], in[0]);
                newInterval[1] = Math.max(newInterval[1], in[1]);
            }
        }
        list.add(newInterval);

        return list.toArray(new int[list.size()][]);
    }


    // My approach with binary search

    // Has one bug
    
    
    /* public int[][] insert(int[][] intervals, int[] newInterval) {
        int newStart = newInterval[0];
        int n = intervals.length;
        int start = 0,end = n-1, mid = 0;
        while(start < end){
            mid = (start + (end - start))/2;
            System.out.println("mid:"+mid);
            if(intervals[mid][0] < newStart){
                end = mid;
            }else{
                start = mid+1;
            }
        }
        
        int insertLocation = start;
        System.out.println(start);
        
        // Copy anything before this
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < insertLocation; i++){
            List<Integer> tmp = new ArrayList<>();
            tmp.add(intervals[i][0]);
            tmp.add(intervals[i][1]);
            ans.add(tmp);
         }
        
        // start merge
        int intStart = Math.min(newInterval[0], intervals[start][0]);
        int intEnd = newInterval[1];
        int i = start;
        while(i < n){
            int[] currentInterval = intervals[i];
            if(intEnd < currentInterval[0]){
                break;
            }
            if(currentInterval[1] > intEnd){
                intEnd = currentInterval[1];
            }
            i++;
        }
        List<Integer> tmp = new ArrayList<>();
        tmp.add(intStart);
        tmp.add(intEnd);
        ans.add(tmp);
        
        // add the remaining
        for(;i<n;i++){
            tmp= new ArrayList<>();
            tmp.add(intervals[i][0]);
            tmp.add(intervals[i][1]);
            ans.add(tmp);
        }
        
        //ans.stream().forEach(e -> System.out.println((int[])e[0]+" : "+e[1]));
        System.out.println(ans);
        ans.stream().map(lst -> new )
        
        return intervals;
    } 
    */


}