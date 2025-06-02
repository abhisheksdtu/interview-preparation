package LLD.Design_Tic_Tac_Toe.models;

public class PlayingPiece{
    public PieceType pieceType;

    public PlayingPiece(PieceType pieceType){
        this.pieceType = pieceType;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
