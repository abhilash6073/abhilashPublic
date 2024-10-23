//client (where you will start the game) should always talk to the controller and not to the internal code
package TicTacToe.Controller;

import TicTacToe.Strategies.WinningStrategy.OrderOneWinningStrategy;
import TicTacToe.Strategies.WinningStrategy.WinningStrategy;
import TicTacToe.models.Game;
import TicTacToe.models.GameState;
import TicTacToe.models.Move;
import TicTacToe.models.Player;

import java.util.List;

public class GameController {

    //what all things do we need for starting a game - we need a board for which we need the dimension as input
    //we need a list of players
    //game state would be in progress to begin with

    public Game createGame(int dimension, List<Player> players){
        try { return Game.builder()
                .setPlayers(players)
                .setWinningStrategies(List.of(new OrderOneWinningStrategy(dimension)))
                .setDimension(dimension)
                .build();

        } catch (Exception e) {
            System.out.println("Something went wrong, could not start the game");
        }
        return null;
    }

    public void displayBoard(Game game){
        game.getBoard().printBoard();
    }

    public GameState getGameState(Game game){
        return game.getGameState();
    }

    public Move executeMove(Game game, Player player){
        Move move = player.makeMove(game.getBoard());
        updateGameMove(game, move);
        return move;
    }

    public void updateGameMove(Game game, Move move){
        game.getMoves().add(move);
    }

    public String getWinner(Game game){
        return game.getWinner().getName();
    }

    public Player checkWinner(Game game, Move recentMove){
        for(WinningStrategy winningStrategy : game.getWinningStrategies()){
            Player player = winningStrategy.checkWinner(game, game.getBoard(), recentMove);
            if(player != null){
                return player;
            }
        }
        return null;
    }
}
