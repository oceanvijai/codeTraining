public class MinimumHeightTrees{

	/**
	

	A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.

	Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).

	Return a list of all MHTs' root labels. You can return the answer in any order.

	The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.			


	https://leetcode.com/problems/minimum-height-trees/



	**/

	/**
		Approach: 
		The first intution behind is, there can be either one or two root/centroids which has the min heigh
		// because, if there is 3, then the mid one in the 3 will be the ans

		So to get the mid root(s), start deleting the leafs until you have 1 or 2 nodes left which will be the mid


	**/
	


	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(edges.length == 0){
            List<Integer> ans = new ArrayList<>();
            ans.add(0);
            return ans;
        }
        
        Map<Integer, Set<Integer>> nodes = new HashMap<>();
        
        // Build the graph
        for(int[] edge : edges){
            Set<Integer> neighbours = nodes.getOrDefault(edge[0],new HashSet<Integer>());
            neighbours.add(edge[1]);
            nodes.put(edge[0], neighbours);
            
            neighbours = nodes.getOrDefault(edge[1],new HashSet<Integer>());
            neighbours.add(edge[0]);
            nodes.put(edge[1], neighbours);
        }
        
        // Find the leaf nodes
        List<Integer> leafs = new ArrayList<>();
        for(Map.Entry<Integer, Set<Integer>> node: nodes.entrySet()){
            if(node.getValue().size() == 1){
                leafs.add(node.getKey());
            }
        }
        
        // start triming
        List<Integer> tmp = new ArrayList<>(); 
        while(nodes.size() > 2){
            for(Integer leaf : leafs){
                // remove from parent
                Integer parent = nodes.get(leaf).stream().findFirst().get();
                nodes.get(parent).remove(leaf);
                // If parent became a leaf save it
                if(nodes.get(parent).size() == 1){
                    tmp.add(parent);
                }
                
                // remove leaf
                nodes.remove(leaf);
            }
            
            leafs = tmp;
            tmp = new ArrayList<Integer>();
        }
        
        List<Integer> ans = new ArrayList<>();
        for(Map.Entry<Integer, Set<Integer>> node: nodes.entrySet()){
            ans.add(node.getKey());
        }
        return ans;
    }
}




