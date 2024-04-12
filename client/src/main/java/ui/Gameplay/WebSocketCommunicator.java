package ui.Gameplay;

import chess.ChessGame;
import chess.ChessMove;
import com.google.gson.Gson;

import dataAccess.DataAccessException;
import userCommands.*;


import webSocketMessages.ErrorMessage;
import webSocketMessages.LoadMessage;
import webSocketMessages.NotificationMessage;
import webSocketMessages.ServerMessage;
import webSocketMessages.userCommands.UserGameCommand;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketCommunicator {
    Session session;
    ServerMessageHandler notificationHandler;


    public WebSocketCommunicator(String url, ServerMessageHandler notificationHandler) throws DataAccessException {
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
                    switch(notification.getServerMessageType()){
                        case NOTIFICATION:
                            NotificationMessage actual = new Gson().fromJson(message,NotificationMessage.class);
                            notificationHandler.notifyy(actual.getMessage());
                        case ERROR:
                            ErrorMessage actual1 = new Gson().fromJson(message,ErrorMessage.class);
                            notificationHandler.error(actual1.getErrorMessage());
                        case LOAD_GAME:
                            LoadMessage actual2 = new Gson().fromJson(message,LoadMessage.class);
                            notificationHandler.laodGame(actual2.getGame());
                    }
                    // message handler
                    // switch statement to see what thing to do
                    // remember to implement the interface in the UI

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

    public void Join_Player(UserGameCommand.CommandType comand, String authToken, int gameID, ChessGame.TeamColor color) throws DataAccessException{
        try{
            var action = new Join_Player(comand, authToken, gameID, color);
            this.session.getBasicRemote().sendText(new Gson().toJson(action));
        } catch (IOException ex){
            ex.printStackTrace();
            throw new DataAccessException(ex.getMessage());
        }
    }

    public void Join_Observer(UserGameCommand.CommandType comand,int gameID, String authToken) throws DataAccessException{
        try{
            var action = new Join_Observer(comand,authToken,gameID);
            this.session.getBasicRemote().sendText(new Gson().toJson(action));
        } catch (IOException ex){
            ex.printStackTrace();
            throw new DataAccessException(ex.getMessage());
        }
    }

    public void Make_Move(UserGameCommand.CommandType command, int gameID, String authToken, ChessMove move) throws DataAccessException{
        try{
            var action = new Make_Move(command, authToken, gameID, move);
            this.session.getBasicRemote().sendText(new Gson().toJson(action));
        } catch (IOException ex){
            ex.printStackTrace();
            throw new DataAccessException(ex.getMessage());
        }
    }

    public void Leave(UserGameCommand.CommandType command, int gameID, String authToken) throws  DataAccessException{
        try{
            var action = new Leave(command, authToken, gameID);
            this.session.getBasicRemote().sendText(new Gson().toJson(action));
        } catch (IOException ex){
            ex.printStackTrace();
            throw new DataAccessException(ex.getMessage());
        }
    }

    public void Resign(UserGameCommand.CommandType command,int gameID, String authToken) throws DataAccessException{
        try{
            var action = new Resign(command, authToken,gameID);
            this.session.getBasicRemote().sendText(new Gson().toJson(action));
        } catch (IOException ex){
            ex.printStackTrace();
            throw new DataAccessException(ex.getMessage());
        }
    }
}
