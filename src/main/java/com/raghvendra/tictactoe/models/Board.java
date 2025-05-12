package main.java.com.raghvendra.tictactoe.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> board;

    public Board(int dimension) {
        this.board = new ArrayList();

        // creating a board for dimension x dimension size
        for(int i = 0; i < dimension; i++) {
            board.add(new ArrayList<>());

            for(int j = 0; j < dimension; j++) {
                board.get(i).add(new Cell(i, j));
            }
        }
    }

    public void printBoard() {
        for(int i = 0; i < board.size(); i++) {
            for(int j = 0; j < board.size(); j++) {
                Cell currentCell = board.get(i).get(j);
                if(currentCell.getCellState().equals(CellState.EMPTY)) {
                    System.out.print("|   |");
                } else {
                    System.out.print("| " + currentCell.getPlayer().getSymbol().getaChar() + " |");
                }
            }
            System.out.println();
        }
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

}
