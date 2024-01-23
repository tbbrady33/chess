package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class RookMoves implements ChessMovement{
    public RookMoves(){
    }
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> moves = new HashSet<ChessMove>();

        // moves to the right
        boolean nomoresquares = false;
        int i = 1;
        while (nomoresquares) {
            if (myPosition.getColumn() + i < 7) {

                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + i)).getPieceType() == null) {
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

                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() - i)).getPieceType() == null) {
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

                if (board.getPiece(new ChessPosition(myPosition.getRow()+1, myPosition.getColumn())).getPieceType() == null) {
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

                if (board.getPiece(new ChessPosition(myPosition.getRow()-1, myPosition.getColumn())).getPieceType() == null) {
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