package chess;

import java.util.ArrayList;
import java.util.Collection;

public class RookMoves implements ChessMovement{
    private Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> moves = new ArrayList<ChessMove>();

        // moves to the right
        boolean nomoresquares = false;
        int i = 1;
        while (nomoresquares) {
            if (myPosition.getColumn() + i < 7) {

                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + i)).getPieceType() == ChessPiece.PieceType.None) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + i)));
                } else if (board.getPiece(myPosition).getTeamColor() == board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + i)).getTeamColor()) {
                    nomoresquares = true;
                    break;
                } else if (board.getPiece(myPosition).getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + i)).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + i)));
                }
            } else {
                nomoresquares = true;
                break;
            }
            i++;
        }
        // moves to the left
        nomoresquares = false;
        i = 1;
        while (nomoresquares) {
            if (myPosition.getColumn() - i > 0) {

                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() - i)).getPieceType() == ChessPiece.PieceType.None) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() - i)));
                } else if (board.getPiece(myPosition).getTeamColor() == board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() - i)).getTeamColor()) {
                    nomoresquares = true;
                    break;
                } else if (board.getPiece(myPosition).getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() - i)).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() - i)));
                }
            } else {
                nomoresquares = true;
                break;
            }
            i++;
        }
        // moves up
        nomoresquares = false;
        i = 1;
        while (nomoresquares) {
            if (myPosition.getColumn() + i < 7) {

                if (board.getPiece(new ChessPosition(myPosition.getRow()+1, myPosition.getColumn())).getPieceType() == ChessPiece.PieceType.None) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow()+1, myPosition.getColumn() )));
                } else if (board.getPiece(myPosition).getTeamColor() == board.getPiece(new ChessPosition(myPosition.getRow()+1, myPosition.getColumn())).getTeamColor()) {
                    nomoresquares = true;
                    break;
                } else if (board.getPiece(myPosition).getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow()+1, myPosition.getColumn())).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow()+1, myPosition.getColumn())));
                }
            } else {
                nomoresquares = true;
                break;
            }
            i++;
        }

        // moves down
        nomoresquares = false;
        i = 1;
        while (nomoresquares) {
            if (myPosition.getColumn() - i > 0) {

                if (board.getPiece(new ChessPosition(myPosition.getRow()-1, myPosition.getColumn())).getPieceType() == ChessPiece.PieceType.None) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow()-1, myPosition.getColumn() )));
                } else if (board.getPiece(myPosition).getTeamColor() == board.getPiece(new ChessPosition(myPosition.getRow()-1, myPosition.getColumn())).getTeamColor()) {
                    nomoresquares = true;
                    break;
                } else if (board.getPiece(myPosition).getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow()-1, myPosition.getColumn())).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow()-1, myPosition.getColumn())));
                }
            } else {
                nomoresquares = true;
                break;
            }
            i++;
        }

        return moves;
    }
}