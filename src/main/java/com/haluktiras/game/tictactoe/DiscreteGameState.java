package com.haluktiras.game.tictactoe;

import java.util.List;

public interface DiscreteGameState {
    List<DiscreteGameState> availableStates();
    boolean isOver();
}
