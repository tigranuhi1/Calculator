import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an expression..");
        String expr = sc.nextLine();

        System.out.println(String.format("Result:\t%s", calculate(expr)));
    }

    static Double calculate(String expr){
        String simpleExpr = getSimpleExpression(expr);
        String[] exprArr = simpleExpr.split(" ");
        Double res = Double.parseDouble(exprArr[0]);
        for (int i = 1; i < exprArr.length; i += 2) {
            res = calc(res, exprArr[i].charAt(0), Double.parseDouble(exprArr[i + 1]));
        }

        return res;
    }

    private static String getSimpleExpression(String expr) {
        ArrayList<String> exprLst = new ArrayList<>();
        String[] exprArr = expr.split(" ");

        exprLst.add(exprArr[0]);
        for (int i = 1; i < exprArr.length; i++) {
            if (exprArr[i].equals("*") || exprArr[i].equals("/")) {
                String value = String.valueOf(calc(Double.parseDouble(exprLst.get(exprLst.size() - 1)), exprArr[i].charAt(0), Double.parseDouble(exprArr[i + 1])));
                exprLst.set(exprLst.size() - 1, value);
                i++;
            }
            else
                exprLst.add(exprArr[i]);
        }

        return String.join(" ", exprLst);
    }

    private static Double calc(double firstNum, char op, double secondNum) {
        switch (op) {
            case '+':
                return firstNum + secondNum;
            case '-':
                return firstNum - secondNum;
            case '*':
                return firstNum * secondNum;
            case '/':
                return firstNum / secondNum;
            default:
                return Double.NaN;
        }
    }
}
