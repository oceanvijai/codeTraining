public class GCD {

    public int gcd(int A, int B) {
        
        if(A == 0 || B == 0){
            return Math.max(A,B);
        }
        
        while(A != B){
            int x = Math.abs(A - B);
            A = Math.min(A,B);
            B = x;
        }
        
        return A;
    }
}