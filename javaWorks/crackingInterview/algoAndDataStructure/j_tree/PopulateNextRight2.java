public class PopulateNextRight2 {
    
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
     * This is not a perfect binary tree
     */

    /**
     * Approach: If u see visually, We have to join the next pointers of next level
     * before going to the next level We also, have to start connecting from the
     * right, since we need to find the next right for the nodes in the left
     * 
     * So first we do for the next and then left and right
     * 
     * this is a kind of modification of pre order travversal
     */

    public void connectRecursive(TreeLinkNode root) {
        if (root == null) {
            return;
        }

        if (root.next != null) {
            connect(root.next);
        }

        if (root.left != null) {
            if (root.right != null) {
                root.left.next = root.right;
                root.right.next = getNextRight(root.next);
            } else {
                root.left.next = getNextRight(root.next);
            }
        } else if (root.right != null) {
            root.right.next = getNextRight(root.next);
        }

        connect(root.left);
        connect(root.right);
    }

    private TreeLinkNode getNextRight(TreeLinkNode root) {

        TreeLinkNode t = root;

        while (root != null) {
            if (root.left != null) {
                return root.left;
            }
            if (root.right != null) {
                return root.right;
            }
            root = root.next;
        }

        return null;
    }

    // Itertatively - use levelhead like in the linkedlist dummy head
    public void connectIteratively(TreeLinkNode root) {
        TreeLinkNode levelHead = new TreeLinkNode(-1);
        TreeLinkNode lvlPointer = levelHead;

        while (root != null) {
            if (root.left != null) {
                lvlPointer.next = root.left;
                lvlPointer = lvlPointer.next;
            }

            if (root.right != null) {
                lvlPointer.next = root.right;
                lvlPointer = lvlPointer.next;
            }

            if (root.next != null) {
                root = root.next;
            } else {
                root = levelHead.next;
                levelHead.next = null; // reset
                lvlPointer = levelHead; // start fresh again
            }
        }
    }
}