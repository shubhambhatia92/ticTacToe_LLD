package org.tictactoe.models;

import org.tictactoe.factories.BotPlayingStrategyFactory;
import org.tictactoe.strategies.botplayingstrategy.BotPlayingStrategy;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name ,char symbol , BotDifficultyLevel difficultyLevel) {
        super(name, symbol, PlayerType.BOT);
        this.botDifficultyLevel = difficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getStrategyForDifficultyLevel(difficultyLevel);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
    @Override
    public Move decideMove(Board board) {
        return botPlayingStrategy.decideMove(this, board);
    }

    public static BotDifficultyLevel getBotDifficultyLevel(String level){
        if(level.equals("EASY")){
            return BotDifficultyLevel.EASY;
        }
        else
            if(level.equals("MEDIUM")){
                return BotDifficultyLevel.MEDIUM;
            }
        else
            if(level.equals("HARD")){
                return BotDifficultyLevel.HARD;
            }
         else {
                // THROW EXCEPTION HERE
             return BotDifficultyLevel.EASY;
            }
    }
}
