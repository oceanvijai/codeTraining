public class MostSignificantBitPosition{

/*	 0] 00110101  Given
 *   1]  0011010  After dropping the 0th right-most bit
 *   2]   001101  After dropping the 1st right-most bit
 *   3]    00110  After dropping the 2nd right-most bit
 *   4]     0011  After dropping the 3rd right-most bit
 *   5]      001  After dropping the 4th right-most bit
 *
 *   At step 5] we are at the 5th and last set bit.
 */
	
	private int msb(int x){
        int position = -1;
        while(x >= 1){
            x = x >> 1;
            position++;
        }
        return position;
    }

}