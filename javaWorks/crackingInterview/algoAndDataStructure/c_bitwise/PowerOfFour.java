public class PowerOfFour{
	//Given an integer (signed 32 bits), write a function to check whether it is a power of 4.


	/** 
		Solution:1
			check if only one bit is set and the bit is in even position using right/left shift
			O(logn) -> n number of bits

		Solution:2
			check if only one bit is set (otherwise power of 2): n & (n-1) == 0
			all power of 4 will have mod 3 reminder as 1
			O(1)

	**/

	public boolean isPowerOfFour(int num) {
        boolean isPowerOfTwo = ((num & (num-1)) == 0);
        boolean mode3 = num % 3 == 1;
        
        return isPowerOfTwo && mode3;
    }
}