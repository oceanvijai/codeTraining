import java.util.*;
public class InfixToPostFix {


    // Given an expression with operators * + - / ()
    // eveluate the expression and give the result

    // To eveluate the expression we can first convert it to postfix format
    // Then we can use stack to eveluate the result

    // Algorithm
    // 1: create an char array or String - output
    // 2. create a stack for the operators alone - operator stack
    // 3. iterate from left to right
    //      If we see an operand , we append it to the output string
    //      If we find an operator, we have two choices
    //          if the operator has HIGHER OR EQUAL precedence than the one at the top of the stack
    //              PUSH it into the operator stack
    //      If the operator is having LOWER
    //              start poping the elements out of stack and add it to the output
    //              do this until the current operator is having higher precedence
    //              And then push the current one one to the stack
    //      If we find '(' -> then push it to the stack
    //      If we find ')' -> then pop everthing out of stack and add to output
    // 4. Finally pop all the elements of the stack and append to the output

    private static String convertInfixToPostFix(String expression) {
        StringBuilder sb = new StringBuilder("");
        LinkedList<Character> stack = new LinkedList<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(') { // push '(' into stack and wait for ')'
                stack.addFirst(c);
            } else if (c == ')') {
                processCloseBracket(sb, stack);
            } else if (isOperand(c) == true) {
                sb.append(c);
            } else {
                processOperator(sb, stack, c);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(Character.toString(stack.pollFirst()));
        }

        return sb.toString();

    }

    private static void processOperator(StringBuilder sb, LinkedList<Character> stack, char c) {
        if (!stack.isEmpty() && getPriority(c) >= getPriority(stack.peekFirst())) {
            stack.addFirst(c);
        } else {
            while (!stack.isEmpty() && getPriority(c) <= getPriority(stack.peekFirst())) {
                sb.append(stack.pollFirst());
            }
            stack.offerFirst(c);
        }
    }

    private static void processCloseBracket(StringBuilder sb, LinkedList<Character> stack) {
        char c1 = stack.pollFirst();
        sb.append(c1);
        while (!stack.isEmpty() && stack.peekFirst() != '(') {
            sb.append(stack.pollFirst());
        }
        stack.pollFirst(); // to remove the '('
    }

    private static boolean isOperand(char c) {
        boolean result = true;
        switch (c) {
        case '+':
        case '-':
        case '*':
        case '/':
            result = false;
            break;
        }

        return result;
    }

    private static int getPriority(char c) {
        int priority = 0;

        switch (c) {
        case '*':
        case '/':
            priority = 3;
            break;
        case '+':
        case '-':
            priority = 2;
            break;
        case '^': 
            priority = 1;
            break;
        }

        return priority;

    }

    public static void main(String[] args)  
    { 
        String exp = "a+b*(c^d-e)^(f+g*h)-i"; 
        System.out.println(convertInfixToPostFix(exp)); 
    }


    // Then how can we evaluate the postfix expression

    // It is quite easy

    /**
     *  Start iterationg from left to right
     *  If an operand is found put in into the stack
     *  If an opeartor is found, pop two from the stack, do the operation and push it into the stack
     *  Finally we have one element in the stack
     */

}