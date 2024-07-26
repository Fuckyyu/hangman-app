package ru.polina_project.hangman.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaskerTest {

    @Test
    void doMask() {
        // test input
        String testWordToMask = "polina";
        char testSymbolToMask = '*';

        // test result
        String expectedResult = "******";

        assertEquals(Masker.doMask(testSymbolToMask, testWordToMask), expectedResult);
    }
}