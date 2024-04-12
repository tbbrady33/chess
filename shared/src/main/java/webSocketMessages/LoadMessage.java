package webSocketMessages;


import Model.GameData;

public class LoadMessage extends ServerMessage{
    private GameData game;
    public LoadMessage(ServerMessageType type, GameData game) {
        super(type);
        this.game = game;
    }

    public GameData getGame() {
        return game;
    }

    public void setGame(GameData game) {
        this.game = game;
    }
}
