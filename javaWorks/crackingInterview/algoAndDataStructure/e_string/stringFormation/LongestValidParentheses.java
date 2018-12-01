public class LongestValidParentheses {

    /**
     * Given a string containing just the characters '(' and ')', find the length of
     * the longest valid (well-formed) parentheses substring.
     * 
     * Input: "(()" Output: 2 Explanation: The longest valid parentheses substring
     * is "()"
     */

     /**
     * Approach 1: stack
     * 
     * 1.   Initilize the stack with -1 to take care of edge case
     * 2.   If we see '(' we push it into the stack
     * 3.   If we see ')' we have two option
     *      we pop then
     * 4.   if the stack is empty, meaning, all the previous are parired, but this ')'
     *      seems to end the valid sequence, so push this index to the stack, so we can
     *      calculate the next valid sequence with this as the end
     * 5.   If not empty, calculate the size of valid length so far by peeking on to the
     *      stack top index
     */

    public int longestValidParentheses(String s) {

        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }
    
    /**
     * Approach 2: DP
     * 
     * Dp upto which we find a valid paranthesis and check for previous (i-1) or
     * (i-2) for what happend previously
     */

    public int longestValidParentheses_DP(String s) {
        int[] dp = new int[s.length()];
        int ans = 0;

        for (int i = 1; i < s.length(); i++) {
            // if I see '(' I dont do anything, leaving it as zero
            // But if I see ')' I do the following
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = ((i >= 2) ? dp[i - 2] : 0) + 2;
                } else if ((i - dp[i - 1]) > 0 && s.charAt((i - dp[i - 1]) - 1) == '(') { // check for continilous
                                                                                          // brackets
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
            }
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

}