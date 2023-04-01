package org.tictactoe.strategies.gameWinningstrategies;

import org.tictactoe.models.Board;
import org.tictactoe.models.Cell;
import org.tictactoe.models.Player;

public interface GameWinningStrategy {
    boolean checkWinner(Board board,
                        Player lastMovePlayer,
                        Cell moveCell);
    boolean checkDraw(int TotalMoveCount , int dimension);

    public boolean doUndo(char symbol,int row , int col);
}
