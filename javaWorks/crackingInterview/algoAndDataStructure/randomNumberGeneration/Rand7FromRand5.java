public class Rand7FromRand5 {
    /**
     * Implement a method rand7() given randS(). That is, given a method that
     * generates a random number between 0 and 4 (inclusive), write a method that
     * generates a random number between 0 and 6 (inclusive).
     */

    /**
     * Now for getting numbers between 0 - 6 using rand5 once is not enought we need
     * to call rand5 one more time and which will in turn produce another set of
     * numbers from 0 to 4
     * 
     * Lets say, r1 be the number we got from the first time, and r2 from the second
     * time Now we need to combine these some how, such that we get value between 0
     * to 6 with EQUAL PROBABILITY
     */

    /**
     * Let say we add r1 and r2 from above. Then what do we get
     *  0 + 0 -> 0      1 + 0 -> 1      2 + 0 -> 2      3 + 0 -> 3      4 + 0 -> 4  
     *  0 + 1 -> 1      1 + 1 -> 2      2 + 1 -> 3      3 + 1 -> 4      4 + 1 -> 5
     *  0 + 2 -> 2      1 + 2 -> 3      2 + 2 -> 4      3 + 2 -> 5      4 + 2 -> 6
     *  0 + 3 -> 3      1 + 3 -> 4      2 + 3 -> 5      3 + 3 -> 6      4 + 3 -> 7
     *  0 + 4 -> 4      1 + 4 -> 5      2 + 4 -> 6      3 + 4 -> 7      4 + 4 -> 8
     */

    /**
    * Lets take a mod 7 of all the values above and we get
    *  0 - 2 times , 1 -> 3 times, 2 -> 3 times, 3 -> 4 times, 4 -> 5 times, 5 -> 4times, 6 -> 3times
    *  7 -> 2 times , 8 -> 1 time  
    *  So they are not evenly distributed
    */

    /**
     *  So what we can do is use a commom multiple and increase our range ans see if any multiple is giving us 
     *  a uniform distribution of values 
     */

    /**
    *  so, out formula is (m * r1 + r2)
    */

    /**
     * Lets try 2 for m
     */

    /**
     *  0 + 0 -> 0      2 + 0 -> 2      4 + 0 -> 4      6 + 0 -> 6      8 + 0 -> 8  
     *  0 + 1 -> 1      2 + 1 -> 3      4 + 1 -> 5      6 + 1 -> 7      8 + 1 -> 9
     *  0 + 2 -> 2      2 + 2 -> 4      4 + 2 -> 6      6 + 2 -> 8      8 + 2 -> 10
     *  0 + 3 -> 3      2 + 3 -> 5      4 + 3 -> 7      6 + 3 -> 9      8 + 3 -> 11
     *  0 + 4 -> 4      2 + 4 -> 6      4 + 4 -> 8      6 + 4 -> 10     8 + 4 -> 12
     *  after mod 7 we get
     * 
     * 0 - 2 times , 1 -> 4 times, 2 -> 3 times, etc and is not evenly distributed
     *  
     */

     /**
     * like wisw we can keep on trying and we will find 5 for m as follows
     * 
     *  0 + 0 -> 0      5 + 0 -> 5      10 + 0 -> 10      15 + 0 -> 15      20 + 0 -> 20  
     *  0 + 1 -> 1      5 + 1 -> 6      10 + 1 -> 11      15 + 1 -> 16      20 + 1 -> 21
     *  0 + 2 -> 2      5 + 2 -> 7      10 + 2 -> 12      15 + 2 -> 17      20 + 2 -> 22
     *  0 + 3 -> 3      5 + 3 -> 8      10 + 3 -> 13      15 + 3 -> 18      20 + 3 -> 23
     *  0 + 4 -> 4      5 + 4 -> 9      10 + 4 -> 14      15 + 4 -> 19      20 + 4 -> 24
     *  after mod 7 we get
     * 
     *  0 -> 4, 1 -> 4, 2 -> 4, 3 -> 4, 4-> 3, 5 -> 3, 6 -> 3
     * So if we see 0 to 3 has 4 times and rest has 3
     *  so we can level them if we skip the last 4 combination in the table
     *  then a have come 3 times
     *   so our formula is as follows
     *    (5 * r1 + r2) if its less than 21 (skiping the last 4), then return it
     *    otherwise try agin
     */

     public static int rand7(){
         while(true){
            int val = 5 * rand5() + rand5();
            if(val < 21){
                return val % 7;
            }
        }
     }


    /**
    *  There is another little complicated way to get this as well
    *  use m as 2 and still get it evenly distributed
    */

    /**
     * When we use 2 for m
     * we get the following for r1
     *  0 * 2 = 0
     *  1 * 2 = 2
     *  2 * 2 = 4
     *  3 * 2 = 6
     *  4 * 2 = 8
     */

    /**
     * So we find only the even number from 0 to 8
     * but our requirement is to get all numbers 1 to 6
     * So we can do a trick here.
     * We can get r2 and make sure it will generte either 0 or 1
     * 
     * That way when we add r2 to r1, we get both the even (add with 0) and odd (add with 1)
     * how do we do that,
     *  We take the values of r2 and mod with 2. which will produce only 0 and 1
     * so we will get, 0 -> 3 times and 1 -> 2 times, so to skip one zero, we skip r2 if it generate 4
     *  so we can make r2 to generate 0 -> 2 times and 1 -> 2 times
     */

    /**
     * then we have a logic as 
     *  (2 * r1) + (r2 != 4 then r2 % 2 )   or it can be written as r1 + (r2 % 2)
     */

    public static int rand7_with2(){
        while(true){
            int r1 = 2 * rand5();
            int r2 = rand5();
            if(r2 != 4){
                int zeroOrOne = r2 % 2;
                if(r1 + r2 < 7){ // to skip 8
                    return r1 + r2;
                }
            }
       }
    }

    // ------------------------------------------------------------------------------------------------

    /**
     *  On the same line 
     *  rand12() using rand6()
     */

     // So we can generate 1 to 5 using r1
     // Then we can add 6 to r1 if r2 is odd and add 0 to r1 id r2 is even

     // So, r1 + (r2 % 2) * 6


    // ------------------------------------------------------------------------------------------------

     /**
      * rand3() using rand2()
      */

      // So we can generate 0 and 1 deom rand2 as r1
      // then for generating 2 we need to muliply r2 by by 2

      // if r2 is 0, it produces, 0 and 1 with r1
      // if r2 is 1, it produces, 2 and 3 with r1, so skip 3
      
      // r1 + (r2 * 2) and spik 3
}