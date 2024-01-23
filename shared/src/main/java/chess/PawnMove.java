package chess;

import java.util.ArrayList;
import java.util.Collection;

public class PawnMove implements ChessMovement{
    public PawnMove(){

    }
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
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
        // white pawn is on the last rank
        else if(board.getPiece(myPosition).getTeamColor()== ChessGame.TeamColor.WHITE && myPosition.getRow() == 6){
            if (board.getPiece(new ChessPosition(myPosition.getRow()+1, myPosition.getColumn())).getPieceType() == ChessPiece.PieceType.None) {
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()), ChessPiece.PieceType.KNIGHT));
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()), ChessPiece.PieceType.BISHOP));
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()), ChessPiece.PieceType.ROOK));
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()), ChessPiece.PieceType.QUEEN));
            }
            if (board.getPiece(new ChessPosition(myPosition.getRow()+1, myPosition.getColumn() + 1)).getTeamColor() == ChessGame.TeamColor.BLACK) {
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()+1), ChessPiece.PieceType.KNIGHT));
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()+1), ChessPiece.PieceType.BISHOP));
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()+1), ChessPiece.PieceType.ROOK));
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()+1), ChessPiece.PieceType.QUEEN));
            }
            if (board.getPiece(new ChessPosition(myPosition.getRow()+1, myPosition.getColumn() - 1)).getTeamColor() == ChessGame.TeamColor.BLACK) {
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()-1), ChessPiece.PieceType.KNIGHT));
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()-1), ChessPiece.PieceType.BISHOP));
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()-1), ChessPiece.PieceType.ROOK));
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()-1), ChessPiece.PieceType.QUEEN));
            }

        }
        else if(board.getPiece(myPosition).getTeamColor()== ChessGame.TeamColor.BLACK && myPosition.getRow() == 1){
            if (board.getPiece(new ChessPosition(myPosition.getRow()-1, myPosition.getColumn())).getPieceType() == ChessPiece.PieceType.None) {
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()), ChessPiece.PieceType.KNIGHT));
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()), ChessPiece.PieceType.BISHOP));
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()), ChessPiece.PieceType.ROOK));
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()), ChessPiece.PieceType.QUEEN));
            }
            if (board.getPiece(new ChessPosition(myPosition.getRow()-1, myPosition.getColumn() + 1)).getTeamColor() == ChessGame.TeamColor.WHITE) {
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()+1), ChessPiece.PieceType.KNIGHT));
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()+1), ChessPiece.PieceType.BISHOP));
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()+1), ChessPiece.PieceType.ROOK));
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()+1), ChessPiece.PieceType.QUEEN));
            }
            if (board.getPiece(new ChessPosition(myPosition.getRow()-1, myPosition.getColumn() - 1)).getTeamColor() == ChessGame.TeamColor.WHITE) {
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()-1), ChessPiece.PieceType.KNIGHT));
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()-1), ChessPiece.PieceType.BISHOP));
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()-1), ChessPiece.PieceType.ROOK));
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()-1), ChessPiece.PieceType.QUEEN));
            }

        }
        return moves;

    }

}
