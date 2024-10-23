package TicTacToe.Strategies.WinningStrategy;

import TicTacToe.models.Board;
import TicTacToe.models.Game;
import TicTacToe.models.Move;
import TicTacToe.models.Player;

public interface WinningStrategy {
   Player checkWinner(Game game, Board board, Move lastMove);
}
