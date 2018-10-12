public class MatrixMultiplication{

    /**
     * Given a sequence of matrices, find the most efficient way to multiply these matrices together. 
     * The problem is not actually to perform the multiplications, 
     * but merely to decide in which order to perform the multiplications.
     */

     /**
      * if we had four matrices A, B, C, and D, we would have
      * (ABC)D = (AB)(CD) = A(BCD) = ....
      * 
      * For example, suppose A is a 10 × 30 matrix, B is a 30 × 5 matrix, and C is a 5 × 60 matrix. Then,
      * (AB)C = (10×30×5) + (10×5×60) = 1500 + 3000 = 4500 operations
      *  A(BC) = (30×5×60) + (10×30×60) = 9000 + 18000 = 27000 operations.
      */

      



}