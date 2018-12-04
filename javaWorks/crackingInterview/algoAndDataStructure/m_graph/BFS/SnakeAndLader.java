public class SnakeAndLader {

    /**
     * On an N x N board, the numbers from 1 to N*N are written boustrophedonically
     * starting from the bottom left of the board, and alternating direction each
     * row. For example, for a 6 x 6 board, the numbers are written as follows:
     * 
     * You start on square 1 of the board (which is always in the last row and first
     * column). Each move, starting from square x, consists of the following:
     * 
     * 
     * You choose a destination square S with number x+1, x+2, x+3, x+4, x+5, or
     * x+6, provided this number is <= N*N. (This choice simulates the result of a
     * standard 6-sided die roll: ie., there are always at most 6 destinations.)
     * 
     * If S has a snake or ladder, you move to the destination of that snake or
     * ladder. Otherwise, you move to S.
     * 
     * A board square on row r and column c has a "snake or ladder" if board[r][c]
     * != -1. The destination of that snake or ladder is board[r][c].
     * 
     * Note that you only take a snake or ladder at most once per move: if the
     * destination to a snake or ladder is the start of another snake or ladder, you
     * do not continue moving. (For example, if the board is `[[4,-1],[-1,3]]`, and
     * on the first move your destination square is `2`, then you finish your first
     * move at `3`, because you do not continue moving to `4`.)
     * 
     * Return the least number of moves required to reach square N*N. If it is not
     * possible, return -1.
     */

    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n * n];

        // copy elements to 1D array
        int i = n - 1, j = 0, index = 0, inc = 1;
        while (index < n * n) {
            arr[index++] = board[i][j];
            if (inc == 1 && j == n - 1) {
                inc = -1;
                i--;
            } else if (inc == -1 && j == 0) {
                inc = 1;
                i--;
            } else {
                j += inc;
            }
        }

        // Start the BFS

        boolean[] visited = new boolean[n * n];
        Queue<Integer> q = new LinkedList<>();
        int start = arr[0] > -1 ? arr[0] - 1 : 0;
        q.offer(start);
        visited[start] = true;

        // the BFS levels will give us the steps
        int levels = 0;

        while (!q.isEmpty()) {
            int levelSize = q.size();
            while (levelSize-- > 0) {
                int cur = q.poll();
                if (cur == n * n - 1) {
                    return levels;
                }

                // gather next levels
                for (int next = cur + 1; next <= Math.min(cur + 6, n * n - 1); next++) {
                    // If dest is a snake or a ladder, queue it for next level
                    int dest = arr[next] > -1 ? arr[next] - 1 : next;
                    if (!visited[dest]) {
                        visited[dest] = true;
                        q.offer(dest);
                    }
                }
            }
            levels++;
        }

        return -1;
    }
}