package ru.polina_project.hangman.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordValidationTest {
    @Test
    public void testDoesInputLineIsChar_ValidInput() {
        assertTrue(WordValidation.doesInputLineIsChar("a"));
        assertTrue(WordValidation.doesInputLineIsChar("Z"));
    }

    @Test
    public void testDoesInputLineIsChar_InvalidInput() {

        assertFalse(WordValidation.doesInputLineIsChar(""));
        assertFalse(WordValidation.doesInputLineIsChar("1"));
        assertFalse(WordValidation.doesInputLineIsChar("ab"));
    }
}