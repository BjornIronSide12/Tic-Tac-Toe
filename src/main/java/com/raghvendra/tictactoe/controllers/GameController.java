package main.java.com.raghvendra.tictactoe.controllers;

import java.util.List;

import main.java.com.raghvendra.tictactoe.models.Game;
import main.java.com.raghvendra.tictactoe.models.GameState;
import main.java.com.raghvendra.tictactoe.models.Player;
import main.java.com.raghvendra.tictactoe.strategies.winningstrategies.WinningStrategy;

public class GameController {
    public Game startGame(int dimensionOfBoard, 
                   List<Player> players,
                   List<WinningStrategy> winningStrategies) throws Exception {
                
                return Game.getBuilder()
                        .setPlayers(players)
                        .setWinningStrategies(winningStrategies)
                        .setDimension(dimensionOfBoard)
                        .build();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    void undo(Game game) {

    }

    public GameState checkState(Game game) {
        return game.getGameState();
    }

    void gateWinner(Game game) {

    }

    public void printBoard(Game game) {
        game.printBoard();
    }
}
