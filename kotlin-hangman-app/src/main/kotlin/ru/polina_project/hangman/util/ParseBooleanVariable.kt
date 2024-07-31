package ru.polina_project.hangman.util

fun parseBooleanOrDefault(value: String?, defaultValue: Boolean): Boolean {
    if (value == null) {
        return defaultValue
    }
    return value.toBoolean()
}