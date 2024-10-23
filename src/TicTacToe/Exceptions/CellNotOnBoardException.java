package TicTacToe.Exceptions;

public class CellNotOnBoardException extends RuntimeException{
    public CellNotOnBoardException(String message) {
        super(message);
    }
}
