package Websocket;


import chess.*;
import com.google.gson.Gson;
import dataAccess.*;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import Model.AuthData;
import Model.GameData;
import userCommands.*;
import webSocketMessages.ErrorMessage;
import webSocketMessages.LoadMessage;
import webSocketMessages.NotificationMessage;
import webSocketMessages.ServerMessage;

import java.io.IOException;

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
    public void onMessage(Session session, String message) throws IOException, DataAccessException, InvalidMoveException{

        webSocketMessages.userCommands.UserGameCommand action = new Gson().fromJson(message, webSocketMessages.userCommands.UserGameCommand.class);
        games = new GameManager();
        this.session = new Conection(action.getAuthString(),session);
        switch (action.getCommandType()) {
            case JOIN_OBSERVER: Join_Observer actualAction = new Gson().fromJson(message, Join_Observer.class);
                join_observer(actualAction);
            case JOIN_PLAYER:
                Join_Player actualAction1 = new Gson().fromJson(message,Join_Player.class);
                joinPlayer(actualAction1);
            case LEAVE:
                Leave actualAction5 = new Gson().fromJson(message,Leave.class);
                leave(actualAction5);
            case MAKE_MOVE:
                Make_Move actualAction2 = new Gson().fromJson(message,Make_Move.class);
                makeMove(actualAction2);
            case RESIGN:
                Resign actualAction3 = new Gson().fromJson(message,Resign.class);
                resign(actualAction3);
        }
    }
    private void leave(Leave action) throws  DataAccessException, IOException{
        int gameID = action.getGameID();
        String authToken = action.getAuthString();
        AuthData user = authorization.getAuth(authToken);
        String username = user.username();
        GameData game = gameization.getGame(gameID);
        // update the user in the database
        if (game.blackUsername() != null && game.blackUsername().equals(username)) {
            gameization.changeUsername(gameID,null, ChessGame.TeamColor.BLACK);
        } else if (game.whiteUsername() != null && game.whiteUsername().equals(username)) {
            gameization.changeUsername(gameID,null, ChessGame.TeamColor.WHITE);
        }
        // update websocket stuff
        for(SingleGame game1: games.getGames()){
            if(game1.getGameID() == gameID){
                game1.remove(authToken);
                game1.broadcast(null,new NotificationMessage(ServerMessage.ServerMessageType.NOTIFICATION,username + "left the game"));
            }

        }



    }
    private void join_observer(Join_Observer action) throws IOException, DataAccessException{

        int gameID = action.getGameID();
        String authToken = action.getAuthString();
        AuthData user = authorization.getAuth(authToken);
        String username = user.username();
        // send load game to root
        var load = new LoadMessage(ServerMessage.ServerMessageType.LOAD_GAME,gameization.getGame(gameID));//what game
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

    private void joinPlayer(Join_Player action) throws IOException, DataAccessException{

        int gameID = action.getGameID();
        String authToken = action.getAuthString();
        AuthData user = authorization.getAuth(authToken);
        String username = user.username();
        if(gameization.getGame(gameID).game().getBoard() == null){
            System.out.print("Game is null");
        }else {
            //update game and database

            GameData game = gameization.getGame(gameID);
            gameization.changeUsername(gameID,username,action.getColor());
            // send load game to root
            var load = new LoadMessage(ServerMessage.ServerMessageType.LOAD_GAME, gameization.getGame(gameID));//what game
            var lgame = new Gson().toJson(load);
            session.send(lgame);

            if (games.getGames() == null) {
                games.addGame(new SingleGame(gameID));
            }

            // Send message to other people
            for (SingleGame game1 : games.getGames()) {
                if (game1.getGameID() == gameID) {
                    game1.add(authToken, session.session);
                    if (action.getColor() == ChessGame.TeamColor.BLACK) {
                        game1.broadcast(authToken, new NotificationMessage(ServerMessage.ServerMessageType.NOTIFICATION, username + "has joined the game as Black!"));
                    } else if (action.getColor() == ChessGame.TeamColor.WHITE) {
                        game1.broadcast(authToken, new NotificationMessage(ServerMessage.ServerMessageType.NOTIFICATION, username + "has joined the game as White!"));
                    }
                }

            }
        }
    }

    private void makeMove(Make_Move action) throws IOException, DataAccessException, InvalidMoveException {
        int gameID = action.getGameID();
        String authToken = action.getAuthString();
        AuthData user = authorization.getAuth(authToken);
        String username = user.username();
        ChessMove move = action.getMove();
        boolean works;

        // check to see if move is valid
        if (move == null) {
            for (SingleGame game1 : games.getGames()) {
                if (game1.getGameID() == gameID) {
                    game1.broadcast(null, new ErrorMessage(ServerMessage.ServerMessageType.ERROR, "Move is null"));
                }
            }
        } else {
            GameData game = gameization.getGame(gameID);
            if (game.game().validMoves(new ChessPosition(move.getStartPosition().getRow(), move.getStartPosition().getColumn())).contains(move)) {
                works = true;
            } else {
                works = false;
                System.out.print("Didnt work try again");
            }

            // update the game

            if (works) {
                game.game().makeMove(move);
            }
            // update the database
            gameization.updateGame(game);


            // send load game to everyone and send move to everyone exept root
            for (SingleGame game1 : games.getGames()) {
                if (game1.getGameID() == gameID) {
                    game1.broadcast(null, new LoadMessage(ServerMessage.ServerMessageType.LOAD_GAME, game));
                    switch (game.game().getBoard().chessarray[move.getEndPosition().getRow()][move.getEndPosition().getColumn()].getPieceType()) {
                        case ChessPiece.PieceType.KING:
                            game1.broadcast(authToken, new NotificationMessage(ServerMessage.ServerMessageType.NOTIFICATION, username + " made the move King to " + endPosString(move)));
                        case ChessPiece.PieceType.ROOK:
                            game1.broadcast(authToken, new NotificationMessage(ServerMessage.ServerMessageType.NOTIFICATION, username + " made the move Rook to " + endPosString(move)));
                        case ChessPiece.PieceType.PAWN:
                            game1.broadcast(authToken, new NotificationMessage(ServerMessage.ServerMessageType.NOTIFICATION, username + " made the move Pawn to " + endPosString(move)));
                        case QUEEN:
                            game1.broadcast(authToken, new NotificationMessage(ServerMessage.ServerMessageType.NOTIFICATION, username + " made the move Queen to " + endPosString(move)));
                        case BISHOP:
                            game1.broadcast(authToken, new NotificationMessage(ServerMessage.ServerMessageType.NOTIFICATION, username + " made the move Bishop to " + endPosString(move)));
                        case KNIGHT:
                            game1.broadcast(authToken, new NotificationMessage(ServerMessage.ServerMessageType.NOTIFICATION, username + " made the move Knight to " + endPosString(move)));

                    }
                }
            }
        }
    }

    private void resign(Resign action) throws DataAccessException, IOException {
            int gameID = action.getGameID();
            String authToken = action.getAuthString();
            AuthData user = authorization.getAuth(authToken);
            String username = user.username();
            GameData game = gameization.getGame(gameID);


            if(game.game().getBoard() == null){
                System.out.print("Cant find board");
            }
            else {
                // mark game as over
                //change game
                game.game().setGameOver(true);
                //update database
                gameization.updateGame(game);


                // send message that game is over by resignation
                for (SingleGame game1 : games.getGames()) {
                    if (game1.getGameID() == gameID) {
                        game1.broadcast(null, new NotificationMessage(ServerMessage.ServerMessageType.NOTIFICATION, username + " has resigned, the game is over."));
                    }
                }
            }
    }



    private String endPosString(ChessMove move){
        // make string for end pos
        String Col = "";
        String Row = "";
        switch (move.getEndPosition().getColumn()){
            case 1: Col = "A";
            case 2: Col = "B";
            case 3: Col = "C";
            case 4: Col = "D";
            case 5: Col = "E";
            case 6: Col = "F";
            case 7 : Col = "G";
            case 8: Col = "H";
        }
        switch (move.getEndPosition().getRow()){
            case 1: Row = "1";
            case 2: Row = "2";
            case 3: Row = "3";
            case 4: Row = "4";
            case 5: Row = "5";
            case 6: Row = "6";
            case 7 : Row = "7";
            case 8: Row = "8";
        }
        return Col + Row;

    }


}
