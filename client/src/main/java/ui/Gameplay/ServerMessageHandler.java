package ui.Gameplay;

import server.GameData;

public interface ServerMessageHandler {
    void notifyy(String message);
    void laodGame(GameData game);

    void error(String errormessage);
}
