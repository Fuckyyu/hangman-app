package ru.polina_project.hangman.util;

public class HangmanStage {
    public static final String[] STAGES = {
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
}
