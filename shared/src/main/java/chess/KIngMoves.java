package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class KIngMoves implements ChessMovement{
    public KIngMoves(){

    }
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> moves = new HashSet<ChessMove>();
        // up up move
        if((myPosition.getRow() <= 7) && (board.getPiece((new ChessPosition(myPosition.getRow() +1, myPosition.getColumn()))) == null ||
                board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn())).getTeamColor()
                != board.getPiece(myPosition).getTeamColor())){
            moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+1, myPosition.getColumn())));
        }
        // up right move
        if((myPosition.getRow() <= 7) && (myPosition.getColumn() <= 7) && (board.getPiece((new ChessPosition(myPosition.getRow() +1, myPosition.getColumn() + 1))) == null ||
                board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1)).getTeamColor()
                        != board.getPiece(myPosition).getTeamColor())){
            moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+1, myPosition.getColumn() + 1)));
        }
        // right right move
        if((myPosition.getColumn() <= 7) && (board.getPiece((new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1))) == null ||
                board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1)).getTeamColor()
                        != board.getPiece(myPosition).getTeamColor())){
            moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1)));
        }
        // right down move
        if((myPosition.getRow() > 0) && (myPosition.getColumn() <= 7) &&(board.getPiece((new ChessPosition(myPosition.getRow() -1, myPosition.getColumn() + 1))) == null ||
                board.getPiece(new ChessPosition(myPosition.getRow() -1, myPosition.getColumn() + 1)).getTeamColor()
                        != board.getPiece(myPosition).getTeamColor())){
            moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-1, myPosition.getColumn() + 1)));
        }
        // down down move
        if((myPosition.getRow() > 0) &&(board.getPiece((new ChessPosition(myPosition.getRow() -1, myPosition.getColumn()))) == null ||
                board.getPiece(new ChessPosition(myPosition.getRow() -1, myPosition.getColumn())).getTeamColor()
                        != board.getPiece(myPosition).getTeamColor())){
            moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-1, myPosition.getColumn())));
        }
        // down left move
        if((myPosition.getRow() > 0) && (myPosition.getColumn() > 0)&&(board.getPiece((new ChessPosition(myPosition.getRow() -1, myPosition.getColumn()-1))) == null ||
                board.getPiece(new ChessPosition(myPosition.getRow() -1, myPosition.getColumn()-1)).getTeamColor()
                        != board.getPiece(myPosition).getTeamColor())){
            moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()-1)));
        }
        // left left move
        if((myPosition.getColumn() > 0) &&(board.getPiece((new ChessPosition(myPosition.getRow(), myPosition.getColumn()-1))) == null ||
                board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn()-1)).getTeamColor()
                        != board.getPiece(myPosition).getTeamColor())){
            moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow(), myPosition.getColumn()-1)));
        }
        // left up move
        if((myPosition.getColumn() > 0)&& (myPosition.getRow() <= 7) &&(board.getPiece((new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()-1))) == null ||
                board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()-1)).getTeamColor()
                        != board.getPiece(myPosition).getTeamColor())){
            moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()-1)));
        }


        return moves;
    }
}
