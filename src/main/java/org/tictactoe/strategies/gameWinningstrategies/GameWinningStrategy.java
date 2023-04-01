package org.tictactoe.strategies.gameWinningstrategies;

import org.tictactoe.models.Board;
import org.tictactoe.models.Cell;
import org.tictactoe.models.Player;

public interface GameWinningStrategy {
    boolean checkWinner(Board board,
                        Player lastMovePlayer,
                        Cell moveCell);

    // undo and check draw can be implemented in game class itself but if different winning strategy has there
    // own way of draw or only then to implement(This is just showcase of design pattern)
    boolean checkDraw(int TotalMoveCount , int dimension);

    public boolean doUndo(char symbol,int row , int col);
}
