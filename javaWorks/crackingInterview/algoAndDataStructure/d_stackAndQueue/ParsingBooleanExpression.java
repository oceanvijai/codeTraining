public class ParsingBooleanExpression{


  /**
    A boolean expression is an expression that evaluates to either true or false. It can be in one of the following shapes:

      't' that evaluates to true.
      'f' that evaluates to false.
      '!(subExpr)' that evaluates to the logical NOT of the inner expression subExpr.
      '&(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical AND of the inner expressions subExpr1, subExpr2, ..., subExprn where n >= 1.
      '|(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical OR of the inner expressions subExpr1, subExpr2, ..., subExprn where n >= 1.
      Given a string expression that represents a boolean expression, return the evaluation of that expression.
      
      It is guaranteed that the given expression is valid and follows the given rules.




      Example 1:

      Input: expression = "&(|(f))"
      Output: false
      Explanation: 
      First, evaluate |(f) --> f. The expression is now "&(f)".
      Then, evaluate &(f) --> f. The expression is now "f".
      Finally, return false.
      Example 2:
      
      Input: expression = "|(f,f,f,t)"
      Output: true
      Explanation: The evaluation of (false OR false OR false OR true) is true.
      Example 3:
      
      Input: expression = "!(&(f,t))"
      Output: true
      Explanation: 
      First, evaluate &(f,t) --> (false AND true) --> false --> f. The expression is now "!(f)".
      Then, evaluate !(f) --> NOT false --> true. We return true.
  **/


  /**
    Approach: This is clearly a stack problem. But its not straight forward, since here we have 
    multiple expressions to evaluate for a single operator (usually its only 2).

    So we have a list at each stack level to evaluate.

    Time: O(n), since each one is visited either once or twice
    Space: O(n), stack and the list on each level
  **/

  int currentIndex = 1;
    public boolean parseBoolExpr(String expression) {
        return solve(expression, expression.charAt(0));
    }

    private boolean solve(String expression, char currentSymbol){
        List<Boolean> subExpressions = new ArrayList<>();
        while(currentIndex < expression.length()){
            if(isSymbol(expression.charAt(currentIndex))){
                subExpressions.add( solve(expression, expression.charAt(currentIndex++)) ); 
                continue;
            }else if(expression.charAt(currentIndex) == 'f'){
                subExpressions.add(false);
            }else if(expression.charAt(currentIndex) == 't'){
                subExpressions.add(true);
            }else if(expression.charAt(currentIndex) == ')'){
                currentIndex++;
                break;
            }
            currentIndex++;
        }

        boolean ans = subExpressions.get(0);
        if(currentSymbol == '!'){
            return !ans;
        }

        for(int i=0; i < subExpressions.size(); i++){
            boolean subExpression = subExpressions.get(i);

            if(currentSymbol == '&'){
                ans = ans & subExpression;
            }else if(currentSymbol == '|'){
                ans = ans | subExpression;
            }
        }
        return ans;
    }

    private boolean isSymbol(char c){
        return c == '&' || c == '|' || c == '!';
    }


  
}
