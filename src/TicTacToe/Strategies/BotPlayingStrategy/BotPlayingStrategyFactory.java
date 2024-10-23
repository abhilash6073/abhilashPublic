package TicTacToe.Strategies.BotPlayingStrategy;

import TicTacToe.models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyDifficultyLevel(BotDifficultyLevel level){
        return switch(level){
            case EASY -> new RandomBotPlayingStrategy();
            case MEDIUM -> new RandomBotPlayingStrategy();
            case HARD -> new RandomBotPlayingStrategy();
        };
    }
}
