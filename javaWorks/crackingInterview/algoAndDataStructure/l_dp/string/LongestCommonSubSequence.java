public class LongestCommonSubSequence {
    /**
     * LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
     * 
     * LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
     */

    int lcs(char[] X, char[] Y, int m, int n) {
        int L[][] = new int[m + 1][n + 1];

        /*
         * Following steps build L[m+1][n+1] in bottom up fashion. Note that L[i][j]
         * contains length of LCS of X[0..i-1] and Y[0..j-1]
         */
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (X[i - 1] == Y[j - 1])
                    L[i][j] = L[i - 1][j - 1] + 1;
                else
                    L[i][j] = max(L[i - 1][j], L[i][j - 1]);
            }
        }
        return L[m][n];
    }
}