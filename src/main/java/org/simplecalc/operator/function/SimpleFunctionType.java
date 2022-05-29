package org.simplecalc.operator.function;

import lombok.Getter;

public enum SimpleFunctionType {
    SQRT("sqrt", "sqr") {
        @Override
        public Double method(Double value) {
            return Math.sqrt(value);
        }
    },
    SIN("sin", "sinus") {
        @Override
        public Double method(Double value) {
            if (!radianScale)
                value = value * Math.PI / 180.0;
            return Math.sin(value);
        }
    },
    COS("cos", "cosinus") {
        @Override
        public Double method(Double value) {
            if (!radianScale)
                value = value * Math.PI / 180.0;
            return Math.cos(value);
        }
    },
    TG("tan", "tg") {
        @Override
        public Double method(Double value) {
            if (!radianScale)
                value = value * Math.PI / 180.0;
            return Math.tan(value);
        }
    },
    CTG("cotan", "ctg") {
        @Override
        public Double method(Double value) {
            if (!radianScale)
                value = value * Math.PI / 180.0;
            return 1 / Math.tan(value);
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

    @Getter
    private static Boolean radianScale = false;

    abstract Double method(Double value);

}
