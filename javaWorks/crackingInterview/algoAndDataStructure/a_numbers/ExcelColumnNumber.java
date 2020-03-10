public class ExcelColumnNumber{
    // Given a column title as appears in an Excel sheet, return its corresponding column number.

    /**
     * A -> 1
     * B -> 2
     * ...
     * AA -> 27
     * AB -> 28
     */

    public int titleToNumber(String A) {
        int colValue = 0;
        int i = 0;
        int j = A.length()-1;
        
        while(j >= 0){
            int adder = (int)Math.pow(26,i) * getMappedValue(A.charAt(j));
            colValue+= adder;
            j--;
            i++;
        }
        
        return colValue;
    }
    
    private int getMappedValue(char c){
        int ascii = (int)c;
        //System.out.println("ascii: "+ascii);
        return ((ascii+1) % 65);
    }
}
