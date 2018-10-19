public class NumberSwap {

    /**
     * Write a function to swap a number in place (that is, without temporary
     * variables).
     */


    /**
     * we can swap two number as follows
     * 
     * let a have the difference between a and b
     * then, usign that update b to have a, by adding b's current value to the difference
     * Then, set a = b - a;
     */
    
    private void swap() {
        int a = 5;
        int b = 8;

        a = a - b; // 5 - 8 = -3
        b = a + b; // -3 +8 = 5
        a = b - a; // 5 - (-3) = 8

    }

    /**
     * We can also achieve using xor 
     */


    private void swap_XOR() {
        int a = 5; // In binary 0101
        int b = 8; // In binary 1000

        a = a ^ b; // 1101 -> 13
        b = a ^ b; // 0101 -> 5
        a = a ^ b; // 1000 -> 8

    }

}
