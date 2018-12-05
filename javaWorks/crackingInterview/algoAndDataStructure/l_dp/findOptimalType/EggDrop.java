public class EggDrop {
    /**
     * You are given K eggs, and you have access to a building with N floors from 1
     * to N.
     * 
     * Each egg is identical in function, and if an egg breaks, you cannot drop it
     * again.
     * 
     * You know that there exists a floor F with 0 <= F <= N such that any egg
     * dropped at a floor higher than F will break, and any egg dropped at or below
     * floor F will not break.
     * 
     * Each move, you may take an egg (if you have an unbroken one) and drop it from
     * any floor X (with 1 <= X <= N).
     * 
     * Your goal is to know with certainty what the value of F is.
     * 
     * What is the minimum number of moves that you need to know with certainty what
     * F is, regardless of the initial value of F?
     */

    /**
     * O(K N^2) algorithm
     * 
     * This can be further optimized to O(KN) and O(KlogN) as follows
     */

    public int superEggDrop(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];

        // We need one trial for one floor and 0 trials for 0 floors
        for (int i = 1; i <= K; i++) {
            dp[i][1] = 1;
        }

        // We always need j trials for one egg and j floors.
        for (int j = 1; j <= N; j++) {
            dp[1][j] = j;
        }

        int r = 0;
        for (int e = 2; e <= K; e++) {
            for (int f = 2; f <= N; f++) {
                dp[e][f] = Integer.MAX_VALUE;
                // trying from 0 to f floors with e eggs
                for (int x = 1; x <= f; x++) {
                    // Max of broken vs not-broken
                    r = 1 + Math.max(dp[e - 1][x - 1], dp[e][f - x]);

                    dp[e][f] = Math.min(dp[e][f], r);
                }
            }
        }

        return dp[K][N];
    }

    /**
     * O(KN) solution
     * 
     * We skip the third loop, by moving along with the second loop and finding
     * which is the index 'x' which give optimal solution
     * 
     * so, we get O(K * 2N) => O(KN)
     */

    public int superEggDrop_optimized(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];

        // We need one trial for one floor and 0 trials for 0 floors
        for (int i = 1; i <= K; i++) {
            dp[i][1] = 1;
        }

        // We always need j trials for one egg and j floors.
        for (int j = 1; j <= N; j++) {
            dp[1][j] = j;
        }

        int r = 0;
        for (int e = 2; e <= K; e++) {
            int x = 1;
            for (int f = 1; f <= N; f++) {
                dp[e][f] = Integer.MAX_VALUE;
                while (x < f && Math.max(dp[e - 1][x - 1], dp[e][f - x]) > Math.max(dp[e - 1][x], dp[e][f - x - 1]))
                    x++;

                r = 1 + Math.max(dp[e - 1][x - 1], dp[e][f - x]);
                dp[e][f] = Math.min(dp[e][f], r);
            }
        }

        return dp[K][N];
    }


    /**
     * For O(KlogN) we need to a do a binary search in the palce of the inner loop for f
     */
}