package org.simplecalc.operator.function;

import lombok.Getter;

public enum AdvancedFunctionType {
    SURD("surd", "srd") {
        @Override
        public Double method(Double value, Double additive) {
            return Math.pow(value, 1 / additive);
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

    abstract Double method(Double value, Double additive);
}
