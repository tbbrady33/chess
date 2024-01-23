package chess;

import java.util.ArrayList;
import java.util.Collection;

public class BishopMoves implements ChessMovement {
    public BishopMoves() {

    }

    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> moves = new ArrayList<ChessMove>();

        // Bishop up and to the right
        boolean nomoresquares = false;
        int i = 1;
        while (nomoresquares) {
            if (myPosition.getColumn() + i < 7 || myPosition.getRow() + i < 7) {

                if (board.getPiece(new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() + i)).getPieceType() == ChessPiece.PieceType.None) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() + i)));
                } else if (board.getPiece(myPosition).getTeamColor() == board.getPiece(new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() + i)).getTeamColor()) {
                    nomoresquares = true;
                    break;
                } else if (board.getPiece(myPosition).getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() + i)).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() + i)));
                }
            } else {
                nomoresquares = true;
                break;
            }
            i++;
        }

        // down and to the right
        nomoresquares = false;
        i = 1;
        while (nomoresquares) {
            if ((myPosition.getColumn() + i )< 7 || (myPosition.getRow() - i) >0) {

                if (board.getPiece(new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() + i)).getPieceType() == ChessPiece.PieceType.None) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() + i)));
                } else if (board.getPiece(myPosition).getTeamColor() == board.getPiece(new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() + i)).getTeamColor()) {
                    nomoresquares = true;
                    break;
                } else if (board.getPiece(myPosition).getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() + i)).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() + i)));
                }
            } else {
                nomoresquares = true;
                break;
            }
            i++;
        }

        // down and to the left
        nomoresquares = false;
        i = 1;
        while (nomoresquares) {
            if ((myPosition.getColumn() - i )> 0 || (myPosition.getRow() - i) >0) {

                if (board.getPiece(new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() - i)).getPieceType() == ChessPiece.PieceType.None) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() - i)));
                } else if (board.getPiece(myPosition).getTeamColor() == board.getPiece(new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() - i)).getTeamColor()) {
                    nomoresquares = true;
                    break;
                } else if (board.getPiece(myPosition).getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() - i)).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() - i)));
                }
            } else {
                nomoresquares = true;
                break;
            }
            i++;
        }
        // up and to the left;
        nomoresquares = false;
        i = 1;
        while (nomoresquares) {
            if ((myPosition.getColumn() - i )< 7 || (myPosition.getRow() + i) >0) {

                if (board.getPiece(new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() - i)).getPieceType() == ChessPiece.PieceType.None) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() + i)));
                } else if (board.getPiece(myPosition).getTeamColor() == board.getPiece(new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() - i)).getTeamColor()) {
                    nomoresquares = true;
                    break;
                } else if (board.getPiece(myPosition).getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() - i)).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() - i)));
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
