public class InorderTraversalIterative {
    
    /**
     * So do waht recursion does
     * Use a stack and keep looking for left
     * and push it to stack
     * 
     * If not found pop and process the root and push the right to the stack
     */
    
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;

        while (cur != null || !stack.empty()) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            list.add(cur.val);
            cur = cur.right;
        }

        return list;
    }
}