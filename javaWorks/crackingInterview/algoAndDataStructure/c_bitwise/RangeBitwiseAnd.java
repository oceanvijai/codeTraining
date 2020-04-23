public class RangeBitwiseAnd{
	
	// Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

	// Input: [5,7]
	// Output: 4

	public int rangeBitwiseAnd(int m, int n) {
        if(n == m){
            return m;
        }
        int ans = 0;
        while (m > 0 && n > 0){
            int mPos = mostSignificantBitPoistion(m);
            System.out.println(mPos);
            int nPos = mostSignificantBitPoistion(n);
            System.out.println(nPos);
            
            if(mPos != nPos ){
                break;
            }
            
            //result = result + (int)Math.pow(2,mPos);
            ans = ans + (1 << mPos);
            
            // remove the number form m and n
            m = m - (1 << mPos); 
            n = n - (1 << mPos);
        }
        
        return ans;
    }
    
    private int mostSignificantBitPoistion(int x){
        int position = -1;
        while(x > 0){
            x = x >> 1;
            position++;
        }
        return position;
    }
}