public class Minesweeper {

    /**
     * 
     * You are given a 2D char matrix representing the game board. 'M' represents an
     * unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a
     * revealed blank square that has no adjacent (above, below, left, right, and
     * all 4 diagonals) mines, digit ('1' to '8') represents how many mines are
     * adjacent to this revealed square, and finally 'X' represents a revealed mine.
     * 
     * Now given the next click position (row and column indices) among all the
     * unrevealed squares ('M' or 'E'), return the board after revealing this
     * position according to the following rules:
     * 
     * If a mine ('M') is revealed, then the game is over - change it to 'X'.
     * 
     * If an empty square ('E') with no adjacent mines is revealed, then change it
     * to revealed blank ('B') and all of its adjacent unrevealed squares should be
     * revealed recursively.
     * 
     * If an empty square ('E') with at least one adjacent mine is revealed, then
     * change it to a digit ('1' to '8') representing the number of adjacent mines.
     * 
     * Return the board when no more squares will be revealed.
     * 
     * Input: 

        [['E', 'E', 'E', 'E', 'E'],
        ['E', 'E', 'M', 'E', 'E'],
        ['E', 'E', 'E', 'E', 'E'],
        ['E', 'E', 'E', 'E', 'E']]

        Click : [3,0]

        Output: 

        [['B', '1', 'E', '1', 'B'],
        ['B', '1', 'M', '1', 'B'],
        ['B', '1', '1', '1', 'B'],
        ['B', 'B', 'B', 'B', 'B']]
     * 
     * 
     */

    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length, n = board[0].length;
        LinkedList<int[]> queue = new LinkedList<>();
        queue.addLast(click);

        while (!queue.isEmpty()) {
            int[] q = queue.pollFirst();
            int x = q[0];
            int y = q[1];

            if (board[x][y] == 'M') { // Mine
                board[x][y] = 'X';
                break;
            }

            // get the adjacent mines
            int mines = countMines(board, x, y);
            // If there are mines around, mark it with the count and dont proceed further
            if (mines > 0) {
                board[x][y] = (char) (mines + '0');
                continue;
            }

            // No mines then lets mark them do further BFS
            board[x][y] = 'B';

            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (i == 0 && j == 0)
                        continue;
                    int r = x + i, c = y + j;
                    if (r < 0 || r >= m || c < 0 || c < 0 || c >= n)
                        continue;
                    if (board[r][c] == 'E') {
                        queue.add(new int[] { r, c });
                        board[r][c] = 'B'; // Avoid to be added again.
                    }
                }
            }

        }

        return board;
    }

    private int countMines(char[][] board, int x, int y) {
        int count = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0)
                    continue;
                int r = i + x;
                int c = j + y;
                if (r < 0 || r >= board.length || c < 0 || c < 0 || c >= board[0].length)
                    continue;
                if (board[r][c] == 'M' || board[r][c] == 'X')
                    count++;
            }
        }

        return count;
    }
}