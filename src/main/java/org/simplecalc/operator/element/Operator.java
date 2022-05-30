package org.simplecalc.operator.element;

import lombok.Getter;

import java.util.regex.Pattern;

public class Operator extends Element<OperatorType> {
    @Getter
    private static final Pattern pattern = Pattern.compile("[+\\-*/\\\\^]");
    public Operator(OperatorType value) {
        super(value);
    }
}
