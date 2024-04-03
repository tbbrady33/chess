package ui.Gameplay;

import chess.ChessGame;
import com.google.gson.Gson;

import com.sun.nio.sctp.NotificationHandler;
import dataAccess.DataAccessException;
import webSocketMessages.Action;
import webSocketMessages.Notification;
import webSocketMessages.serverMessages.ServerMessage;
import webSocketMessages.userCommands.UserGameCommand;

import javax.swing.*;
import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketCommunicator {
    Session session;
    ServerMessage notificationHandler;


    public WebSocketCommunicator(String url, ServerMessage notificationHandler) throws DataAccessException {
        try {
            url = url.replace("http", "ws");
            URI socketURI = new URI(url + "/connect");
            this.notificationHandler = notificationHandler;

            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            this.session = container.connectToServer(this, socketURI);

            //set message handler
            this.session.addMessageHandler(new MessageHandler.Whole<String>() {
                @Override
                public void onMessage(String message) {
                    ServerMessage notification = new Gson().fromJson(message, ServerMessage.class);
                    notificationHandler.notify(notification);
                }
            });
        } catch (DeploymentException | IOException | URISyntaxException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }

    //Endpoint requires this method, but you don't have to do anything
    public void onOpen(Session session, EndpointConfig endpointConfig) {
    }

    // just send the join and stuff like that here then I will take care of the messages in the server
    public void Join_Player(int gameID, ChessGame.TeamColor playerColor, String authToken) throws DataAccessException{
        try{
            var action = new webSocketMessages.userCommands.UserGameCommand(UserGameCommand.CommandType.JOIN_PLAYER, authToken);
            this.session.getBasicRemote().sendText(new Gson().toJson(action));
        } catch (IOException ex){
            ex.printStackTrace();
            throw new DataAccessException(ex.getMessage());
        }
    }

    public void Join_Observer(int gameID, String authToken) throws DataAccessException{
        try{
            var action = new webSocketMessages.userCommands.UserGameCommand(UserGameCommand.CommandType.JOIN_OBSERVER, authToken);
            this.session.getBasicRemote().sendText(new Gson().toJson(action));
        } catch (IOException ex){
            ex.printStackTrace();
            throw new DataAccessException(ex.getMessage());
        }
    }

    public void Make_Move(int gameID){

    }

    public void Leave(int gameID){

    }

    public void Resign(int gameID){

    }
}
