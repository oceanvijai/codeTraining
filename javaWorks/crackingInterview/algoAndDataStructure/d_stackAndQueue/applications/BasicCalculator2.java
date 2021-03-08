public class BasicCalculator2{
	/**
		Given a string s which represents an expression, evaluate this expression and return its value. 

		The integer division should truncate toward zero.


		Example 1:

		Input: s = "3+2*2"
		Output: 7
		Example 2:

		Input: s = " 3/2 "
		Output: 1
		Example 3:

		Input: s = " 3+5 / 2 "
		Output: 5

		1 <= s.length <= 3 * 105
		s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
		s represents a valid expression.
		All the integers in the expression are non-negative integers in the range [0, 231 - 1].
	**/

	/**
		Approach:1 - using stack

		Approach 2 - without stack

		In both approach we do the following.
		We will save the current number, previous number
		the we will also save the previous ops

		If the previous op is + or -, we just add only the "previous number" to the result
		If not then we proces the curret and previous number and save it in previous number.

		Because, if lets say 3+5+6*7, when the prev is '+' we can add 3 to the result, since it will not affect the result
		///ly, when the next prevOp is again '+', we can add 5 directly to result, since again it will not affect the next operation.
		But we cannot add 6, since when the next prev becomes '*', we need to multiply 6 ad 7, ad then only add to result

	**/


	public int calculate(String s) {
	    int len;
	    if(s==null || (len = s.length())==0) return 0;
	    Stack<Integer> stack = new Stack<Integer>();
	    int num = 0;
	    char sign = '+';
	    for(int i=0;i<len;i++){
	        if(Character.isDigit(s.charAt(i))){
	            num = num*10+s.charAt(i)-'0';
	        }
	        if((!Character.isDigit(s.charAt(i)) &&' '!=s.charAt(i)) || i==len-1){
	            if(sign=='-'){
	                stack.push(-num);
	            }
	            if(sign=='+'){
	                stack.push(num);
	            }
	            if(sign=='*'){
	                stack.push(stack.pop()*num);
	            }
	            if(sign=='/'){
	                stack.push(stack.pop()/num);
	            }
	            sign = s.charAt(i);
	            num = 0;
	        }
	    }

	    int re = 0;
	    for(int i:stack){
	        re += i;
	    }
	    return re;
	}


	// Without stack


	public int calculate(String s) {
        char prevOp = '+';
        char currentOp = '1';
        int preNum = 0;
        int currentNum = 0;
        s = s.trim().replaceAll(" +", "");
        
        int i = 0;
        int res = 0;
        while(i < s.length()){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                int num = Integer.parseInt(c+"");
                while(i+1 < s.length() && Character.isDigit(s.charAt(i+1))){
                    num = num * 10 + Integer.parseInt(s.charAt(i+1)+"");
                    i++;
                }
                currentNum = num;
            }else {
                currentOp = c;
                if(prevOp == '+'){
                    // Add only the previous number, 
                    // since current number operation is based on the next operation
                    res += preNum;
                    preNum = currentNum;
                }else if(prevOp == '-'){
                    // Add only the previous number, 
                    // since current number operation is based on the next operation
                    res += preNum;
                    // set the prev number as -current number, so we can just add if necessary
                    preNum = -currentNum;
                }else if(prevOp == '*'){
                    preNum = preNum * currentNum;
                }else if(prevOp == '/'){
                    preNum = preNum / currentNum;
                }
                prevOp = currentOp;
            }
            i++;
        }
        
        // process the remaining
        if(prevOp == '+'){
            res += preNum;
            preNum = currentNum;
        }else if(prevOp == '-'){
            res += preNum;
            preNum = -currentNum;
        }else if(prevOp == '*'){
            preNum = preNum * currentNum;
        }else if(prevOp == '/'){
            preNum = preNum / currentNum;
        }
        
        // Collect the rest
        res += preNum;
        
        return res;
    }


}