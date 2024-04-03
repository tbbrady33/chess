package webSocketMessages.serverMessages;

import chess.ChessGame;

public class LoadMessage extends ServerMessage{
    private ChessGame game;
    private ServerMessageType type;
    public LoadMessage(ServerMessageType type, ChessGame game) {
        super(type);
        this.type = type;
        this.game = game;
    }

    public ChessGame getGame() {
        return game;
    }


    public ServerMessageType getType() {
        return type;
    }

}
