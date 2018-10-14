public class SpiralOrderMatrix {

    // Given an integer n, generate a square matrix filled with elements from 1 to
    // n2 in spiral order.

    /**
     * 
        [
            [ 1, 2, 3 ],
            [ 8, 9, 4 ],
            [ 7, 6, 5 ]
        ]
     */

    public class Solution {
        public ArrayList<ArrayList<Integer>> generateMatrix(int A) {

            int[][] arr = new int[A][A];

            int t = 0;
            int l = 0;
            int r = A - 1;
            int b = A - 1;
            int count = 0;
            while (l <= r && t <= b) {
                for (int i = l; i <= r; i++) {
                    arr[t][i] = ++count;
                }
                t++;

                for (int i = t; i <= b; i++) {
                    arr[i][r] = ++count;
                }
                r--;

                for (int i = r; i >= l; i--) {
                    arr[b][i] = ++count;
                }
                b--;

                for (int i = b; i >= t; i--) {
                    arr[i][l] = ++count;
                }
                l++;
            }

            ArrayList<ArrayList<Integer>> result = new ArrayList<>();

            if (A == 1) {
                ArrayList<Integer> row = new ArrayList(A - 1);
                row.add(1);
                result.add(row);
                return result;
            }

            for (int i = 0; i <= A - 1; i++) {
                ArrayList<Integer> row = new ArrayList<>();
                for (int j = 0; j <= A - 1; j++) {
                    row.add(arr[i][j]);
                }
                result.add(row);
            }

            return result;
        }
    }
}