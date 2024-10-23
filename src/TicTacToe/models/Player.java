package TicTacToe.models;

import TicTacToe.Exceptions.CellNotOnBoardException;
import TicTacToe.Exceptions.InvalidCellChosenException;

import java.util.Scanner;

public class Player {
    private static int idCounter = 0;
    private int id;
    private String name;
    private PlayerType playerType;
    private Symbol symbol;
    private Scanner scanner;

    public Player(String name, PlayerType playerType, Symbol symbol) {
        this.id = idCounter++;
        this.name = name;
        this.playerType = playerType;
        this.symbol = symbol;
        this.scanner = new Scanner(System.in);
        //scanner has been taken inside constructor so that we don't need to create the scanner object again and again whenever we need inputs
    }

    public Move makeMove(Board board){
        System.out.println("Please enter the row for " + this.getName() +  "'s move");
        int row = scanner.nextInt();
        System.out.println("Please enter the col for " + this.getName() +  "'s move");
        int col = scanner.nextInt();
        //validate the move and throw an exception
        //validate whether the new cell is
        //a) on the board
        //b)not filled
        //c)not blocked
        //TODO- check
        try{
            if (row<0 || row > board.getSize()-1 || col<0 || col > board.getSize()-1){
                throw new CellNotOnBoardException("Error: Cell chosen is outside the dimensions of the board");
        }
        }catch (CellNotOnBoardException e){
            System.out.println(e.getMessage());
            return makeMove(board);
        }
        try{if (!board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)){
                throw new InvalidCellChosenException("Error: Chosen cell is already FILLED/BLOCKED. Try another cell.");
            }
        }catch (InvalidCellChosenException e){
            System.out.println(e.getMessage());
            return makeMove(board);
        }

        board.getBoard().get(row).get(col).setPlayer(this);
        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
       return new Move(new Cell(row, col, this), this);
       //we are using 'this' here for player because we are inside the Player class itself
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
