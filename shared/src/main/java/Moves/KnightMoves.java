package Moves;

import chess.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class KnightMoves implements ChessMovement {
    public KnightMoves(){

    }
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> moves = new HashSet<>();
        ChessPiece myPiece = board.getPiece(myPosition);
        // Up right move
        if(myPosition.getRow() <= 6 && myPosition.getColumn() <= 7) {
            if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1)) == null) {
                moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1)));
            } else if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1)) != null &&
                    myPiece.getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1)).getTeamColor()) {
                moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1)));
            }
        }

        // up left move
        if(myPosition.getRow() <= 6 && myPosition.getColumn() >= 2) {
            if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() - 1)) == null) {
                moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() - 1)));
            } else if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() - 1)) != null &&
                    myPiece.getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() - 1)).getTeamColor()) {
                moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() - 1)));
            }
        }

        // Right up move
        if(myPosition.getRow() <= 7 && myPosition.getColumn() <= 6) {
            if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2)) == null) {
                moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2)));
            } else if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2)) != null &&
                    myPiece.getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2)).getTeamColor()) {
                moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2)));
            }
        }

        // Right down move
        if(myPosition.getRow() >= 2 && myPosition.getColumn() <= 6) {
            if (board.getPiece(new ChessPosition(myPosition.getRow() -1, myPosition.getColumn() + 2)) == null) {
                moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() -1, myPosition.getColumn() + 2)));
            } else if (board.getPiece(new ChessPosition(myPosition.getRow() -1, myPosition.getColumn() + 2)) != null &&
                    myPiece.getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow() -1, myPosition.getColumn() + 2)).getTeamColor()) {
                moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() -1, myPosition.getColumn() + 2)));
            }
        }

        // Down right move
        if(myPosition.getRow() >= 3 && myPosition.getColumn() <= 7) {
            if (board.getPiece(new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn() + 1)) == null) {
                moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn() + 1)));
            } else if (board.getPiece(new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn() + 1)) != null &&
                    myPiece.getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn() + 1)).getTeamColor()) {
                moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn() + 1)));
            }
        }

        // Down left move
        if(myPosition.getRow() >= 3 && myPosition.getColumn() >= 2) {
            if (board.getPiece(new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn() - 1)) == null) {
                moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn() - 1)));
            } else if (board.getPiece(new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn() - 1)) != null &&
                    myPiece.getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn() - 1)).getTeamColor()) {
                moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn() - 1)));
            }
        }

        // Left down move
        if(myPosition.getRow() >= 2 && myPosition.getColumn() >= 3) {
            if (board.getPiece(new ChessPosition(myPosition.getRow() -1 , myPosition.getColumn() - 2)) == null) {
                moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 2)));
            } else if (board.getPiece(new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 2)) != null &&
                    myPiece.getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 2)).getTeamColor()) {
                moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 2)));
            }
        }

        // Left up move
        if(myPosition.getRow() <= 7 && myPosition.getColumn() >= 3) {
            if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 2)) == null) {
                moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 2)));
            } else if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 2)) != null &&
                    myPiece.getTeamColor() != board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 2)).getTeamColor()) {
                moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 2)));
            }
        }
        return moves;
    }
}
