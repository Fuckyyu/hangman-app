package ru.polina_project.hangman.util

fun doesInputLineIsChar(inputLine: String): Boolean {
    return inputLine.length == 1 && Character.isLetter(inputLine[0])
}
