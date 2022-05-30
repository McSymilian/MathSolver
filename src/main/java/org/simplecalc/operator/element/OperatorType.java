package org.simplecalc.operator.element;

import org.simplecalc.exception.MathException;
import org.simplecalc.operator.CalculationOperator;

import java.util.Arrays;

public enum OperatorType implements CalculationOperator {
    ADD("+") {
        @Override
        public Double calculate(Double... args) throws MathException {
            if (args.length < 2) throw new MathException();
            return Arrays.stream(args).reduce(.0,  Double::sum);
        }
    },
    SUB("-") {
        @Override
        public Double calculate(Double... args) throws MathException {
            if (args.length < 2) throw new MathException();
            return Arrays.stream(args).reduce((a, b) -> a - b).get();
        }
    },
    DIV("/") {
        @Override
        public Double calculate(Double... args) throws MathException {
            if (args.length != 2) throw new MathException();
            return Arrays.stream(args).reduce((a, b) -> a / b).get();
        }
    },
    MUL("*") {
        @Override
        public Double calculate(Double... args) throws MathException {
            if (args.length < 2) throw new MathException();
            return Arrays.stream(args).reduce((a, b) -> a * b).get();
        }
    },
    POW("^") {
        @Override
        public Double calculate(Double... args) throws MathException {
            if (args.length != 2) throw new MathException();
            return Arrays.stream(args).reduce(Math::pow).get();
        }
    };

    public final String value;

    OperatorType(String value) {
        this.value = value;
    }

}
