public class ReverseWords2 {
    /**
     * Given an input string , reverse the string word by word.
     */

    /**
     * Input: ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
     * 
     * Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
     */

    /**
     * A word is defined as a sequence of non-space characters. The input string
     * does not contain leading or trailing spaces. The words are always separated
     * by a single space.
     * 
     * Follow up: Could you do it in-place without allocating extra space?
     */

    public void reverseWords(char[] str) {
        
        int i = 0;
        
        int start = 0;
        
        while(i < str.length){
            
            if(str[i] != ' '){
                i++;
            }else {
                reverse(start,i-1,str);
                i++;
                start = i;
            }
        }
        
        reverse(start,i-1,str);
        
        // for(char x : str){
        //     System.out.print(x+", ");
        // }
        
        reverse(0,str.length -1, str);        
    }
    
    private void reverse(int start, int end, char[] str){
        
        while(start <= end){
            char t = str[start];
            str[start] = str[end];
            str[end] = t;
            start++;
            end--;
        }
    }
}