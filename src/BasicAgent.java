import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by daniel on 4.10.16.
 */
public class BasicAgent {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input =br.readLine();
        Token t = evaluate(input);

        System.out.println();
    }

    public static Token evaluate(String input) {
        ArrayList<Token> infixList = new ArrayList<>();

        if (input.length() > 0) {
            int index = 0;
            do {
                String symbol = input.substring(index, index + 1);
                // oddelovac
                if (symbol.matches("[ ,]")) {
                    index++;
                    System.out.println(symbol);
                }
                // NUMBER
                else if(symbol.matches("-") && input.substring(index + 1, index + 2).matches("[0-9]") ||
                        symbol.matches("[0-9]")) {
                    String substring = symbol;
                    index++;

                    boolean isNumber = true;
                    do {
                        if(index < input.length()) {
                            symbol = input.substring(index, index + 1);
                            if (symbol.matches("[0-9.]")) {
                                substring = substring + symbol;
                            } else {
                                isNumber = false;
                            }
                            index++;
                        } else {
                            isNumber = false;
                        }
                    } while(isNumber);
                    infixList.add(new Token(Token.Type.NUMBER, Float.parseFloat(substring)));

                // ostatni
                } else {
                    index++;
                }
            } while (index < input.length()) ;
        }
        return null;
    }
}
/*
            // cislo
            } else if(symbol.matches("[0-9-]")) {
                String substring = symbol;
                input = input.substring(1);

                boolean isNumber = true;
                do {
                    if(input.length() > 0) {
                        symbol = input.substring(0, 1);
                        if (symbol.matches("[0-9.]")) {
                            substring = substring + symbol;
                        } else {
                            isNumber = false;
                        }
                        input = input.substring(1);
                    } else {
                        isNumber = false;
                    }
                } while(isNumber);
                infixList.add(new Token(Token.Type.NUMBER, Float.parseFloat(substring)));
            }
        }

        for(Token t : infixList) {
            if(t.isNumber()) {
                System.out.println(t.getValue());
            }
        }
        System.out.println(infixList);
        */