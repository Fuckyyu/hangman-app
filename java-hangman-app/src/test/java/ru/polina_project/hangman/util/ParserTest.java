package ru.polina_project.hangman.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.polina_project.hangman.util.Parser.loadWords;

public class ParserTest {
    private static Path tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = Files.createTempFile("test-words", ".txt");
        List<String> words = List.of("word1", "word2", "word3", "word4");
        Files.write(tempFile, words);
    }
    @AfterEach
    public void  tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    public void testLoadWords() {
        String filePath = tempFile.toString();
        List<String> expectedWords = List.of("word1", "word2", "word3", "word4");
        List<String> actualWords = Parser.loadWords(filePath);
        assertEquals(expectedWords, actualWords);

    }



//    @Test
//    public void setUp() throws IOException {
//        Path tempFile = Files.createTempFile("test-words", ".txt");
//        Files.write(tempFile, List.of("word1", "word2", "word3"));
//
//        List<String> words = Parser.loadWords(tempFile.toString());
//
//        assertNotNull(words);
//        assertEquals(3, words.size());
//        assertTrue(words.contains("word1"));
//        assertTrue(words.contains("word2"));
//        assertTrue(words.contains("word3"));
//
//        Files.deleteIfExists(tempFile);
//    }
}

