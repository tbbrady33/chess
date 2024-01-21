package chess;

import java.util.Collection;
import java.util.ArrayList;


public abstract class ChessMovement {
    public void move(){}
    // make all the peices subclassses with peice moves collections and return that so all the other class has to do is return the result from this class
    private class PawnMove{
        private Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
            ArrayList<ChessPosition> moves = new ArrayList<ChessPosition>();
            // white pawn not moved yet
            if ((board.getPiece(myPosition).getTeamColor() == ChessGame.TeamColor.WHITE) && (myPosition.getRow() == 1)) {
                if (board.getPiece(new ChessPosition(2, myPosition.getColumn())).getPieceType() == ChessPiece.PieceType.None) {
                    moves.add(new ChessPosition(2, myPosition.getColumn()));
                }
                if (board.getPiece(new ChessPosition(3, myPosition.getColumn())).getPieceType() == ChessPiece.PieceType.None) {
                    moves.add(new ChessPosition(3, myPosition.getColumn()));
                }
                if (board.getPiece(new ChessPosition(2, myPosition.getColumn() + 1)).getTeamColor() == ChessGame.TeamColor.BLACK) {
                    moves.add(new ChessPosition(2, myPosition.getColumn() + 1));
                }
                if (board.getPiece(new ChessPosition(2, myPosition.getColumn() - 1)).getTeamColor() == ChessGame.TeamColor.BLACK) {
                    moves.add(new ChessPosition(2, myPosition.getColumn() - 1));
                }
            }
            // Black pawn not moved yet
            if ((board.getPiece(myPosition).getTeamColor() == ChessGame.TeamColor.BLACK) && (myPosition.getRow() == 6)){
                if (board.getPiece(new ChessPosition(5, myPosition.getColumn())).getPieceType() == ChessPiece.PieceType.None) {
                    moves.add(new ChessPosition(5, myPosition.getColumn()));
                }
                if (board.getPiece(new ChessPosition(4, myPosition.getColumn())).getPieceType() == ChessPiece.PieceType.None) {
                    moves.add(new ChessPosition(4, myPosition.getColumn()));
                }
                if (board.getPiece(new ChessPosition(5, myPosition.getColumn() + 1)).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    moves.add(new ChessPosition(5, myPosition.getColumn() + 1));
                }
                if (board.getPiece(new ChessPosition(5, myPosition.getColumn() - 1)).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    moves.add(new ChessPosition(5, myPosition.getColumn() - 1));
                }
            }
            //White piece that has moved
            if (board.getPiece(myPosition).getTeamColor() == ChessGame.TeamColor.WHITE){
                if (board.getPiece(new ChessPosition(myPosition.getRow()+1, myPosition.getColumn())).getPieceType() == ChessPiece.PieceType.None) {
                    moves.add(new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()));
                }
                if (board.getPiece(new ChessPosition(myPosition.getRow()+1, myPosition.getColumn() + 1)).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    moves.add(new ChessPosition(myPosition.getRow()+1, myPosition.getColumn() + 1));
                }
                if (board.getPiece(new ChessPosition(myPosition.getRow()+1, myPosition.getColumn() - 1)).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    moves.add(new ChessPosition(myPosition.getRow()+1, myPosition.getColumn() - 1));
                }
            }
            // Black pawn that has moved
            if (board.getPiece(myPosition).getTeamColor() == ChessGame.TeamColor.WHITE){
                if (board.getPiece(new ChessPosition(myPosition.getRow()-1, myPosition.getColumn())).getPieceType() == ChessPiece.PieceType.None) {
                    moves.add(new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()));
                }
                if (board.getPiece(new ChessPosition(myPosition.getRow()-1, myPosition.getColumn() + 1)).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    moves.add(new ChessPosition(myPosition.getRow()-1, myPosition.getColumn() + 1));
                }
                if (board.getPiece(new ChessPosition(myPosition.getRow()-1, myPosition.getColumn() - 1)).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    moves.add(new ChessPosition(myPosition.getRow()-1, myPosition.getColumn() - 1));
                }
            }


        }

    }
}
