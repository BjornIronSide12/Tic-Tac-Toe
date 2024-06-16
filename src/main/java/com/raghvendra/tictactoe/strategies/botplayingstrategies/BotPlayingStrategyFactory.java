package main.java.com.raghvendra.tictactoe.strategies.botplayingstrategies;

import main.java.com.raghvendra.tictactoe.models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel level) {
        return new EasyBotPlayingStrategy();
    }
}
