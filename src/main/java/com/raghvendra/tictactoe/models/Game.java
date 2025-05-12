package main.java.com.raghvendra.tictactoe.models;

import java.util.ArrayList;
import java.util.List;

import main.java.com.raghvendra.tictactoe.exceptions.PlayersCountDimensionMismatchException;
import main.java.com.raghvendra.tictactoe.strategies.gamewinningstrategy.GameWinningStrategy;
import main.java.com.raghvendra.tictactoe.strategies.gamewinningstrategy.OrderOneGameWinningStrategy;


/**
 * Game
 */
public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameState gameStatus;
    private int nextPlayerIndex;
    private GameWinningStrategy gameWinningStrategy;
    private Player winner;
    private int emptyCells;

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    private Game() {
    }

    public int getEmptyCells() {
        return this.emptyCells;
    }

    public void setEmptyCells(int count) {
        this.emptyCells = count;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public void undo() {}

    public void makeNextMove() {
        Player toMovePlayer = players.get(nextPlayerIndex);

        System.out.println("It is " + players.get(nextPlayerIndex).getName() + "'s turn.");

        // Move is validated in makeMove method
        Move move = toMovePlayer.makeMove(this.board);


        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        System.out.println("Move happened at: " +
                row + ", " + col + ".");

        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        emptyCells--; // 1 cell is filled will reduce the count of empty cells
        board.getBoard().get(row).get(col).setPlayer(players.get(nextPlayerIndex));

        Move finalMove = new Move(
                board.getBoard().get(row).get(col),
                players.get(nextPlayerIndex)
        );

        this.moves.add(finalMove);

        if (gameWinningStrategy.checkWinner(
                board, players.get(nextPlayerIndex), finalMove.getCell()
        )) {
            gameStatus = GameState.ENDED;
            winner = players.get(nextPlayerIndex);
        }
        if(gameStatus.equals(GameState.IN_PROGRESS) && emptyCells == 0) {
            gameStatus = GameState.DRAW;
        }
        nextPlayerIndex += 1;
        nextPlayerIndex %= players.size();
    }

    public void displayBoard() {
        this.board.printBoard();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameState gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public static class Builder {
        private int dimension;
        private List<Player> players;


        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }


        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private boolean valid() throws Exception {
            if (this.dimension < 3) {
                throw new PlayersCountDimensionMismatchException("Dimension of game can't be 1");
            }

            if (this.players.size() != this.dimension - 1) {
                throw new PlayersCountDimensionMismatchException("Number of Players must be Dimension - 1");
            }

            // Validate no 2 people with same char

            // Validate all 1 bot

            return true;
        }

        public Game build() throws Exception {
            try {
                valid();
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }

            Game game = new Game();
            game.setGameStatus(GameState.IN_PROGRESS);
            game.setPlayers(players);
            game.setMoves(new ArrayList<>());
            game.setBoard(new Board(dimension));
            game.setNextPlayerIndex(0);
            game.setGameWinningStrategy(new OrderOneGameWinningStrategy(dimension));
            game.setEmptyCells(dimension*dimension);

            return game;
        }
    }
}