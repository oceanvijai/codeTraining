public class Sieve{

        // get all prime numbers till N
    
    // the approach here is, sieve of eratosthenes
    // all non prime numbers are divisble by prime
    // we can can make a array and mark all the poistions (numbers as positions) which are divisiable by the primes
    // So generate as much as array as you want and finally check for the next prime you want in that array

    // time : O(n loglog n)
     
    public ArrayList<Integer> sieve(int A) {
        if(A < 2){
            return new ArrayList<Integer>();
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        int[] dp = new int[A+1];
        
        for(int i = 2; i <= Math.sqrt(A); i++){
            if(dp[i] == 0){
                int j = 2;
                while(j * i <= A){
                    dp[j * i] = -1;
                    j++;
                }
            }
        }
        
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i = 2; i <= A; i++){
            if(dp[i] == 0)
            result.add(i);
        }
        
        return result;
        
    }


    /**
     * We can optimize it to O(n) as follows,
     * 
     * We do this by visiting a index only once
     * 
     * https://www.geeksforgeeks.org/sieve-eratosthenes-0n-time-complexity/
     * 
     */

}