public class FlowerPlantingWithNoAdjacent{
	
	/**
		You have n gardens, labeled from 1 to n, and an array paths where paths[i] = [xi, yi] describes 
		a bidirectional path between garden xi to garden yi. In each garden, you want to plant one of 4 types of flowers.

		All gardens have at most 3 paths coming into or leaving it.

		Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, 
		they have different types of flowers.

		Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)th garden. 
		The flower types are denoted 1, 2, 3, or 4. It is guaranteed an answer exists.




		Example 1:
		Input: n = 3, paths = [[1,2],[2,3],[3,1]]
		Output: [1,2,3]
		Explanation:
		Gardens 1 and 2 have different types.
		Gardens 2 and 3 have different types.
		Gardens 3 and 1 have different types.
		Hence, [1,2,3] is a valid answer. Other valid answers include [1,2,4], [1,4,2], and [3,2,1].
		
		Example 2:
		Input: n = 4, paths = [[1,2],[3,4]]
		Output: [1,2,1,2]
		

		Example 3:
		Input: n = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
		Output: [1,2,3,4]

	**/

	/**
		Approach: Intutively we can say this is a natural fit for BFS. More sepcifically bipartate only with 4 colors

		But we have an issue. A given node/garden can 4 possible flowers. We need to try each one of them and if not 
		possible, we have to backtrack.
		But this will be exponential. So lets use some of the properties mentioned above.

		From the above statement, we know an answer is always available. So we can try Greedy approach and has a set
		assigned to each garden, which says, which are the flowers we cannot asign to that garden.

		So when we do BFS, we pick a flower greedily based o its set and update its child/edges set to make sure the 
		flower taken by the current node/garden is not pickedup by its child.

	**/


	public int[] gardenNoAdj(int n, int[][] paths) {
        // Build the graph
        List<Integer>[] graph = new ArrayList[n+1];
        for(int[] path : paths){
            if(graph[path[0]] == null){
                graph[path[0]] = new ArrayList<>();
            }
            graph[path[0]].add(path[1]);
            
            if(graph[path[1]] == null){
                graph[path[1]] = new ArrayList<>();
            }
            graph[path[1]].add(path[0]);
        }
        
        // Do BFS and assign the values available for each node
        Set<Integer>[] notAvailableValues = new HashSet[n+1];
        boolean[] seen = new boolean[n+1];
        int[] values = new int[n+1];
        
        for(int i = 1; i < n+1; i++){
            if(seen[i]){
                continue;
            }
            
            LinkedList<Integer> queue = new LinkedList<>();
            queue.addFirst(i);
            while(!queue.isEmpty()){
                int garden = queue.pollFirst();
                seen[garden] = true;
                // Based on its set, find which flower it can pick greedily
                int possibleValue = getPossibleValue(garden,notAvailableValues); 
                values[garden] = possibleValue;
                
                List<Integer> edges = graph[garden];
                if(edges != null){
                    for(Integer edge : edges){
                        if(!seen[edge]){
                            queue.addLast(edge);

                            // Not update the edges set to include the flower of the parent, so it wont be picked up
                            if(notAvailableValues[edge] == null){
                                notAvailableValues[edge] = new HashSet<Integer>();
                            }
                            
                            notAvailableValues[edge].add(possibleValue);
                        }
                    }
                }
            }
        }
        
        // Collect the answer
        return Arrays.copyOfRange(values, 1, n + 1);
    }
    
    private int getPossibleValue(int garden, Set<Integer>[] notAvailableValues){
        if(notAvailableValues[garden] == null){
            notAvailableValues[garden] = new HashSet<Integer>();
        }
        
        for(int i = 1; i <=4; i++){
            if(!notAvailableValues[garden].contains(i)){
                return i;
            }
        }
        
        return 0;
    }

}