package org.simplecalc.operator;

import lombok.Data;
import org.simplecalc.exception.MathException;
import org.simplecalc.operator.element.Element;
import org.simplecalc.operator.element.Number;
import org.simplecalc.operator.element.Operator;
import org.simplecalc.operator.element.Operator.Type;

import java.util.Locale;
import java.util.regex.Pattern;

@Data
public class BasicOperator {
    public final Element<?>[] elements = new Element[3];
    private final Double result;
    private final String equation;

    public BasicOperator(String content) throws MathException {
        equation = content;
        var match = Pattern
                .compile(
                        "(?<first>-?\\d+([,.]\\d+)?) ?" +
                                "(?<operator>[+\\-/\\\\*^]|(sqrt?)) ?" +
                                "(?<second>-?\\d+([,.]\\d+)?)")
                .matcher(
                        content.trim()
                                .toLowerCase(Locale.ROOT));

        if (match.matches()) {
            elements[0] = new Number<>(
                    Double.parseDouble(
                            match.group("first")
                                    .replace(",", ".")
                    )
            );
            elements[2] = new Number<>(
                    Double.parseDouble(
                            match.group("second")
                                    .replace(",", ".")
                    )
            );

            elements[1] = switch (match.group("operator")) {
                case "+" -> new Operator(Type.ADD);
                case "-" -> new Operator(Type.SUB);
                case "*" -> new Operator(Type.MUL);
                case "/","\\\\" -> new Operator(Type.DIV);
                case "^" -> new Operator(Type.POW);
                default -> new Operator(Type.SUR);
            };
            result = count(elements);
        }
        else throw new MathException("Syntax Error: " + equation);
    }

    public static Double count(Element<?>[] elements) {
        return switch ((Type) elements[1].value) {
            case ADD -> (double)elements[0].value + (double)elements[2].value;
            case SUB -> (double)elements[0].value - (double)elements[2].value;
            case MUL -> (double)elements[0].value * (double)elements[2].value;
            case DIV -> (double)elements[0].value / (double)elements[2].value;
            case POW -> Math.pow((double)elements[0].value, (double)elements[2].value);
            case SUR -> Math.pow((double)elements[2].value, 1/(double)elements[0].value);
        };
    }
}
