public class NQueen {
    
    ArrayList<ArrayList<String>> ans = new ArrayList<>();
    public ArrayList<ArrayList<String>> solveNQueens(int a) {

        if (a <= 0) {
            return ans;
        }

        int[] queenColumn = new int[a]; // The index it the row and value is the column
        solve(0, queenColumn, a);
        return ans;
    }

    private void solve(int kthQueen, int[] queenColumn, int n) {
        if (kthQueen == n) {
            addResults(queenColumn);
            return;
        } else {
            for (int col = 0; col < n; col++) {
                if (isPossible(kthQueen, col, queenColumn)) {
                    queenColumn[kthQueen] = col;
                    solve(kthQueen + 1, queenColumn, n);
                    queenColumn[kthQueen] = 0;
                }
            }
        }
    }

    private boolean isPossible(int row, int column, int[] queenColumn) {
        // System.out.println("check of: "+row+" col: "+column);
        for (int r = 0; r < row; r++) {
            // check if present in the same column
            if (queenColumn[r] == column) {
                return false;
            }

            // check in diagonals
            int colDiff = Math.abs(queenColumn[r] - column);
            int rowDiff = Math.abs(r - row);
            // meaning they are in any diagonal
            if (colDiff == rowDiff) {
                return false;
            }

        }

        return true;
    }

    private void addResults(int[] tempCol) {
        ArrayList<String> res = new ArrayList<String>();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tempCol.length; i++) {
            int pos = tempCol[i];
            for (int j = 0; j < tempCol.length; j++) {
                if (pos == j) {
                    builder.append('Q');
                } else {
                    builder.append('.');
                }
            }
            if (i != tempCol.length - 1) {
                builder.append(' ');
            }
        }
        res.add(builder.toString());
        ans.add(res);
    }
}