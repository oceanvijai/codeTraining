public class FlattenToLinkedList {

    /**
     * Approach one
     * 
     * Get the left tree flatern it first
     * 
     * Then store the right tree and set the root.left = null and root.right =
     * lefttree
     * 
     * Then go to the left of the current right tree (previously left) and
     * 
     * Add the stored right to the right of the left
     * 
     * then do the same for the unprocessed right tree
     */

    public TreeNode flatten(TreeNode a) {
        if (a == null) {
            return null;
        }

        TreeNode leftTree = null;
        if (a.left != null) {
            leftTree = flatten(a.left);
        }

        TreeNode rightTree = a.right; // backup

        if (leftTree != null) {
            a.left = null;
            a.right = leftTree;

            TreeNode c = leftTree;
            while (c.right != null) {
                c = c.right;
            }

            c.right = rightTree; // attach at the end of the left tree
        }

        // now do the same for the right tree

        flatten(rightTree);

        return a;
    }

    /**
     * Approach two - optimized
     * 
     * 
     * Store take the root and store first its right ans then the stack
     * 
     * Now if we peek, we will get the left of the tree Now, make the root.left =
     * null and root.right = the left (peeked one)
     * 
     * Now pop the head of the stack and do the same, eventually we will be adding
     * the right to the last left
     */

    public TreeNode flatten(TreeNode a) {

        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.addFirst(a);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollFirst();

            if (node.right != null) {
                stack.addFirst(node.right);
            }
            if (node.left != null) {
                stack.addFirst(node.left);
            }

            TreeNode peek = stack.peekFirst();
            node.left = null;
            node.right = peek;

        }

        return a;
    }
}