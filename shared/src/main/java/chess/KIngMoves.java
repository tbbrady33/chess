package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class KIngMoves implements ChessMovement{
    public KIngMoves(){

    }
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> Moves = new HashSet<>();
        ChessPiece myPiece = board.getPiece(myPosition);
        // up up move
        if(myPosition.getRow() <= 7) {
            if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn())) == null) {
                Moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn())));
            } else if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn())) != null &&
                    myPiece.getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn())).getTeamColor()) {
                Moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn())));
            }
        }

        // up right move
        if(myPosition.getRow() <= 7 && myPosition.getColumn() <= 7) {
            if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1)) == null) {
                Moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1)));
            } else if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1)) != null &&
                    myPiece.getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1)).getTeamColor()) {
                Moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1)));
            }
        }

        // right right move
        if(myPosition.getColumn() <= 7) {
            if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1)) == null) {
                Moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1)));
            } else if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1)) != null &&
                    myPiece.getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1)).getTeamColor()) {
                Moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1)));
            }
        }

        // right down move
        if(myPosition.getRow() >= 2 && myPosition.getColumn() <= 7) {
            if (board.getPiece(new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 1)) == null) {
                Moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 1)));
            } else if (board.getPiece(new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 1)) != null &&
                    myPiece.getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 1)).getTeamColor()) {
                Moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 1)));
            }
        }
        // down down move

        if(myPosition.getRow() >= 2) {
            if (board.getPiece(new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn())) == null) {
                Moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn())));
            } else if (board.getPiece(new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn())) != null &&
                    myPiece.getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn())).getTeamColor()) {
                Moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn())));
            }
        }

        // down left move
        if(myPosition.getRow() >= 2 && myPosition.getColumn() >= 2) {
            if (board.getPiece(new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 1)) == null) {
                Moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 1)));
            } else if (board.getPiece(new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 1)) != null &&
                    myPiece.getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 1)).getTeamColor()) {
                Moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 1)));
            }
        }

        // left left move
        if(myPosition.getColumn() >= 2) {
            if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() - 1)) == null) {
                Moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() - 1)));
            } else if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() - 1)) != null &&
                    myPiece.getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() - 1)).getTeamColor()) {
                Moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() - 1)));
            }
        }

        // left up move
        if(myPosition.getRow() <= 7 && myPosition.getColumn() >= 2) {
            if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 1)) == null) {
                Moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 1)));
            } else if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 1)) != null &&
                    myPiece.getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 1)).getTeamColor()) {
                Moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 1)));
            }
        }
        return Moves;
    }
}
