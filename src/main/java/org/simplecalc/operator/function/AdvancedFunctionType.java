package org.simplecalc.operator.function;

import lombok.Getter;
import org.simplecalc.exception.FunctionException;
import org.simplecalc.operator.CalculationOperator;

public enum AdvancedFunctionType implements CalculationOperator {
    SURD("surd", "srd") {
        @Override
        public Double calculate(Double... args) throws FunctionException {
            if (args.length != 2) throw new FunctionException(this, args.length);

            return Math.pow(args[0], 1 / args[1]);
        }
    };

    @Getter
    private final String defaultSyntax;

    @Getter
    private final String spareSyntax;

    @Getter
    @Deprecated
    private static Boolean radianScale = false;

    AdvancedFunctionType(String defaultSyntax, String spareSyntax) {
        this.defaultSyntax = defaultSyntax;
        this.spareSyntax = spareSyntax;
    }
}
