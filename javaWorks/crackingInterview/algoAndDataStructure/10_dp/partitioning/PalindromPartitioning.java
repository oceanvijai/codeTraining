public class PalindromPartitioning {

    // The approach has two steps
    // Step 1: use a 2D array and see In a diagnol fashion search if a starting f
    // rom one position to another is a palinfrom or not
    // We can do this by slowly increasign the size of the string and looking for
    // previous size

    // Step 2: Then use an 1D array, and partition at every index based on the
    // prvious calculation

    // Here we have 2 base case
    // 1: string with length 1 is a palindrom
    // 2: string with lenght 2 is palindrom if both are same

    public static int minCut(String A) {
        int n = A.length();
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            if (A.charAt(i) == A.charAt(i + 1)) {
                dp[i][i + 1] = true;
            }
        }

        for (int L = 2; L < n; L++) {
            for (int i = 0; i < n - L; i++) {
                int j = i + L;
                if (A.charAt(i) == A.charAt(j) && dp[i + 1][j - 1] == true) {
                    dp[i][j] = true;
                }
            }
        }

        int[] cuts = new int[n];
        int[][] brackets = new int[n][n];

        for (int i = 1; i < n; i++) {
            if (dp[0][i]) {
                cuts[i] = 0; // Full string from 0 to i is palidrom
                brackets[0][i] = i;
            } else {
                cuts[i] = i;
                for (int j = 0; j < i; j++) {
                    if (dp[j + 1][i] == true) { //
                        int val = cuts[j] + 1; // min cut so far + 1
                        if (cuts[i] > val) {
                            cuts[i] = val;
                            brackets[0][i] = j;
                            brackets[j + 1][i] = i;
                        }
                    }
                }
            }
        }

        printDP(brackets);
        System.out.println(printBracket(A, brackets, dp, 0, n - 1));

        return cuts[n - 1];
    }

    public static void main(String[] args) {
        String str = "abbbc";
        // ababb -> 1 -> aba|bb
        // ababbbabbababa -> 3 - a|bab|bbabb|ababa
        // abbbc - > 2 -> a | bbb| c
        System.out.println(minCut(str));
    }

    private static String printBracket(String str, int[][] bracket, boolean[][] isPalindrom, int start, int end) {
        if (start == end) {
            return "(" + str.charAt(start) + ")";
        }

        if (start > end) {
            return "";
        }

        String s = "(";

        int partitionIndex = bracket[start][end];

        if (isPalindrom[start][end] == true && partitionIndex == end) {
            return "(" + str.substring(start, end + 1) + ")";
            // s = s +printBracket(str,bracket,start,partitionIndex);
        } else {
            s = s + printBracket(str, bracket, isPalindrom, start, partitionIndex);
            s = s + printBracket(str, bracket, isPalindrom, partitionIndex + 1, end);
        }

        s = s + ")";
        return s;
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