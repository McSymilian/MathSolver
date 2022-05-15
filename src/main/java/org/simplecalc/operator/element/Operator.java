package org.simplecalc.operator.element;

public class Operator extends Element<Operator.Type> {
    public Operator(Type value) {
        super(value);
    }

    public enum Type {
        ADD("+"), SUB("-"),
        DIV("/"), MUL("*"),
        POW("^"), SQR("sqr");

        public final String value;

        Type(String value) {
            this.value = value;
        }
    }
}
