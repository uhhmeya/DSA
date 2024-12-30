//Code created by Ameya Mandhare
//code referenced GeeksForGeeks, programmariz, and chatgpt
import java.io.*;

public class URCalculator {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            return;
        }

        String inputFile = args[0];
        String outputFile = "output.txt";

        URList<String> infixExpressions = readFile(inputFile);
        URList<Double> results = new URList<>();

        for (int i = 0; i < infixExpressions.size(); i++) {
            String infix = infixExpressions.get(i);
            String[] tokens = tokenizeExpression(infix);  // Use the new tokenize method
            URQueue<String> postfixQueue = InfixToPostFix.infixToPostfix(tokens);
            Double result = Postfix.Postfix(postfixQueue);
            results.add(result);
        }

        writeFile(outputFile, results);
    }

    public static String[] tokenizeExpression(String expr) {
        return expr.split("(?<=[-+*/^()])|(?=[-+*/^()])");
    }

    private static URList<String> readFile(String filePath) throws IOException {
        URList<String> lines = new URList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
        return lines;
    }

    private static void writeFile(String filePath, URList<Double> results) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (int i = 0; i < results.size(); i++) {
            writer.write(results.get(i).toString());
            writer.newLine();
        }
        writer.close();
    }
}
