package TicTacToe.Strategies.WinningStrategy;

import TicTacToe.Exceptions.GameDrawnException;
import TicTacToe.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements WinningStrategy{
    //we need to create a list of hashmaps to keep count of the symbols in each row and column
    //we also need hashmaps to keep count of symbols in each of the diagonals and corners
    private int dimension;
    private int symbolsAdded;
    private List<HashMap<Character, Integer>> rowSymbolCount= new ArrayList<>();
    private List<HashMap<Character, Integer>> colSymbolCount= new ArrayList<>();
    private HashMap<Character, Integer> topLeftDiagonalSymbolCount = new HashMap<>();
    private HashMap<Character, Integer> bottomLeftDiagonalSymbolCount = new HashMap<>();
    private HashMap<Character, Integer> cornerSymbolCount = new HashMap<>();

    //constructor
    //this will add hashmaps for each row and each col as per the dimension
    public OrderOneWinningStrategy(int dimension){
        this.dimension = dimension;
        for (int i=0; i<dimension; i++){
            rowSymbolCount.add(new HashMap<>());
            colSymbolCount.add(new HashMap<>());
        }
    }

    //checking if a cell is on the topLeftDiagonal
    public boolean isCellTopLeftDiagonal(int row, int col){
        return row==col;
    }

    //checking if the cell is on the bottom left diagonal
    public boolean isCellBottomLeftDiagonal(int row, int col){
        return row + col == (dimension -1);
    }

    //checking if the cell is a corner cell or not
    public boolean isCornerCell(int row, int col){
        if (row == 0 || row==dimension-1){
                return (col==0 || col == dimension-1);
            }
            return false;
    }




    @Override
    public Player checkWinner(Game game, Board board, Move lastMove) {
        symbolsAdded++;
        char symbol = lastMove.getPlayer().getSymbol().getSymbolChar();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();
        int dimension = board.getSize();

        if (checkForRowWins(row, col, symbol, lastMove) != null) {
            return lastMove.getPlayer();
        }
        else if (checkForColWins(row, col, symbol, lastMove)!= null){
            return lastMove.getPlayer();
        }
        else if (checkForDiagonalWins(row, col, symbol, lastMove)!= null){
            return lastMove.getPlayer();
        }
        else if (checkForCornerWins(row, col, symbol, lastMove)!=null){
            return lastMove.getPlayer();
        }

        try{if (symbolsAdded == (dimension*dimension)){
                game.setGameState(GameState.DRAW);
                board.printBoard();
                throw new GameDrawnException("Game Drawn, Board is full!");
            }
        } catch(GameDrawnException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Player checkForRowWins ( int row, int col, char symbol, Move lastMove){
            //checking if the symbol is present in a particular, if not then add the symbol with count 0
            //incrementing the count of the symbol by 1
            if (!rowSymbolCount.get(row).containsKey(symbol)) {
                rowSymbolCount.get(row).put(symbol, 0);
            }
            rowSymbolCount.get(row).put(symbol, rowSymbolCount.get(row).get(symbol) + 1);

            //after updating the count, check if this player is a winner now
            //winning criteria - count of symbols in the row equal the dimension of the board
            if (rowSymbolCount.get(row).get(symbol) == dimension) {
                return lastMove.getPlayer();
            }
            return null;
        }


        private Player checkForColWins ( int row, int col, char symbol, Move lastMove){
            //similarly check for column
            if (!colSymbolCount.get(col).containsKey(symbol)) {
                colSymbolCount.get(col).put(symbol, 0);
            }
            colSymbolCount.get(col).put(symbol, colSymbolCount.get(col).get(symbol) + 1);
            //after updating the count, check if this player is a winner now
            //winning criteria - count of symbols in the col equal the dimension of the board
            if (colSymbolCount.get(col).get(symbol) == dimension) {
                return lastMove.getPlayer();
            }
            return null;
        }

        private Player checkForDiagonalWins ( int row, int col, char symbol, Move lastMove){
            //similarly check if the cell is part of the top left diagonal, if yes, update the respective hashmap
            if (isCellTopLeftDiagonal(row, col)) {
                if (!topLeftDiagonalSymbolCount.containsKey(symbol)) {
                    topLeftDiagonalSymbolCount.put(symbol, 0);
                }
                topLeftDiagonalSymbolCount.put(symbol, topLeftDiagonalSymbolCount.get(symbol) + 1);
                //after updating the count, check if this player is a winner now
                //winning criteria - count of symbols in the col equal the dimension of the board
                if (topLeftDiagonalSymbolCount.get(symbol) == dimension) {
                    return lastMove.getPlayer();
                }
            }

            if (isCellBottomLeftDiagonal(row, col)) {
                if (!bottomLeftDiagonalSymbolCount.containsKey(symbol)) {
                    bottomLeftDiagonalSymbolCount.put(symbol, 0);
                }
                bottomLeftDiagonalSymbolCount.put(symbol, bottomLeftDiagonalSymbolCount.get(symbol) + 1);
                //after updating the count, check if this player is a winner now
                //winning criteria - count of symbols in the col equal the dimension of the board
                if (bottomLeftDiagonalSymbolCount.get(symbol) == dimension) {
                    return lastMove.getPlayer();
                }
            }
            return null;
        }

        private Player checkForCornerWins ( int row, int col, char symbol, Move lastMove){
            //checking for the 4 corners
            if (isCornerCell(row, col)) {
                if (!cornerSymbolCount.containsKey(symbol)) {
                    cornerSymbolCount.put(symbol, 0);
                }
                cornerSymbolCount.put(symbol, cornerSymbolCount.get(symbol) + 1);
                //after updating the count, check if this player is a winner now
                //winning criteria - count of symbols in the col equal the dimension of the board
                if (cornerSymbolCount.get(symbol) == 4) {
                    return lastMove.getPlayer();
                }
            }
            return null;
        }


    }

