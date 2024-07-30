package ru.polina_project.hangman.util

object WordValidation {
    fun doesInputLineIsChar(inputLine: String): Boolean {
        return inputLine.length == 1 && Character.isLetter(inputLine[0])
    }
}