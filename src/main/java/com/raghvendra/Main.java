package main.java.com.raghvendra;

import java.util.ArrayList;
import java.util.List;

import main.java.com.raghvendra.tictactoe.controllers.GameController;
import main.java.com.raghvendra.tictactoe.models.Bot;
import main.java.com.raghvendra.tictactoe.models.BotDifficultyLevel;
import main.java.com.raghvendra.tictactoe.models.Game;
import main.java.com.raghvendra.tictactoe.models.GameState;
import main.java.com.raghvendra.tictactoe.models.Player;
import main.java.com.raghvendra.tictactoe.models.PlayerType;
import main.java.com.raghvendra.tictactoe.models.Symbol;
import main.java.com.raghvendra.tictactoe.strategies.winningstrategies.WinningStrategy;

public class Main {
    public static void main(String[] args) {

        GameController gameController = new GameController();

        try {
            int dimensionOfGame = 3; 

            List<Player> players = new ArrayList<>();
            players.add(
                    new Player(1L , "Bob", new Symbol('X'), PlayerType.HUMAN)
                    );
            
            players.add(                    
                    new Bot(2L, "GPT", new Symbol('O'), BotDifficultyLevel.HARD)
                    );

            List<WinningStrategy> winningStrategies = new ArrayList<>();

            Game game = gameController
                            .startGame(dimensionOfGame,
                                       players,
                                       winningStrategies
            );
            while(gameController.checkState(game).equals(GameState.IN_PROGRESS)) {
                // 1 Print the board
                // 2 mention who's turn
                // 3 ask to move 
                
                gameController.printBoard(game);
                gameController.makeMove(game);
                
            }
        }
        catch (Exception e) {
            System.out.println("Something went wrong");
        }


        System.out.println("GAME IS STARTING");

        // Game game = gameController.startGame();

        // while(gameController.getGameStatus(game).equals(IN_PROGRESS) ) {
        //     gameController.printBoard(game);
        //     gameController.makeNextMove(game);
        // }
        // if(gameController.getStatus(game).equals(DRAW)) {
        //     System.out.println("GAME HAS DRAWN");
        // }
        // else {
        //     System.out.println("GAME HAS WON");
        // }
    }
}
