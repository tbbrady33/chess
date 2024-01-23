package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class KnightMoves implements ChessMovement {
    public KnightMoves(){

    }
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> moves = new HashSet<ChessMove>();

        // Up right move
        if (myPosition.getRow() <= 5 && myPosition.getColumn() <= 6 &&
                (board.getPiece(new ChessPosition(myPosition.getRow() + 2 , myPosition.getColumn()+1)).getTeamColor()!=
                board.getPiece(myPosition).getTeamColor() || board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()+1)).getPieceType() ==
                        null)) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1)));
        }

        // Up left move
        if (myPosition.getRow() <= 5 && myPosition.getColumn() >= 1 &&
                (board.getPiece(new ChessPosition(myPosition.getRow() + 2 , myPosition.getColumn()-1)).getTeamColor()!=
                        board.getPiece(myPosition).getTeamColor() || board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()-1)).getPieceType() ==
                        null)) {
            moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() - 1)));
        }

        // Right Up move
        if (myPosition.getRow() <= 6 && myPosition.getColumn() <= 5 &&
                (board.getPiece(new ChessPosition(myPosition.getRow() + 1 , myPosition.getColumn()+2)).getTeamColor()!=
                        board.getPiece(myPosition).getTeamColor() || board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()+2)).getPieceType() ==
                        null)) {
            moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2)));
        }
        // Right down move
        if (myPosition.getRow() >= 1 && myPosition.getColumn() <= 5 &&
                (board.getPiece(new ChessPosition(myPosition.getRow() - 1 , myPosition.getColumn()+2)).getTeamColor()!=
                        board.getPiece(myPosition).getTeamColor() || board.getPiece(new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn()+2)).getPieceType() ==
                        null)) {
            moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 2)));
        }
        // Down right move
        if (myPosition.getRow() >= 2 && myPosition.getColumn() <= 6 &&
                (board.getPiece(new ChessPosition(myPosition.getRow() -2 , myPosition.getColumn()+1)).getTeamColor()!=
                        board.getPiece(myPosition).getTeamColor() || board.getPiece(new ChessPosition(myPosition.getRow() -2, myPosition.getColumn()+1)).getPieceType() ==
                        null)) {
            moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn() + 1)));
        }

        // Down left move
        if (myPosition.getRow() >= 2 && myPosition.getColumn() >= 1 &&
                (board.getPiece(new ChessPosition(myPosition.getRow() -2 , myPosition.getColumn()-1)).getTeamColor()!=
                        board.getPiece(myPosition).getTeamColor() || board.getPiece(new ChessPosition(myPosition.getRow() -2, myPosition.getColumn()-1)).getPieceType() ==
                        null)) {
            moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn() - 1)));
        }

        // Left down move
        if (myPosition.getRow() >= 1 && myPosition.getColumn() >= 2 &&
                (board.getPiece(new ChessPosition(myPosition.getRow() -1 , myPosition.getColumn()-2)).getTeamColor()!=
                        board.getPiece(myPosition).getTeamColor() || board.getPiece(new ChessPosition(myPosition.getRow() -1, myPosition.getColumn()-2)).getPieceType() ==
                        null)) {
            moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 2)));
        }
        // Left up move
        if (myPosition.getRow() <= 6 && myPosition.getColumn() >= 2 &&
                (board.getPiece(new ChessPosition(myPosition.getRow() +1 , myPosition.getColumn()-2)).getTeamColor()!=
                        board.getPiece(myPosition).getTeamColor() || board.getPiece(new ChessPosition(myPosition.getRow() +1, myPosition.getColumn()-2)).getPieceType() ==
                        null)) {
            moves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 2)));
        }

        return moves;
    }
}
