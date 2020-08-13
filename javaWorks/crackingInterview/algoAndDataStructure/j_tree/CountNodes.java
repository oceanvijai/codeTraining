class CountNodes  {


    /** 
    Given a complete binary tree, count the number of nodes.

    In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
    **/

    /**
        If the tree is balanced then the size is (2^h)-1  where h is the height
        If the tree is not balanced, go to the sub nodes and check if its balanced and if not add one and go down the nodes
    
    **/

    public int Solution(TreeNode root) {
        
        int leftHeight = leftHeight(root);
        int rightHeight = rightHeight(root);
        
        if(leftHeight == rightHeight){
            return (1 << leftHeight) -1;
        }
        
        return 1 + countNodes(root.left) + countNodes(root.right);
        
    }
    
    private int leftHeight(TreeNode root){
        if(root == null){
            return 0;
        }
        return 1 + leftHeight(root.left);
    }
    
    private int rightHeight(TreeNode root){
        if(root == null){
            return 0;
        }
        return 1 + rightHeight(root.right);
    }
}