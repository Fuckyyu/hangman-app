package ru.polina_project.hangman.game;

import ru.polina_project.hangman.util.HangmanStage;
import ru.polina_project.hangman.util.Masker;
import ru.polina_project.hangman.util.Parser;
import ru.polina_project.hangman.util.WordValidation;

import java.util.*;

import static ru.polina_project.hangman.util.ParsingIntBooleanVariables.parseBooleanOrDefault;
import static ru.polina_project.hangman.util.ParsingIntBooleanVariables.parseIntOrDefault;


public class Game {
    static boolean IS_GAME_ENABLED = parseBooleanOrDefault(System.getenv("IS_GAME_ENABLED"), false);
    static int ATTEMPTS = parseIntOrDefault(System.getenv("ATTEMPTS"), 8);
    private static final Set<Character> enteredLetters = new HashSet<>();

    public static boolean wasLetterEnteredBefore(char letter) {
        return enteredLetters.contains(letter);
    }


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
                --------------------------------------------------------------------------------------------------------------------------------------------
                                                      Your word to decode is: %s                                      
                --------------------------------------------------------------------------------------------------------------------------------------------
                       
                """, maskedWord);

        while (IS_GAME_ENABLED) {
            System.out.printf("""
                                       
                                        
                                                    Type a letter to decode the word: %s                              
                                        
                    ...
                                        
                    """, maskedWord);

            String line;
            while (true) {
                Scanner scanner = new Scanner(System.in);
                line = scanner.next();
                if (WordValidation.doesInputLineIsChar(line)) break;
                System.out.println("Input letter is incorrect!!!");

            }
            char letter = line.charAt(0);
            if (wasLetterEnteredBefore(letter)) {
                System.out.println("You've already entered this letter!");
                continue;
            }
            enteredLetters.add(letter);


            boolean isSuggestedLetterExistInMaskedWord = false; // сделать нейминг
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == line.charAt(0)) {
                    maskedWord.setCharAt(i, line.charAt(0));
                    isSuggestedLetterExistInMaskedWord = true;
                }
            }

            if (maskedWord.toString().equals(word)) {
                System.out.printf("""
                        --------------------------------------------------------------------------------------------------------------------------------------------
                                                    Congratulations! You've decoded the word: %s
                        --------------------------------------------------------------------------------------------------------------------------------------------                
                        """, word);
                IS_GAME_ENABLED = false;
            } else if (isSuggestedLetterExistInMaskedWord) {
                System.out.printf("""
                        --------------------------------------------------------------------------------------------------------------------------------------------              
                                                    Good result! Keep guessing! Your word is looking now: %s
                        --------------------------------------------------------------------------------------------------------------------------------------------                
                        """, maskedWord);
            } else {
                ATTEMPTS--;

                if (ATTEMPTS > 0) {
                    System.out.printf("""
                            ****************************************************************************************************************************************
                                                                Your letter is wrong! You have only %s attempts. 
                            ****************************************************************************************************************************************
                                                    
                            """, ATTEMPTS);
                    System.out.println(HangmanStage.STAGES[ATTEMPTS]);
                } else {
                    System.out.println(HangmanStage.STAGES[0]);
                    System.out.printf("""
                            ****************************************************************************************************************************************                
                                                        Your attempts are over :(  The decoded word was: %s
                            ****************************************************************************************************************************************                
                            """, word);
                    IS_GAME_ENABLED = false;
                }
            }
        }
    }
}
