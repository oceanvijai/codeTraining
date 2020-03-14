public class DecimalToBinary{
    /**
     * 
     */


    public String findDigitsInBinary(int a) {
	    if(a <= 0 ){
	        return "0";
	    }
	    
	    StringBuilder ans = new StringBuilder();
	    while(a > 0){
	        ans.append(a % 2);      //we have to append remainder to ans
	        a = a / 2;
	    }
	    
	    return ans.reverse().toString();   //to fetch the binary equivalent
    }
    
    /**
     * mine not so good
     */

    public String findDigitsInBinary(int A) {
        if(A == 0){
            return "0";
        }
        
        ArrayList<Integer> b = new ArrayList<>();
        while(A > 1){
            if(A % 2 == 0){
                b.add(0);
            }else{
                b.add(1);
            }
            A= A/2;
        }
    
        StringBuffer ans = new StringBuffer("1");
        for(int i = b.size(); i > 0; i--){
            ans.append(b.get(i-1));
        }
    
        return ans.toString();
    }
}