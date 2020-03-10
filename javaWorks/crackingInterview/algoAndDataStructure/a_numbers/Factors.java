public class Factors {

    /**
     * The intution is, let say A = 36
     * Now factores of 36 are
     * [1,2,3,4,6,8,12,19,36]
     * If you see, after squareRoot of 36, which is 6, cofactors of the previous elements
     * Like (2*19) = (3*12) = (4*8) = (6*6) = 36
     * So, lets a = 2, b= 19 and, A = 36
     * So, if we have a and A till sqareRoot of A
     * So, we can find b when when iterate and add it to our answers
     */



    public ArrayList<Integer> allFactors(int A) {
        ArrayList<Integer> r = new ArrayList<>();
        int a = (int)Math.sqrt(A);
        for(int i =1; i <= a; i++){
            if(A % i == 0){
                r.add(i);
                if(A/i != i){
                    r.add(A/i);
                }
            }
        }
        Collections.sort(r);
        return r;
    }
}