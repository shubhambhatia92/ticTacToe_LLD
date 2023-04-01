package org.tictactoe.strategies.botplayingstrategy;

import org.tictactoe.models.Board;
import org.tictactoe.models.Move;
import org.tictactoe.models.Player;

public interface BotPlayingStrategy {

    Move decideMove(Player player, Board board);
}
