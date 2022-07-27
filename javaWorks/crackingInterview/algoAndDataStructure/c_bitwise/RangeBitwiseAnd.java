public class RangeBitwiseAnd{
	
	// Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

	// Input: [5,7]
	// Output: 4
	
	/**
	    Think about the a range lets say 17 to 19
	    17 - 10001
	    18 - 10010
	    19 - 10011
	    
	    you can note that, the most significant bit is the only common bit in the range
	    Likewise, 
	    
	    if we have range 18 to 19
	    you have the most significant bit as common bit and then the 2nd bit. 
	    So only the those bits will produce 1 in the AND operation
	    
	    so in most cases the we need to find the most significant bit add it to the answer,
	    then find the 2ns most common significant bit, add it to the answer and so on until the MSB are not in the same location
	**/

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
