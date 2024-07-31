package ru.polina_project.hangman.util;

public class ParsingBooleanVariable {
    public static boolean parseBooleanOrDefault(String value, boolean defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }
}
