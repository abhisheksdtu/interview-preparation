package LLD.Design_Tic_Tac_Toe.models;

import LLD.Design_Tic_Tac_Toe.models.PlayingPiece;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.List;


public class Board {
    public int size;
    public PlayingPiece[][] board;

    public Board(int size) {
        this.size = size;
        this.board = new PlayingPiece[size][size];
    }

    public PlayingPiece[][] getBoard() {
        return board;
    }

    public void printBoard() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].pieceType.name() + "   ");
                } else {
                    System.out.print("    ");
                }
                System.out.print(" | ");

            }
            System.out.println();
        }
    }

    public List<Pair<Integer, Integer>> getFreeCells() {

        List<Pair<Integer, Integer>> freeCells = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (board[i][j] == null) {
                    freeCells.add(new Pair<>(i, j));
                }
            }
        }

        return freeCells;
    }

    public boolean addPiece(int r, int c, PlayingPiece playingPiece) {
        if (board[r][c] != null) {
            return false;
        }
        board[r][c] = playingPiece;
        return true;
    }
}
