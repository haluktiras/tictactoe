package com.haluktiras.game.tictactoe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.PrintStream;
import java.util.Scanner;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.util.Strings.join;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameRunnerTest {

    @Mock
    private PrintStream printStream;

    @Test
    public void moveHumanContinuesToAcceptInputUntilValid() {
        Scanner scanner = scannerWithInputs("", " 1, 1", "invalid", "-1,1", "3,1", "1,2,3", "0,0");
        GameRunner runner = new GameRunner(scanner, printStream);

        runner.moveHuman();

        verify(printStream, times(6)).println(GameRunner.INSTRUCTION_TEXT);
    }

    @Test
    public void moveHumanErrorWhenOffBoard() {
        Scanner scanner = scannerWithInputs("-1,0", "3,3", "0,0");
        GameRunner runner = new GameRunner(scanner, printStream);

        runner.moveHuman();

        verify(printStream).printf("(%d,%d) is not on the board. ", -1, 0);
        verify(printStream).printf("(%d,%d) is not on the board. ", 3, 3);
        verify(printStream, times(2)).println(GameRunner.INSTRUCTION_TEXT);
    }

    @Test
    public void moveHumanErrorWhenRepeatMove() {
        Scanner scanner = scannerWithInputs("1,1", "1,1", "0,0");
        GameRunner runner = new GameRunner(scanner, printStream);

        runner.moveHuman();
        runner.moveHuman();

        verify(printStream).printf("(%d,%d) has already been taken. ", 1, 1);
        verify(printStream).println(GameRunner.INSTRUCTION_TEXT);
    }

    @Test
    public void moveHumanSwitchesPlayers() {
        Scanner scanner = scannerWithInputs("1,1", "0,0");
        GameRunner runner = new GameRunner(scanner, printStream);

        assertThat(runner.getGameState().getCurrentPlayer()).isEqualTo(Player.X);
        runner.moveHuman();
        assertThat(runner.getGameState().getCurrentPlayer()).isEqualTo(Player.O);
    }

    @Test
    public void moveComputerSwitchesPlayers() {
        GameRunner runner = new GameRunner(new Scanner(""), printStream);
        GameState nextState = mock(GameState.class);
        when(nextState.getLastMove()).thenReturn(new Position(0, 0));

        assertThat(runner.getGameState().getCurrentPlayer()).isEqualTo(Player.X);
        runner.moveComputer();
        assertThat(runner.getGameState().getCurrentPlayer()).isEqualTo(Player.O);
    }

    private Scanner scannerWithInputs(String... inputs) {
        String joinedInputs = join(inputs).with("\n");
        Scanner scanner = new Scanner(joinedInputs);
        return scanner;
    }
}