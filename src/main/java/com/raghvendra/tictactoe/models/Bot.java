package main.java.com.raghvendra.tictactoe.models;

import main.java.com.raghvendra.tictactoe.strategies.botplayingstrategies.BotPlayingStrategy;
import main.java.com.raghvendra.tictactoe.strategies.botplayingstrategies.BotPlayingStrategyFactory;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public  Bot(Long id, String name, Symbol symbol,
                 BotDifficultyLevel botDifficultyLevel) {
        super(id, name, symbol, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory
                                    .getBotPlayingStrategyForDifficultyLevel(botDifficultyLevel);
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
    
    @Override
    public Move makeMove(Board board) {
        // TODO Auto-generated method stub
        Move move = botPlayingStrategy.makeMove(board);
        move.setPlayer(this);
        
        return move;
    }
}
