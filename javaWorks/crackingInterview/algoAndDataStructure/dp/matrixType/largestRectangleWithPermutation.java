public class largestRectangleWithPermutation {

    // permutation of column is allowed
    // Approach is simple

    // Since column is allowed to permutate we can pre-process the matrix
    // make a dp matrix and see how many continious 1's are there at/till each level

    // Then sort each row in the DP
    // then find the largest rectangle by using the same techinque for stack and
    // queue histogram rectangle

    public int solve(ArrayList<ArrayList<Integer>> A) {
        int row = A.size();
        int col = A.get(0).size();
        int[][] dp = new int[row][col];

        for (int c = 0; c < col; c++) {
            dp[0][c] = A.get(0).get(c);
        }


        // Pre populate the dp
        for (int r = 1; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (A.get(r).get(c) == 1) {
                    dp[r][c] = dp[r - 1][c] + 1;
                }
            }
        }

        // get the max rectangle every row
        int result = 0;
        for (int r = 0; r < row; r++) {
            int sum = maxSumOnRow(dp[r]);
            if (sum > result) {
                result = sum;
            }
        }

        return result;

    }

    private int maxSumOnRow(int[] dp) {

        ArrayList<Integer> list = new ArrayList<>();
        for (int i : dp) {
            list.add(i);
        }

        Collections.sort(list, Collections.reverseOrder());
        int maxConSum = -1;

        for (int i = 0; i < list.size(); i++) {
            int sum = list.get(i) * (i + 1);

            if (maxConSum < sum) {
                maxConSum = sum;
            }
        }

        return maxConSum;
    }

}