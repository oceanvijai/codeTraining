public class ValidateParentheses {
    /**
     * Given a string containing only three types of characters: '(', ')' and '*',
     * write a function to check whether this string is valid. We define the
     * validity of a string by these rules:
     * 
     * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
     * 
     * Any right parenthesis ')' must have a corresponding left parenthesis '('.
     * 
     * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
     * 
     * '*' could be treated as a single right parenthesis ')' or a single left
     * parenthesis '(' or an empty string.
     * 
     * An empty string is also valid.
     */

    /**
     * We use recursion to check all possible cases, when we encounter *
     */

    public boolean checkValidString(String s) {
        return validate(s, 0, 0);
    }

    private boolean validate(String s, int i, int count) {
        if (count < 0)
            return false;

        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                count++;
            } else if (s.charAt(i) == ')') {
                if (count <= 0)
                    return false;
                count--;
            } else if (s.charAt(i) == '*') {
                return validate(s, i + 1, count + 1) || validate(s, i + 1, count - 1) || validate(s, i + 1, count);
            }
            i++;
        }

        return count == 0;
    }
}