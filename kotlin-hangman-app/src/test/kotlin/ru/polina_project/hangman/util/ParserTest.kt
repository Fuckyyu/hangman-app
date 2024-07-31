package ru.polina_project.hangman.util

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Files.deleteIfExists
import java.nio.file.Files.write
import java.nio.file.Path

class ParserTest {

    var tempFile: Path? = null

    @BeforeEach
    @Throws(IOException::class)
    fun setUp() {
        tempFile = Files.createTempFile("test-words", ".txt")
        val words = listOf("word1", "word2", "word3", "word4")
        write(tempFile, words)
    }

    @AfterEach
    @Throws(IOException::class)
    fun tearDown() {
        deleteIfExists(tempFile)
    }

    @Test
    fun testLoadWords() {
        val filePath = tempFile.toString()
        val expectedWords = listOf("word1", "word2", "word3", "word4")
        val actualWords: List<String> =loadWords(filePath)
        assertEquals(expectedWords, actualWords)
    }
}