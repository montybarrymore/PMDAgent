import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

        while(input.length() > 0) {
            String symbol = input.substring(0,1);

            // oddelovac
            if(symbol.matches("[ ,]")) {
                input = input.substring(1);
            } else if(symbol.matches("[0-9-]")) {
                String substring = symbol;
                input = input.substring(1);

                boolean isNumber = true;
                do {
                    symbol = input.substring(0,1);
                    if(symbol.matches("[0-9.]")) {
                        substring = substring + symbol;
                    } else {
                        isNumber = false;
                    }

                    input = input.substring(1);

                    if(input.length() == 0) {
                        isNumber = false;
                    }

                } while(isNumber);

                System.out.println(substring);
            }
        }
        return null;
    }
}
