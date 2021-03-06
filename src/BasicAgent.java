import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by daniel on 4.10.16.
 */
public class BasicAgent {
    enum Operator {PLUS, MULTIPLY, DIVIDE, MINUS};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Token t = evaluate(input);

        System.out.println(t.getFloat().toString());
        System.out.println();
    }

    /**
     * Priority operatoru
     * 1. výrazy v zátvorkách     ( )
     * 2. funkcie
     * 3. umocnenie ^
     * 4. polarita + -
     * 5. násobenie *
     *    delenie /
     * 6. sčítanie +
     *    odčítanie -
     * 7. relácia "je rovný" =
     *    relácia "je menší" <
     *    relácia "je väčší" >
     *    relácia "je rovný alebo menší" <=
     *    relácia "nerovná sa" <>
     *    relácia "je rovný alebo väčší" >=
     * 8. dvojkový komplement NOT
     * 9. logický súčin AND
     *    logický súčet OR
     *
     * @param input
     * @return
     */
    public static Token evaluate(String input) {
        Token returnValue = null;
        input = input.trim();
        if(input.length() > 0) {
            while(input.startsWith("(") && input.endsWith(")")) {
                input = input.substring(1);
                input = input.substring(0, input.length() - 1);
            }

            int bracketCount = 0;
            int dividerPosition = -1;
            int dividerPriority = 0;
            Operator operator = null;

            for (int i = 0; i < input.length(); i++) {
                String chr = input.substring(i, i + 1);
                if(chr.equals("(")) {
                    bracketCount++;
                }
                if(chr.equals(")")) {
                    bracketCount--;
                }

                if(bracketCount == 0) {
                    if(chr.equals("+")) {
                        if(dividerPriority <= 6) {
                            dividerPosition = i;
                            dividerPriority = 6;
                            operator = Operator.PLUS;
                        }
                    } else if (chr.equals("-")) {
                        int i2 = i;
                        boolean isOperator = true;
                        while(i2 > -1 && isOperator){
                            if(input.substring(i2, i2+1).matches("[-*/+]")) {
                                isOperator = false;
                            }

                            if(input.substring(i2, i2+1).equals(")")) {
                                i2 = -1;
                            } else {
                                i2++;
                            }
                        }

                        if(isOperator) {
                            if(dividerPriority <= 6) {
                                dividerPosition = i;
                                dividerPriority = 6;
                                operator = Operator.MINUS;
                            }
                        }
                    } else if (chr.equals("*")) {
                        if(dividerPriority <= 5) {
                            dividerPosition = i;
                            dividerPriority = 5;
                            operator = Operator.MULTIPLY;
                        }
                    } else if (chr.equals("/")) {
                        if(dividerPriority <= 5) {
                            dividerPosition = i;
                            dividerPriority = 5;
                            operator = Operator.DIVIDE;
                        }
                    }
                }

            }

            if(dividerPriority > 0) {
                String left = input.substring(0, dividerPosition);
                String right = input.substring(dividerPosition + 1);
                Token leftEval = evaluate(left);
                Token rightEval = evaluate(right);
                switch (operator) {
                    case PLUS:
                        if(leftEval.isNumber() && rightEval.isNumber()) {
                            Float value = leftEval.getFloat() + rightEval.getFloat();
                            returnValue = new Token(Token.Type.NUMBER, value);
                        }
                        break;
                    case MINUS:
                        returnValue = new Token(Token.Type.NUMBER, leftEval.getFloat() - rightEval.getFloat());
                        break;
                    case MULTIPLY:
                        returnValue = new Token(Token.Type.NUMBER, leftEval.getFloat() * rightEval.getFloat());
                        break;
                    case DIVIDE:
                        returnValue = new Token(Token.Type.NUMBER, leftEval.getFloat() / rightEval.getFloat());
                        break;
                }

                System.out.println();
            } else {
                returnValue = new Token(Token.Type.NUMBER, Float.parseFloat(input));
                System.out.println(input);
            }
        }

        return returnValue;
    }
}