package webSocketMessages.serverMessages;

import chess.ChessGame;

public class LoadMessage extends ServerMessage{
    private ChessGame game;
    public LoadMessage(ServerMessageType type, ChessGame game) {
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
