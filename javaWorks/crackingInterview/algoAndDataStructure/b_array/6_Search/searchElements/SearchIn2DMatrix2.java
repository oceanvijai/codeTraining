public class SearchIn2DMatrix2 {
    /**
     * Write an efficient algorithm that searches for a value in an m x n matrix.
     * This matrix has the following properties:
     * 
     * Integers in each row are sorted in ascending from left to right. Integers in
     * each column are sorted in ascending from top to bottom.
     * 
     * 
     * [
        [1,   4,  7, 11, 15],
        [2,   5,  8, 12, 19],
        [3,   6,  9, 16, 22],
        [10, 13, 14, 17, 24],
        [18, 21, 23, 26, 30]
        ]
     */
    

    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0) {
            return false;
        }
        int cols = matrix[0].length;
        if (cols == 0) {
            return false;
        }

        int r = 0;
        int c = cols - 1; // by taking the last element, we check the entire row

        while (r <= rows - 1 && c >= 0) {
            if (matrix[r][c] == target) {
                return true;
            } else if (matrix[r][c] < target) { // It is not possible in this row
                r++;
            } else {
                c--;
            }
        }

        return false;

    }
}