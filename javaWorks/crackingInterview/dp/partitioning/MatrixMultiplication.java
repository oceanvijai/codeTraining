public class MatrixMultiplication {

    /**
     * Given a sequence of matrices, find the most efficient way to multiply these
     * matrices together. The problem is not actually to perform the
     * multiplications, but merely to decide in which order to perform the
     * multiplications.
     */

    /**
     * if we had four matrices A, B, C, and D, we would have (ABC)D = (AB)(CD) =
     * A(BCD) = ....
     * 
     * For example, suppose A is a 10 × 30 matrix, B is a 30 × 5 matrix, and C is a
     * 5 × 60 matrix. Then, (AB)C = (10×30×5) + (10×5×60) = 1500 + 3000 = 4500
     * operations A(BC) = (30×5×60) + (10×30×60) = 9000 + 18000 = 27000 operations.
     */

    private static int solve(int arr[]) {

        int n = arr.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }

        for (int L = 1; L < n; L++) {
            for (int i = 0; i < n - L - 1; i++) {
                int j = i + L;
                int val = dp[i][j - 1] + arr[i] * arr[j] * arr[j + 1];
                dp[i][j] = val;
            }
        }

        // printDP(dp);

        int[] part = new int[n];
        int[][] bracket = new int[n][n];

        for (int i = 1; i < n - 1; i++) {
            part[i] = dp[0][i];
            bracket[0][i] = i; // assuming we can take the full
            for (int j = 0; j <= i - 1; j++) {
                int val = part[j] + dp[j + 1][i] + arr[0] * arr[j + 1] * arr[i + 1];
                if (val < part[i]) {
                    part[i] = val;

                    bracket[0][i] = j;
                    bracket[j+1][i] = i; // where we made the partition
                }
            }
        }

        /*
         * for (int i = 0; i < n; i++) { System.out.print(part[i]);
         * System.out.print(", "); } System.out.println("");
         */
        printDP(bracket);

        String ans = printBracket(bracket,0,n-2);
        System.out.println(ans);

        return part[n - 2];
    }


    public static void main(String[] args) {
        // {4,2,3,1,3} -> 26 -> ((A(BC))D)
        // {1,2,3,4,3} -> 30 -> (((AB)C)D)
        // {1,2,3} -> 6 > (AB)
        int[] arr = {1,2,3};
        System.out.println(solve(arr));
    }

    private static String printBracket(int[][] bracket, int start, int end) {
       if(start == end){
           //char c = (char)65 + start;
           // return String.valueOf(c);
           return Character.toString((char) (65+start));
       }

       if(start > end){
        return "";
       }

       String str = "(";

       int partionIndex = bracket[start][end];
       if(partionIndex == end){
        str = str + printBracket(bracket, start, partionIndex-1);
        str = str + printBracket(bracket, partionIndex, end);
       }else{
        str = str + printBracket(bracket, start, partionIndex);
        str = str + printBracket(bracket, partionIndex+1, end);
       }
       
       str = str+")";
       return str;
    }

    private static void printDP(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                System.out.print(dp[i][j]);
                System.out.print("   ");
            }
            System.out.println("");
        }
    }

}