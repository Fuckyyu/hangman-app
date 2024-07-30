package ru.polina_project.hangman.util

class Masker {
    companion object {
        fun doMask(maskSymbol: Char, wordToMask: String): String {
            return maskSymbol.toString().repeat(wordToMask.length)
        }
    }
}
