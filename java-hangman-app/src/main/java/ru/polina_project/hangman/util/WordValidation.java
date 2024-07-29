package ru.polina_project.hangman.util;

public class WordValidation {
    public static boolean doesInputLineIsChar(String inputLine) {
        return inputLine.length() == 1 && Character.isLetter(inputLine.charAt(0)); // todo как проверять символы в джаве что он является буквой
    }
}
