public class CompleteBinaryTreeInserter{
	
	/**

		A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible.

		Write a data structure CBTInserter that is initialized with a complete binary tree and supports the following operations:

		CBTInserter(TreeNode root) initializes the data structure on a given tree with head node root;

		CBTInserter.insert(int v) will insert a TreeNode into the tree with value node.val = v so that the tree remains complete, and returns the value of the parent of the inserted TreeNode;

		CBTInserter.get_root() will return the head node of the tree.



		Example 1:

		Input: inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]
		Output: [null,1,[1,2]]
		Example 2:

		Input: inputs = ["CBTInserter","insert","insert","get_root"], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]
		Output: [null,3,4,[1,2,3,4,5,6,7,8]]


	**/


	/**
		In the constructor make sure, the last insertable node is at the front. All other nodes followed according to BFS

	**/



	TreeNode root = null;
    LinkedList<TreeNode> queue = new LinkedList<>();
    
    public CompleteBinaryTreeInserter(TreeNode root) {
        this.root = root;
        queue.addLast(this.root);
        
        while(!queue.isEmpty()){
            TreeNode node = queue.pollFirst();
            
            if(node.left != null){
                queue.addLast(node.left);
            }
            
            if(node.right != null){
                queue.addLast(node.right);
            }
            
            if(node.left == null || node.right == null){
                queue.addFirst(node);
                break;
            }
        }
    }
    
    public int insert(int v) {
        TreeNode currentNode = queue.peekFirst();
        //System.out.println("inserting at parent: "+currentNode.val);
        if(currentNode.left == null){
            currentNode.left = new TreeNode(v);
            queue.addLast(currentNode.left);
        }else{
            currentNode.right = new TreeNode(v);
            queue.addLast(currentNode.right);
            queue.pollFirst();
        }
        return currentNode.val;
    }
    
    public TreeNode get_root() {
        return root;
    }



}