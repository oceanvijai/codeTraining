public class TrailingZerosInFactorial {

    /**
     * Write an algorithm which computes the number of trailing zeros in n
     * factorial.
     */

    /**
     * Consider a factorial like 19! :
     * 19! = 1*2*3*4*5*6*7*8*9*10*11*12*13*14*15*16*17*18*19 
     * Out of these, only 2 and 5 will produce a zero
     * So, how many 2 in 19 -> 9
     *     how many 5 in 19 -> 3
     */


    /**
     * There is a GOTCHA here,
     * 25 will produce an extra 5 and 125 will produce 2 extra 5 and so on
     * 
     * So, we count the number of 5's produced by powers of 5 within the given number
     */

     int trailingZeros(int num){
        int count = 0;
        for(int i = 5; (num / i) > 1 ;i = i * 5){
            count = count + (num / i);
        }
        return count;
     }

     
}
