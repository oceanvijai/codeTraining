public class NumberToNumberConversion {

    /**
     * Conversion: Write a function to determine the number of bits you would need
     * to flip to convert integer A to integer B. 
     * 
     * EXAMPLE
     * 
     * Input: 29 (or: 11101), 15 (or: 01111) 
     * output: 2
     */

     // 1: XOR the first and second number
     // we will get only bits which are different
     
     // then count them

     int bitSwapRequired(int a, int b) { 
        int diff = a ^ b; 
        int count = 0;

        while(diff != 0){
            diff = diff & (diff -1);
            count++;
        }


        return count;
     }
}