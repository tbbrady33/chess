package chess;

import java.util.ArrayList;
import java.util.Collection;

public class BishopMoves implements ChessMovement {
    public BishopMoves() {

    }

    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> moves = new ArrayList<ChessMove>();

        // Bishop up and to the right
        boolean nomoresquares = true;
        int i = 1;
        while (nomoresquares) {
            if (myPosition.getColumn() + i <= 8 && myPosition.getRow() + i <= 8) {

                if (board.getPiece(new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() + i)) == null) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() + i)));
                } else if (board.getPiece(myPosition).getTeamColor() == board.getPiece(new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() + i)).getTeamColor()) {
                    nomoresquares = false;
                    break;
                } else if (board.getPiece(myPosition).getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() + i)).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() + i)));
                    nomoresquares = false;
                    break;
                }
            } else {
                nomoresquares = false;
                break;
            }
            i++;
        }

        // down and to the right
        nomoresquares = true;
        i = 1;
        while (nomoresquares) {
            if ((myPosition.getColumn() + i )<= 8 && (myPosition.getRow() - i) >0) {

                if (board.getPiece(new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() + i)) == null) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() + i)));
                } else if (board.getPiece(myPosition).getTeamColor() == board.getPiece(new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() + i)).getTeamColor()) {
                    nomoresquares = false;
                    break;
                } else if (board.getPiece(myPosition).getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() + i)).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() + i)));
                    nomoresquares = false;
                    break;
                }
            } else {
                nomoresquares = false;
                break;
            }
            i++;
        }

        // down and to the left
        nomoresquares = true;
        i = 1;
        while (nomoresquares) {
            if ((myPosition.getColumn() - i ) > 0 && (myPosition.getRow() - i) >0) {

                if (board.getPiece(new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() - i)) == null) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() - i)));
                } else if (board.getPiece(myPosition).getTeamColor() == board.getPiece(new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() - i)).getTeamColor()) {
                    nomoresquares = false;
                    break;
                } else if (board.getPiece(myPosition).getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() - i)).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() - i)));
                    nomoresquares = false;
                    break;
                }
            } else {
                nomoresquares = false;
                break;
            }
            i++;
        }
        // up and to the left;
        nomoresquares = true;
        i = 1;
        while (nomoresquares) {
            if ((myPosition.getColumn() - i ) > 0 && (myPosition.getRow() + i) <= 8) {

                if (board.getPiece(new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() - i)) == null) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() - i)));
                } else if (board.getPiece(myPosition).getTeamColor() == board.getPiece(new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() - i)).getTeamColor()) {
                    nomoresquares = false;
                    break;
                } else if (board.getPiece(myPosition).getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() - i)).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() - i)));
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
