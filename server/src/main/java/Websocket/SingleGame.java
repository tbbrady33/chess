package Websocket;

import org.eclipse.jetty.websocket.api.Session;
import webSocketMessages.ServerMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class SingleGame {
    public final ConcurrentHashMap<String, Conection> connections = new ConcurrentHashMap<>();

    private int gameID;

    public SingleGame(int gameID){
        this.gameID = gameID;
    }

    public int getGameID() {
        return gameID;
    }


    public void add(String visitorName, Session session) {
        var conection = new Conection(visitorName, session);
        connections.put(visitorName, conection);
    }

    public void remove(String visitorName) {
        connections.remove(visitorName);
    }

    public void broadcast(String excludeVisitorName, ServerMessage notification) throws IOException {
        var removeList = new ArrayList<Conection>();
        for (var c : connections.values()) {
            if (c.session.isOpen()) {
                if (!c.authToken.equals(excludeVisitorName)) {
                    c.send(notification.toString());
                }
            } else {
                removeList.add(c);
            }
        }

        // Clean up any connections that were left open.
        for (var c : removeList) {
            connections.remove(c.authToken);
        }
    }
}
