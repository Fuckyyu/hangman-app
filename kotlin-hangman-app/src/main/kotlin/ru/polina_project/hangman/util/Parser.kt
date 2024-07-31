package ru.polina_project.hangman.util

import java.io.File

fun loadWords(filePath: String): List<String> {
    return File(filePath).readLines().filter { it.isNotEmpty() }
}


