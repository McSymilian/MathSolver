package org.simplecalc.operator.element;

import lombok.Getter;

import java.util.regex.Pattern;

public class Number<T> extends Element<T> {
    @Getter
    private static final Pattern advancedPattern = Pattern.compile("(?<integer>-?\\d+)(?<fraction>[,.]\\d+)?");
    @Getter
    private static final Pattern simplePattern = Pattern.compile("-?\\d+([,.]\\d+)?");

    public final T value;

    public Number(T value) {
        super(value);
        this.value = value;
    }
}
