import javax.swing.text.Position.Bias;

public class FindDuplicates{
    /**
     * You have an array with all the numbers from 1 to N, where N is at most 32,000. 
     * The array may have duplicate entries and you do not know what N is.
     * 
     * With only 4 kilobytes of memory available, how would you print all duplicate
     * elements in the array?
     */

     /**
      * So there are many duplicates in the array
      * We have 2 KB memory = 32000 bits
      * we have 32,000 numbers
      */

      /** 
       * This is straight foward, but point is how you will implement a class like BitSet in java API
       */

       private class BitSet{
           int[] bits;

           public BitSet(int size){
                bits = new int[size / 32]; // or (size >> 5)
           }

           boolean get(int pos) { 
               int index = pos / 32;
               int val = bits[index];
               return (val & (1 << (pos % 32))) == 0 ? false : true ; 
           }

           boolean set(int pos) { 
            int index = pos / 32;
            int bitPosition = pos % 32;
            int mask =  1 << bitPosition;

            int val = bits[index];
            return bits[index] = val | mask;
        }
       }
    
}