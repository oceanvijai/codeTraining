public class RedundantConnection{
	/**
		In this problem, a tree is an undirected graph that is connected and has no cycles.

		The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

		The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.

		Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.


		Example 1:
		Input: [[1,2], [1,3], [2,3]]
		Output: [2,3]
		Explanation: The given undirected graph will be like this:
		  1
		 / \
		2 - 3
		Example 2:
		Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
		Output: [1,4]
		Explanation: The given undirected graph will be like this:
		5 - 1 - 2
		    |   |
		    4 - 3

	**/



	public int[] findRedundantConnection(int[][] edges) {
        Map<Integer,Integer> parentMap = new HashMap<>();
        Map<Integer,Integer> rankMap = new HashMap<>();
        
        for(int[] edge : edges){
            int u = edge[0], v = edge[1];
            
            int parentOfU = unionFind(u, parentMap);
            int parentOfv = unionFind(v, parentMap);
            
            if(parentOfU == parentOfv){
                return edge;
            }
            
            if(rankMap.getOrDefault(parentOfU, 0) >= rankMap.getOrDefault(parentOfv, 0)){
                rankMap.put(parentOfU, rankMap.getOrDefault(parentOfU, 0)+1);
                parentMap.put(parentOfv,parentOfU);
            }else{
                rankMap.put(parentOfv, rankMap.getOrDefault(parentOfv, 0)+1);
                parentMap.put(parentOfU,parentOfv);
            }
        }
        
        return null;
    }
    
    private int unionFind(int node, Map<Integer,Integer> parentMap){
        int parent = parentMap.getOrDefault(node, node);
        if(node == parent){
            return node;
        }
        
        int p = unionFind(parent, parentMap);
        parentMap.put(node, p);
        
        return p;
    }
}