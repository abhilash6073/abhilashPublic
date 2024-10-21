package TicTacToe.models;

import java.util.Scanner;

public class Player {

    private Long id;
    private String name;
    private PlayerType playerType;
    private Symbol symbol;
    private Scanner scanner;

    public Player(Long id, String name, PlayerType playerType, Symbol symbolChar, Scanner scanner) {
        this.id = id;
        this.name = name;
        this.playerType = playerType;
        this.symbol = symbol;
        this.scanner = new Scanner(System.in);
        //scanner has been taken inside constructor so that we don't need to create the scanner object again and again whenever we need inputs
    }

    public Move makeMove(Board board){
        System.out.println("Please enter the row for your move");
        int row = scanner.nextInt();
        System.out.println("Please enter the col for your move");
        int col = scanner.nextInt();
        //validate the move and throw an exception
        //validate whether the new cell is
        //a) on the board
        //b)not filled
        //c)not blocked
        //TODO- check


       return new Move(new Cell(row, col, this), this);
       //we are using 'this' here for player because we are inside the Player class itself
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbolChar(Symbol symbol) {
        this.symbol = symbol;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
