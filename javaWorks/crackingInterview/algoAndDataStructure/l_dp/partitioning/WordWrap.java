public class WordWrap {
    /**
     * Given a sequence of words, and a limit on the number of characters that can
     * be put in one line (line width). Put line breaks in the given sequence such
     * that the lines are printed neatly
     * 
     * Solution:
     * 
     * Badness - We define badness has square of empty spaces in every line. So 2
     * empty space on one line gets penalized as 4 (2^2) while 1 each empty space on
     * 2 lines gets penalized as 2(1 + 1). So we prefer 1 empty space on different
     * lines over 2 empty space on one line.
     * 
     * So first we calculate the badness of every line we can form with the given
     * words
     * 
     * Then as usual, we do the partition figuring out which partition will give us
     * less badness cost
     */

    public String justify(String words[], int width) {
        int cost[][] = new int[words.length][words.length];


        //next 2 for loop is used to calculate cost of putting words from
        //i to j in one line. If words don't fit in one line then we put
        //Integer.MAX_VALUE there.
        for(int i=0 ; i < words.length; i++){
            cost[i][i] = width - words[i].length();
            for(int j=i+1; j < words.length; j++){
                cost[i][j] = cost[i][j-1] - words[j].length() - 1; 
            }
        }
        
        for(int i=0; i < words.length; i++){
            for(int j=i; j < words.length; j++){
                if(cost[i][j] < 0){
                    cost[i][j] = Integer.MAX_VALUE;
                }else{
                    cost[i][j] = (int)Math.pow(cost[i][j], 2);
                }
            }
        }

        // c[i] will have total cost of optimal arrangement of words 
        // from 1 to i 
        int[] c = new int[n+1]; 
        c[0] = 0;
        for (int j = 1; j <= n; j++){
            c[i] = cost[0][i];
            for(int i = 1; i <= j; i++){
                if(cost[i][j] != Integer.MAX_VALUE && c[i-1]+cost[i][j] < c[j]){
                    c[j] = c[i-1]+cost[i][j];
                }
            }
        }

        //printSolution(); 
    }
}