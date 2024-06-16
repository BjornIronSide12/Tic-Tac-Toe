package main.java.com.raghvendra.tictactoe.strategies.winningstrategies;

import java.util.List;
import java.util.Map;

import main.java.com.raghvendra.tictactoe.models.Board;
import main.java.com.raghvendra.tictactoe.models.Move;
import main.java.com.raghvendra.tictactoe.models.Symbol;

public class RowWinningStrategy implements WinningStrategy {

    private List<Map<Symbol, Integer>> symbolCount;

    @Override
    public boolean checkWinner(Board board, Move move) {
        // TODO Auto-generated method stub
        return false;
    }
}
