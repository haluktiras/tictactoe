package com.haluktiras.game.tictactoe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.PrintStream;

import static com.haluktiras.game.tictactoe.Player.O;
import static com.haluktiras.game.tictactoe.Player.X;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class BoardPrinterTest {

    private BoardPrinter printer;
    @Mock
    private PrintStream printStream;

    @Before
    public void setup() {
        printer = new BoardPrinter(printStream);
    }

    @Test
    public void printGameBoardEmpty() {
        GameBoard board = new GameBoard();

        printer.printGameBoard(board);

        InOrder inOrder = inOrder(printStream);
        inOrder.verify(printStream).printf("%s|%s|%s\n", " ", " ", " ");
        inOrder.verify(printStream).println("-+-+-");
        inOrder.verify(printStream).printf("%s|%s|%s\n", " ", " ", " ");
        inOrder.verify(printStream).println("-+-+-");
        inOrder.verify(printStream).printf("%s|%s|%s\n", " ", " ", " ");
    }

    @Test
    public void printGameBoard() {
        GameBoard board = new GameBoard(new Player[][]{{O, X, O}, {X, null, O}, {X, O, X}});

        printer.printGameBoard(board);

        InOrder inOrder = inOrder(printStream);
        inOrder.verify(printStream).printf("%s|%s|%s\n", "O", "X", "O");
        inOrder.verify(printStream).println("-+-+-");
        inOrder.verify(printStream).printf("%s|%s|%s\n", "X", " ", "O");
        inOrder.verify(printStream).println("-+-+-");
        inOrder.verify(printStream).printf("%s|%s|%s\n", "X", "O", "X");
    }

}