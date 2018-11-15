public class InorderSuccessor {

    // successor we search root or its right 
    // follow up: predessor we search root or left

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;
        while(curr!= null || !stack.isEmpty()){
            while(curr != null){
                stack.addFirst(curr);
                curr = curr.left;
            }
            
            TreeNode node = stack.pollFirst();
            if(node.val == p.val){
                if(node.right == null){
                    return stack.peekFirst();
                }else{
                    return findSuccessor(node.right);
                }
                
            }
            
            curr = node.right;
        }
        return null;
    }
    
    private TreeNode findSuccessor(TreeNode root){
        while(root.left != null){
                root = root.left;
        }
        
        return root;
    }

}