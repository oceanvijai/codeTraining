public class SumRoot2LeafNumbers {
    /**
     * Given a binary tree containing digits from 0-9 only, each root-to-leaf path
     * could represent a number.
     * 
     * An example is the root-to-leaf path 1->2->3 which represents the number 123.
     * 
     * Find the total sum of all root-to-leaf numbers.
     * 
     * Note: A leaf is a node with no children.
     */

    public int sumNumbers(TreeNode root) {
        return sum(root, 0);
    }

    private int sum(TreeNode root, int currentSum) {
        if (root == null) {
            return currentSum;
        }

        int val = currentSum * 10 + root.val; // sum at this level

        if (root.left != null && root.right != null) {
            return sum(root.left, val) + sum(root.right, val);
        }
        if (root.left == null && root.right == null) {
            return val;
        } else if (root.left != null) {
            return sum(root.left, val);
        } else {
            return sum(root.right, val);
        }

    }
}