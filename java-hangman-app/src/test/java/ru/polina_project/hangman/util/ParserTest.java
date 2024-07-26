package ru.polina_project.hangman.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.polina_project.hangman.util.Parser.loadWords;

public class ParserTest {
    private Path tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        Path tempFile = Files.createTempFile("test-words", ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.toFile()))) {
            writer.write("1word\n");
            writer.write("2word\n");
            writer.write("3word\n");
        }
    }

    @AfterEach
    public void deleteFile() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    public void testLoadWords() throws IOException {
        List<String> words1 = Parser.loadWords(tempFile.toString());
        List<String> words = Parser.loadWords(tempFile.toString());
        assertTrue(words.contains("1word"));
        assertTrue(words.contains("2word"));
        assertTrue(words.contains("3word"));


    }


}

