package org.simplecalc.operator.function;

import lombok.Getter;
import lombok.Setter;
import org.simplecalc.exception.FunctionException;
import org.simplecalc.operator.CalculationOperator;

public enum SimpleFunctionType implements CalculationOperator {
    SQRT("sqrt", "sqr") {
        @Override
        public Double calculate(Double... args) throws FunctionException {
            if (args.length > 1) throw new FunctionException(this, args.length);

            return Math.sqrt(args[0]);
        }
    },
    SIN("sin", "sinus") {
        @Override
        public Double calculate(Double... args) throws FunctionException {
            if (args.length > 1) throw new FunctionException(this, args.length);

            if (!radianScale)
                args[0] = args[0] * Math.PI / 180.0;
            return Math.sin(args[0]);
        }
    },
    COS("cos", "cosinus") {
        @Override
        public Double calculate(Double... args) throws FunctionException {
            if (args.length > 1) throw new FunctionException(this, args.length);

            if (!radianScale)
                args[0] = args[0] * Math.PI / 180.0;
            return Math.cos(args[0]);
        }
    },
    TG("tan", "tg") {
        @Override
        public Double calculate(Double... args) throws FunctionException {
            if (args.length > 1) throw new FunctionException(this, args.length);

            if (!radianScale)
                args[0] = args[0] * Math.PI / 180.0;
            return Math.tan(args[0]);
        }
    },
    CTG("cotan", "ctg") {
        @Override
        public Double calculate(Double... args) throws FunctionException {
            if (args.length > 1) throw new FunctionException(this, args.length);

            if (!radianScale)
                args[0] = args[0] * Math.PI / 180.0;
            return 1 / Math.tan(args[0]);
        }
    };

    SimpleFunctionType(String defaultSyntax, String spareSyntax) {
        this.defaultSyntax = defaultSyntax;
        this.spareSyntax = spareSyntax;
    }

    @Getter
    private final String defaultSyntax;

    @Getter
    private final String spareSyntax;

    @Setter
    @Getter
    private static Boolean radianScale = false;

}
