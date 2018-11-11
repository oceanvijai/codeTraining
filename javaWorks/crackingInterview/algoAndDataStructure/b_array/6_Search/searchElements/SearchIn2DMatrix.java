public class SearchIn2DMatrix {

    /**
     * Write an efficient algorithm that searches for a value in an m x n matrix.
     * This matrix has the following properties:
     * 
     * Integers in each row are sorted from left to right. The first integer of each
     * row is greater than the last integer of the previous row.
     */

    public boolean searchMatrix(int[][] matrix, int target) {
        int r = matrix.length;
        if (r == 0) {
            return false;
        }
        int c = matrix[0].length;
        if (c == 0) {
            return false;
        }

        int start = 0;
        int end = r - 1;
        int mid = start;

        while (start <= end) {
            mid = (start + end) / 2;

            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] < target) {
                if (matrix[mid][c - 1] >= target) {
                    break;
                } else {
                    start = mid + 1;
                }
            } else {
                end = mid - 1;
            }
        }

        //System.out.println(mid);

        int[] row = matrix[mid];
        start = 0;
        end = c - 1;

        while (start <= end) {
            mid = (start + end) / 2;

            if (row[mid] == target) {
                return true;
            } else if (row[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }

        }

        return false;
    }
}