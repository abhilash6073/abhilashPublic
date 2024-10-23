package TicTacToe.Strategies.BotPlayingStrategy;

import TicTacToe.models.Board;
import TicTacToe.models.Move;
import TicTacToe.models.Player;

public interface BotPlayingStrategy {
    Move makeMove(Player player, Board board);
}
