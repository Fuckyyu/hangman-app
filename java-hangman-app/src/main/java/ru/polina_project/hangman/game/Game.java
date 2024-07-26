package ru.polina_project.hangman.game;

import ru.polina_project.hangman.util.HangmanStage;
import ru.polina_project.hangman.util.Masker;
import ru.polina_project.hangman.util.Parser;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    static boolean IS_GAME_ENABLED = Boolean.parseBoolean(System.getenv("IS_GAME_ENABLED"));
    static int ATTEMPTS = Integer.parseInt(System.getenv("ATTEMPTS"));


    public static void startGame() {
        System.out.println("""
                --------------------------------------------------------------------------------------------------------
                 |                                    Hangman game is started!                                        |
                --------------------------------------------------------------------------------------------------------
                """);
        processGame();
    }

    private static String chooseRandomWord(List<String> words) {
        return words.get(new Random().nextInt(words.size()));
    }

    private static void processGame() {
        List<String> words = Parser.loadWords("/Users/polynww/IdeaProjects/hangman-app/java-hangman-app/src/main/resources/words.txt");
        String word = chooseRandomWord(words);
        StringBuilder maskedWord = new StringBuilder(Masker.doMask('*', word));

        System.out.printf("""
                --------------------------------------------------------------------------------------------------------
                |                                      Your word to decode is: %s                                      |
                --------------------------------------------------------------------------------------------------------
                       \s
                %n""", maskedWord);

        while (IS_GAME_ENABLED) {
            System.out.println("""
                                        
                    ----------------------------------------------------------------------------------------------------
                     |                                Type a letter of decoded word:                                  |
                    ----------------------------------------------------------------------------------------------------
                    """);
            Scanner scanner = new Scanner(System.in);
            String line = scanner.next();

            // сделать валидацию на приходящий символ
            // char

            boolean isSuggestedLetterExistInMaskedWord = false; // сделать нейминг
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == line.charAt(0)) {
                    maskedWord.setCharAt(i, line.charAt(0));
                    isSuggestedLetterExistInMaskedWord = true;
                }
            }

            if (maskedWord.toString().equals(word)) {
                System.out.printf("""
                                        --------------------------------------------------------------------------------------------
                                                    Congratulations! You've decoded the word: %s
                                        --------------------------------------------------------------------------------------------
                        %n""", word);
                IS_GAME_ENABLED = false;
            } else if (isSuggestedLetterExistInMaskedWord) {
                System.out.printf("""
                                        --------------------------------------------------------------------------------------------
                                                    Good result! Keep guessing! Your word is looking now: %s
                                        --------------------------------------------------------------------------------------------
                        %n""", maskedWord);
            } else {
                ATTEMPTS--;

                System.out.printf("""
                        --------------------------------------------------------------------------------------------
                                       Your letter is wrong! You have only %s attempts. Y
                        --------------------------------------------------------------------------------------------
                        %n""", ATTEMPTS);

                if (ATTEMPTS > 0) {
                    System.out.println(HangmanStage.STAGES[HangmanStage.STAGES.length - ATTEMPTS]);
                } else {
                    System.out.printf("""
                                    --------------------------------------------------------------------------------------------
                                                Your attempts are over :(  The decoded word was: %s
                                    --------------------------------------------------------------------------------------------
                    %n""", word);
                    IS_GAME_ENABLED = false;
                }
            }
        }
    }
}
