public class CommonAncestor {

    // Case involved here
    /**
     * 1: If the current subtree has either one of the nodes, set it appotiate
     * boolean in the result
     * 
     * 2. If the current node has one result on the left and one on the right, then
     * the current is ansestor 3. else return
     * 
     */

    private class Node {
        public Node left;
        public Node right;
    }

    public Node firstCommonAncestorWithoutParent(Node root, Node p, Node q) {
        if (root == null || p == null || q == null) {
            return null;
        }

        return findCommonAncestor(root, p, q).ancestor;
    }

    private Result findCommonAncestor(Node root, Node p, Node q) {
        if (node == null) {
            return new Result(null, false);
        }

        Result l = findCommonAncestor(root, p, q);
        if (l.isAncestor) {
            return l;
        }

        Result r = findCommonAncestor(root, p, q);
        if (r.isAncestor) {
            return l;
        }

        if (l.node != null && r.node != null) {
            return new Result(root, true);
        } else if (root == p || root == q) {
            // here there are two case, one if found only one or found both
            boolean isAncestor = r.node != null || l.node != null;
            return new Result(root, isAncestor);
        } else {
            // else we need to atleast propogate back what we found so far
            return new Result(r.node != null ? r.node : l.node, false);
        }

    }

    private class Result {
        public Node node;
        public boolean isAncestor;

        public Result(Node ancestor, boolean isAncestor) {
            this.ancestor = ancestor;
            this.isAncestor = isAncestor;
        }
    }

    // Simpler

    private boolean foundBoth = false;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root.val == p.val || root.val == q.val) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        if (left != null && foundBoth) {
            return left;
        }
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            foundBoth = true;
            return root;
        }

        return left != null ? left : right;

    }

}