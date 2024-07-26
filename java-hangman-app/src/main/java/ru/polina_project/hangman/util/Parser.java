package ru.polina_project.hangman.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static List<String> loadWords(String filePath) {
        List<String> filesWords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    filesWords.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filesWords;
    }
}
