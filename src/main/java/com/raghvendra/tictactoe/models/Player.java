package main.java.com.raghvendra.tictactoe.models;

import java.util.Scanner;

public class Player {
    private Symbol symbol;
    private String name;
    private PlayerType playerType;
    public Player(String name, Symbol symbol, PlayerType type) {
        this.playerType = type;
        this.name = name;
        this.symbol = symbol;
    }

    public Move makeMove(Board board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please tell row count, where you want to move (Starting from 0)");
        int row = scanner.nextInt();

        System.out.println("Please tell column count, where you want to move (Starting from 0)");
        int col = scanner.nextInt();

        // Move should be made within the size of the board
        if(!validMove(row, col, board)) {
            return makeMove(board);
        }

        // To avoid overwriting the cell which is already filled
        Cell currentCell = board.getBoard().get(row).get(col);
        if(filledCell(currentCell)) {
            return makeMove(board);
        }
        return new Move(new Cell(row, col), this);
    }

    public boolean filledCell(Cell cell) {
        if(cell.getCellState().equals(CellState.FILLED)) {
            System.out.println("The cell is occupied already, please select an empty cell");
            return true;
        }
        return false;
    }
    public boolean validMove(int row, int col, Board board) {
        int boardSize = board.getBoard().size();
        if(row >= boardSize|| col >= boardSize || row < 0 || col < 0) {
            System.out.println("Input cell is out of the scope of our board");
            return false;
        }
        return true;
    }

    public Symbol getSymbol() {
        return symbol;
    }
    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public PlayerType getPlayerType() {
        return playerType;
    }
    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
    public Bot makeBot()  {
        return new Bot(this.name, this.getSymbol(), BotDifficultyLevel.EASY);
    }
}
