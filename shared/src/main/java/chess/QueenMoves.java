package chess;

import java.util.ArrayList;
import java.util.Collection;

public class QueenMoves implements ChessMovement {
    public QueenMoves() {

    }
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> moves = new ArrayList<ChessMove>();
        moves.add(new RookMoves().pieceMoves(board,myPosition));
        moves.add(new BishopMoves().pieceMoves(board,myPosition));
        return moves;
    }
}
