public class CountingBits{
	
	/** 

		Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

	**/

	/** 

	Input: 2
	Output: [0,1,1]


	Input: 5
	Output: [0,1,1,2,1,2]

	**/


	/** 

	It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
	Space complexity should be O(n).


	**/


	public int[] countBits(int num) {
        int[] ans = new int[num+1];
        if(num >= 1){
            ans[1] = 1;
        }
        
        int currentPower = 2;        
        for(int i = 2; i <= num; i++){
            if(i == currentPower){
                ans[i] = 1;
            }else{
            	// Get the number of bit for the current power
                int val = ans[currentPower];
                // get the number of bits after removing the current power
                val = val + ans[i - currentPower];
                ans[i] = val;
            }
            if(currentPower * 2 == i+1){
                currentPower = currentPower * 2;
            }
        }
        return ans;
    }
}
