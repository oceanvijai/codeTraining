public class MinCostConnect{
	
	/**

		You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].

		The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, 
		where |val| denotes the absolute value of val.

		Return the minimum cost to make all points connected. All points are connected if there is exactly 
		one simple path between any two points.


		Example 1:
		Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
		Output: 20

		

		Example 2:

		Input: points = [[3,12],[-2,5],[-4,1]]
		Output: 18
		Example 3:

		Input: points = [[0,0],[1,1],[1,0],[-1,1]]
		Output: 4
		Example 4:

		Input: points = [[-1000000,-1000000],[1000000,1000000]]
		Output: 4000000

	**/



	public int minCostConnectPoints(int[][] points) {
        int ans = 0, n = points.length;
        Set<Integer> visitedSet = new HashSet<>();
        int[] distance = new int[n];
        
        // Initilize all distance from the point1
        for(int i =1; i < n; i++){
            distance[i] = cost(points, 0, i);
        }
        
        visitedSet.add(0);
        int next = getNextMin(visitedSet, distance);
        // try until all vertices are added to the visitedSet
        while(next != -1){
            // relax distace from the "next" point
            for(int i = 0; i < n; i++){
                if(i == next || visitedSet.contains(i)){
                    continue;
                }
                
                distance[i] = Math.min(distance[i], cost(points, next, i));
            }
            
            // Add the visted node to the cost
            ans += distance[next];
            visitedSet.add(next);
            // get the next node to vist
            next = getNextMin(visitedSet, distance);
        }
        
        return ans;
    }
    
    private int getNextMin(Set<Integer> visitedSet, int[] distance){
        int nextMin = -1;
        for(int i = 0; i < distance.length; i++){
            if(visitedSet.contains(i)){
                continue;
            }
            
            if(nextMin == -1){
                nextMin = i;
            }else{
                nextMin = distance[nextMin] < distance[i] ? nextMin : i;
            }
        }
        
        return nextMin;
    }
    
    private int cost(int[][] points, int a, int b) {
        return Math.abs(points[a][0] - points[b][0]) + Math.abs(points[a][1] - points[b][1]);
    }

}