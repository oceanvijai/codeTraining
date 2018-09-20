public class RotateMatrix{

    // For NxN matrix of pixels where each pixel is of 4 bytes, 
    // write a method to rotate the matrix by 90 degrees.

    public void rotate(int[][] matrix){

        int rows = matrix.length-1;
        int columns = rows;

        for(int i = 0 ; i < rows / 2; i++){
            for(int j = i ; j < columns-i; j++){
                int top = matrix[i][columns-j]; // top right
                int right = matrix[columns-j][rows - i]; 
                int bottom = matrix[row -i][j];
                int left = matrix[i][j];
                
                
                matrix[i][columns -j] = left; // left -> top
                matrix[columns-j][rows - i] = top; // top -> right
                matrix[row -i][j] = right; // right -> bottom
                matrix[i][j] = bottom; // bottom -> left
            }
        }
    }
}