package org.simplecalc.operator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.simplecalc.exception.MathException;
import org.simplecalc.operator.element.Element;
import org.simplecalc.operator.element.Number;
import org.simplecalc.operator.element.Operator;
import org.simplecalc.operator.element.Operator.Type;

import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Pattern;

@Data
@EqualsAndHashCode(callSuper=true)
public class BasicOperator extends MathOperator {
    private final Double result;
    private final String equation;
    private final Pattern basicPattern = Pattern.compile(
            "(?<first>" + Number.getSimplePattern().pattern() + ") ?" +
                    "(?<operator>" + Operator.getPattern().pattern() + ") ?" +
                    "(?<second>" + Number.getSimplePattern().pattern() + ")");



    public BasicOperator(String content) throws MathException {
        equation = content;

        content = content
                .trim()
                .replace(",", ".")
                .toLowerCase(Locale.ROOT);

        var basicMatch = basicPattern.matcher(content);

        if (!basicMatch.matches())
            throw new MathException();

        var first = new Number<>(
                Double.parseDouble(
                        basicMatch.group("first"))
        );
        var second = new Number<>(
                Double.parseDouble(
                        basicMatch.group("second"))
        );

        result = Arrays.stream(Operator.Type.values())
                .filter(type -> basicMatch.group("operator").equals(type.value))
                .limit(1)
                .toList()
                .get(0)
                .calculate(first.value, second.value);
    }

    public static Double count(Element<?>... elements) throws MathException {
        if (elements[0] instanceof Operator operator)
            return operator.value.calculate(
                    Arrays.stream(elements)
                            .filter(element -> element instanceof Number)
                            .map(element -> Double.parseDouble(element.value.toString()))
                            .toArray(Double[]::new)
                    );

        else throw new IllegalStateException("First element must be operator");
    }
}
