public class backspaceCompare{

	// Given two strings S and T, return if they are equal when both are typed 
	// into empty text editors. # means a backspace character.

	// Input: S = "ab#c", T = "ad#c"
	// Output: true
	// Explanation: Both S and T become "ac".

	// Input: S = "ab##", T = "c#d#"
	// Output: true
	// Explanation: Both S and T become "".

	// Can you solve it in O(N) time and O(1) space?

	// Approach:
	// Since its o(1) space, we might either modify the exiting string or use two pinter
	// Modifying existing string is bit too complex,

	// Since the backslack affects the string in front, lets start the iteration from the back
	// remove all the back slashed chars and compare the chars after that removal


	public boolean sulution(String S, String T) {
        int s = S.length() -1;
        int t = T.length() -1;
        
        int s_skips = 0;
        int t_skips = 0;
        
        while(s >= 0 || t >= 0){
            while(s >= 0){
                if(S.charAt(s) == '#'){ // count the skips
                    s--;
                    s_skips++;
                }else if(s_skips > 0){ // make the skips
                    s_skips--;
                    s--;
                }else{
                    break; // When there are no # and skips are done break out and compare
                }
                
            }
            
            
            while(t >= 0){
                if(T.charAt(t) == '#'){
                    t--;
                    t_skips++;
                }else if(t_skips > 0){
                    t_skips--;
                    t--;
                }else{
                    break;
                }
                
            }
            
            //System.out.println(s);
            //System.out.println(t);
            
            if(s >= 0 && t >= 0 && S.charAt(s) != T.charAt(t)){
                return false;
            }
            
            if((s >= 0) != (t >= 0)){
                return false;
            }
            
            s--;
            t--;
        }
    
        return true;
    }


}



