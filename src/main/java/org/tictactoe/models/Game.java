package org.tictactoe.models;

import org.tictactoe.exception.InvalidGameConstructionParametersException;
import org.tictactoe.strategies.gameWinningstrategies.GameWinningStrategy;
import org.tictactoe.strategies.gameWinningstrategies.OrderOneGameWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game  {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int nextPlayerIndex;
    private GameWinningStrategy gameWinningStrategy;
    private Player winner;

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
    public void displayBoard() {
        this.board.display();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }


    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
    private Game(){
    }


    public static Builder getBuilder(){
        return new Builder();
        }

       public void undo(Game game){

           nextPlayerIndex -= 1;
           nextPlayerIndex %= players.size();

        Player toMovePlayer = players.get(nextPlayerIndex);
        Move lastMove = moves.get(moves.size()-1);
        moves.remove(moves.size()-1);
        Cell lastMoveCell =lastMove.getCell();
        lastMoveCell.setCellState(CellState.EMPTY);
        char lastMoveSymbol = lastMoveCell.getPlayer().getSymbol();
        gameWinningStrategy.doUndo(lastMoveSymbol , lastMoveCell.getRow(),lastMoveCell.getCol());
        System.out.println("undo done by player - " + toMovePlayer.getName());

    }

       public void makeNextMove(){
            Player toMovePlayer = players.get(nextPlayerIndex);
           System.out.println("It is " + players.get(nextPlayerIndex).getName() + "'s turn.");

           Move move = toMovePlayer.decideMove(this.board);

           // validate move

           int row = move.getCell().getRow();
           int col = move.getCell().getCol();
           System.out.println("Move happened at: " +
                   row + ", " + col + ".");

           board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
           board.getBoard().get(row).get(col).setPlayer(players.get(nextPlayerIndex));

           Move FinalMove = new Move(players.get(nextPlayerIndex),
                   board.getBoard().get(row).get(col)
           );
           this.moves.add(FinalMove);

           if(gameWinningStrategy.checkWinner(board, players.get(nextPlayerIndex), FinalMove.getCell())){
               gameStatus = GameStatus.ENDED;
               winner = players.get(nextPlayerIndex);
           }
           if(gameWinningStrategy.checkDraw(moves.size(), board.getBoard().size())){
               gameStatus = GameStatus.DRAW;
           }
           nextPlayerIndex+=1;
           nextPlayerIndex %= players.size();
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

           private boolean valid() throws InvalidGameConstructionParametersException {
               if (this.dimension < 3) {
                   throw new InvalidGameConstructionParametersException("Dimension of game can't be 1");
               }

               if (this.players.size() != this.dimension - 1) {
                   throw new InvalidGameConstructionParametersException("Number of Players must be Dimension - 1");
               }

               // Validate no 2 people with same char

               // Validate all 1 bot

               return true;
           }

           public Game build() throws InvalidGameConstructionParametersException {
               try {
                   valid();
               } catch (Exception e) {
                   throw new InvalidGameConstructionParametersException(e.getMessage());
               }
            Game game = new Game();
               game.setGameStatus(GameStatus.IN_PROGRESS);
               game.setPlayers(players);
               game.setMoves(new ArrayList<>());
               game.setBoard(new Board(dimension));
               game.setNextPlayerIndex(0);
               game.setGameWinningStrategy(new OrderOneGameWinningStrategy(dimension));

               return game;

           }


       }

}
