package com.haluktiras.game.tictactoe;

public final class Position {

    private final int row;
    private final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Position)) {
            return false;
        }
        Position other = (Position) obj;
        return row == other.row && col == other.col;
    }

    @Override
    public int hashCode() {
        return row * 3 + col;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Position: ");
        return builder.append('(').append(row).append(',').append(col).append(')').toString();
    }
}
