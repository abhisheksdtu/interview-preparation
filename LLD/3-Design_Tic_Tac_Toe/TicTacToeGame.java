package LLD.Design_Tic_Tac_Toe;

import LLD.Design_Tic_Tac_Toe.models.Board;
import LLD.Design_Tic_Tac_Toe.models.PieceType;
import LLD.Design_Tic_Tac_Toe.models.Player;
import LLD.Design_Tic_Tac_Toe.models.PlayingPieceO;
import LLD.Design_Tic_Tac_Toe.models.PlayingPieceX;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class TicTacToeGame {
    public Board board;
    public Deque<Player> players;

    public void initializeGame() {
        players = new LinkedList<>();

        PlayingPieceX playingPieceX = new PlayingPieceX();
        Player player1 = new Player("Player 1", playingPieceX);


        PlayingPieceO playingPieceO = new PlayingPieceO();
        Player player2 = new Player("Player 2", playingPieceO);

        players.add(player1);
        players.add(player2);

        board = new Board(3);
    }

    public String startGame() {
        boolean noWinner = true;

        while (noWinner) {
            Player player = players.removeFirst();
            board.printBoard();

            if (board.getFreeCells().isEmpty()) {
                break;
            }

            System.out.println("Player - " + player.getName() + " Enter row, column ");
            Scanner sc = new Scanner(System.in);
            int row = sc.nextInt();
            int col = sc.nextInt();

            boolean pieceAddedSuccessfully = board.addPiece(row, col, player.playingPiece);

            if (!pieceAddedSuccessfully) {
                System.out.println("Incorrect position chosen, try again");
                players.addFirst(player);
                continue;
            }

            players.addLast(player);

            boolean winner = isThereWinner(row, col, player.getPlayingPiece().getPieceType());

            if (winner) {
                return player.getName();
            }

        }

        return "Tie";
    }

    private boolean isThereWinner(int row, int col, PieceType pieceType) {
        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        //need to check in row
        for(int i=0;i<board.size;i++) {

            if(board.getBoard()[row][i] == null || board.getBoard()[row][i].pieceType != pieceType) {
                rowMatch = false;
            }
        }

        //need to check in column
        for(int i=0;i<board.size;i++) {

            if(board.getBoard()[i][col] == null || board.getBoard()[i][col].pieceType != pieceType) {
                columnMatch = false;
            }
        }

        //need to check diagonals
        for(int i=0, j=0; i<board.size;i++,j++) {
            if (board.getBoard()[i][j] == null || board.getBoard()[i][j].pieceType != pieceType) {
                diagonalMatch = false;
            }
        }

        //need to check anti-diagonals
        for(int i=0, j=board.size-1; i<board.size;i++,j--) {
            if (board.getBoard()[i][j] == null || board.getBoard()[i][j].pieceType != pieceType) {
                antiDiagonalMatch = false;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;

    }
}
