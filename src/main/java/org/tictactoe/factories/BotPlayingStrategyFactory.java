package org.tictactoe.factories;

import org.tictactoe.models.BotDifficultyLevel;
import org.tictactoe.strategies.botplayingstrategy.BotPlayingStrategy;
import org.tictactoe.strategies.botplayingstrategy.RandomBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getStrategyForDifficultyLevel(BotDifficultyLevel difficultyLevel) {
        // specify strategy to use for bot in of else statement as of now kept only one strategy
        return new RandomBotPlayingStrategy();
    }
}
