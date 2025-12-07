package org.example;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CalculatorIntegrationTest {

    @Test
    void calculateComplexExpression() {
        double result = Calculator.calculate("(1 + 2) * (3 + 4) - 5");
        assertEquals(16.0, result, 1e-9);
    }

    @Test
    void calculateWithSpaces() {
        double result = Calculator.calculate(" 2 + 3 * 4 ");
        assertEquals(14.0, result, 1e-9);
    }
}
