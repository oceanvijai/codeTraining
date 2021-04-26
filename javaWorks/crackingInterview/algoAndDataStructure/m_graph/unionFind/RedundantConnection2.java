public class RedundantConnection2{
	/**

		In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus 
		every node has exactly one parent, except for the root node which has no parents.
		The given input is a directed graph that started as a rooted tree with n nodes (with distinct values from 1 to n), with 
		one additional directed edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed.

		The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [ui, vi] that represents a directed edge 
		connecting nodes ui and vi, where ui is a parent of child vi.

		Return an edge that can be removed so that the resulting graph is a rooted tree of n nodes. If there are multiple answers, return 
		the answer that occurs last in the given 2D-array.


		Input: edges = [[1,2],[1,3],[2,3]]
		Output: [2,3]

		Input: edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
		Output: [4,1]

	**/

	/**
		Approach: Union Find. Just like RedundantConnection. But the problem is a bit different here

		We need to make sure of 2 two things.
		1. All vertices have exactly one parent
		2. No cycles are formed
		3. Both

		Finding point 1 and 2 are simple.
		If both occurs at the same time, like this [[2,1],[3,1],[4,2],[1,4]], where vertex 1 has 2 parent. And edge 1,4 forms a cycle.
		Then we need to make a decesion here.

		We need to delete a edge which removes the cycle as well removes 2 parennts to a nodes. So we have the following options.
		1. delete [1,4]. It removes the cycle. But vertex 1 still has two parents because of edge [2,1] and [3,1]
		2. So we need to decide which one to delete among [2,1] and [3,1] so it also removes the cycle
		3. We dono, so we have to try both and see which works out.

		So, since [3,1] comes later in the array. Lets assume, we delete it (deleteOption2). [2,1] is deleteOption1.
		So lets not include it when we do union find.

		If all goes, well, then we know we made the right decesion and so we return it.
		If we still find a cycle, like in our case. We know we made the wrong decesion.
		So lets return deleteOption1 which is [2,1]

		If we dont have a vertex with two parents at all, then we care only about the cycle.


		Note that, this scenario can never occur. [1,2],[2,3],[3,1],[3,4],[4,5],[6,5]. Where the cycle edge is somehere 
		and the two parent vertex somewhere.
		Since, as per the problem. the graph was orginally a tree. When a new vertex got added at last. It had a cycle/two parent vertex.
		So, the edge that was added last, create the problems.

	**/


	public int[] findRedundantDirectedConnection(int[][] edges) {
        Map<Integer, Integer> parent = new HashMap<>();
        
        int[] deleteOption1 = null;
        int[] deleteOption2 = null;
        // First find if we have a node with 2 parents
        for(int[] edge: edges){
            int s = edge[0];
            int e = edge[1];
            
            if(parent.containsKey(e)){
                deleteOption1 = new int[]{parent.get(e), e}; // Edge which come earlier in the input array
                deleteOption2 = edge; // Since this comes later in the input array
                break;
            }
            
            parent.put(e,s);
        }
        
        parent = new HashMap<>();
        for(int[] edge: edges){
            int s = edge[0];
            int e = edge[1];
            
            // Try deleting deleteOption2 first, since it come later in the array
            if(deleteOption2 != null && deleteOption2[0] == s && deleteOption2[1] == e){
                continue;
            }
            
            int parentOfS = unioFind(s, parent);
            int parentOfe = unioFind(e, parent);
            
            if(parentOfS == parentOfe){
                // If we still get a cycle after deleting deleteOption2, then return the cyclic edge or deleteOption1
                return deleteOption1 != null ? deleteOption1 : edge;
            }
                        
            parent.put(parentOfe, parentOfS);
            parent.put(e, parentOfS);
        }
        
        // If not cycle was found after deleting deleteOption2, return deleteOption2
        return deleteOption2;
    }
    
    private int unioFind(int vertex, Map<Integer, Integer> parent){
        int p = parent.getOrDefault(vertex, vertex);
        
        if(p == vertex){
            return p;
        }
        
        p = unioFind(p, parent);
        parent.put(vertex, p);
        
        return p;
    }
}