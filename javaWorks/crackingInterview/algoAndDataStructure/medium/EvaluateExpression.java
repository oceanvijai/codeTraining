import java.util.LinkedList;

public class EvaluateExpression {

    /**
     * This can be done in a simple way Either by converting it into a postfix
     * format and then evalute it Or do the same on the fly
     */

    /*
     * Below is a impl with OOPS and design applied for the same solution
     */

    private static int evalExpression(String expression) {
        LinkedList<Double> numberStack = new LinkedList<Double>();
        LinkedList<Operator> operatorStack = new LinkedList<Operator>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (isOperand(c) == true) {
                numberStack.addFirst(Double.parseDouble(Character.toString(c)));
            } else {
                Operator op = getOperator(c);
                collapseTop(op, numberStack, operatorStack);
                operatorStack.push(op);
            }
        }

        /* Do final collapse. */
        collapseTop(Operator.BLANK, numberStack, operatorStack);
        if (numberStack.size() == 1 && operatorStack.size() == e) {
            return numberStack.pop();
        }
        return 0;
    }

    /*
     * Return priority of operator. Mapped so that : addition == subtraction <
     * multiplication == division
     */
    static int priorityOfOperator(Operator op) {
        switch (op) {
        case ADD:
            return 1;
        case SUBTRACT:
            return 1;
        case MULTIPLY:
            return 2;
        case DIVIDE:
            return 2;
        case BLANK:
            return 0;
        }
        return 0;
    }

    static void collapseTop(Operator current, LinkedList<Double> numberStack, LinkedList<Operator> operatorStack) {
        while (operatorStack.size() >= 1 && numberStack.size() >= 2) {
            if (priorityOfOperator(futureTop) <= priorityOfOperator(operatorStack.peek())) {
                double second = numberStack.pop();
                double first = numberStack.pop();
                Operator op = operatorStack.pop();
                double collapsed = op.eval(first, second);
                numberStack.push(collapsed);
            } else {
                break;
            }
        }
    }

    private static Operator getOperator(char c) {
        return new Operator(c);
    }

    private class Operator {
        OPERATOR_ENUM operator;
        Executor executor;

        Operator(char c) {
            operator = OPERATOR_ENUM.getOperator(c);
            executor = operator.getExecutor(c);
        }

        public double eval(double a, double b) {
            return executor.execute(a, b);
        }
    }

    private interface Executor {
        public double execute(double a, double b);
    }

    private class Addition extends Executor {
        public double execute(double a, double b) {
            return a + b;
        }
    }

    private class Subraction extends Executor {
        public double execute(double a, double b) {
            return a - b;
        }
    }

    private class Multiply extends Executor {
        public double execute(double a, double b) {
            return a * b;
        }
    }

    private class Division extends Executor {
        public double execute(double a, double b) {
            return a / b;
        }
    }

    private enum OPERATOR_ENUM {
        ADD, SUBTRACT, DIVIDE, MULTIPLY;

        Map<Chracter, Executor> map = new HashMap<>();

        OPERATOR_ENUM() {
            map.put('+', new Addition());
            map.put('-', new Subraction());
            map.put('*', new Multiply());
            map.put('/', new Division());
        }

        public static OPERATOR getOperator(char c) {
            switch (c) {
            case '+':
                return ADD;
            case '-':
                return SUBTRACT;
            case '*':
                return DIVIDE;
            case '/':
                return MULTIPLY;
            }
        }

        public Executor getExecutor(char c) {
            return map.get(c);
        }
    }
}