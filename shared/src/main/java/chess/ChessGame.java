package chess;

import java.util.Collection;
import java.util.HashSet;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {

    private TeamColor team;
    private ChessBoard board;
    public ChessGame() {

    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {

        return team;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {

        this.team = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        Collection<ChessMove> moves = new HashSet<ChessMove>();
        moves = board.getPiece(startPosition).pieceMoves(board,startPosition);
        // Find all the checks and remove them, loop through
        if(board.getPiece(startPosition).getPieceType() == ChessPiece.PieceType.KING){

        }else {

        }
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        if(board.getPiece(move.getStartPosition()).getTeamColor() != team){
            throw new InvalidMoveException();
        }
        // doesnt work throw expeption
        ;
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        // Call King pos
        //Loop through valid moves of the oposite team to see if one of them has that position

        return null;
    }

    /**
     * *Loop throught and find the king of the team in question and return the position
     * @param teamColor
     * @return
     */
    public ChessPosition KingPos(TeamColor teamColor){
        return null;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        // call king pos
        // if king has no valid moves return true else return false
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        // if all the peices on a team have no moves return true only if it is their turn
    }

    /**
     * Find all the peices accociated with team color
     * @param teamColor
     * @return
     */
    public Collection<ChessPosition> allPieces(TeamColor teamColor){
        // loop through all squares to see what team they are on and return the ones with the same teamColor
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {

        this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {

        return board;
    }
}
