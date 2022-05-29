package org.simplecalc.operator.element;

import lombok.Getter;

import java.util.regex.Pattern;

public class Operator extends Element<Operator.Type> {
    @Getter
    private static final Pattern pattern = Pattern.compile("[+\\-*/\\\\^]");
    public Operator(Type value) {
        super(value);
    }

    public enum Type {
        ADD("+"), SUB("-"),
        DIV("/"), MUL("*"),
        POW("^");

        public final String value;

        Type(String value) {
            this.value = value;
        }
    }
}
