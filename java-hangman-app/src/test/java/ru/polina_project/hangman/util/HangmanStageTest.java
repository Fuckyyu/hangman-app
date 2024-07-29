package ru.polina_project.hangman.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HangmanStageTest {
    @Test
    void testHangmanStages() {
        final String[] expectedStages = {
                """
                        +-----+
                        |     |
                        O     |
                       /|\\    |
                       / \\    |
                              |
                        =========
            """,
                """
                        +-----+
                        |     |
                        0     |
                       /|\\    |
                         \\    |
                              |
                        =========
            """,
                """
                        +-----+
                        |     |
                        0     |
                       /|\\    |
                              |
                              |
                        =========
            
            """,
                """
                        +-----+
                        |     |
                        O     |
                        |\\    |
                              |
                              |
                        =========
            """,
                """
                        +-----+
                        |     |
                        O     |
                        |     |
                              |
                              |
                        =========
            
            """,
                """
                        +-----+
                        |     |
                        O     |
                              |
                              |
                              |
                        =========
            """,
                """
                        +-----+
                        |     |
                              |
                              |
                              |
                              |
                        =========
            """,
                """
                              +
                              |
                              |
                              |
                              |
                              |
                        =========
            """
        };

        assertArrayEquals(expectedStages, HangmanStage.STAGES);
    }
}