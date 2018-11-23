public class ZigZagConversion {
    /**
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number
     * of rows like this: (you may want to display this pattern in a fixed font for
     * better legibility)
     */


    public String convert(String s, int numRows) {
        char[] c = s.toCharArray();
        int len = c.length;
        StringBuffer[] sb = new StringBuffer[numRows];
        
            for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();
        
        int i = 0;
        while(i < s.length()){
            for (int j = 0; j < numRows && i < s.length(); j++) // vertically down
            {
                sb[j].append(c[i]);
                i++;
            }
            
            for (int j = numRows-2; j >= 1  && i < s.length(); j--) //  bottom up
            {
                sb[j].append(c[i]);
                i++;
            }
                
        }
        
        return String.join("", sb);
        
    }
}