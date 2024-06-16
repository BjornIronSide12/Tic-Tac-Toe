package main.java.com.raghvendra.tictactoe.strategies.winningstrategies;

import main.java.com.raghvendra.tictactoe.models.Board;
import main.java.com.raghvendra.tictactoe.models.Move;

public interface WinningStrategy {
    
    boolean checkWinner(Board board, Move move); 

}
