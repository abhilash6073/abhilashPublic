package TicTacToe.Strategies.WinningStrategy;

public class WinningStrategyFactory {
    public static WinningStrategy getWinningStrategy(int dimension){
        return new OrderOneWinningStrategy(dimension);
    }

    //TODO - can we take input from the user on which winning strategies to be considered in the game
}
