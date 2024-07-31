package ru.polina_project.hangman.game

import ru.polina_project.hangman.util.STAGES
import ru.polina_project.hangman.util.doMask
import ru.polina_project.hangman.util.doesInputLineIsChar
import ru.polina_project.hangman.util.loadWords
import java.util.*


val ENTERED_LETTERS: MutableSet<Char> = HashSet()

fun main() {
    startGame()
}

fun clearEnteredLetters() {
    ENTERED_LETTERS.clear()
}

fun wasLetterEnteredBefore(letter: Char): Boolean {
    return ENTERED_LETTERS.contains(letter)
}

fun chooseRandomWord(words: List<String>): String {
    return words[Random().nextInt(words.size)]
}

fun loadWords(): List<String> {
    return loadWords("/Users/polynww/IdeaProjects/hangman-app/java-hangman-app/src/main/resources/words.txt")
}

fun InitialStateOfMaskedWord(maskedWord: StringBuilder?) {
    System.out.printf(
        """
                --------------------------------------------------------------------------------------------------------------------------------------------
                                                      Your word to decode is: %s                                      
                --------------------------------------------------------------------------------------------------------------------------------------------
                       
                
                       
                """.trimIndent(), maskedWord
    )
}

fun displayMaskedWord(maskedWord: StringBuilder?) {
    System.out.printf(
        """
                                                Type a letter to decode the word: %s                              
                ...
                
                """.trimIndent(), maskedWord
    )
}

fun inputLetterForDecodedWord(): Char {
    val scanner = Scanner(System.`in`)
    var line: String
    while (true) {
        line = scanner.next()
        if (doesInputLineIsChar(line)) {
            break
        }
        println("Input letter is incorrect!!!")
    }
    return line[0]
}

fun updateMaskedWord(word: String, maskedWord: StringBuilder, letter: Char): Boolean {
    var isLetterInWord = false
    for (i in 0 until word.length) {
        if (word[i] == letter) {
            maskedWord.setCharAt(i, letter)
            isLetterInWord = true
        }
    }
    return isLetterInWord
}

fun isMaskedWordGuessed(word: String?) {
    System.out.printf(
        """
                --------------------------------------------------------------------------------------------------------------------------------------------
                                                    Congratulations! You've decoded the word: %s
                --------------------------------------------------------------------------------------------------------------------------------------------                
                
                """.trimIndent(), word
    )
}

fun isSuggestedLetterExistInMaskedWord(maskedWord: StringBuilder?) {
    System.out.printf(
        """
                --------------------------------------------------------------------------------------------------------------------------------------------              
                                                    Good result! Keep guessing! Your word is looking now: %s
                --------------------------------------------------------------------------------------------------------------------------------------------                
                
                """.trimIndent(), maskedWord
    )
}

fun isEnteredWrongLetter(attemptsCounter: Int, word: String?): Int {
    var attemptsCounter = attemptsCounter
    attemptsCounter--
    if (attemptsCounter > 0) {
        System.out.printf(
            """
                    ****************************************************************************************************************************************
                                                                Your letter is wrong! You have only %s attempts.
                    ****************************************************************************************************************************************
                    
                    """.trimIndent(), attemptsCounter
        )
        println(STAGES.get(attemptsCounter))
    } else {
        println(STAGES.get(0))
        System.out.printf(
            """
                    ****************************************************************************************************************************************                
                                                        Your attempts are over :(  The decoded word was: %s
                    ****************************************************************************************************************************************                
                    
                    """.trimIndent(), word
        )
    }
    return attemptsCounter
}

fun startGame() {
    clearEnteredLetters()

    println(
        """
                --------------------------------------------------------------------------------------------------------
                 |                                    Hangman game is started!                                        |
                --------------------------------------------------------------------------------------------------------
                
                """.trimIndent()
    )
    processGame()
}

fun processGame() {
    val words = loadWords()
    val word = chooseRandomWord(words)
    val maskedWord: StringBuilder = StringBuilder(doMask('*', word))
    InitialStateOfMaskedWord(maskedWord)

    var attemptsCounter = 8
    while (true) {
        displayMaskedWord(maskedWord)
        val letter = inputLetterForDecodedWord()

        if (wasLetterEnteredBefore(letter)) {
            println("You've already entered this letter!")
            continue
        }
        ENTERED_LETTERS.add(letter)

        val doesLetterContainsInMaskedWord = updateMaskedWord(word, maskedWord, letter)

        if (maskedWord.toString() == word) {
            isMaskedWordGuessed(word)
            break
        } else if (doesLetterContainsInMaskedWord) {
            isSuggestedLetterExistInMaskedWord(maskedWord)
        } else {
            attemptsCounter = isEnteredWrongLetter(attemptsCounter, word)
            if (attemptsCounter == 0) {
                break
            }
        }
    }
}

