package Moves;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessMovement;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class RookMoves implements ChessMovement {
    public RookMoves(){
    }
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> moves = new HashSet<ChessMove>();

        // moves to the right
        boolean nomoresquares = true;
        int i = 1;
        while (nomoresquares) {
            if (myPosition.getColumn()+ i <= 8) {

                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + i)) == null) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + i)));
                } else if (board.getPiece(myPosition).getTeamColor() == board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + i)).getTeamColor()) {
                    nomoresquares = false;
                    break;
                } else if (board.getPiece(myPosition).getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + i)).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + i)));
                    nomoresquares = false;
                    break;
                }
            } else {
                nomoresquares = false;
                break;
            }
            i++;
        }
        // moves to the left
        nomoresquares = true;
        i = 1;
        while (nomoresquares) {
            if (myPosition.getColumn() - i > 0) {

                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() - i)) == null) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() - i)));
                } else if (board.getPiece(myPosition).getTeamColor() == board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() - i)).getTeamColor()) {
                    nomoresquares = false;
                    break;
                } else if (board.getPiece(myPosition).getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() - i)).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() - i)));
                    nomoresquares = false;
                    break;
                }
            } else {
                nomoresquares = false;
                break;
            }
            i++;
        }
        // moves up
        nomoresquares = true;
        i = 1;
        while (nomoresquares) {
            if (myPosition.getRow()+ i <= 8) {

                if (board.getPiece(new ChessPosition(myPosition.getRow()+i, myPosition.getColumn())) == null) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow()+i, myPosition.getColumn() )));
                } else if (board.getPiece(myPosition).getTeamColor() == board.getPiece(new ChessPosition(myPosition.getRow()+i, myPosition.getColumn())).getTeamColor()) {
                    nomoresquares = false;
                    break;
                } else if (board.getPiece(myPosition).getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow()+i, myPosition.getColumn())).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow()+i, myPosition.getColumn())));
                    nomoresquares = false;
                    break;
                }
            } else {
                nomoresquares = false;
                break;
            }
            i++;
        }

        // moves down
        nomoresquares = true;
        i = 1;
        while (nomoresquares) {
            if (myPosition.getRow()- i > 0) {

                if (board.getPiece(new ChessPosition(myPosition.getRow()-i, myPosition.getColumn())) == null) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow()-i, myPosition.getColumn() )));
                } else if (board.getPiece(myPosition).getTeamColor() == board.getPiece(new ChessPosition(myPosition.getRow()-i, myPosition.getColumn())).getTeamColor()) {
                    nomoresquares = false;
                    break;
                } else if (board.getPiece(myPosition).getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow()-i, myPosition.getColumn())).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow()-i, myPosition.getColumn())));
                    nomoresquares = false;
                    break;
                }
            } else {
                nomoresquares = false;
                break;
            }
            i++;
        }

        return moves;
    }
}