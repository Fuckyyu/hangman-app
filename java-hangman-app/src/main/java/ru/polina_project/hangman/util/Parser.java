package ru.polina_project.hangman.util;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Parser {
    @SneakyThrows
    public static List<String> loadWords(String filePath) {
        @Cleanup Stream<String> lines = Files.lines(Paths.get(filePath));
        return lines
                .filter(line -> !line.isEmpty())
                .toList();
    }
}
