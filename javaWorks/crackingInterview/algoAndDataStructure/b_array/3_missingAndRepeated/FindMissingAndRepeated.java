import java.util.List;

public class FindMissingAndRepeated {

    /**
     * You are given a read only array of n integers from 1 to n. Each integer Each
     * integer appears exactly once except A which appears twice and B which is
     * missing.
     */

    private void findMissingAndRepeated(final List<Integer> A) {
        int r = 0;
        int m = 0;
        int T[] = new int[A.size()];
        long actualSum = 0;
        long listSum = 0;
        for (int i = 0; i < A.size(); i++) {
            if (T[A.get(i) - 1] != 1) {
                T[A.get(i) - 1] = 1;
                listSum += (long) A.get(i);
            } else {
                r = A.get(i);
            }
            actualSum += (long) i + 1;
        }
        ArrayList<Integer> result = new ArrayList<Integer>();
        m = (int) (actualSum - listSum);
        result.add(0, r);
        result.add(1, m);
        return result;
    }

    /**
     * Sum of numbers = n*(n+1)/2
     * Sun of squares = n*(n+1)*(2n+1) / 6
     * 
     * 
     * Another optimized method is as follows sum(actual) = sum(n) + A - B So,
     * sum(actual) - sum(n) = A - B; (EQ1)
     * 
     * Then SumOfSquare(actual) = SumOfSquare(n) + A^2 - B^2 
     * SumOfSquare(actual) - SumOfSquare(n) = A^2 - B^2 
     * SumOfSquare(actual) - SumOfSquare(n) = (A - B) * (A + B) 
     * (SumOfSquare(actual) - SumOfSquare(n)) / (A - B) = (A + B) (EQ2)
     * 
     * with EQ1 and EQ2 we can find A and B
     */

    public void findMissingAndRepeated_optimized(final List<Integer> A) {

        // here lets have, x as the repeated number and y as the missing
        int n = A.size();

        // Lets find the fifference x - y
        long actualSum = A.stream().mapToInt(Integer::intValue).sum();
        long sum = (n * (n + 1)) / 2;
        // So x-y is
        long diffOfAns = actualSum - sum;

        /**
         * Then, find x + y
         */

        long actualSquareSum = A.stream().mapToInt((i) -> (i * i)).sum();
        long squareSum = (n * (n + 1) * ((2 * n) + 1)) / 6;
        // So x+y is
        long sumOfAns = ((actualSquareSum - squareSum) / diffOfAns); // as per EQ2

        // We now have x - y and x + y
        int x = (int) ((diffOfAns + sumOfAns) / 2);
        int y = (int) (sumOfAns - x);
    }

}