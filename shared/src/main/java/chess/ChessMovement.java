package chess;

import java.util.Collection;
import java.util.ArrayList;


public abstract class ChessMovement {
    public void move(){}
    // make all the peices subclassses with peice moves collections and return that so all the other class has to do is return the result from this class
    private class PawnMove{
        private Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
            ArrayList<ChessMove> moves = new ArrayList<ChessMove>();
            // white pawn not moved yet
            if ((board.getPiece(myPosition).getTeamColor() == ChessGame.TeamColor.WHITE) && (myPosition.getRow() == 1)) {
                if (board.getPiece(new ChessPosition(2, myPosition.getColumn())).getPieceType() == ChessPiece.PieceType.None) {
                    moves.add(new ChessMove(myPosition,new ChessPosition(2, myPosition.getColumn())));
                }
                if (board.getPiece(new ChessPosition(3, myPosition.getColumn())).getPieceType() == ChessPiece.PieceType.None) {
                    moves.add(new ChessMove(myPosition,new ChessPosition(3, myPosition.getColumn())));
                }
                if (board.getPiece(new ChessPosition(2, myPosition.getColumn() + 1)).getTeamColor() == ChessGame.TeamColor.BLACK) {
                    moves.add(new ChessMove(myPosition,new ChessPosition(2, myPosition.getColumn()+1)));
                }
                if (board.getPiece(new ChessPosition(2, myPosition.getColumn() - 1)).getTeamColor() == ChessGame.TeamColor.BLACK) {
                    moves.add(new ChessMove(myPosition,new ChessPosition(2, myPosition.getColumn()-1)));
                }
            }
            // Black pawn not moved yet
            else if ((board.getPiece(myPosition).getTeamColor() == ChessGame.TeamColor.BLACK) && (myPosition.getRow() == 6)){
                if (board.getPiece(new ChessPosition(5, myPosition.getColumn())).getPieceType() == ChessPiece.PieceType.None) {
                    moves.add(new ChessMove(myPosition,new ChessPosition(5, myPosition.getColumn())));
                }
                if (board.getPiece(new ChessPosition(4, myPosition.getColumn())).getPieceType() == ChessPiece.PieceType.None) {
                    moves.add(new ChessMove(myPosition,new ChessPosition(4, myPosition.getColumn())));
                }
                if (board.getPiece(new ChessPosition(5, myPosition.getColumn() + 1)).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    moves.add(new ChessMove(myPosition,new ChessPosition(5, myPosition.getColumn()+1)));
                }
                if (board.getPiece(new ChessPosition(5, myPosition.getColumn() - 1)).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    moves.add(new ChessMove(myPosition,new ChessPosition(5, myPosition.getColumn()-1)));
                }
            }
            //White piece that has moved position is less than the last rank
            else if (board.getPiece(myPosition).getTeamColor() == ChessGame.TeamColor.WHITE && myPosition.getRow() < 6){
                if (board.getPiece(new ChessPosition(myPosition.getRow()+1, myPosition.getColumn())).getPieceType() == ChessPiece.PieceType.None) {
                    moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+1, myPosition.getColumn())));
                }
                if (board.getPiece(new ChessPosition(myPosition.getRow()+1, myPosition.getColumn() + 1)).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()+1)));
                }
                if (board.getPiece(new ChessPosition(myPosition.getRow()+1, myPosition.getColumn() - 1)).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()-1)));
                }
            }
            // Black pawn that has moved position is less than the last rank
            else if (board.getPiece(myPosition).getTeamColor() == ChessGame.TeamColor.BLACK && myPosition.getRow() > 1){
                if (board.getPiece(new ChessPosition(myPosition.getRow()-1, myPosition.getColumn())).getPieceType() == ChessPiece.PieceType.None) {
                    moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-1, myPosition.getColumn())));
                }
                if (board.getPiece(new ChessPosition(myPosition.getRow()-1, myPosition.getColumn() + 1)).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()+1)));
                }
                if (board.getPiece(new ChessPosition(myPosition.getRow()-1, myPosition.getColumn() - 1)).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()+1)));
                }
            }
            return moves;
            // white on the last rank
            //Black on the last rank
            // Need to implement the last two


        }

    }
    private class RookMoves{
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
            // moves down

            return moves;
        }

    }
}
