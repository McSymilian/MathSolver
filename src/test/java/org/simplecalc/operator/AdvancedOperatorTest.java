package org.simplecalc.operator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.simplecalc.exception.MathException;

class AdvancedOperatorTest {
    @Test
    public void simpleTestSet() throws MathException {
        Assertions.assertEquals(5, new AdvancedOperator("1 + 2 * 2").getResult());
        Assertions.assertEquals(Math.sqrt(15), new AdvancedOperator("sqrt15").getResult());
        Assertions.assertEquals(Math.sqrt(Math.sqrt(16)), new AdvancedOperator("4surd16").getResult());
    }

    @Test
    public void mediumTestSet() throws MathException {
        Assertions.assertEquals(5, new AdvancedOperator("1 + 2 * 2").getResult());
        Assertions.assertEquals(5, new AdvancedOperator("1 + 2 ^ 2").getResult());
        Assertions.assertEquals(Math.sqrt(16), new AdvancedOperator("sqrt16").getResult());
        Assertions.assertEquals(2, new AdvancedOperator("4^0.5").getResult());
    }

    @Test
    public void advancedTestSet() throws MathException {
        Assertions.assertEquals(10, new AdvancedOperator("2 + 2 * 2 ^ 2").getResult());
        Assertions.assertEquals(8, new AdvancedOperator("2 surd4 * 2 ^ 2").getResult());
        Assertions.assertEquals(8, new AdvancedOperator("sqrt4 * 2 ^ 2").getResult());
        Assertions.assertEquals(10, new AdvancedOperator("2 + sqrt4 * 2 ^ 2").getResult());
        Assertions.assertEquals(24.9, new AdvancedOperator("10 + 2 * 2 ^ 2 + 3 * 2,3").getResult());
    }

}