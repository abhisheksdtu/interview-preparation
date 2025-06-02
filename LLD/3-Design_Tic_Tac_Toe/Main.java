package LLD.Design_Tic_Tac_Toe;

import LLD.Design_Tic_Tac_Toe.TicTacToeGame;

public class Main {

    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        game.initializeGame();
        System.out.println("game winner is: " + game.startGame());
    }
}
