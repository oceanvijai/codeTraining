public class HammingDistance{

    /**
     * HammingDistance(2, 7) = 2, as only the first and the 
     * third bit differs in the binary representation of 2 (010) and 7 (111).
     */

     /**
      * Let f(x, y) be the hamming distance defined above.

        A=[2, 4, 6]

        We return,
        f(2, 2) + f(2, 4) + f(2, 6) + 
        f(4, 2) + f(4, 4) + f(4, 6) +
        f(6, 2) + f(6, 4) + f(6, 6) = 

        0 + 2 + 1
        2 + 0 + 1
        1 + 1 + 0 = 8
      */

      /**
       * Approach, we dont have to calculate (2,4) and (4,2) which are same, so just double it
       * take every unique pair, see how many bits they differ using XOR and then count the bits
       * Then sum the distances and double them while returning
       */

    public int hammingDistance(final List<Integer> A) {
        int sum = 0;
        int s = A.size() - 1;
        
        //System.out.println(s);
        for(int i = 0; i <= s; i++){
            for(int j =i+1; j <= s; j++){
                //System.out.println(i);
                int a = A.get(i);
                int b = A.get(j);
                int c = getDiffBitCount(a,b);
                sum = sum + c;
            } 
        }
        
        return sum*2;
    }
    
    private int getDiffBitCount(int a, int b){
        int c = a^b;
        int count = 0;
        while(c > 0){
            count++;
            c = c & (c-1);
        }
        return count;
    }



    /**
     * Optimized solution
     * Lets say, we have [2,4,6]
     * in binary for [010, 100, 110]
     * 
     * To pair the number and bit which has different bits, we can do this,
     * 
     * for bit location 0, count the number for zeros and ones and multiple them.
     * for bit location 1, count the number for zeros and ones and multiple them.
     * Like wise do it for all 31 bits
     * 
     * Then multiply them with 2, since we need to conside the duplicate pair as seen in the above solution
     * 
     */


    public int hammingDistance(final List<Integer> A) {
        int n = A.size();
        int dist = 0;
        for(int i = 0; i < 31; i++) {
            int oneCount = 0;
            for(int j = 0; j < n; j++) {
                int num = A.get(j);
                oneCount += (num & 1 << i) != 0? 1 : 0;
            }
            int zeroCount = n - oneCount;
            dist += (2 * oneCount * zeroCount);
        }
        return dist;
    }
}