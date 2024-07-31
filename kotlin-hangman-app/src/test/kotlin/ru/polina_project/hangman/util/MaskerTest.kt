package ru.polina_project.hangman.util

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class MaskerTest {
@Test
fun testDoMask() {
    val maskSymbol = '*'
    val wordToMask = "hangman"

    val expectedMaskedWord = "*******"

    val actualMaskedWord = doMask(maskSymbol, wordToMask)
    assertEquals(expectedMaskedWord, actualMaskedWord)
}
}