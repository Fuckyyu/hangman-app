package ru.polina_project.hangman.util;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    @SneakyThrows
    public static List<String> loadWords(String filePath) {
        return Files.lines(Paths.get(filePath))
                .filter(line -> !line.isEmpty())
                .toList();
    }
}
