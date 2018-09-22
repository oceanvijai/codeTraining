public class SuccessorNode {

    /**
     * Write an algorithm to find the "next" node (i.e., in-order successor) of a
     * given node in a binary search tree. You may assume that each node has a link
     * to its parent.
     */

    /**
     * The difficulty in this problem is as follows, let say, the node we want to
     * find is n lets say its parent is p our answer, the successor is x First, lets
     * say we find the n in 3 positions n is left of p n as root n somewhere right
     * subtree of p
     */

    /**
     * First case: n is left of p this is easy we just need to return p as answer
     */

    /**
     * scond case: n as root this is also fine, we just need to go to the right of
     * n, and find the left most node
     */

    /**
     * third case: n somewhere right subtree of p this is tricky but easy. If we are
     * to the right of p, then we need to return p's parent if p's parent is the
     * left of of its parent. If p's parent is right of ite parent, then again go up
     * on level and do the same check again
     */

    private class Node {
        public Node left;
        public Node right;
        public Node parent;
    }

    public Node findSuccessor(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) { // case 2
            return getLeftMost(node);
        }

        // node.parent != null is for both case 1 and 3
        // node.parent.right takes care of returning the cade for case 1 and for case 3
        // it return
        // the unprocessed super parent
        while (node.parent != null && node.parent.right == node) {
            node = node.parent;
        }
        return node.parent;
    }

    private Node getLeftMost(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}