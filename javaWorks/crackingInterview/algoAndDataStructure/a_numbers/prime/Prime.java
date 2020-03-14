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

}