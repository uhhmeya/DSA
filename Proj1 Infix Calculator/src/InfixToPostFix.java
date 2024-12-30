//Code created by Ameya Mandhare
//code referenced GeeksForGeeks, programmariz, and chatgpt
public class InfixToPostFix {

    public static URQueue<String> infixToPostfix(String[] tokens) {
        URQueue<String> output = new URQueue<>();
        URStack<String> operators = new URStack<>();

        for (String x : tokens) {
            if (isNumber(x)) {
                output.enqueue(x);
            } else if (x.equals("(")) {
                operators.push(x);
            } else if (x.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    output.enqueue(operators.pop());
                }
                operators.pop();
            } else if (isOperator(x)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(x)) {
                    output.enqueue(operators.pop());
                }
                operators.push(x);
            }
        }

        while (!operators.isEmpty()) {
            output.enqueue(operators.pop());
        }

        return output;
    }

    private static boolean isNumber(String x) {
        try {
            Double.parseDouble(x);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(String x) {
        return x.equals("+") || x.equals("-") || x.equals("*") || x.equals("/") || x.equals("^") ||
                x.equals("%") || x.equals("sin") || x.equals("cos") || x.equals("tan");
    }

    private static int precedence(String operator) {
        switch (operator) {
            case "^":
                return 4;
            case "sin":
            case "cos":
            case "tan":
                return 3;
            case "*":
            case "/":
            case "%":
                return 2;
            case "+":
            case "-":
                return 1;
            default:
                return 0;
        }
    }
}
