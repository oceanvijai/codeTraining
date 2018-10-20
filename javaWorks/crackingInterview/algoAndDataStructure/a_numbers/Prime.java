public class Prime{


    // check is the given number is prime

    public boolean isPrime(int p){
        if (p < 2) { 
            return false;
        }
        int sqrt = (int) Math.sqrt(p); 
        for(int i = 2 ; i < sqrt; i++){
            if(p % i ==0){
                return false;
            }
        }

        return true;
    }


    // get the next prime number
    
    // the approach here is, all non prime numbers are divisble by prime
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




}