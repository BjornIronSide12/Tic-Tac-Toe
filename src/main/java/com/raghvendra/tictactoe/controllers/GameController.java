package main.java.com.raghvendra.tictactoe.controllers;

import java.util.List;

import main.java.com.raghvendra.tictactoe.models.Game;
import main.java.com.raghvendra.tictactoe.models.GameState;
import main.java.com.raghvendra.tictactoe.models.Player;

public class GameController {
    public Game startGame(int dimensionOfBoard, 
                   List<Player> players) throws Exception {
                try {
                    return Game.getBuilder()
                            .setPlayers(players)
                            .setDimension(dimensionOfBoard)
                            .build();
                } catch(Exception e) {
                    System.out.println("Error in starting the game");
            return null;
        }
    }

    public void makeMove(Game game) {
        game.makeNextMove();
    }

    void undo(Game game) {

    }

    public GameState checkState(Game game) {
        return game.getGameStatus();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public void printBoard(Game game) {
        game.displayBoard();
    }
}
