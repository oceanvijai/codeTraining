public class UniqueBinarySearchTrees {

    /**
     * Given n, how many structurally unique BST's (binary search trees) that store
     * values 1 ... n?
     * 
     * https://www.youtube.com/watch?v=0pTN0qzpt-Y
     * 
     * 
     * Input: 3
        Output: 5
        Explanation:
        Given n = 3, there are a total of 5 unique BST's:

        1         3     3      2      1
            \       /     /      / \      \
            3     2     1      1   3      2
            /     /       \                 \
        2     1         2                 3
     */



    /**
     * Approach: We are basically doing,
     * 
     * lets say for any value of n we make each element from 1 to n as root and
     * calculate its combination with the left and right subtrees count.
     * For more details : https://www.youtube.com/watch?v=0pTN0qzpt-Y
     * basically its a catlan number
     */

    public int numTrees(int n) {

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int root = 1; root <= i; root++) {
                dp[i] += dp[root - 1] * dp[i - root];
            }
        }

        return dp[n];
    }

}
