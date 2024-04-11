package Websocket;


import chess.ChessGame;
import com.google.gson.Gson;
import dataAccess.*;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.client.io.ConnectionManager;
import server.AuthData;
import userCommands.Join_Observer;
import userCommands.Leave;
import webSocketMessages.serverMessages.LoadMessage;
import webSocketMessages.serverMessages.NotificationMessage;
import webSocketMessages.serverMessages.ServerMessage;

import java.io.IOException;
import java.util.HashSet;

@WebSocket
public class WebsocketHandler {
    private Conection session;
    private GameDAO gameization;

    private AuthDAO authorization;

    private UserDAO userization;



    public WebsocketHandler(AuthDAO authorization, GameDAO gameization, UserDAO userization){
        this.authorization = authorization;
        this.gameization = gameization;
        this.userization = userization;
    }


    static GameManager games;
    @OnWebSocketMessage
    public void onMessage(Conection session, String message) throws IOException, DataAccessException {
        this.session = session;
        webSocketMessages.userCommands.UserGameCommand action = new Gson().fromJson(message, webSocketMessages.userCommands.UserGameCommand.class);

        switch (action.getCommandType()) {
            case JOIN_OBSERVER: Join_Observer actualAction = new Gson().fromJson(message, Join_Observer.class);
                join_observer(actualAction);
            //case JOIN_PLAYER ->;
            //case LEAVE -> ;
            //case MAKE_MOVE -> ;
            //case RESIGN -> ;
        }
    }
    private void join_observer(Join_Observer action) throws IOException, DataAccessException{

        int gameID = action.getGameID();
        String authToken = session.authToken;
        AuthData user = authorization.getAuth(authToken);
        String username = user.username();
        // send load game to root
        var load = new LoadMessage(ServerMessage.ServerMessageType.LOAD_GAME,gameization.getGame(gameID).game());//what game
        var lgame = new Gson().toJson(load);
        session.send(lgame);
        // send message to other people

        for(SingleGame game: games.getGames()){
            if(game.getGameID() == gameID){
                game.add(authToken, session.session);
                game.broadcast(authToken, new NotificationMessage(ServerMessage.ServerMessageType.NOTIFICATION,username + "has joined the game as an observer"));
            }

        }
    }
}
