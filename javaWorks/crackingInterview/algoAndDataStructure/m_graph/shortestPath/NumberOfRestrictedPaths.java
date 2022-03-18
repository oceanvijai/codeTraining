public class NumberOfRestrictedPaths{
	
	/**
		There is an undirected weighted connected graph. You are given a positive integer n which denotes that the graph has n nodes labeled from 1 to n, and an array edges where each edges[i] = [ui, vi, weighti] denotes that there is an edge between nodes ui and vi with weight equal to weighti.

		A path from node start to node end is a sequence of nodes [z0, z1, z2, ..., zk] such that z0 = start and zk = end 
		and there is an edge between zi and zi+1 where 0 <= i <= k-1.

		The distance of a path is the sum of the weights on the edges of the path. Let distanceToLastNode(x) denote the shortest 
		distance of a path between node n and node x. A restricted path is a path that also satisfies that distanceToLastNode(zi) > distanceToLastNode(zi+1) where 0 <= i <= k-1.

		Return the number of restricted paths from node 1 to node n. Since that number may be too large, return it modulo 109 + 7.


		Example 1:
		Input: n = 5, edges = [[1,2,3],[1,3,3],[2,3,1],[1,4,2],[5,2,2],[3,5,1],[5,4,10]]
		Output: 3
		Explanation: Each circle contains the node number in black and its distanceToLastNode value in blue. The three restricted paths are:
		1) 1 --> 2 --> 5
		2) 1 --> 2 --> 3 --> 5
		3) 1 --> 3 --> 5

		Example 2:
		Input: n = 7, edges = [[1,3,1],[4,1,2],[7,3,4],[2,5,3],[5,6,1],[6,7,2],[7,5,3],[2,6,4]]
		Output: 1
		Explanation: Each circle contains the node number in black and its distanceToLastNode value in blue. The only restricted path is 1 --> 3 --> 7.

	**/


	/**
		Approach : Use can instead start from node n and do dijkstra.
		That way we find shortest path from all the nodes

		The we do a DFS shuch that the next node is smaller.
		Also we memorise so we can use it for the next time


	**/

	public int countRestrictedPaths(int n, int[][] edges) {
        // Build the graph
        List<int[]>[] graph = new ArrayList[n+1];
        for(int[] edge : edges){
            int s = edge[0];
            int d = edge[1];
            int weight = edge[2];
            
            if(graph[s] == null){
                graph[s] = new ArrayList<>();
            }
            
            if(graph[d] == null){
                graph[d] = new ArrayList<>();
            }
            
            graph[s].add(new int[]{d, weight});
            graph[d].add(new int[]{s, weight});
        }
        
        // Do dijkstra and get the distance matrix
        int[] distance = dijkstra(n,graph);
        
        // Now we can DFS + DP
        // We start with 1 and find all path to n
         int[] dp = new int[n+1];
        for(int i = 0; i <= n; i++) dp[i] = -1;
        
        int count = DFS(1,n, graph, dp, distance);
        return count;
    }
    
    private int[] dijkstra(int n, List<int[]>[] graph){
        int[] distance = new int[n+1];
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((a,b)->(a[1]-b[1]));
        for(int i = 1; i < n; i++) distance[i] = 1000001;
        
        heap.offer(new int[]{n, 0});
        Set<Integer> visited = new HashSet<>();
        while(heap.size() > 0){
            int[] c = heap.poll();
            int node = c[0];
            int weight = c[1];
            if(visited.contains(node)){
                continue;
            }
            
            distance[node] = weight;
            visited.add(node);
            
            if(graph[node] != null){
                for(int[] edge : graph[node]){
                    int e = edge[0], w = edge[1];
                    if(!visited.contains(e)){
                        heap.offer(new int[]{e, weight+w});
                    }
                }
            }
        }
        
        return distance;
    }
    
    private int DFS(int node, int n, List<int[]>[] graph, int[] dp, int[] distance){
        if(node == n){
            return 1;
        }
        
        if(dp[node] != -1){
            return dp[node];
        }
        
        int count = 0;
        if(graph[node] != null){
            for(int[] edge : graph[node]){
                if(distance[node] > distance[edge[0]]){
                    count = (count + DFS(edge[0],n, graph, dp, distance)) % 1_000_000_007;
                }
            }
        }
        
        dp[node] = count;
        return count;
    }
}