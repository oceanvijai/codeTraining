public class ValidateBinaryTree{
	/**
		You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i], return true if and only if all the given nodes form exactly one valid binary tree.

		If node i has no left child then leftChild[i] will equal -1, similarly for the right child.

		Note that the nodes have no values and that we only use the node numbers in this problem.


		Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
		Output: true

		Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
		Output: false

		Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
		Output: false

		Input: n = 6, leftChild = [1,-1,-1,4,-1,-1], rightChild = [2,-1,-1,5,-1,-1]
		Output: false

	**/


	/**
		Approach: DFS

		This can be done in many ways.
		With DFS, we need to confirm the following things,

		1. Only one root needs to be there, meaning all except one node will not have a parent
			- Two seperate connected components will have two differrent roots
		2. A node can have only one parent


	**/




	public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        // Find the root
        Set<Integer> nodes = new HashSet<>();
        for(int i = 0; i < n; i++){
            nodes.add(i);
        }
        
        // Root is when its not child of any node
        for(int i = 0; i < n; i++){
            nodes.remove(leftChild[i]);
            nodes.remove(rightChild[i]);
        }
        
        if(nodes.size() != 1){
            return false;
        }
        
        int root = -1;
        for(int i = 0; i < n; i++){
            if(nodes.contains(i)){
                root = i;
                break;
            }
        }
        
        // Do DFS from the root
        boolean[] seen = new boolean[n];
        boolean result = DFS(root,seen,leftChild,rightChild);
        if(!result){
            return false;
        }
            
        int count = 0;
        for(int i = 0; i < n; i++){
            if(!seen[i]){
                count++;
                if(count > 1){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean DFS(int node, boolean[] seen, int[] leftChild, int[] rightChild){
        if(node == -1){
            return true;
        }
        
        if(seen[node]){
            return false;
        }
        
        seen[node] = true;
        
        return DFS(leftChild[node], seen, leftChild, rightChild) &&
                DFS(rightChild[node], seen, leftChild, rightChild);
    }
}