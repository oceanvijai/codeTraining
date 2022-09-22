public class Prime{


    // check is the given number is prime
    
    // We dont have to check all number from 1 till p and try and divide to see if it is producing reminder 0
    // Since the after squareRoot of p, the division is redendant. Lets take an example
    // What are the divisors of 36, 2,3,4,6,9,12,18,36
    // If you watch closly after sqrt(36) which is 6, the divisors are reduant. 
    // like 1*36=36, 2*18=36, 3*12=36, 4*9=36, 6*6=36, 9*4=36, 12*3=36, 18*2=36, 36*1=36.
    // So if we just conside numbers between 1 to sqrt(p) and if they divide p without reminder, then its not a prime.

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
