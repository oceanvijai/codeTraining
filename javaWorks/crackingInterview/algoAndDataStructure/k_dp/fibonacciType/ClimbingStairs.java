public class ClimbingStairs {
    /**
     * You are climbing a stair case. It takes n steps to reach to the top.
     * 
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can
     * you climb to the top?
     */

     // So if we climb one step first, then there are f(n-1) ways to get to the top
     // if we climb two steps first, then there are f(n-2) ways to get to the top

     public int climbStairs(int A) {
        int count[] = new int[A];
        
        count[0] = 1;
        if(A > 1){
        count[1] = 2;
         for(int s = 2; s < A; s++){
                count[s] = count[s-1] + count[s-2];
            }   
        }
        
        return count[A-1];
    }

}