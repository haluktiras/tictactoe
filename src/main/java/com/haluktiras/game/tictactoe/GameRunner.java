package com.haluktiras.game.tictactoe;

import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class GameRunner {

    static final String GAME_OPTIONS_TEXT =
            "Please select the game mode by entering \n" +
                    "'1' for 1 Player\n" +
                    "'2' for 2 players\n" +
                    "'3' for watch bot mode";
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
        while(!gameState.isOver()){
            printStream.print(GAME_OPTIONS_TEXT);
            String input = scanner.nextLine();
            if(!isOneOf(input, "1", "2", "3")) continue;
            if("1".equals(input)) run1PlayerMode();
            if("2".equals(input)) run2PlayersMode();
            if("3".equals(input)) runBotMode();
        }

    }

    public void run1PlayerMode() throws InterruptedException {
        printInstructions();
        boardPrinter.initBoard();
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

    public void run2PlayersMode() throws InterruptedException {
        printInstructions();
        boardPrinter.initBoard();
        while (!gameState.isOver()) {
            moveHuman();
            boardPrinter.printGameBoard(gameState.getGameBoard());
            if(gameState.isOver()) break;
            Thread.sleep(1955);
            moveHuman();
            boardPrinter.printGameBoard(gameState.getGameBoard());
        }
        printGameOver();
    }

    public void runBotMode() throws InterruptedException {
        printInstructions();
        boardPrinter.initBoard();
        while (!gameState.isOver()) {
            moveComputer();
            boardPrinter.printGameBoard(gameState.getGameBoard());
            if(gameState.isOver()) break;
            Thread.sleep(1955);
            moveComputer();
            boardPrinter.printGameBoard(gameState.getGameBoard());
        }
        printGameOver();
    }

    private boolean isOneOf(String optionEntry, String... compareValues){
        for(String compareValue : compareValues){
            if(compareValue.equals(optionEntry)) return true;
        }
        printStream.println("Please select a valid value");
        return false;
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
                printStream.print("Player " + gameState.getCurrentPlayer() + " [ "
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
            printStream.println("Player X won.");
        } else if (gameState.hasWin(Player.O)) {
            printStream.println("Player O won.");
        } else {
            printStream.println("Game ended in a draw.");
        }
    }

    private void printGameModeInstructions() {
        printStream.println(GAME_OPTIONS_TEXT);
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
