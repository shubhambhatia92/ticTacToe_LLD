package org.tictactoe;

import org.tictactoe.controller.GameController;
import org.tictactoe.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameController gameController = new GameController();
        System.out.println("give dimension of game");
        int dimension = scanner.nextInt();
        System.out.println("Will there be bot in game? y/n");
        String isBot = scanner.next();

        List<Player> players = new ArrayList<>();

        int toIterate = dimension - 1;
        if(isBot.equals("y")){
            toIterate = dimension-2;
        }
        for(int i = 0;i < toIterate; i++){
            System.out.println("What is the  name of player " + i);
            String playerName = scanner.next();

            System.out.println("What is character of player " + i);
            String playerSymbol = scanner.next();

            players.add(new Player(playerName, playerSymbol.charAt(0), PlayerType.HUMAN));

        }
        if(isBot.equals("y")){
            System.out.println("What is the name of bot?");
            String playerName = scanner.next();

            System.out.println("What is the char of bot?");
            String playerSymbol = scanner.next();

            System.out.print("Give bot difficulty level options available - EASY,MEDIUM,HARD");
            String botDLevel = scanner.next();
            BotDifficultyLevel botLevel = Bot.getBotDifficultyLevel(botDLevel);

            players.add(new Bot(playerName, playerSymbol.charAt(0), botLevel));
        }
        Game game = gameController.createGame(
                dimension,players
        );

        while (gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)) {
            System.out.println("This is the current board:");

            gameController.displayBoard(game);

            System.out.println("Does anyone want to undo? y/n");

            String input = scanner.next();

            if (input.equals("y")) {
                gameController.undo(game);
            } else {
                gameController.executeNextMove(game);
            }
        }

        System.out.println("Game has ended.");
        gameController.displayBoard(game);

        if (!game.getGameStatus().equals(GameStatus.DRAW)) {
            System.out.println("Winner is: ." + gameController.getWinner(game).getName());
        }
        else
        {
            System.out.println("Game is a Draw");
        }

    }
}