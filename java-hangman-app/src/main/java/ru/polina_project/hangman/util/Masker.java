package ru.polina_project.hangman.util;

public class Masker {
    public static String doMask(char maskSymbol, String wordToMask) {
        return String.valueOf(maskSymbol).repeat(wordToMask.length());
    }
}
