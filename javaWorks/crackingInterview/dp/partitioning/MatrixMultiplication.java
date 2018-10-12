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

    private static int minCost(int[] arr){
        int n = arr.length;
        int[][] dp = new int[n][n];

        for(int i = 0; i < n; i++){
            dp[i][i] = 0;
        }

        for(int i = 0; i < n-1; i++){
            dp[i][i+1] = arr[i] * arr[i+1] * arr[i+2];
        }
        
        for(int L = 1; L < n; L++){
            for(int i = 0; i < n-L; i++){
                int j = i + L;
                int val = dp[i-1] 
            }
        }

        return 0;
    }


    public static void main(String[] args) {
        String str = "ababa";
        System.out.println(minCut(str));
    }



}