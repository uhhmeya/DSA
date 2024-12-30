//Code created by Ameya Mandhare
//code referenced GeeksForGeeks, programmariz, and chatgpt
public class Postfix {

    public static Double Postfix(URQueue<String> z) {
        URStack<Double> resultStack = new URStack<>();

        while (!z.isEmpty()) {
            String x = z.dequeue();

            Double v = isNumber(x);
            if (v != null) {
                resultStack.push(v);
            } else {
                if (x.equals("!")) {
                    if (resultStack.isEmpty()) return 0.0;
                    double a1 = resultStack.pop();
                    resultStack.push(opApply(x, a1, 0));
                } else {
                    if (resultStack.size() < 2) return 0.0;
                    double a2 = resultStack.pop();
                    double a1 = resultStack.pop();
                    resultStack.push(opApply(x, a1, a2));
                }
            }
        }

        return resultStack.isEmpty() ? 0.0 : resultStack.pop();
    }


    private static Double isNumber(String x) {
        try {
            return Double.parseDouble(x);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static double opApply(String operator, double a1, double a2) {
        switch (operator) {
            case "+":
                return a1 + a2;
            case "-":
                return a1 - a2;
            case "*":
                return a1 * a2;
            case "/":
                return a2 != 0 ? a1 / a2 : 0.0;
            case "^":
                return Math.pow(a1, a2);
            case "%":
                return a1 % a2;
            case "~":
                return -a1;
            case "sin":
                return Math.sin(Math.toRadians(a1));
            case "cos":
                return Math.cos(Math.toRadians(a1));
            case "tan":
                return Math.tan(Math.toRadians(a1));
            default:
                return 0.0;
        }
    }


}
