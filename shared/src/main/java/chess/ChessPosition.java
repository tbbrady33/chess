package chess;

/**
 * Represents a single square position on a chess board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPosition {
    public int piecerow;
    public int piececol;
    public ChessPosition(int row, int col) {
        piecerow = row;
        piececol = col;
    }

    /**
     * @return which row this position is in
     * 1 codes for the bottom row
     */
    public int getRow() {
        return piecerow;}

    /**
     * @return which column this position is in
     * 1 codes for the left row
     */
    public int getColumn(){return piececol;
    }
}
