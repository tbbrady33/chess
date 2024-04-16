package userCommands;

import chess.ChessGame;

public class JoinPlayer extends UserGameCommand {

    private int gameID;
    private ChessGame.TeamColor playerColor;
    public JoinPlayer(CommandType commandType, String authToken, int gameID, ChessGame.TeamColor playerColor){
        super(commandType,authToken);
        this.playerColor = playerColor;
        this.gameID = gameID;
    }

    public int getGameID() {
        return gameID;
    }


    public ChessGame.TeamColor getColor() {
        return playerColor;
    }

}
