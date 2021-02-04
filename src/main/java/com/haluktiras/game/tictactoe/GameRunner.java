package com.haluktiras.game.tictactoe;

import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class GameRunner {

    static final String INSTRUCTION_TEXT =
            "Enter '<row>,<col>' to play a position. For example, '0,2'.";

    private GameState gameState;

    private BoardPrinter boardPrinter;
    private Scanner scanner;
    private PrintStream printStream;

    public GameRunner(Scanner scanner,
                               PrintStream printStream) {
        this.gameState = new GameState();
        this.boardPrinter = new BoardPrinter(printStream);
        this.scanner = scanner;
        this.printStream = printStream;
    }

    public void run() throws InterruptedException {
        printInstructions();
        while (!gameState.isOver()) {
            moveHuman();
            boardPrinter.printGameBoard(gameState.getGameBoard());
            if(gameState.isOver()) break;
            Thread.sleep(1955);
            moveComputer();
            boardPrinter.printGameBoard(gameState.getGameBoard());
        }
        printGameOver();
    }

    GameState getGameState() {
        return gameState;
    }

    void moveComputer() {
        Position computerPosition;
        while (true) {
            do {
                computerPosition = generateRandomPosition();
            } while (computerPosition == null);

            if (gameState.play(computerPosition.getRow(), computerPosition.getCol())) {
                gameState.switchPlayer();
                printStream.print("Player O [ "
                        + computerPosition.getRow()
                        + ", "
                        + computerPosition.getCol()
                        +" ]: ");
                return;
            }
        }
    }

    void moveHuman() {
        Position userPosition;
        while (true) {
            do {
                printStream.print("Player X [row,col]: ");
                String input = scanner.nextLine();
                userPosition = parseUserInput(input);
            } while (userPosition == null);

            try {
                if (gameState.play(userPosition.getRow(), userPosition.getCol())) {
                    gameState.switchPlayer();
                    return;
                } else {
                    printStream.printf("(%d,%d) has already been taken. ", userPosition.getRow(),
                            userPosition.getCol());
                    printInstructions();
                }
            } catch (IllegalArgumentException e) {
                printStream.printf("(%d,%d) is not on the board. ", userPosition.getRow(),
                        userPosition.getCol());
                printInstructions();
            }
        }
    }

    private void printGameOver() {
        if (gameState.hasWin(Player.X)) {
            ((PrintStream) printStream).println("Player X won.");
        } else if (gameState.hasWin(Player.O)) {
            printStream.println("Player O won.");
        } else {
            printStream.println("Game ended in a draw.");
        }
    }

    private void printInstructions() {
        printStream.println(INSTRUCTION_TEXT);
    }

    private Position parseUserInput(String input) {
        String[] posInput = input.split(",");
        if (posInput.length != 2) {
            printInstructions();
            return null;
        }
        int row, col;
        try {
            row = Integer.parseInt(posInput[0]);
            col = Integer.parseInt(posInput[1]);
        } catch (NumberFormatException e) {
            printInstructions();
            return null;
        }
        return new Position(row, col);
    }

    private Position generateRandomPosition() {
        Random rand = new Random();
        int upperBound = 3;
        int col = rand.nextInt(upperBound);
        int row = rand.nextInt(upperBound);
        return new Position(col, row);
    }

}
