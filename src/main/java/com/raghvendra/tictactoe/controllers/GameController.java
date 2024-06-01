package main.java.com.raghvendra.tictactoe.controllers;

import java.util.List;

import main.java.com.raghvendra.tictactoe.models.Game;
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

    void makeMove(Game game) {

    }

    void undo(Game game) {

    }

    void checkState(Game game) {

    }

    void gateWinner(Game game) {

    }

    void printBoard(Game game) {

    }
}
