package main.java.com.raghvendra.tictactoe.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.com.raghvendra.tictactoe.exceptions.DuplicateSymbolException;
import main.java.com.raghvendra.tictactoe.exceptions.MoreThanOneBotException;
import main.java.com.raghvendra.tictactoe.exceptions.PlayersCountDimensionMismatchException;
import main.java.com.raghvendra.tictactoe.strategies.winningstrategies.WinningStrategy;


/**
 * Game
 */
public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextMovePlayerIndex;
    private List<WinningStrategy> winningStrategies;

    public static Builder getBuilder() {
        return new Builder();
    }

    //All of the attributes of the game class should be set in the constructor 
    // *** THIS IS MANDETORY *** TO avoid null pointer exception 
    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.nextMovePlayerIndex = 0;
        this.gameState = GameState.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.board = new Board(dimension);

    }

    public static class Builder {
        // Builder has only those attributes which are inputs given by clients 

        private List<Player> players = new ArrayList<>();
        
        private List<WinningStrategy> winningStrategies = new ArrayList<>();

        private int dimension;


        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder addPlayer(Player player) {
            this.players.add(player);
            return this;
        }

        public Builder addWinningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategies.add(winningStrategy);
            return this;
        }
        
        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        private void validateBotCount() throws MoreThanOneBotException {
            int botCount = 0;
            for(Player player: players) {
                if(player.getPlayerType().equals(PlayerType.BOT)) {
                    botCount++;
                }
            }

            if(botCount > 1) {
                throw new MoreThanOneBotException();
            }
        }
        
        private void validateDimensionAndPlayerCount() throws PlayersCountDimensionMismatchException{
            if(players.size() != dimension - 1) {
                throw new PlayersCountDimensionMismatchException();
            }
        }

        private void validateSymbolsForPlayers() throws DuplicateSymbolException {
            Map<Character, Integer> symbolCounts = new HashMap<>();

            for(Player player: players) {
                if(!symbolCounts.containsKey(player.getSymbol().getaChar())) {
                    symbolCounts.put(player.getSymbol().getaChar(), 0);
                }

                symbolCounts.put(
                    player.getSymbol().getaChar(),
                    symbolCounts.get(player.getSymbol().getaChar()) + 1
            );
            
            if(symbolCounts.get(player.getSymbol().getaChar()) > 1) {
                throw new DuplicateSymbolException();
                }
            }
        }
        // We need to validate the data before building the game "Builder design pattern"
        private void validate() throws Exception {
            validateBotCount();
            validateDimensionAndPlayerCount();                
            validateSymbolsForPlayers();
        }

        // Builder requrires a build() method 
        // And we also need to make the constructor of the Game as private 
        public Game build() throws Exception {
            validate();
            return new Game(
                    dimension,
                    players,
                    winningStrategies
            );
        }

    }

    public void printBoard() {
        board.printBoard();
    }

    private boolean validateMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(row >= board.getSize()) {
            return false;
        }
        if(col >= board.getSize()) {
            return false;
        }

        if(board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)) {
            return true;
        }
        return false;
    }

    private boolean checkWinner(Board board, Move move) {
        for(WinningStrategy winningStrategy: winningStrategies) {
            if(winningStrategy.checkWinner(board, move)) {
                return true;
            }
        }
        return false;
    }

    public void makeMove() {
        Player currentMovePlayer = players.get(nextMovePlayerIndex);
        
        System.out.println("It is " + currentMovePlayer.getName() + "'s to turn, please make a move");

        Move move = currentMovePlayer.makeMove(board);

        System.out.println(currentMovePlayer.getName() + " has made a move at row: " 
                        + move.getCell().getRow() + " column: " + move.getCell().getCol() + " .");
 

        if(!validateMove(move)) {
            System.out.println("Invalid Move. Please try again");
            return;
        }
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Cell cellToChange = board.getBoard().get(row).get(col);
        cellToChange.setCellState(CellState.FILLED);
        cellToChange.setPlayer(currentMovePlayer);
        Move FinalMoveObject = new Move(cellToChange, currentMovePlayer);
        moves.add(FinalMoveObject);

        nextMovePlayerIndex += 1;
        nextMovePlayerIndex %= players.size();

        if(checkWinner(board, FinalMoveObject)) {
            gameState = GameState.WIN;
            winner = currentMovePlayer;
        }

        if(moves.size() == this.board.getSize()*this.board.getSize()) {
            gameState = GameState.DRAW;
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    public Board getBoard() {
        return board;
    }
    public void setBoard(Board board) {
        this.board = board;
    }
    public List<Move> getMoves() {
        return moves;

    }
    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
    public Player getWinner() {
        return winner;
    }
    public void setWinner(Player winner) {
        this.winner = winner;
    }
    public GameState getGameState() {
        return gameState;
    }
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    public int getNextMovePlayerIndex() {
        return nextMovePlayerIndex;
    }
    public void setNextMovePlayerIndex(int nextMovePlayerIndex) {
        this.nextMovePlayerIndex = nextMovePlayerIndex;
    }
    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }
    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }
    
}