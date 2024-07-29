package ru.polina_project.hangman.runner;

import ru.polina_project.hangman.game.Game;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean IS_GAME_ENABLED = true;


        while (IS_GAME_ENABLED) {
            System.out.println("Do you want to play the game Hangman? (1 - YES, 0 - NO)");
            int input = scanner.nextInt();

            if (input == 1) {
                Thread gameThread = new Thread(Game::startGame);
                gameThread.start();
                try {
                    gameThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Game is over. Do you want to play again? (1 - YES, 0 - NO)");
                int chosenNumber = scanner.nextInt();
                if (chosenNumber == 0) {
                    IS_GAME_ENABLED = false;
                }
            } else {
                IS_GAME_ENABLED = false;
            }
        }
        System.out.println("Program is finished");
        scanner.close();
    }
}



