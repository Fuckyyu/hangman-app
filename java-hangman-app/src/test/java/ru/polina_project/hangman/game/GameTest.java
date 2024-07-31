package ru.polina_project.hangman.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ru.polina_project.hangman.util.Parser;
import ru.polina_project.hangman.util.WordValidation;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameTest {

    @Test
    void testClearEnteredLetters() {
        Game.ENTERED_LETTERS.add('a');
        Game.clearEnteredLetters();
        assertFalse(Game.ENTERED_LETTERS.contains('a'));
    }

    @Test
    void testWasLetterEnteredBefore() {
        char letter = 'a';
        assertFalse(Game.wasLetterEnteredBefore(letter));
        Game.ENTERED_LETTERS.add(letter);
        assertTrue(Game.wasLetterEnteredBefore(letter));
    }
    @Test
    void testUpdateMaskedWord() {
        String word = "apple";
        StringBuilder maskedWord = new StringBuilder("*****");
        char letter = 'p';
        boolean result = Game.updateMaskedWord(word, maskedWord, letter);
        assertTrue(result);
        assertEquals("*pp**", maskedWord.toString());
    }
    @Test
    void testIsEnteredWrongLetter() {
        int attemptsCounter = 2;
        String word = "apple";
        int newAttemptsCounter = Game.isEnteredWrongLetter(attemptsCounter, word);
        assertEquals(1, newAttemptsCounter);
    }

}
