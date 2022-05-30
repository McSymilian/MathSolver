package org.simplecalc.operator;

import org.simplecalc.exception.MathException;

public interface CalculationOperator {
    Double calculate(Double... args) throws MathException;
}
