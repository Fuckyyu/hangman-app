package ru.polina_project.hangman.util

object HangmanStage {

    val STAGES = listOf(
        """
                        +-----+
                        |     |
                        O     |
                       /|\    |
                       / \    |
                              |
                        =========
            
            """
            .trimIndent(),
        """
                        +-----+
                        |     |
                        0     |
                       /|\    |
                         \    |
                              |
                        =========
            
            """
            .trimIndent(),
        """
                        +-----+
                        |     |
                        0     |
                       /|\    |
                              |
                              |
                        =========
            
            """
            .trimIndent(),
        """
                        +-----+
                        |     |
                        O     |
                        |\    |
                              |
                              |
                        =========
            
            """
            .trimIndent(),
        """
                        +-----+
                        |     |
                        O     |
                        |     |
                              |
                              |
                        =========
            
            """
            .trimIndent(),
        """
                        +-----+
                        |     |
                        O     |
                              |
                              |
                              |
                        =========
            
            """
            .trimIndent(),
        """
                        +-----+
                        |     |
                              |
                              |
                              |
                              |
                        =========
            
            """
            .trimIndent(),
        """
                              +
                              |
                              |
                              |
                              |
                              |
                        =========
            
            """
            .trimIndent()
    )
}


