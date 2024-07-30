package ru.polina_project.hangman.game

import ru.polina_project.hangman.util.*
import java.util.*


    private val ENTERED_LETTERS: MutableSet<Char> = HashSet()

    private fun wasLetterEnteredBefore(letter: Char): Boolean {
        return ENTERED_LETTERS.contains(letter)
    }

    fun startGame() {
        clearEnteredLetters()

        println(
            """
                --------------------------------------------------------------------------------------------------------
                 |                                    Hangman game is started!                                        |
                --------------------------------------------------------------------------------------------------------
                
                """
        )
        processGame()
    }

    private fun chooseRandomWord(words: List<String>): String {
        return words[Random().nextInt(words.size)]
    }

    private fun processGame() {
        val words =
            loadWords("/Users/polynww/IdeaProjects/hangman-app/java-hangman-app/src/main/resources/words.txt")
        val word = chooseRandomWord(words)
        val maskedWord = StringBuilder(doMask('*', word))

        System.out.printf(
            """
                --------------------------------------------------------------------------------------------------------------------------------------------
                                                      Your word to decode is: %s                                      
                --------------------------------------------------------------------------------------------------------------------------------------------
                      
                """, maskedWord
        )

        var attemptsCounter = 8
        while (true) {
            System.out.printf(
                """
                                                    Type a letter to decode the word: %s                              
                                        
                    ...
                     
                    """, maskedWord
            )

            var line: String
            while (true) {
                val scanner = Scanner(System.`in`)
                line = scanner.next()
                if (doesInputLineIsChar(line)) break
                println("Input letter is incorrect!!!")
            }
            val letter = line[0]

            if (wasLetterEnteredBefore(letter)) {
                println("You've already entered this letter!")
                continue
            }
            ENTERED_LETTERS.add(letter)

            var isSuggestedLetterExistInMaskedWord = false
            for (i in 0 until word.length) {
                if (word[i] == line[0]) {
                    maskedWord.setCharAt(i, line[0])
                    isSuggestedLetterExistInMaskedWord = true
                }
            }

            if (maskedWord.toString() == word) {
                System.out.printf(
                    """
                        --------------------------------------------------------------------------------------------------------------------------------------------
                                                    Congratulations! You've decoded the word: %s
                        --------------------------------------------------------------------------------------------------------------------------------------------                
                        
                        """, word
                )
                break
            } else if (isSuggestedLetterExistInMaskedWord) {
                System.out.printf(
                    """
                        --------------------------------------------------------------------------------------------------------------------------------------------              
                                                    Good result! Keep guessing! Your word is looking now: %s
                        --------------------------------------------------------------------------------------------------------------------------------------------                
                        
                        """, maskedWord
                )
            } else {
                attemptsCounter--

                if (attemptsCounter > 0) {
                    System.out.printf(
                        """
                            ****************************************************************************************************************************************
                                                                Your letter is wrong! You have only %s attempts. 
                            ****************************************************************************************************************************************
                                                     
                            """, attemptsCounter
                    )
                    println(STAGES[attemptsCounter])
                } else {
                    println(STAGES[0])
                    System.out.printf(
                        """
                            ****************************************************************************************************************************************                
                                                        Your attempts are over :(  The decoded word was: %s
                            ****************************************************************************************************************************************                
                            
                            """, word
                    )
                    break
                }
            }
        }
    }

    private fun clearEnteredLetters() {
        ENTERED_LETTERS.clear()
    }

