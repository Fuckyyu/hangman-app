package ru.polina_project.hangman.util

fun doMask(maskSymbol: Char, wordToMask: String): String {
    return maskSymbol.toString().repeat(wordToMask.length)
}


