public class SubtreeofAnotherTree {
    /**
     * Given two non-empty binary trees s and t, check whether tree t has exactly
     * the same structure and node values with a subtree of s.
     * 
     * A subtree of s is a tree consists of a node in s and all of this node's
     * descendants. The tree s could also be considered as a subtree of itself.
     */

    public boolean isSubtree(TreeNode s, TreeNode t) {

        StringBuilder sb = new StringBuilder("");

        toPreOrder(s, sb);
        String bigString = sb.toString();
        System.out.println(bigString);

        sb = new StringBuilder("");
        toPreOrder(t, sb);
        String smallString = sb.toString();
        System.out.println(smallString);

        return bigString.contains(smallString);

    }

    private void toPreOrder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(",X");
            return;
        }

        sb.append("," + root.val);
        toPreOrder(root.left, sb);
        toPreOrder(root.right, sb);

    }
}