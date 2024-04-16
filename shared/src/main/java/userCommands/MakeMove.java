package userCommands;

import chess.ChessMove;

public class MakeMove extends UserGameCommand {
    private int gameID;
    private ChessMove move;
    public MakeMove(CommandType commandType, String authToken, int gameID, ChessMove move){
        super(commandType,authToken);
        this.gameID = gameID;
        this.move = move;
    }

    public int getGameID() {
        return gameID;
    }


    public ChessMove getMove() {
        return move;
    }

}
