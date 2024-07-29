package ru.polina_project.hangman.runner;

import ru.polina_project.hangman.game.Game;

import java.util.Scanner;

public class Runner {
    private static boolean IS_GAME_ENABLED = true;

    private static void appRunner(Scanner scanner) {
        int input = scanner.nextInt();

        if (input == 1) {
            Game.startGame();
        } else {
            IS_GAME_ENABLED = false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!IS_GAME_ENABLED) {
            System.out.println("Game is disabled");
            return;
        }

        System.out.println("Do you want to play the game Hangman? (1 - YES, 0 - NO)");
        appRunner(scanner);


         while (IS_GAME_ENABLED) {
            System.out.println("Do you want to play the game Hangman AGAIN? (1 - YES, 0 - NO)");
            appRunner(scanner);
        }
        System.out.println("Program is finished");

        scanner.close();
    }
}



