package org.simplecalc.operator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.simplecalc.exception.FunctionException;
import org.simplecalc.exception.MathException;
import org.simplecalc.operator.function.FunctionOperator;

import java.util.regex.Pattern;
@Data
@EqualsAndHashCode(callSuper=true)
public class AdvancedOperator extends MathOperator {
    private final Double result;
    private final String equation;
    public AdvancedOperator(String content) throws MathException {
        equation = content;

        while (!content.matches("-?\\d+([.,]\\d+(E\\d+)?)?")) {

            var generalFunctionPattern = FunctionOperator.getGeneralPattern();
            var mostImportantPattern = Pattern.compile("-?\\d+([,.]\\d+)? ?\\^ ?-?\\d+([,.]\\d+)?");
            var midImportantPattern = Pattern.compile("-?\\d+([,.]\\d+)? ?[*/\\\\] ?-?\\d+([,.]\\d+)?");
            var leastImportantPattern =  Pattern.compile("-?\\d+([,.]\\d+)? ?[+-] ?-?\\d+([,.]\\d+)?");

            var leastMatch = leastImportantPattern.matcher(content);
            var midMatch = midImportantPattern.matcher(content);
            var mostMatch = mostImportantPattern.matcher(content);
            var functionMatch = generalFunctionPattern.matcher(content);

            if (functionMatch.find())
                content = content.replaceFirst(generalFunctionPattern.pattern(), new FunctionOperator(functionMatch.group()).getResult().toString());
            else if (mostMatch.find())
                content = content.replaceFirst(mostImportantPattern.pattern(), new BasicOperator(mostMatch.group()).getResult().toString());
            else if (midMatch.find())
                content = content.replaceFirst(midImportantPattern.pattern(), new BasicOperator(midMatch.group()).getResult().toString());
            else if (leastMatch.find())
                content = content.replaceFirst(leastImportantPattern.pattern(), new BasicOperator(leastMatch.group()).getResult().toString());
            else throw new MathException();

        }
        result = Double.parseDouble(content);
    }
}
