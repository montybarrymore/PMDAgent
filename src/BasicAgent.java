import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by daniel on 4.10.16.
 */
public class BasicAgent {
    enum Operator {PLUS};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Float t = evaluate(input);

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
    public static Float evaluate(String input) {
        Float returnValue = null;
        if(input.length() > 0) {
            int bracketCount = 0;
            int dividerPosition = -1;
            int dividerPriority = 1000;
            Operator operator = null;

            for (int i = 0; i < input.length() - 2; i++) {
                String chr = input.substring(i, i + 1);
                if(chr.equals("(")) {
                    bracketCount++;
                }
                if(chr.equals(")")) {
                    bracketCount--;
                }

                if(bracketCount == 0) {
                    String trigram = input.substring(i, i + 3);
                    if(trigram.equals(" + ")) {
                        if(dividerPriority >= 6) {
                            dividerPosition = i + 1;
                            dividerPriority = 6;
                            operator = Operator.PLUS;
                        }
                    }
                }

            }

            if(dividerPriority < 1000) {
                String left = input.substring(0, dividerPosition - 1);
                String right = input.substring(dividerPosition + 2);
                switch (operator) {
                    case PLUS:
                        returnValue = evaluate(left) + evaluate(right);
                }

                System.out.println();
            } else {
                returnValue = Float.parseFloat(input);
                System.out.println(input);
            }
        }

        return returnValue;
    }
}