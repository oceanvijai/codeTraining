public class MaximumSumQueries{

  /**
      You are given a 2D integer array intervals, where intervals[i] = [lefti, righti] describes the ith interval starting at lefti and 
      ending at righti (inclusive). The size of an interval is defined as the number of integers it contains, or more formally righti - lefti + 1.

      You are also given an integer array queries. The answer to the jth query is the size of the smallest interval i such 
      that lefti <= queries[j] <= righti. If no such interval exists, the answer is -1.

      Return an array containing the answers to the queries.


      Example 1:

      Input: intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]
      Output: [3,3,1,4]
      Explanation: The queries are processed as follows:
      - Query = 2: The interval [2,4] is the smallest interval containing 2. The answer is 4 - 2 + 1 = 3.
      - Query = 3: The interval [2,4] is the smallest interval containing 3. The answer is 4 - 2 + 1 = 3.
      - Query = 4: The interval [4,4] is the smallest interval containing 4. The answer is 4 - 4 + 1 = 1.
      - Query = 5: The interval [3,6] is the smallest interval containing 5. The answer is 6 - 3 + 1 = 4.

  **/

  /**
    Approach: sort the queries and save their original index, this way we need to check only a subset of the intervals who's
    end is greater than the query value.

    Now, put only those intervals in the heap, and check which one is the smallest interval. Give that as the answer for that query
     repeat for other queries

  **/


    public int[] minInterval(int[][] intervals, int[] queries) {
        // Sort intervals by start
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

        // Sort queries, retain the original indices
        List<int[]> qs = new ArrayList<>();
        for(int i = 0; i < queries.length; i++)
            qs.add(new int[]{queries[i], i});
        Collections.sort(qs, (a,b)-> a[0] - b[0]);

        // try and answer all the queries
        int[] ans = new int[queries.length];
        // queue sorted based on the intervals size
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b) -> (a[1] - a[0]) - (b[1] - b[0]));
        int currentIntervalIndex = 0;
        for(int[] q: qs){
            int queryValue = q[0];
            int queryOrgIndex = q[1];

            // Now consider all intervals which are applicable for the query
            while(currentIntervalIndex < intervals.length && intervals[currentIntervalIndex][0] <= queryValue){
                queue.offer(intervals[currentIntervalIndex]);
                currentIntervalIndex++;
            }

            // Now evict any existing intervals which are NOT applicable for the current query
            while(!queue.isEmpty() && queue.peek()[1] < queryValue){
                queue.poll();
            }

             // Now the element at the top of the heap will be the ans
            int result = queue.isEmpty() ? -1: (queue.peek()[1] - queue.peek()[0])+1;
            ans[queryOrgIndex] = result;

        }

        return ans;
        
    }


  
  
}
