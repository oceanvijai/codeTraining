public class FindNonRepeatingNumbers {

    /**
     * Possible question
     * 
     * 1. In a array where all elements are repeated twice, except for one. Find
     * that.
     * 
     * 2. In a array where all elements are repeated twice except for two. Find
     * those
     * 
     * 3. In a array where all elements are repeated thrice except for one. Find the
     * one
     */

    /**
     * 1. This is straight forward, XOR all the elements and we will get the result
     */

    public int singleNumber(int[] nums) {
        int ans = nums[0];
        for(int i =1; i < nums.length; i++){
            ans = ans ^ nums[i];
        }
        
        return ans;
        
    }

    /**
     * 2. This can be approached the same we we did XOR for finding two missing
     * element in an array (FindTwoMissingNumbers.java)
     * 
     * XOR of all will give you x XOR y.
     * Then find the rigth set bit
     * Then XOR only with elements with the set bit and we will get one missing number
     */

      public int[] singleNumber(int[] nums) {
        // first xor to get the xor of the two elements alone
        int x = 0;
        for(int i=0; i<nums.length;i++){
            x = x ^ nums[i];
        }
        
        // find the position of least significant set bit - the bit which both numbers differ
        /* int bitPos = 0;
        for(int i = 0; i < 32; i++){
            if((x & (1<<i)) != 0){
                bitPos = i;
                break;
            }
        } 
        int set_bit_no = 1 << bitPos; */
        int set_bit_no = x & ~(x-1);
        
        // find all number with this position set and xor them
        int firstNumber = 0;
        for(Integer num : nums){
            if((num & set_bit_no) != 0){
                firstNumber = firstNumber ^ num;
            }
        }
        
        int secondNumber = x ^ firstNumber;
        
        int[] result = {firstNumber,secondNumber};
        return result;
        
    }


     /**
      * 3: https://www.geeksforgeeks.org/find-the-element-that-appears-once/
      * This is actually simple
      * 
      * Approach is, one each bit location count the number if bits and mod 3
      * So the number(bits) which repeated 3 times gets mod out and only one bit remains. Store its bit value
      * Do the same for all the bit locations and finally form a number with these bit locations
      * 
      * https://www.youtube.com/watch?v=mHfvInveXDQ
      */

     // This works for positive numbers
     public int singleNumber(int[] nums) {
        int missingNumber = 0;  // form the missing number in this variable 
        for(int i=0; i< 32; i++){
            int count = 0;
            int mask = 1 << i;  // create the mask
            for(Integer num: nums){
                int bit = num & mask;
                if(bit != 0){ // Not equal to zero takes are of -ve numbers are well
                    count++;
                }
            }
            
            if((count % 3) > 0){ // All number repeated 3 times will go off except for the one element
                missingNumber = missingNumber | (1 << i);
            }
        }
        
        return missingNumber;
    }



}