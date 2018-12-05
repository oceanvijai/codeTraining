public class RecoverBinarySearchTree {

    /**
     * Two elements of a binary search tree (BST) are swapped by mistake.
     * 
     * Recover the tree without changing its structure.
     */

    /**
     * How do we find these two elements? For example, we have the following tree
     * that is printed as in order traversal:
     * 
     * 6, 3, 4, 5, 2
     * 
     * We compare each node with its next one and we can find out that 6 is the
     * first element to swap because 6 > 3 and 2 is the second element to swap
     * because 2 < 5.
     * 
     * Really, what we are comparing is the current node and its previous node in
     * the "in order traversal".
     * 
     * Let us define three variables, firstElement, secondElement, and prevElement.
     * Now we just need to build the "do some business" logic as finding the two
     * elements. See the code below:
     */

    TreeNode first;
    TreeNode second;

    TreeNode prevElement = new TreeNode(Integer.MIN_VALUE); // prev in INORDER traversal

    public void recoverTree(TreeNode root) {
        search(root);

        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;

    }

    private void search(TreeNode root) {
        if (root == null) {
            return;
        }

        search(root.left);

        if (first == null && root.val <= prevElement.val) {
            // prev: because its most likely the larger element in the prev
            first = prevElement;
        }

        if (first != null && root.val <= prevElement.val) {
            // root: because the second one is most likely a smaller number, so it needs to
            // swaped
            second = root;
        }

        prevElement = root;

        search(root.right);

    }
}