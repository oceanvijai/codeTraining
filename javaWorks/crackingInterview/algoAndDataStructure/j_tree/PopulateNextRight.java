public class PopulateNextRight {

    /**
     * struct TreeLinkNode 
     * { 
     *      TreeLinkNode *left; 
     *      TreeLinkNode *right; 
     *      TreeLinkNode *next; 
     * }
     */

    /**
     * You may only use constant extra space. Recursive approach is fine, implicit
     * stack space does not count as extra space for this problem. 
     * 
     * You may assume that it is a perfect binary tree 
     * (ie, all leaves are at the same level, and every parent has two children).
     */


    /**
     * Approach: If u see visually, in a perfect binary tree, the left next is
     * always the right And the right is got by going throught the next of the
     * parent and get its left
     * 
     * this is a kind of modification of pre order travversal
     */
    
    public void connect(TreeLinkNode root) {

        if (root == null) {
            return;
        }

        if (root.left != null && root.right != null) {
            root.left.next = root.right;
        }

        if (root.right != null && root.next != null) {
            root.right.next = root.next.left;
        }

        connect(root.left);
        connect(root.right);

    }

    public void connectIteratively(TreeLinkNode root) {
        TreeLinkNode levelHead = null;

        while (root != null) {
            if (levelHead == null) {
                levelHead = root.left;
            }

            if (root.left != null && root.right != null) {
                root.left.next = root.right;
            }

            if (root.right != null && root.next != null) {
                root.right.next = root.next.left;
            }

            if (root.next != null) {
                root = root.next;
            } else {
                root = levelHead;
                levelHead = null;
            }
        }
    }
}