package chess;

import java.util.Collection;
import java.util.concurrent.BlockingDeque;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    private ChessGame.TeamColor pieceColor;
    private ChessPiece.PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN,
        None
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        switch (board.getPiece(myPosition).getPieceType()){
            case(ChessPiece.PieceType.QUEEN):
                return new QueenMoves().pieceMoves(board,myPosition);
            case(PieceType.KING):
                return new KIngMoves().pieceMoves(board,myPosition);
            case(PieceType.ROOK):
                return new RookMoves().pieceMoves(board,myPosition);
            case(PieceType.BISHOP):
                return new BishopMoves().pieceMoves(board,myPosition);
            case(PieceType.KNIGHT):
                return new KnightMoves().pieceMoves(board,myPosition);
            case(PieceType.PAWN):
                return new PawnMove().pieceMoves(board,myPosition);


        }

    }
}
