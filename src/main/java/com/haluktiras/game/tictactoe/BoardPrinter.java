package com.haluktiras.game.tictactoe;

import java.io.PrintStream;

public class BoardPrinter {

    private PrintStream printStream;

    public BoardPrinter(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void initBoard() {
        printStream.print("Game Board Creation…");
        printGameBoard(new GameBoard());
        printStream.println("Board Created.");
        printStream.println("The game will start with player X");
    }

    public void printGameBoard(GameBoard board) {
        printStream.println();
        printRow(0, board);
        printStream.println("-+-+-");
        printRow(1, board);
        printStream.println("-+-+-");
        printRow(2, board);
        printStream.println("--------------------------");
    }

    private void printRow(int row, GameBoard board) {
        printStream.printf("%s|%s|%s\n", markToString(board.getMark(row, 0)),
                markToString(board.getMark(row, 1)), markToString(board.getMark(row, 2)));
    }

    private static String markToString(Player player) {
        return player == null ? " " : player.toString();
    }

}
