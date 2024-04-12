package ui.Gameplay;

import Model.GameData;

public interface ServerMessageHandler {
    void notifyy(String message);
    void laodGame(GameData game);

    void error(String errormessage);
}
