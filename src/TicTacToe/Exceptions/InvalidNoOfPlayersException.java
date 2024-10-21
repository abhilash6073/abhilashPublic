package TicTacToe.Exceptions;

public class InvalidNoOfPlayersException extends RuntimeException {
    public InvalidNoOfPlayersException(String message) {
        super(message);
    }
}
