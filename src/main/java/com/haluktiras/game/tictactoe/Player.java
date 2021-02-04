package com.haluktiras.game.tictactoe;

public enum Player {

    O, X;

    public static Player opponentOf(Player player) {
        return player == X ? O : X;
    }

}
