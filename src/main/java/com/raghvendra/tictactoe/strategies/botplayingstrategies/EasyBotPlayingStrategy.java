package main.java.com.raghvendra.tictactoe.strategies.botplayingstrategies;

import java.util.List;

import main.java.com.raghvendra.tictactoe.models.*;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
   
    @Override
    public Move makeMove(Board board) {
        // TODO Auto-generated method stub
        for(List<Cell> row: board.getBoard()) {
            for(Cell cell: row) {
                if(cell.getCellState().equals(CellState.EMPTY)) {
                    return new Move(
                                cell,
                                cell.getPlayer());
                }
            }
        }
        return null;
    }
}
