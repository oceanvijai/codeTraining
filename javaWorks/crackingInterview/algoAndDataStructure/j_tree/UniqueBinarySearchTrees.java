public class UniqueBinarySearchTrees {

    /**
     * Given an integer n, generate all structurally unique BST's (binary search
     * trees) that store values 1 ... n.
     *  for time complexity: https://www.youtube.com/watch?v=0pTN0qzpt-Y
     *  O(Cn) - Cn - catlan number
     * Input: 3
            Output:
            [
            [1,null,3,2],
            [3,2,null,1],
            [3,1,null,null,2],
            [2,1,3],
            [1,null,2,null,3]
            ]
            Explanation:
            The above output corresponds to the 5 unique BST's shown below:

            1         3     3      2      1
                \       /     /      / \      \
                3     2     1      1   3      2
                /     /       \                 \
            2     1         2                 3
     */

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generate(1, n);
    }

    private List<TreeNode> generate(int start, int end) {
        List<TreeNode> lst = new ArrayList<>();

        if (start > end) {
            lst.add(null);
            return lst;
        }

        for (int root = start; root <= end; root++) {
            List<TreeNode> left = generate(start, root - 1);
            List<TreeNode> right = generate(root + 1, end);

            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode rootNode = new TreeNode(root);
                    rootNode.left = l;
                    rootNode.right = r;
                    lst.add(rootNode);
                }
            }
        }

        return lst;
    }
}
