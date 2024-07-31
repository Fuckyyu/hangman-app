package ru.polina_project.hangman.game;

import com.github.stefanbirkner.systemlambda.SystemLambda;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ru.polina_project.hangman.util.Masker;
import ru.polina_project.hangman.util.Parser;
import ru.polina_project.hangman.util.WordValidation;

import java.util.List;

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


    @Test
    void testProcessGameSuccess() throws Exception {
        try (MockedStatic<Parser> parserMock = Mockito.mockStatic(Parser.class);
             MockedStatic<Masker> maskerMock = Mockito.mockStatic(Masker.class);
             MockedStatic<WordValidation> wordValidationMock = Mockito.mockStatic(WordValidation.class)) {

            parserMock.when(() -> Parser.loadWords(anyString())).thenReturn(List.of("apple"));
            maskerMock.when(() -> Masker.doMask('*', "apple")).thenReturn("*****");
            wordValidationMock.when(() -> WordValidation.doesInputLineIsChar(anyString())).thenReturn(true);

            String input = "a\np\np\nl\ne\n";
            String expectedOutput = """
                    --------------------------------------------------------------------------------------------------------------------------------------------
                                                          Your word to decode is: *****
                    --------------------------------------------------------------------------------------------------------------------------------------------
                    
                                                    Type a letter to decode the word: *****
                    ...
                    --------------------------------------------------------------------------------------------------------------------------------------------
                                                        Good result! Keep guessing! Your word is looking now: a****
                    --------------------------------------------------------------------------------------------------------------------------------------------
                                                    Type a letter to decode the word: a****
                    ...
                    --------------------------------------------------------------------------------------------------------------------------------------------
                                                        Good result! Keep guessing! Your word is looking now: app**
                    --------------------------------------------------------------------------------------------------------------------------------------------
                                                    Type a letter to decode the word: app**
                    ...
                    You've already entered this letter!
                                                    Type a letter to decode the word: app**
                    ...
                    --------------------------------------------------------------------------------------------------------------------------------------------
                                                        Good result! Keep guessing! Your word is looking now: appl*
                    --------------------------------------------------------------------------------------------------------------------------------------------
                                                    Type a letter to decode the word: appl*
                    ...
                    --------------------------------------------------------------------------------------------------------------------------------------------
                                                        Congratulations! You've decoded the word: apple
                    --------------------------------------------------------------------------------------------------------------------------------------------
                    """;

            String actualOutput = SystemLambda.tapSystemOut(() ->
                    SystemLambda.withTextFromSystemIn(input).execute(Game::processGame)
            );

            assert actualOutput.contains(expectedOutput);
        }
    }
}
