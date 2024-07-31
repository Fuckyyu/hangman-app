package ru.polina_project.hangman.game;

import ru.polina_project.hangman.util.HangmanStage;
import ru.polina_project.hangman.util.Masker;
import ru.polina_project.hangman.util.Parser;
import ru.polina_project.hangman.util.WordValidation;

import java.util.*;

public class Game {
    static final Set<Character> ENTERED_LETTERS = new HashSet<>();

    public static void main(String[] args) {
        startGame();
    }

    static void clearEnteredLetters() {
        ENTERED_LETTERS.clear();
    }

    static boolean wasLetterEnteredBefore(char letter) {
        return ENTERED_LETTERS.contains(letter);
    }

    static String chooseRandomWord(List<String> words) {
        return words.get(new Random().nextInt(words.size()));
    }

    static List<String> loadWords() {
        return Parser.loadWords("/Users/polynww/IdeaProjects/hangman-app/java-hangman-app/src/main/resources/words.txt");
    }

    static void InitialStateOfMaskedWord(StringBuilder maskedWord) {
        System.out.printf("""
                --------------------------------------------------------------------------------------------------------------------------------------------
                                                      Your word to decode is: %s                                      
                --------------------------------------------------------------------------------------------------------------------------------------------
                       
                """, maskedWord);
    }

    static void displayMaskedWord(StringBuilder maskedWord) {
        System.out.printf("""
                                                Type a letter to decode the word: %s                              
                ...
                """, maskedWord);
    }

    static char inputLetterForDecodedWord() {
        Scanner scanner = new Scanner(System.in);
        String line;
        while (true) {
            line = scanner.next();
            if (WordValidation.doesInputLineIsChar(line)) {
                break;
            }
            System.out.println("Input letter is incorrect!!!");
        }
        return line.charAt(0);
    }

    static boolean updateMaskedWord(String word, StringBuilder maskedWord, char letter) {
        boolean isLetterInWord = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                maskedWord.setCharAt(i, letter);
                isLetterInWord = true;
            }
        }
        return isLetterInWord;
    }

    static void isMaskedWordGuessed(String word) {
        System.out.printf("""
                --------------------------------------------------------------------------------------------------------------------------------------------
                                                    Congratulations! You've decoded the word: %s
                --------------------------------------------------------------------------------------------------------------------------------------------                
                """, word);
    }

    static void isSuggestedLetterExistInMaskedWord(StringBuilder maskedWord) {
        System.out.printf("""
                --------------------------------------------------------------------------------------------------------------------------------------------              
                                                    Good result! Keep guessing! Your word is looking now: %s
                --------------------------------------------------------------------------------------------------------------------------------------------                
                """, maskedWord);
    }

    static int isEnteredWrongLetter(int attemptsCounter, String word) {
        attemptsCounter--;
        if (attemptsCounter > 0) {
            System.out.printf("""
                    ****************************************************************************************************************************************
                                                                Your letter is wrong! You have only %s attempts.
                    ****************************************************************************************************************************************
                    """, attemptsCounter);
            System.out.println(HangmanStage.STAGES[attemptsCounter]);
        } else {
            System.out.println(HangmanStage.STAGES[0]);
            System.out.printf("""
                    ****************************************************************************************************************************************                
                                                        Your attempts are over :(  The decoded word was: %s
                    ****************************************************************************************************************************************                
                    """, word);
        }
        return attemptsCounter;
    }

    public static void startGame() {
        clearEnteredLetters();

        System.out.println("""
                --------------------------------------------------------------------------------------------------------
                 |                                    Hangman game is started!                                        |
                --------------------------------------------------------------------------------------------------------
                """);
        processGame();
    }

    static void processGame() {
        List<String> words = loadWords();
        String word = chooseRandomWord(words);
        StringBuilder maskedWord = new StringBuilder(Masker.doMask('*', word));
        InitialStateOfMaskedWord(maskedWord);

        int attemptsCounter = 8;
        while (true) {
            displayMaskedWord(maskedWord);
            char letter = inputLetterForDecodedWord();

            if (wasLetterEnteredBefore(letter)) {
                System.out.println("You've already entered this letter!");
                continue;
            }
            ENTERED_LETTERS.add(letter);

            boolean doesLetterContainsInMaskedWord = updateMaskedWord(word, maskedWord, letter);

            if (maskedWord.toString().equals(word)) {
                isMaskedWordGuessed(word);
                break;
            } else if (doesLetterContainsInMaskedWord) {
                isSuggestedLetterExistInMaskedWord(maskedWord);
            } else {
                attemptsCounter = isEnteredWrongLetter(attemptsCounter, word);
                if (attemptsCounter == 0) {
                    break;
                }
            }
        }
    }
}
