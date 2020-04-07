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

    /**
     * 2. This can be approached the same we we did XOR for finding two missing
     * element in an array (FindTwoMissingNumbers.java)
     * 
     * XOR of all will give you x XOR y.
     * Then find the rigth set bit
     * Then XOR only with elements with the set bit and we will get one missing number
     */


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

}