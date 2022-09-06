public class FindParity{
  
  /**
      The parity of a binary word is 1 if the number of Is in the word is odd; otherwise, it is 0
  
      Approach: Simple enough. Have a memtable of any size which already knows the count of its bit, 
      then break the given number into the same size which means within the same number range and get the bit count in O(1)
      
      Here we have used chunk size as 4, and count all the bits.
      Time is O(1) since even in worse case the iteration time is fixed
  
  **/
  
  
  static int[] sixteenBitBitCount = new int[]{0,1,1,2,1,2,2,3,1,2,2,3,2,3,3,4};
    
     public static void main(String []args){
        System.out.println("Hello, World!");
        System.out.println(findParity(23232323));

     }
     
     private static int findParity(long num){
         int bitCount = 0;
         
         while(num > 0){
            int mask = (int)(num & 15);
            System.out.println("mask: "+mask);
            bitCount += sixteenBitBitCount[mask];
            num = num >>> 4;
         }
         
         return bitCount%2 == 0 ? 0 : 1;
     }
  
}
