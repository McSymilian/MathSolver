package org.simplecalc.operator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.simplecalc.exception.MathException;
import org.simplecalc.operator.element.Element;
import org.simplecalc.operator.element.Number;
import org.simplecalc.operator.element.Operator;
import org.simplecalc.operator.element.Operator.Type;

import java.util.Locale;
import java.util.regex.Pattern;

@Data
@EqualsAndHashCode(callSuper=true)
public class BasicOperator extends MathOperator {
    public final Element<?>[] elements = new Element[3];
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

        elements[0] = new Number<>(
                Double.parseDouble(
                        basicMatch.group("first"))
        );
        elements[2] = new Number<>(
                Double.parseDouble(
                        basicMatch.group("second"))
        );

        elements[1] = switch (basicMatch.group("operator")) {
            case "+" -> new Operator(Type.ADD);
            case "-" -> new Operator(Type.SUB);
            case "*" -> new Operator(Type.MUL);
            case "^" -> new Operator(Type.POW);
            default -> new Operator(Type.DIV);
        };
        result = count(elements);
    }

    public static Double count(Element<?>[] elements) {
        return switch ((Type) elements[1].value) {
            case ADD -> (double)elements[0].value + (double)elements[2].value;
            case SUB -> (double)elements[0].value - (double)elements[2].value;
            case MUL -> (double)elements[0].value * (double)elements[2].value;
            case DIV -> (double)elements[0].value / (double)elements[2].value;
            case POW -> Math.pow((double)elements[0].value, (double)elements[2].value);
        };
    }
}
