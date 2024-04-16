package ui.Gameplay;

import chess.ChessGame;
import chess.ChessMove;
import com.google.gson.Gson;

import DataAccess.DataAccessException;
import ui.UserInterface;
import userCommands.*;


import webSocketMessages.Error;
import webSocketMessages.LoadGame;
import webSocketMessages.Notification;
import webSocketMessages.ServerMessage;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class WebSocketCommunicator extends Endpoint{
    Session session;
    ServerMessageHandler notificationHandler;


    public WebSocketCommunicator(String url, ServerMessageHandler notification) throws DataAccessException {
        try {
            url = url.replace("http", "ws");
            URI socketURI = new URI(url + "/connect");
            this.notificationHandler = notification;

            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            this.session = container.connectToServer(this, socketURI);

            //set message handler
            this.session.addMessageHandler(new MessageHandler.Whole<String>() {
                @Override
                public void onMessage(String message) {
                    ServerMessage notification = new Gson().fromJson(message, ServerMessage.class);
                    switch(notification.getServerMessageType()){
                        case NOTIFICATION:
                            Notification actual = new Gson().fromJson(message, Notification.class);
                            notificationHandler.notifyy(actual.getMessage());
                            break;
                        case ERROR:
                            Error actual1 = new Gson().fromJson(message, Error.class);
                            notificationHandler.error(actual1.getErrorMessage());
                            break;
                        case LOAD_GAME:
                            LoadGame actual2 = new Gson().fromJson(message, LoadGame.class);
                            notificationHandler.laodGame(actual2.getGame());
                            break;

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
    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
    }

    // just send the join and stuff like that here then I will take care of the messages in the server

    public void joinPlayer(UserGameCommand.CommandType comand, String authToken, int gameID, ChessGame.TeamColor color) throws DataAccessException{
        try{
            var action = new JoinPlayer(comand, authToken, gameID, color);
            this.session.getBasicRemote().sendText(new Gson().toJson(action));
        } catch (IOException ex){
            ex.printStackTrace();
            throw new DataAccessException(ex.getMessage());
        }
    }

    public void joinObserver(UserGameCommand.CommandType comand,int gameID, String authToken) throws DataAccessException{
        try{
            var action = new JoinObserver(comand,authToken,gameID);
            this.session.getBasicRemote().sendText(new Gson().toJson(action));
        } catch (IOException ex){
            ex.printStackTrace();
            throw new DataAccessException(ex.getMessage());
        }
    }

    public void makeMove(UserGameCommand.CommandType command, int gameID, String authToken, ChessMove move) throws DataAccessException{
        try{
            var action = new MakeMove(command, authToken, gameID, move);
            this.session.getBasicRemote().sendText(new Gson().toJson(action));
        } catch (IOException ex){
            ex.printStackTrace();
            throw new DataAccessException(ex.getMessage());
        }
    }

    public void leave(UserGameCommand.CommandType command, int gameID, String authToken) throws  DataAccessException{
        try{
            var actionsing = new Leave(command, authToken, gameID);
            this.session.getBasicRemote().sendText(new Gson().toJson(actionsing));
        } catch (IOException ex){
            ex.printStackTrace();
            throw new DataAccessException(ex.getMessage());
        }
    }

    public void resign(UserGameCommand.CommandType command,int gameID, String authToken) throws DataAccessException{
        try{
            var actions = new Resign(command, authToken,gameID);
            var text = new Gson().toJson(actions);
            this.session.getBasicRemote().sendText(text);
        } catch (IOException ex){
            ex.printStackTrace();
            throw new DataAccessException(ex.getMessage());
        }
    }
}
