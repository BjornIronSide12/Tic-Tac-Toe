package main.java.com.raghvendra.tictactoe.strategies.botplayingstrategies;

import main.java.com.raghvendra.tictactoe.models.Board;
import main.java.com.raghvendra.tictactoe.models.Move;

public interface BotPlayingStrategy {
    
    Move makeMove(Board board);
}
