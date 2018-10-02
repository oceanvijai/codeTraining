public class CountBits{

    public int countBits(int i){
        int count = 0;

        while(i != 0){
            count = n & 1;
            n = n >> 1;
        }

        return count;
    }

    public int countBits_1(int n){
        int count = 0;

        while(n != 0){
            n = n & (n-1);
            count++;
        }
        return count;
    }
}