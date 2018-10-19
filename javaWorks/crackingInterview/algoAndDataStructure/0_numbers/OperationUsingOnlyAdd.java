public class OperationUsingOnlyAdd {
    /**
     * Write methods to implement the multiply, subtract, and divide operations for
     * integers. The results of all of these are integers. Use only the add
     * operator.
     */

    /**
     * Now lets start with simple negating a number Since, this is useful later
     */

    private static int negate(int a) {
        int sign = a > 0 ? -1 : 1;

        int newNumber = 0;
        while (a > 0) {
            newNumber = newNumber + sign;
            a = a + sign;
        }
        return newNumber;
    }

    /**
     * Subraction
     */

    private static int subtract(int a, int b) {
        return a + negate(b);
    }

    /**
     * Multiplication
     * 
     * Simply add a b times
     */

    private static int multiply(int a, int b) {
        if (a < b) {
            return multiply(b, a); // faster, if number of addition is less
        }

        int sum = 0;
        for (int i = 0; i < b; i++) {
            sum = sum + a;
        }

        if (b < 0) {
            sum = negate(sum);
        }

        return sum;
    }

    /**
     * Division - tricky but easy
     * 
     * if a / b we just need to add b to itself until it reaches a
     */

    private static int division(int a, int b) {
        if (b == 0) {
            // throw exception
        }

        int count = 0;
        int sum = 0;
        while (sum <= a) {
            sum = sum + b;
            count++;
        }

        return count;
    }
}
