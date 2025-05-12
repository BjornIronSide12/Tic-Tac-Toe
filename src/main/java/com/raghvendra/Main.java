package main.java.com.raghvendra;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.com.raghvendra.tictactoe.controllers.GameController;
import main.java.com.raghvendra.tictactoe.models.Game;
import main.java.com.raghvendra.tictactoe.models.GameState;
import main.java.com.raghvendra.tictactoe.models.Player;
import main.java.com.raghvendra.tictactoe.models.PlayerType;
import main.java.com.raghvendra.tictactoe.models.Symbol;

import static main.java.com.raghvendra.tictactoe.models.GameState.DRAW;

public class Main {
    public static void main(String[] args) {

        GameController gameController = new GameController();

        try {
            Scanner scanner = new Scanner(System.in);
            int dimensionOfGame = 0;
            int countOfPlayers = 0 ;
            String isBot = null;


            // Taking dimension input
            System.out.println("Count of players: ");
            if(scanner.hasNextInt()) {
                countOfPlayers = scanner.nextInt();
            }
            dimensionOfGame = countOfPlayers+1;
            System.out.println("Do you want a bot y/n?: ");
            if(scanner.hasNext()) {
                isBot = scanner.next();
            }

            List<Player> players = new ArrayList<>();
            for (int i = 1; i <= countOfPlayers; i++) {
                System.out.println("Please enter name of Player " + i + ":");
                String playerName = scanner.next();
                System.out.println("Please enter the symbol:");
                Symbol playerSymbol = new Symbol(scanner.next().charAt(0));
                players.add(new Player(playerName, playerSymbol, PlayerType.HUMAN));
            }

            if(isBot != null && isBot.equals("y")) {
                dimensionOfGame++; // we have a new bot in the game
                System.out.println("Please enter name of the Bot:");
                String botName = scanner.next();
                System.out.println("Please enter the symbol:");
                Symbol botSymbol = new Symbol(scanner.next().charAt(0));
                players.add(new Player(botName, botSymbol, PlayerType.BOT));
            }


            Game game = gameController.startGame(dimensionOfGame,
                                       players
            );
            while(gameController.checkState(game).equals(GameState.IN_PROGRESS)) {
                // 1 Print the board
                // 2 mention who's turn
                // 3 ask to move 
                System.out.println("Current State of the Board");
                gameController.printBoard(game);
                gameController.makeMove(game);
                
            }
            if(gameController.checkState(game).equals(DRAW)) {
                System.out.println("GAME HAS DRAWN");
            }
            else {
                System.out.println("GAME is ENDED");
                System.out.println("Winner is: " + gameController.getWinner(game).getName());
            }

            scanner.close();
        }
        catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }
}
