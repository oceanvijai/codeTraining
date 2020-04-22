public class PainterPartition {

   /**
     * You have to paint N boards of length {A0, A1, A2, A3 … AN-1}. There are K
     * painters available and you are also given how much time a painter takes to
     * paint 1 unit of board.
     * 
     * You have to get this job done as soon as possible under the constraints that
     * any painter will only paint contiguous sections of board.
     */

    /**
     * 2 painters cannot share a board to paint. That is to say, a board cannot be
     * painted partially by one painter, and partially by another.
     * 
     * A painter will only paint contiguous boards. Which means a configuration
     * where painter 1 paints board 1 and 3 but not 2 is invalid.
     */

    /**
     * Input : K : Number of painters 
     * 
     * T : Time taken by painter to paint 1 unit of board 
     * 
     * L : A List which will represent length of each board
     * 
     * Input : 
     * K : 2, 
     * T : 5
     * L : [1, 10]
     */


     // Approach : The clue here is the monotonically increasing/decresing answer and a way to validate it 
     // at each state

     // i.e the answer required is TIME,
     // And give any time, we can validate if its possible with the given set of boards and painters


     // So, we find the min time posible and the max time possible
     // then we take the mid and validated if it satisfies the problem constrainsts
  
  
    public int paint(int availablePainter, int timePerUnit, ArrayList<Integer> boards) {
        int maxTime = 0;
        int minTime = Integer.MIN_VALUE;

        for (Integer l : boards) {
            maxTime += l;
            if (minTime < l) {
                minTime = l;
            }
        }

        long start = minTime, end = maxTime, ans = start;

        while (start <= end) {
            long mid = (start + (end - start) / 2) % 10000003;
            if (isPossible(mid, availablePainter, timePerUnit, boards)) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return (int) ((ans * timePerUnit) % 10000003);
    }

    private boolean isPossible(long proposedTimeLimit, int availablePainter, int timePerUnit,
            ArrayList<Integer> boards) {
        int numberOfPainters = 1;
        long time = 0;
        for (Integer length : boards) {
            time += (length);

            if (time > proposedTimeLimit) {
                time = (length);
                numberOfPainters++;
            }
        }

        return numberOfPainters <= availablePainter;
    }

}