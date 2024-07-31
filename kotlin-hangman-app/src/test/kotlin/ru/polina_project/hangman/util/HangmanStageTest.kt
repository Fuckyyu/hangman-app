package ru.polina_project.hangman.util

import kotlin.test.Test
import kotlin.test.assertEquals
class HangmanStageTest {


    @Test
    fun testStagesInitialization() {
        val expectedStageCount = 8

        // Проверка количества этапов
        assertEquals(expectedStageCount, STAGES.size)

        // Проверка первого этапа
        val expectedFirstStage =

            """
                        +-----+
                        |     |
                        O     |
                       /|\    |
                       / \    |
                              |
                        =========
            
            """
        assertEquals(expectedFirstStage, STAGES[0])

        // Проверка последнего этапа
        val expectedLastStage =
            """
                              +
                              |
                              |
                              |
                              |
                              |
                        =========
            
            """
        assertEquals(expectedLastStage, STAGES[STAGES.size - 1])
    }
}