import java.util.Stack;

public class ExpressionEval {

    private static final String OPERATORS = "+-*/";

    public static void main(String[] args) {

        Stack<String> stack = new Stack<>();

        for (String argument : args) {

            if (OPERATORS.contains(argument)) {
                String op2 = stack.pop();
                String op1 = stack.pop();
                Double result = compute(argument, op1, op2);
                if (result == null) {
                    throw new IllegalArgumentException();
                } else {
                    stack.push(result.toString());
                }
            } else {
                stack.push(argument);
            }
        }

        System.out.println("final result:" + stack.pop());
    }

    private static Double compute(String operator, String op1, String op2) {
        double value1 = Double.valueOf(op1);
        double value2 = Double.valueOf(op2);
        switch (operator) {

            case "+":
                return value1 + value2;

            case "-":
                return value1 - value2;

            case "*":
                return value1 * value2;

            case "/":
                return value1 / value2;
        }
        return null;
    }
}