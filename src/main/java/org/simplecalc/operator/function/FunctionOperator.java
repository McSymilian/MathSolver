package org.simplecalc.operator.function;

import lombok.Getter;
import org.simplecalc.exception.FunctionException;
import org.simplecalc.exception.MathException;
import org.simplecalc.operator.MathOperator;
import org.simplecalc.operator.element.Number;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.simplecalc.operator.function.SimpleFunctionType.*;

public class FunctionOperator extends MathOperator {
    @Getter
    private final Double result;
    @Getter
    private final String equation;

    @Getter
    private static final Pattern simplePattern;

    @Getter
    private static final Pattern advancedPattern;

    @Getter
    private static final Pattern generalPattern;
    static {
        var simpleStream = getSimpleStream();

        String simplePrototype = simpleStream
                .collect(
                        Collectors.joining(
                                "|",
                                "(?<name>",
                                ")(?<value>" + Number.getSimplePattern().pattern() + ")"
                        )
                );

        simplePattern = Pattern.compile(simplePrototype);

        Stream<String> advancedStream = getAdvancedStream();

        String advancedPrototype = advancedStream
                .collect(
                        Collectors.joining(
                                "|",
                                "(?<additive>" + Number.getSimplePattern().pattern() + ")(?<name>",
                                ")(?<value>" + Number.getSimplePattern().pattern() + ")"
                        )
                );

        advancedPattern = Pattern.compile(advancedPrototype);

        String generalPrototype = Stream
                .concat(getAdvancedStream(), getSimpleStream())
                .collect(
                        Collectors.joining(
                                "|",
                                "(" + Number.getSimplePattern().pattern() + " ?)?(?<name>",
                                ")(?<value>" + Number.getSimplePattern().pattern() + ")"
                        )
                );
        generalPattern = Pattern.compile(generalPrototype);
    }

    private static Stream<String> getSimpleStream() {
        return Arrays.stream(values())
                .map(type -> "(" + type.getDefaultSyntax() + ")|(" + type.getSpareSyntax() + ")");
    }

    private static Stream<String> getAdvancedStream() {
        return Arrays.stream(AdvancedFunctionType.values())
                .map(type -> "(" + type.getDefaultSyntax() + ")|(" + type.getSpareSyntax() + ")");
    }

    public FunctionOperator(String equation) throws MathException {
        this.equation = equation;
        equation = equation.replace(" ", "");

        var simpleMatch = simplePattern.matcher(equation);
        var advancedMatch = advancedPattern.matcher(equation);

        String funName;
        if (advancedMatch.matches()) {
            funName = advancedMatch.group("name");
            result = Arrays.stream(AdvancedFunctionType.values())
                    .filter(functionType -> funName.equals(functionType.getDefaultSyntax()) || funName.equals(functionType.getSpareSyntax()))
                    .limit(1)
                    .toList()
                    .get(0)
                    .calculate(Double.parseDouble(advancedMatch.group("value")), Double.parseDouble(advancedMatch.group("additive")));
            return;
        }

        if (!simpleMatch.matches())
            throw new FunctionException();

        funName = simpleMatch.group("name");

        result = Arrays.stream(values())
                .filter(functionType -> funName.equals(functionType.getDefaultSyntax()) || funName.equals(functionType.getSpareSyntax()))
                .limit(1)
                .toList()
                .get(0)
                .calculate(Double.parseDouble(simpleMatch.group("value")));
    }
}
