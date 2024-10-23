package TicTacToe.Exceptions;

public class InvalidCellChosenException extends RuntimeException {
    public InvalidCellChosenException(String message) {
        super(message);
    }
}
