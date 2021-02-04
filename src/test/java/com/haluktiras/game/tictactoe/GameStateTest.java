package com.haluktiras.game.tictactoe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class GameStateTest {

    private GameState game;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        game = new GameState();
    }

    @Test
    public void startingPlayerIsX() {
        assertThat(new GameState().getCurrentPlayer()).isEqualTo(Player.X);
    }

    @Test
    public void copyConstructorDeepCopiesBoard() {
        game.play(0, 0);
        GameState copy = new GameState(game);
        assertThat(copy.getGameBoard()).isEqualTo(game.getGameBoard()).isNotSameAs(game.getGameBoard());
        assertThat(copy.getLastMove()).isEqualTo(game.getLastMove());
        assertThat(copy.getCurrentPlayer()).isEqualTo(game.getCurrentPlayer());
    }

    @Test
    public void getAvaliableStatesEmptyBoard() {
        GameState game = new GameState();
        List<DiscreteGameState> states = game.availableStates();
        assertThat(states).hasSize(9);
    }

    @Test
    public void getAvailableStatesLastOne() {
        GameState game = new GameState();
        game.play(0, 0);
        game.play(0, 1);
        game.play(0, 2);
        game.play(1, 0);
        game.play(1, 1);
        game.play(1, 2);
        game.play(2, 0);
        game.play(2, 1);

        List<DiscreteGameState> states = game.availableStates();
        assertThat(states).hasSize(1);
        GameState availableState = (GameState) states.get(0);
        assertThat(availableState.getCurrentPlayer()).isEqualTo(
                Player.opponentOf(game.getCurrentPlayer()));
        assertThat(availableState.getLastMove()).isEqualTo(new Position(2, 2));
    }

    @Test
    public void getAvailableStatesCompleteBoard() {
        GameState game = new GameState();
        game.play(0, 0);
        game.play(0, 1);
        game.play(0, 2);
        game.play(1, 0);
        game.play(1, 1);
        game.play(1, 2);
        game.play(2, 0);
        game.play(2, 1);
        game.play(2, 2);

        assertThat(game.availableStates()).isEmpty();
    }

    @Test
    public void hasWinRow() {
        game.play(0, 0);
        game.play(0, 1);
        game.play(0, 2);
        assertThat(game.hasWin(Player.X));
    }

    @Test
    public void hasWinCol() {
        game.play(0, 0);
        game.play(1, 0);
        game.play(2, 0);
        assertThat(game.hasWin(Player.X));
    }

    @Test
    public void hasWinDiagonal() {
        game.play(0, 0);
        game.play(1, 1);
        game.play(2, 2);
        assertThat(game.hasWin(Player.X));
    }

    @Test
    public void isOverWin() {
        game.play(0, 0);
        game.play(0, 1);
        game.play(0, 2);
        assertThat(game.isOver()).isTrue();
    }

    @Test
    public void isOverDraw() {
        game.play(0, 0);
        game.play(0, 2);
        game.play(1, 1);
        game.play(1, 2);
        game.play(2, 1);
        game.switchPlayer();
        game.play(0, 1);
        game.play(1, 0);
        game.play(2, 0);
        game.play(2, 2);

        assertThat(game.isOver()).isTrue();
    }

    @Test
    public void playOnBoard() {
        assertThat(game.play(0, 0)).isTrue();
        assertThat(game.getLastMove()).isEqualTo(new Position(0, 0));
    }

    @Test
    public void playOffBoard() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("(-1,0) is off the board");
        game.play(-1, 0);
    }

    @Test
    public void playSameLocation() {
        assertThat(game.play(0, 0)).isTrue();
        assertThat(game.play(0, 1)).isTrue();
        // should not affect the last move
        assertThat(game.play(0, 0)).isFalse();
        assertThat(game.getLastMove()).isEqualTo(new Position(0, 1));
    }

    @Test
    public void switchPlayer() {
        assertThat(game.getCurrentPlayer()).isEqualTo(Player.X);
        game.switchPlayer();
        assertThat(game.getCurrentPlayer()).isEqualTo(Player.O);
        game.switchPlayer();
        assertThat(game.getCurrentPlayer()).isEqualTo(Player.X);
    }
}