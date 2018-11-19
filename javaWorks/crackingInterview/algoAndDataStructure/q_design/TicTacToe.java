class TicTacToe {

    int[] countOfThisRow;
    int[] countOfThisColumn;

    int size = 0;

    int diagcount = 0;
    int antiDiagCount = 0;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        countOfThisRow = new int[n];
        countOfThisColumn = new int[n];
        size = n;
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     * 
     * @param row    The row of the board.
     * @param col    The column of the board.
     * @param player The player, can be either 1 or 2.
     * @return The current winning condition, can be either: 0: No one wins. 1:
     *         Player 1 wins. 2: Player 2 wins.
     */
    public int move(int row, int col, int player) {
        int c = (player == 1 ? 1 : -1);

        countOfThisRow[row] += c;
        countOfThisColumn[col] += c;

        // Add if they are in the diagonal
        if (row == col) {
            diagcount += c;
        }

        // Add if they are in the anti diagnol
        if (col == (size - row - 1)) {
            antiDiagCount += c;
        }

        if (Math.abs(countOfThisRow[row]) == size || Math.abs(countOfThisColumn[col]) == size
                || Math.abs(diagcount) == size || Math.abs(antiDiagCount) == size) {
            return player;
        }

        return 0;
    }
}