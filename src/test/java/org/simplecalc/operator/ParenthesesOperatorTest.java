package org.simplecalc.operator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.simplecalc.exception.MathException;

class ParenthesesOperatorTest {
    @Test
    public void simpleTestSet1() throws MathException {
        Assertions.assertEquals(4, new ParenthesesOperator("(2+2)").getResult());
        Assertions.assertEquals(4, new ParenthesesOperator("(2*2)").getResult());
        Assertions.assertEquals(4, new ParenthesesOperator("(2^2)").getResult());
        Assertions.assertEquals(-1, new ParenthesesOperator("(2/-2)").getResult());
        Assertions.assertEquals(2, new ParenthesesOperator("sqrt(2^2)").getResult());
        Assertions.assertEquals(2, new ParenthesesOperator("2surd(4.0)").getResult());
    }

    @Test
    public void mediumTestSet() throws MathException {
        Assertions.assertEquals(2, new ParenthesesOperator("sqrt(sqrt(16.0))").getResult());
        Assertions.assertEquals(new ParenthesesOperator("2surd(sqrt(16))").getResult(), new ParenthesesOperator("sqrt(sqrt16)").getResult());

    }

    @Test
    public void advancedTestSet1() throws MathException {
        Assertions.assertEquals(10, new ParenthesesOperator("2 + sqrt4 * 2 ^ 2").getResult());
        Assertions.assertEquals(new ParenthesesOperator("2 + sqrt(2+2) * 2 ^ 2").getResult(), new ParenthesesOperator("2 + sqrt4 * 2 ^ 2").getResult());
    }
}