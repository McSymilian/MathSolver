package org.simplecalc.operator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.simplecalc.exception.MathException;

class ParenthesesOperatorTest {

    //todo more tests
    @Test
    public void simpleTestSet1() throws MathException {
        Assertions.assertEquals(4, new ParenthesesOperator("(2+2)").getResult());
        Assertions.assertEquals(4, new ParenthesesOperator("(2*2)").getResult());
        Assertions.assertEquals(4, new ParenthesesOperator("(2^2)").getResult());
        Assertions.assertEquals(-1, new ParenthesesOperator("(2/-2)").getResult());
        Assertions.assertEquals(2, new ParenthesesOperator("sqrt(2^2)").getResult());
    }

    @Test
    public void advancedTestSet1() throws MathException {
        Assertions.assertEquals(10, new ParenthesesOperator("2 + sqrt4 * 2 ^ 2").getResult());
        Assertions.assertEquals(new ParenthesesOperator("2 + sqrt(2+2) * 2 ^ 2").getResult(), new ParenthesesOperator("2 + sqrt4 * 2 ^ 2").getResult());
    }
}