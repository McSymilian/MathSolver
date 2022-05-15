package org.simplecalc.operator.element;

public class Number<T> extends Element<T> {
    public final T value;

    public Number(T value) {
        super(value);
        this.value = value;
    }
}
