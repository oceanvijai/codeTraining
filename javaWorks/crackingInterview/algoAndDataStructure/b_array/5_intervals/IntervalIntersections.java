public class IntervalIntersections{
	
	/** Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

	Return the intersection of these two interval lists.

	(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

	**/

	// Input: A = [[0,2],[5,10],[13,23],[24,25]], 
	// B = [[1,5],[8,12],[15,24],[25,26]]
	// Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]


public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<List<Integer>> ans = new ArrayList<>();
        int i = 0, j = 0;
        
        while(i < A.length && j < B.length){
            
            // Get the start and end of a possible interval
            int start = Math.max(A[i][0],B[j][0]);
            int end = Math.min(A[i][1],B[j][1]);
            
            // Check if its really an interval
            // If the its a interval, then start will be lesser than the end
            
            if(start <= end){
                List<Integer> x = new ArrayList<>(2);
                x.add(start);
                x.add(end);
                ans.add(x);
            }
            
            // Now we need to move the pointer, which pointer to move
            // Move the pointer which has LESS end value, so we can see if another interval is 
            // possible with the one which has a greater interval
            if(A[i][1] < B[j][1]){
                i++;
            }else{
                j++;
            }
            
        }
        
        
        int[][] res = new int[ans.size()][2];
        for(int k = 0; k < ans.size(); k++){
            List<Integer> t = ans.get(k);
            int start = t.get(0);
            int end = t.get(1);
            res[k] = new int[]{start,end};
        }
        
        return res;
    }

}
