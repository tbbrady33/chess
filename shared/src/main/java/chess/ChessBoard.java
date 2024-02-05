package chess;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    private ChessPiece[][] chessarray = new ChessPiece[8][8];
    public ChessBoard() {
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        chessarray[position.getRow() - 1 ][position.getColumn() - 1] = piece;
    }

    public void coppyBoard(ChessBoard board){
        for(int i = 1; i <= 8 ; i++){
            for(int j = 1; j <= 8; j++){
                ChessPiece piecetoadd = board.getPiece(new ChessPosition(i,j));
                board.addPiece(new ChessPosition(i,j),piecetoadd);
            }
        }
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {

        return chessarray[position.getRow() - 1][position.getColumn() - 1];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessBoard that)) return false;
        return Arrays.deepEquals(chessarray, that.chessarray);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(chessarray);
    }

    @Override
    public String toString() {
        return "ChessBoard{" +
                "chessarray=" + Arrays.deepToString(chessarray) +
                '}';
    }

    public void removePiece(ChessPosition position, ChessPiece piece){
        chessarray[position.getRow() - 1][position.getColumn() - 1] = null;
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        // white pawns
        for (int i = 1; i <= 8; i++) {
            chessarray[2][i - 1] = null;
        }
        for (int i = 1; i <= 8; i++) {
            chessarray[3][i - 1] = null;
        }
        for (int i = 1; i <= 8; i++) {
            chessarray[4][i - 1] = null;
        }
        for (int i = 1; i <= 8; i++) {
            chessarray[5][i - 1] = null;
        }

        for (int i = 1; i <= 8; i++){
            chessarray[1][i-1] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.PAWN);
        }
        // Black pawns
        for (int i = 1; i <= 8; i++){
            chessarray[6][i-1] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.PAWN);
        }
        // White Rooks
        chessarray[0][0] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.ROOK);
        chessarray[0][7] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.ROOK);

        // Black Rooks
        chessarray[7][0] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.ROOK);
        chessarray[7][7] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.ROOK);

        // White Knights
        chessarray[0][1] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.KNIGHT);
        chessarray[0][6] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.KNIGHT);

        // Black Knights
        chessarray[7][1] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.KNIGHT);
        chessarray[7][6] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.KNIGHT);

        //White Bishops
        chessarray[0][2] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.BISHOP);
        chessarray[0][5] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.BISHOP);

        //Black Bishops
        chessarray[7][2] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.BISHOP);
        chessarray[7][5] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.BISHOP);

        // White Queen
        chessarray[0][3] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.QUEEN);

        //Black Queen
        chessarray[7][3] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.QUEEN);

        //White King
        chessarray[0][4] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.KING);

        // Black King
        chessarray[7][4] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.KING);

    }
}
