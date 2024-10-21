package TicTacToe.models;

public class Symbol {
    private char symbolChar;

    public Symbol(char symbolChar) {
        this.symbolChar = symbolChar;
    }

    public char getSymbolChar() {
        return symbolChar;
    }

    //we are having only getter for symbol
    // so that once a symbol has been set, it is not allowed to be changed
}
