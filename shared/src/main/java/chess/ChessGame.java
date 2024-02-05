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
        //if king is in check only blocks
        if(isInCheck(team)){
            Collection<ChessMove> tomove = new HashSet<>();
            for(ChessMove move: moves){
                ChessBoard otherboard = new ChessBoard();
                otherboard.copyBoard(board);
                ChessPiece newpiece = board.getPiece(move.getStartPosition());
                board.removePiece(move.getStartPosition(),newpiece);
                if(move.getEndPosition() != null){
                    board.removePiece(move.getEndPosition(),null);
                }
                if(move.getPromotionPiece() != null) {
                    board.addPiece(move.getEndPosition(), new ChessPiece(team, move.getPromotionPiece()));
                }
                else{
                    board.addPiece(move.getEndPosition(),newpiece);
                }
                if(isInCheck(team)){
                    tomove.add(move);
                }
                board.copyBoard(otherboard);

            }
            moves.removeAll(tomove);
        }
        // Find all the checks and remove them, loop through
        if(board.getPiece(startPosition).getPieceType() == ChessPiece.PieceType.KING){
            ChessBoard otherboard = new ChessBoard();
            otherboard.copyBoard(board);
            if(moves.isEmpty()){
                return moves;
            }else{
                Collection<ChessMove> toRemove = new HashSet<>();
                for(ChessMove move:moves) {
                    ChessPiece piece = board.getPiece(startPosition);
                    board.removePiece(startPosition,board.getPiece(startPosition));
                    board.addPiece(move.getEndPosition(),piece);
                    if(isInCheck(team)){
                        toRemove.add(move);
                    }
                    board.copyBoard(otherboard);
                    // if is in check add that to another list and subtract the two lists after the loop
                }
                moves.removeAll(toRemove);
                return moves;
            }
        }else {
            return moves;

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
        if (move.getEndPosition().getRow() > 8 || move.getEndPosition().getColumn() > 8){
            throw new InvalidMoveException();
        }
        if(validMoves(move.getStartPosition()).contains(move)){
            ChessPiece piece = board.getPiece(move.getStartPosition());
            board.removePiece(move.getStartPosition(),piece);
            if(move.getEndPosition() != null){
                board.removePiece(move.getEndPosition(),null);
            }
            if(move.getPromotionPiece() != null) {
                board.addPiece(move.getEndPosition(), new ChessPiece(team, move.getPromotionPiece()));
            }
            else{
                board.addPiece(move.getEndPosition(),piece);
            }
        }else{
            throw new InvalidMoveException();
        }
        if(team == TeamColor.WHITE){
            setTeamTurn(TeamColor.BLACK);
        }else if(team == TeamColor.BLACK){
            setTeamTurn(TeamColor.WHITE);
        }

    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        ChessPosition king = KingPos(teamColor);
        TeamColor otherteam = null;
        if(teamColor == TeamColor.BLACK){
            otherteam = TeamColor.WHITE;
        }
        if(teamColor == TeamColor.WHITE){
            otherteam = TeamColor.BLACK;
        }
        Collection<ChessPosition> piecespos = allPieces(otherteam);

        for(ChessPosition pos: piecespos){
            for(ChessMove move:board.getPiece(pos).pieceMoves(board,pos)){
                if(move.getEndPosition().equals(king)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * *Loop throught and find the king of the team in question and return the position
     * @param teamColor
     * @return
     */
    public ChessPosition KingPos(TeamColor teamColor){
        for(int i = 1; i <= 8; i++){
            for(int j = 1; j <= 8; j++){
                if(board.getPiece(new ChessPosition(i,j)) != null && board.getPiece(new ChessPosition(i,j)).getPieceType() == ChessPiece.PieceType.KING){
                    if(board.getPiece(new ChessPosition(i,j)).getTeamColor() == teamColor){
                        return new ChessPosition(i,j);
                    }
                }
            }
        }
        return null;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        ChessPosition king = KingPos(teamColor);
        // if king has no valid moves return true else return false
        ChessBoard otherboard = new ChessBoard();
        otherboard.copyBoard(board);
        // make all possible moves and see if we are still in check if no theen return false otherwise return true
        Collection<ChessPosition> mypieces = allPieces(teamColor);
        if (validMoves(king).isEmpty()) {
            for (ChessPosition piece : mypieces) {
                for (ChessMove move : validMoves(piece)) {
                    ChessPiece newpiece = board.getPiece(move.getStartPosition());
                    board.removePiece(move.getStartPosition(),newpiece);
                    if(move.getEndPosition() != null){
                        board.removePiece(move.getEndPosition(),null);
                    }
                    if(move.getPromotionPiece() != null) {
                        board.addPiece(move.getEndPosition(), new ChessPiece(team, move.getPromotionPiece()));
                    }
                    else{
                        board.addPiece(move.getEndPosition(),newpiece);
                    }
                    if(!isInCheck(team)){
                        board.copyBoard(otherboard);
                        return false;
                    }
                    board.copyBoard(otherboard);
                }
            }
            return true;
        } else{
            return false;
        }
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
        Collection<ChessPosition> piecespos = allPieces(teamColor);
        for(ChessPosition pos: piecespos){
            if(!validMoves(pos).isEmpty()){
                return false;
            }
        }
        return true;
    }

    /**
     * Find all the peices accociated with team color
     * @param teamColor
     * @return
     */
    public Collection<ChessPosition> allPieces(TeamColor teamColor){
        Collection<ChessPosition> pieces = new HashSet<>();
        for(int i = 1; i <= 8; i++){
            for(int j = 1; j <= 8; j++){
                if(board.getPiece(new ChessPosition(i,j)) != null && board.getPiece(new ChessPosition(i,j)).getTeamColor() == teamColor){
                        pieces.add(new ChessPosition(i,j));
                    }
            }
        }
        return pieces;
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
