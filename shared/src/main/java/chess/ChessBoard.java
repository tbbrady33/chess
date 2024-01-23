package chess;
import java.util.ArrayList;
/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    private ArrayList<ArrayList<ChessPiece>> chessarray = new ArrayList<ArrayList<ChessPiece>>();
    public ChessBoard() {
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {

        chessarray.get(position.getRow()).add(position.getColumn(),piece);
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {

        return chessarray.get(position.getRow()).get(position.getColumn());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void removePiece(ChessPosition position, ChessPiece piece){
        chessarray.get(position.getRow()).remove(position.getColumn());
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        chessarray.clear();
        // white pawns
        for (int i = 0; i < 7; i++){
            addPiece(new ChessPosition(1,i), new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.PAWN));
        }
        // Black pawns
        for (int i = 0; i < 7; i++){
            addPiece(new ChessPosition(6,i), new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.PAWN));
        }
        // White Rooks
        addPiece(new ChessPosition(0,0), new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.ROOK));
        addPiece(new ChessPosition(0,7), new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.ROOK));

        // Black Rooks
        addPiece(new ChessPosition(7,0), new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.ROOK));
        addPiece(new ChessPosition(7,7), new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.ROOK));

        // White Knights
        addPiece(new ChessPosition(0,1), new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(0,6), new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.KNIGHT));

        // Black Knights
        addPiece(new ChessPosition(7,1), new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(7,6), new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.KNIGHT));

        //White Bishops
        addPiece(new ChessPosition(0,2), new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(0,5), new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.BISHOP));

        //Black Bishops
        addPiece(new ChessPosition(7,2), new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(7,5), new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.BISHOP));

        // White Queen
        addPiece(new ChessPosition(0,3),new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.QUEEN));

        //Black Queen
        addPiece(new ChessPosition(7,3), new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.QUEEN));

        //White King
        addPiece(new ChessPosition(0,4), new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.KING));

        // Black King
        addPiece(new ChessPosition(7,4), new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.KING));

    }
}
