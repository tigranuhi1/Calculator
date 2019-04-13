import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CalculatorWithBrackets extends Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an expression with brackets..");
        String expr = sc.nextLine();
        //String expr = "( 12 + ( 5 * 6 - 2 ) * ( 2 + 3 ) )";
        //String expr = "11 + ( 12 * 5 / -10 - 9 ) - ( ( 10 * -2 ) - 60 )";

        System.out.println(String.format("Result:\t%s", calc(expr)));
    }
    
    static Double calc(String expr) {
        if(containsBrackets(expr))
            expr = removeBrackets(expr);
        return Calculator.calculate(expr);
    }
    
    private static boolean containsBrackets(String expr) {
        return expr.indexOf("(") != -1 || expr.indexOf(")") != -1;
    }

    private static String removeBrackets(String expr) {
        ArrayList<String> exprLst = new ArrayList<>(Arrays.asList(expr.split(" ")));
        int openIndex = exprLst.indexOf("(");
        int closeIndex = -1;

        while (openIndex != -1) {
            closeIndex = findClosingBracketIndex(exprLst, openIndex + 1);
            Double value = calc(String.join(" ", exprLst.subList(openIndex + 1, closeIndex)));
            exprLst.subList(openIndex, closeIndex + 1).clear();
            exprLst.add(openIndex, String.valueOf(value));
            openIndex = exprLst.indexOf("(");
        }

        return String.join(" ", exprLst);
    }

    private static int findClosingBracketIndex(ArrayList<String> exprLst, int startIndex) {
        int openCnt = 1;
        int closeCnt = 0;
        for (int i = startIndex; i < exprLst.size(); i++) {
            if (exprLst.get(i).equals(")"))
                closeCnt++;
            else if (exprLst.get(i).equals("("))
                openCnt++;
            if(openCnt == closeCnt)
                return i;
        }
        return -1;
    }
}
