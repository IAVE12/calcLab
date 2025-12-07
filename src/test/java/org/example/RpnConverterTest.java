package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

class RpnConverterTest {

    @Test
    void simplePrecedenceWithoutParentheses() {
        List<Token> tokens = Tokenizer.tokenize("2+3*4");
        List<Token> rpn = RpnConverter.toRpn(tokens);

        // ожидаем: 2 3 4 * +
        assertEquals("2", rpn.get(0).getValue());
        assertEquals("3", rpn.get(1).getValue());
        assertEquals("4", rpn.get(2).getValue());
        assertEquals("*", rpn.get(3).getValue());
        assertEquals("+", rpn.get(4).getValue());
    }

    @Test
    void parenthesesChangeOrder() {
        List<Token> tokens = Tokenizer.tokenize("(2+3)*4");
        List<Token> rpn = RpnConverter.toRpn(tokens);

        // ожидаем: 2 3 + 4 *
        assertEquals("2", rpn.get(0).getValue());
        assertEquals("3", rpn.get(1).getValue());
        assertEquals("+", rpn.get(2).getValue());
        assertEquals("4", rpn.get(3).getValue());
        assertEquals("*", rpn.get(4).getValue());
    }

    @Test
    void mismatchedParenthesesThrows() {
        List<Token> tokens = Tokenizer.tokenize("(1+2");
        assertThrows(IllegalArgumentException.class, () -> RpnConverter.toRpn(tokens));
    }
}
