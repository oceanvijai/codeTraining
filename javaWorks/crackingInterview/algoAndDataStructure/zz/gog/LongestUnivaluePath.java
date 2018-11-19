/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/**
 * Given a binary tree, find the length of the longest path where each node in
 * the path has the same value. This path may or may not pass through the root.
 * 
 * Note: The length of path between two nodes is represented by the number of
 * edges between them.
 */
class LongestUnivaluePath {
    int result = 0;

    public int longestUnivaluePath_slve(TreeNode root) {
        if (root == null) {
            return 0;
        }

        longestUnivalue(root);
        return result;
    }

    private int longestUnivalue(TreeNode root) {
        if (root == null)
            return 0;
        int left = longestUnivalue(root.left);
        int right = longestUnivalue(root.right);

        left = root.left != null && root.val == root.left.val ? left + 1 : 0;
        right = root.right != null && root.val == root.right.val ? right + 1 : 0;

        result = Math.max(left + right, result);

        return Math.max(left, right);
    }
}