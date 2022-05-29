package org.simplecalc.operator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.simplecalc.exception.MathException;
import org.simplecalc.exception.ParenthesesException;

import java.util.ArrayList;
import java.util.regex.Pattern;

@Data
@EqualsAndHashCode(callSuper=true)
public class ParenthesesOperator extends MathOperator {

    private final Double result;
    private final String equation;

    public ParenthesesOperator(String content) throws MathException {
        equation = content;

        if (!checkParentheses(content)) throw new ParenthesesException("Syntax Error: ");

        content = content.replace(")(", ")*(");
        var multiplyParPattern = Pattern.compile("(?<value>\\d+(,\\d+)?)\\(");
        var multiplyParMatch = multiplyParPattern.matcher(content);
        while (multiplyParMatch.find())
            content = content.replaceFirst(multiplyParPattern.pattern(), multiplyParMatch.group("value") + "*(");

        var pattern = Pattern.compile("[(\\[{] ?([^(){}\\[\\]]+) ?[)\\]}]");

        while (content.matches(".*" + pattern.pattern() + ".*")) {
            var parMatch = pattern.matcher(content);

            while (parMatch.find()) {
                content = content.replaceFirst(pattern.pattern(), new AdvancedOperator(parMatch.group(1)).getResult().toString());
            }
        }

        result = new AdvancedOperator(content).getResult();


    }

    private boolean checkParentheses (String content) {
        var opened = new ArrayList<Character>();
        for (int i = 0; i < content.length(); i++) {
            switch (content.charAt(i)) {
                case '(', '[', '{' -> opened.add(content.charAt(i));
                case ')' -> {
                    if (opened.size() > 0 && opened.get(opened.size() - 1) == '(') {
                        opened.remove(opened.size() - 1);
                    } else
                        return false;
                }
                case ']' -> {
                    if (opened.size() > 0 && opened.get(opened.size() - 1) == '[') {
                        opened.remove(opened.size() - 1);
                    } else
                        return false;
                }
                case '}' -> {
                    if (opened.size() > 0 && opened.get(opened.size() - 1) == '{') {
                        opened.remove(opened.size() - 1);
                    } else
                        return false;
                }
            }
        }

        return opened.size() == 0;
    }
}
