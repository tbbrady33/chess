package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class QueenMoves implements ChessMovement {
    public QueenMoves() {

    }
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> moves = new HashSet<ChessMove>();
        moves.addAll(new RookMoves().pieceMoves(board,myPosition));
        moves.addAll(new BishopMoves().pieceMoves(board,myPosition));
        return moves;
    }
}
