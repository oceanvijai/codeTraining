public class DecodeAtIndex	{

	/**
		An encoded string S is given.  To find and write the decoded string to a tape, 
		the encoded string is read one character at a time and the following steps are taken:

		If the character read is a letter, that letter is written onto the tape.
		If the character read is a digit (say d), the entire current tape is repeatedly written d-1 more times in total.
		Now for some encoded string S, and an index K, find and return the K-th letter (1 indexed) in the decoded string.



		Example 1:

		Input: S = "leet2code3", K = 10
		Output: "o"
		Explanation: 
		The decoded string is "leetleetcodeleetleetcodeleetleetcode".
		The 10th letter in the string is "o".
		Example 2:

		Input: S = "ha22", K = 5
		Output: "h"
		Explanation: 
		The decoded string is "hahahaha".  The 5th letter is "h".
		Example 3:

		Input: S = "a2345678999999999999999", K = 1
		Output: "a"
		Explanation: 
		The decoded string is "a" repeated 8301530446056247680 times.  The 1st letter is "a".
		 

		Constraints:

		2 <= S.length <= 100
		S will only contain lowercase letters and digits 2 through 9.
		S starts with a letter.
		1 <= K <= 10^9
		It's guaranteed that K is less than or equal to the length of the decoded string.
		The decoded string is guaranteed to have less than 2^63 letters.

	**/

	/**
		Approach 1: Build the string until we hit K length and return the char
					Has memory issue since the string can grow exponentially very quickly
	

		Approah  2: Bad ass. Dont build the string. Instead try and guess/find which char is found in kth index.
					Intuition: the character at k and k*2, k*3 etc will be the same

					1.	First find the length of the string if we build it
					2.	Now, work from back, since the numbers will come first in this case
						1.	If a number comes, divide the size by the number, since let say the decode string is "appleappleapple" size 15
							If we get 3, then divide by 3 we get 5, which actually only "apple"

						2.	If we get a char remove it from the virtual decoded string. Lets say the decoded string is "appleb"
							Then if 'b' is the current char, remove it by making size--;
							i.e, WE ARE CHECKIG IF THE CURRENT char WOULD BE IN kth POSITION OR IN kth MULTIPLE POSITION

						3.	As per the intuition, if we hit a char which is at index k or its multiple, return the char
							Note that we need to update k based on the current size, 
							since we can find the answer when size is a multiple of k or k.

						Ex: Lets say the decode string is encodedString = "apple3", decodedStr="appleappleapple" size = 15 and k = 7 
							Then on iteration 1: current char is 3, so k = 7 (k = k % size), size = 5, decodedStr = "apple" 
									iteration 2: current char is e, so k = 2(7%5), size = 4, decodedStr = "appl" (At this point we can guess 'p' is the answer, 
																												  But let say the encode string a2b3c4d5e6f7g8h9" 
																												  where only a is repeated multiple times
																												  we CANNOT make this guess)
									iteration 3: current char is l, so k = 2(2%4), size = 3, decodedStr = "app"
									iteration 4: current char is p, so k = 2(2%3), size = 2, decodedStr = "ap"
									iteration 5: current char is p, so k = 0(2%2), condition meet, so return "p"

	**/


	public String decodeAtIndex(String S, int K) {
        int n = S.length();
        
        // Get the estimated size of the decoded string 
        long estimatedSize = 0;        
        int i = 0;
        while(i < n){
            char c = S.charAt(i);
            if(Character.isDigit(c)){
                int j = Character.getNumericValue(c);
                estimatedSize = estimatedSize * j;
            }else{
                estimatedSize++;
            }
            i++;
        }
                
        // now work from back and strink the size
        // Also the character at k and k*2, k*3 etc will be the same
        // So, if at some point, k%size == 0, we can get that char as answer
        i = n-1;
        while(i >= 0){
            char c = S.charAt(i); 
            
            K %= estimatedSize;
            if(K == 0 && Character.isLetter(c)){
                return Character.toString(c);
            }
            
            if(Character.isDigit(c)){
                // reduce the number of duplication
                estimatedSize = estimatedSize / Character.getNumericValue(c);
            }else{
                // remvove the character
                estimatedSize--;
            }
            
            i--;
        }
        
        return null;
    }
	
}