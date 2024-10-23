package TicTacToe.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> board;

    //TODO - checkout how board is created
    public Board(int size) {
        this.size = size;
        /*
        if size is 3, what we need is
        [[_,_,_],
        [_,_,_],
        [_,_,_]]
         */
        this.board = new ArrayList<>();//this would give the outer arraylist [], but we also need the inner ones
        for (int i = 0; i < size; i++) {
            this.getBoard().add(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                this.getBoard().get(i).add(new Cell(i, j));
            }
        }
    }

    //printing the board
    public void printBoard() {
        for (int i = 0; i < size; i++) {
            List<Cell> row = board.get(i);
            for (int j = 0; j < size; j++) {
                row.get(j).display(); //making use of Cell.display()
            }
            System.out.println();
        }
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }
}

