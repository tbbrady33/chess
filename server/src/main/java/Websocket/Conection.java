package Websocket;

import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;

public class Conection {
    public String authToken;
    public Session session;

    public Conection(String authToken, Session session) {
        this.authToken = authToken;
        this.session = session;
    }

    public void send(String msg) throws IOException {
        session.getRemote().sendString(msg);
    }
}
