package com.haluktiras.game.tictactoe;

import java.util.Scanner;

public class TicTacToeMain {

    public static void main(String[] args)
            throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        GameRunner game = new GameRunner(scanner, System.out);

        game.run();
    }
}
