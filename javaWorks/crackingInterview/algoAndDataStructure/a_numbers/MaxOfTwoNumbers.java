public class MaxOfTwoNumbers {

    /**
     * Write a method that finds the maximum of two numbers. You should not use
     * if-else or any other comparison operator.
     */

    /**
     * how can be find if a number is greater than other ?
     * This is the basic trick we will follow
     * 
     * let a, b be two numbers
     * if a - b is +ve, a is greater 
     * if a - b is -ve, b is greater
     * 
     * So, if we know the difference sign, we can tell which one is the max
     */

    /**
     * Algorithm
     * 
     * let a, b be the given numbers
     * Let k is 1 if a - b is positive and, 0 if a - b is -ve
     * Let q be the negation of k
     * return a * k + b * q
     * 
     */
    
    private static int flip(int bit) {
        return 1 ^ bit;
    }

    private static int getSign(int c) {
        return flip((c >> 31 & 1));
    }

    private static int getMax_buggy(int a, int b) {
        int c = a - b;
        int k = getSign(c);
        int q = flip(k);
        return ((a * k) + (b * q));
    }
    
    /**
     * But the above logic has an issue if a = Integer.MAX_VALUE and b = - 2;
     * 
     * Then, c overflows and produce a -ve sign
     * 
     * how to solve this
     */

    /**
     * If we look and try out few examples, like this
     * a = -5, b = 3
     * The c = -8, so 'a' needs to be multiplied with zero
     * 
     * If a = 5 and b = -3
     * Then c = 8, so 'a' needs to be multiplied with one
     * 
     * If a = Integer.MAX_VALUE and b = -5
     * then a needs to be multiplied with 1
     * 
     * If a = Integer.MIN_VALUE and b = 6
     * then a needs to be multiplied by 0
     * 
     * So, k has the same value of a when a and b have different signs
     * 
     */

     /**
     * Algorithm changed
     * 
     * 
     * observation/intution/pattern whatever (Need to remember this)
     *  Lets say a=2,b=5   -> k=1
     *           a=5,b=2   -> k=0
                 a=-2,b=-5 -> k=0
                 a=-5,b=-2 -> k=1
                 
                 a=-2,b=5  -> k=1
                 a=-5,b=2  -> k=1
                 a=2,b=-5  -> k=0
                 a=5,b=-2  -> k=0
                 
        From the first set, we can see k depends on whichever value is greater, like if b is greater k=1 & vice versa
        From the second set where a & b have different sign, we notice that k sign is same as a's sign
        So lets do something like this,
        
     *  If a & b have a different sign, then use a's sign for K, then do it normally from the first solution
     * 
     */

    private static int getMax(int a, int b) {
        
        int signOf_a = getSign(a);
        int signOf_b = getSign(b);

        int isaAndBDifferent = signOf_a ^ signOf_b; // Will be 1 if they are different
        int signOfaMinusB = getSign(a - b);
        
        // This is the tricky part
        // if isaAndBDifferent is 1 then a and b are different, so we can give k the sign if a
        // if isaAndBDifferent is 0 then a and b are same, then give the noraml sign to k
        int k = (signOf_a * isaAndBDifferent) + (signOfaMinusB * flip(isaAndBDifferent));
        int q = flip(k);
        return ((a * k) + (b * q));
    }


}
