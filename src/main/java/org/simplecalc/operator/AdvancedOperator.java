package org.simplecalc.operator;

import lombok.Data;
import org.simplecalc.exception.MathException;

import java.util.regex.Pattern;
@Data
public class AdvancedOperator {
    private final Double result;
    private final String equation;
    public AdvancedOperator(String content) throws MathException {
        equation = content;

        while (!content.matches("-?\\d+([.,]\\d+(E\\d+)?)?")) {
            //todo what happens when "sqrt 15"?
            //todo sqr
            content = content.replaceAll("sqrt", "2sqr");


            var mostImportantPattern = Pattern.compile("-?\\d+([,.]\\d+)? ?(\\^|(sqr)) ?-?\\d+([,.]\\d+)?");
            var midImportantPattern = Pattern.compile("-?\\d+([,.]\\d+)? ?[*/\\\\] ?-?\\d+([,.]\\d+)?");
            var leastImportantPattern =  Pattern.compile("-?\\d+([,.]\\d+)? ?[+-] ?-?\\d+([,.]\\d+)?");

            var leastMatch = leastImportantPattern.matcher(content);
            var midMatch = midImportantPattern.matcher(content);
            var mostMatch = mostImportantPattern.matcher(content);


            if (mostMatch.find()) {
                content = content.replaceFirst(mostImportantPattern.pattern(), new BasicOperator(mostMatch.group()).getResult().toString());
            } else if (midMatch.find()) {
                content = content.replaceFirst(midImportantPattern.pattern(), new BasicOperator(midMatch.group()).getResult().toString());
            } else if (leastMatch.find()) {
                content = content.replaceFirst(leastImportantPattern.pattern(), new BasicOperator(leastMatch.group()).getResult().toString());
            }
            else {
                throw new MathException("Syntax error: " + content);
            }

        }
        result = Double.parseDouble(content);
    }
}
