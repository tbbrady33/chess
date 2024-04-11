package webSocketMessages.serverMessages;

import chess.ChessGame;

import GameData;

public class LoadMessage extends ServerMessage{
    private GameData game;
    public LoadMessage(ServerMessageType type, GameData game) {
        super(type);
        this.game = game;
    }

    public ChessGame getGame() {
        return game;
    }

    public void setGame(ChessGame game) {
        this.game = game;
    }
}
