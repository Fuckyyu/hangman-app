package ru.polina_project.hangman.util

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test

class WordValidationTest {
    @Test
    fun testDoesInputLineIsChar_ValidInput() {
        assertTrue(doesInputLineIsChar("a"))
        assertTrue(doesInputLineIsChar("Z"))
    }

    @Test
    fun testDoesInputLineIsChar_InvalidInput() {

        assertFalse(doesInputLineIsChar(""))
        assertFalse(doesInputLineIsChar("1"))
        assertFalse(doesInputLineIsChar("ab"))
        assertFalse(doesInputLineIsChar(" "))
    }
}