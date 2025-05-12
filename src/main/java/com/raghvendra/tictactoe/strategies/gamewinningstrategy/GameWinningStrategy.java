package main.java.com.raghvendra.tictactoe.strategies.gamewinningstrategy;

import main.java.com.raghvendra.tictactoe.models.Board;
import main.java.com.raghvendra.tictactoe.models.Cell;
import main.java.com.raghvendra.tictactoe.models.Player;

public interface GameWinningStrategy {
    boolean checkWinner(Board board, Player lastMovedPlayer, Cell lastCell);
}
