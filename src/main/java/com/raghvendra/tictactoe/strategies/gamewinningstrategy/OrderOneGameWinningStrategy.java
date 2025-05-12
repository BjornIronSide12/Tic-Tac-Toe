package main.java.com.raghvendra.tictactoe.strategies.gamewinningstrategy;

import main.java.com.raghvendra.tictactoe.models.Board;
import main.java.com.raghvendra.tictactoe.models.Cell;
import main.java.com.raghvendra.tictactoe.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneGameWinningStrategy implements GameWinningStrategy{

    private List<HashMap<Character, Integer>> rowsSymbolCount = new ArrayList<>();
    private List<HashMap<Character, Integer>> colSymbolCount = new ArrayList<>();
    private HashMap<Character, Integer> leftColumn = new HashMap<>();
    private HashMap<Character, Integer> rightColumn =  new HashMap<>();

    public OrderOneGameWinningStrategy(int dimension) {
        for (int i = 0; i < dimension; i++) {
            rowsSymbolCount.add(new HashMap<>());
            colSymbolCount.add(new HashMap<>());
        }
    }

    private boolean onLeftDigonal(int row, int col) {
        return row == col;
    }

    private boolean onRightDigonal(int row, int col, int dimension) {
        return (row + col == dimension - 1);
    }
    @Override
    public boolean checkWinner(Board board, Player lastMovedPlayer, Cell lastCell) {
        int row = lastCell.getRow();
        int col = lastCell.getCol();
        int dimension = board.getBoard().size();
        char symbol = lastMovedPlayer.getSymbol().getaChar();

        // incrementing count of the current symbol in the current row
        if(!rowsSymbolCount.get(row).containsKey(symbol)) {
            rowsSymbolCount.get(row).put(symbol, 0);
        }

        rowsSymbolCount.get(row).put(symbol,
                rowsSymbolCount.get(row).get(symbol) + 1
        );


        // incrementing count of the current symbol in the current column
        if(!colSymbolCount.get(col).containsKey(symbol)) {
            colSymbolCount.get(col).put(symbol, 0);
        }

        colSymbolCount.get(col).put(symbol,
                colSymbolCount.get(col).get(symbol) + 1
                );

        // Check if the cell belongs to left digonal
        // incrementing count of the current symbol in the current left digonal
        if(onLeftDigonal(row, col)) {
            if(!leftColumn.containsKey(symbol)) {
                leftColumn.put(symbol, 0);
            }
            leftColumn.put(symbol,
                    leftColumn.get(symbol) + 1
            );
        }

        // Check if the cell belongs to right digonal
        // incrementing count of the current symbol in the current right digonal
        if(onRightDigonal(row, col, dimension)) {
            if(!rightColumn.containsKey(symbol)) {
                rightColumn.put(symbol, 0);
            }
            rightColumn.put(symbol,
                    rightColumn.get(symbol) + 1
            );
        }

        if(rowsSymbolCount.get(row).get(symbol) == dimension ||
            colSymbolCount.get(col).get(symbol) == dimension) {
            return true;
        }
        if(onLeftDigonal(row, col) && leftColumn.get(symbol) == dimension) {
            return true;
        }
        if(onRightDigonal(row, col, dimension) && rightColumn.get(symbol) == dimension) {
            return true;
        }
        return false;
    }
}
