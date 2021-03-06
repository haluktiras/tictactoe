package com.haluktiras.game.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoard {

    private static final int COLS = 3;
    private static final int ROWS = 3;

    private final Player[][] playersBoard;

    public GameBoard() {
        playersBoard = new Player[ROWS][COLS];
    }

    public GameBoard(Player[][] playersBoard) {
        if (playersBoard == null) {
            throw new IllegalArgumentException("board cannot be null");
        }
        this.playersBoard = playersBoard;
    }

    public GameBoard(GameBoard other) {
        playersBoard = new Player[ROWS][COLS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                playersBoard[row][col] = other.playersBoard[row][col];
            }
        }
    }

    public boolean mark(int row, int col, Player player) {
        validatePosition(row, col);
        if (player == null) {
            throw new IllegalArgumentException("cannot mark null player");
        }
        if (playersBoard[row][col] != null) {
            return false;
        } else {
            playersBoard[row][col] = player;
            return true;
        }
    }

    public Player getMark(int row, int col) {
        validatePosition(row, col);
        return playersBoard[row][col];
    }

    public List<Position> getOpenPositions() {
        ArrayList<Position> positions = new ArrayList<Position>();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (playersBoard[row][col] == null) {
                    positions.add(new Position(row, col));
                }
            }
        }
        return positions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Player p = playersBoard[row][col];
                if (p != null) {
                    sb.append(p);
                } else {
                    sb.append(' ');
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GameBoard)) {
            return false;
        }
        GameBoard other = (GameBoard) obj;
        for (int row = 0; row < ROWS; row++) {
            if (!Arrays.equals(playersBoard[row], other.playersBoard[row])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        for (int row = 0; row < ROWS; row++) {
            result = prime * result + Arrays.hashCode(playersBoard[row]);
        }
        return result;
    }

    private static void validatePosition(int row, int col) {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) {
            throw new IllegalArgumentException("(" + row + "," + col + ") is off the board");
        }
    }
}
