public class UniquePaths {
    /**
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in
     * the diagram below).
     * 
     * The robot can only move either down or right at any point in time. The robot
     * is trying to reach the bottom-right corner of the grid (marked 'Finish' in
     * the diagram below).
     * 
     * How many possible unique paths are there?
     */

    /**
     * Input: m = 3, n = 2 Output: 3
     * 
     * Explanation: From the top-left corner, there are a total of 3 ways to reach
     * the bottom-right corner:
     * 
     * 1. Right -> Right -> Down
     * 
     * 2. Right -> Down -> Right
     * 
     * 3. Down -> Right -> Right
     */

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        // Fill up the first columns with 1
        // since the total number of ways to reach any cell in the first columns is 1

        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        // Fill up the first row with 1
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}