package ru.polina_project.hangman.runner
import ru.polina_project.hangman.game.startGame
import java.util.*

var IS_GAME_ENABLED = true

private fun appRunner(scanner: Scanner) {
    val input = scanner.nextInt()

    if (input == 1) {
       startGame()
    } else {
        IS_GAME_ENABLED = false
    }
}

fun main() {
    val scanner = Scanner(System.`in`)

    if (!IS_GAME_ENABLED) {
        println("Game is disabled")
        return
    }

    println("Do you want to play the game Hangman? (1 - YES, 0 - NO)")
    appRunner(scanner)

    while (IS_GAME_ENABLED) {
        println("Do you want to play the game Hangman AGAIN? (1 - YES, 0 - NO)")
        appRunner(scanner)
    }
    println("Program is finished")

    scanner.close()
}
