package org.example;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

class TokenizerTest {

    @Test
    void tokenizeSimpleExpression() {
        List<Token> tokens = Tokenizer.tokenize("2+3*4");
        assertEquals(5, tokens.size());
        assertEquals("2", tokens.get(0).getValue());
        assertEquals("+", tokens.get(1).getValue());
        assertEquals("3", tokens.get(2).getValue());
        assertEquals("*", tokens.get(3).getValue());
        assertEquals("4", tokens.get(4).getValue());
    }

    @Test
    void tokenizeWithSpacesAndParentheses() {
        List<Token> tokens = Tokenizer.tokenize(" ( 1 + 2 ) * 3 ");
        assertEquals(7, tokens.size());
        assertEquals(TokenType.LEFT_PAREN, tokens.get(0).getType());
        assertEquals("1", tokens.get(1).getValue());
        assertEquals("+", tokens.get(2).getValue());
        assertEquals("2", tokens.get(3).getValue());
        assertEquals(TokenType.RIGHT_PAREN, tokens.get(4).getType());
    }

    @Test
    void tokenizeInvalidCharacterThrows() {
        assertThrows(IllegalArgumentException.class, () -> Tokenizer.tokenize("2+3#a"));
    }
}
