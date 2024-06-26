package Websocket;


import DataAccess.DataAccessException;
import chess.*;
import com.google.gson.Gson;
import dataAccess.*;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import Model.AuthData;
import Model.GameData;
import webSocketMessages.Error;
import webSocketMessages.LoadGame;
import webSocketMessages.Notification;
import webSocketMessages.ServerMessage;
import userCommands.*;

import java.io.IOException;

@WebSocket
public class WebsocketHandler {
    private Conection session;
    private GameDAO gameization;

    private AuthDAO authorization;

    private UserDAO userization;

    static GameManager games;

    public WebsocketHandler(AuthDAO authorization, GameDAO gameization, UserDAO userization){
        this.authorization = authorization;
        this.gameization = gameization;
        this.userization = userization;
        this.games = new GameManager();
    }



    @OnWebSocketMessage
    public void onMessage(Session session, String message) throws IOException, DataAccessException, InvalidMoveException{

        UserGameCommand action = new Gson().fromJson(message, UserGameCommand.class);

        this.session = new Conection(action.getAuthString(),session);
        switch (action.getCommandType()) {
            case JOIN_OBSERVER: JoinObserver actualAction = new Gson().fromJson(message, JoinObserver.class);
                joinObserver(actualAction);
                break;
            case JOIN_PLAYER:
                JoinPlayer actualAction1 = new Gson().fromJson(message, JoinPlayer.class);
                joinPlayer(actualAction1);
                break;
            case LEAVE:
                Leave actualAction5 = new Gson().fromJson(message,Leave.class);
                leave(actualAction5);
                break;
            case MAKE_MOVE:
                MakeMove actualAction2 = new Gson().fromJson(message, MakeMove.class);
                makeMove(actualAction2);
                break;
            case RESIGN:
                Resign actualAction3 = new Gson().fromJson(message,Resign.class);
                resign(actualAction3);
                break;
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
                game1.broadcast(null,new Notification(ServerMessage.ServerMessageType.NOTIFICATION,username + "left the game"));
            }

        }



    }
    private void joinObserver(JoinObserver action) throws IOException, DataAccessException{

        int gameID = action.getGameID();
        String authToken = action.getAuthString();
        AuthData user = authorization.getAuth(authToken);
        GameData game1 = gameization.getGame(gameID);
        if (user == null){
            var error = new Error(ServerMessage.ServerMessageType.ERROR, "Coudlnt find user");
            var lerror = new Gson().toJson(error);
            session.send(lerror);
        } else if (game1 == null) {
            var error = new Error(ServerMessage.ServerMessageType.ERROR, "Coudlnt find game");
            var lerror = new Gson().toJson(error);
            session.send(lerror);
        } else {
            String username = user.username();
            // send load game to root
            var load = new LoadGame(ServerMessage.ServerMessageType.LOAD_GAME, gameization.getGame(gameID));//what game
            var lgame = new Gson().toJson(load);
            session.send(lgame);
            // send message to other people

            for (SingleGame game : games.getGames()) {
                if (game.getGameID() == gameID) {
                    game.add(authToken, session.session);
                    game.broadcast(authToken, new Notification(ServerMessage.ServerMessageType.NOTIFICATION, username + "has joined the game as an observer"));
                }

            }
        }
    }

    private void joinPlayer(JoinPlayer action) throws IOException, DataAccessException{
        int gameID = action.getGameID();
        String authToken = action.getAuthString();
        GameData game = gameization.getGame(gameID);
        AuthData user = authorization.getAuth(authToken);

        if(game == null) {
            var error = new Error(ServerMessage.ServerMessageType.ERROR, "Coudlnt find game");
            var lerror = new Gson().toJson(error);
            session.send(lerror);
        } else if (user == null) {
            var error = new Error(ServerMessage.ServerMessageType.ERROR, "Coudlnt find game");
            var lerror = new Gson().toJson(error);
            session.send(lerror);

        } else if(gameization.getGame(gameID).game() == null){
            System.out.print("Game is null");
        } else if (action.getColor() == ChessGame.TeamColor.BLACK && game.blackUsername() == null) {
            var error = new Error(ServerMessage.ServerMessageType.ERROR, "not taken");
            var lerror = new Gson().toJson(error);
            session.send(lerror);
        } else if (action.getColor() == ChessGame.TeamColor.WHITE && game.whiteUsername() == null) {
            var error = new Error(ServerMessage.ServerMessageType.ERROR, "not taken");
            var lerror = new Gson().toJson(error);
            session.send(lerror);
        } else if (!user.username().equals(game.blackUsername()) && action.getColor() == ChessGame.TeamColor.BLACK ) {
            var error = new Error(ServerMessage.ServerMessageType.ERROR, "wrong team");
            var lerror = new Gson().toJson(error);
            session.send(lerror);
        } else if (!user.username().equals(game.whiteUsername()) && action.getColor() == ChessGame.TeamColor.WHITE ) {
            var error = new Error(ServerMessage.ServerMessageType.ERROR, "wrong team");
            var lerror = new Gson().toJson(error);
            session.send(lerror);
        }
        else {
            String username = user.username();
            // send load game to root
            var load = new LoadGame(ServerMessage.ServerMessageType.LOAD_GAME, gameization.getGame(gameID));//what game
            var lgame = new Gson().toJson(load);
            session.send(lgame);

            boolean exists = false;
            if (games.getGames().isEmpty()) {
                games.addGame(new SingleGame(gameID));
                exists = true;
            } else{
                for(SingleGame game2: games.getGames()){
                    if(game2.getGameID() == gameID){
                        exists = true;
                    }
                }
            }
            if(!exists){
                games.addGame(new SingleGame(gameID));
            }


            // Send message to other people
            for (SingleGame game1 : games.getGames()) {
                if (game1.getGameID() == gameID) {
                    game1.add(authToken, session.session);
                    if (action.getColor() == ChessGame.TeamColor.BLACK) {

                        game1.broadcast(authToken, new Notification(ServerMessage.ServerMessageType.NOTIFICATION, username + "has joined the game as Black!"));
                    } else if (action.getColor() == ChessGame.TeamColor.WHITE) {

                        game1.broadcast(authToken, new Notification(ServerMessage.ServerMessageType.NOTIFICATION, username + "has joined the game as White!"));
                    }
                }

            }
        }
    }



    private void makeMove(MakeMove action) throws IOException, DataAccessException, InvalidMoveException {
        int gameID = action.getGameID();
        String authToken = action.getAuthString();
        AuthData user = authorization.getAuth(authToken);
        String username = user.username();
        ChessMove move = action.getMove();
        GameData game = gameization.getGame(gameID);
        boolean works;
        // check to see if move is valid
        if (move == null) {
            for (SingleGame game1 : games.getGames()) {
                if (game1.getGameID() == gameID) {
                    game1.broadcast(null, new Error(ServerMessage.ServerMessageType.ERROR, "Move is null"));
                }
            }
        }else if (game.game().isGameOver()) {
            works = false;
            var error = new Error(ServerMessage.ServerMessageType.ERROR, "Game is over");
            var lerror = new Gson().toJson(error);
            session.send(lerror);
        } else if (!username.equals(game.blackUsername()) && game.game().getTeamTurn() == ChessGame.TeamColor.BLACK) {
            var error = new Error(ServerMessage.ServerMessageType.ERROR, "Wrong team or som");
            var lerror = new Gson().toJson(error);
            session.send(lerror);
        } else if (!username.equals(game.whiteUsername()) && game.game().getTeamTurn() == ChessGame.TeamColor.WHITE) {
            var error = new Error(ServerMessage.ServerMessageType.ERROR, "Wrong team or som");
            var lerror = new Gson().toJson(error);
            session.send(lerror);
        } else {
            ChessPiece.PieceType piece = game.game().getBoard().chessarray[move.getStartPosition().getRow() - 1][move.getStartPosition().getColumn() - 1].getPieceType();
            if (game.game().validMoves(new ChessPosition(move.getStartPosition().getRow(), move.getStartPosition().getColumn())).contains(move)) {
                works = true;
            } else {
                works = false;
                var error = new Error(ServerMessage.ServerMessageType.ERROR, "Not a valid move");
                var lerror = new Gson().toJson(error);
                session.send(lerror);
            }
            // update the game
            if (works) {
                game.game().makeMove(move);
                if (game.game().isInCheckmate(game.game().getTeamTurn())) {
                    sendCheckMateMessage(game,gameID);
                } else if (game.game().isInCheck(game.game().getTeamTurn())) {
                    sendCheckMessage(game,gameID);
                } else if (game.game().isInStalemate(game.game().getTeamTurn())) {
                    for (SingleGame game1 : games.getGames()) {
                        if (game1.getGameID() == gameID) {
                            game1.broadcast(null, new Notification(ServerMessage.ServerMessageType.NOTIFICATION, "Game is over by stalemate"));
                        }
                    }
                    game.game().setGameOver(true);
                }
                // update the database
                gameization.updateGame(game.game(), gameID);
                // send load game to everyone and send move to everyone exept root
                for (SingleGame game1 : games.getGames()) {
                    if (game1.getGameID() == gameID) {
                        game1.broadcast(null, new LoadGame(ServerMessage.ServerMessageType.LOAD_GAME, game));
                        switch (piece) {
                            case ChessPiece.PieceType.KING:
                                game1.broadcast(authToken, new Notification(ServerMessage.ServerMessageType.NOTIFICATION, username + " made the move King to " + endPosString(move)));
                                break;
                            case ChessPiece.PieceType.ROOK:
                                game1.broadcast(authToken, new Notification(ServerMessage.ServerMessageType.NOTIFICATION, username + " made the move Rook to " + endPosString(move)));
                                break;
                            case ChessPiece.PieceType.PAWN:
                                game1.broadcast(authToken, new Notification(ServerMessage.ServerMessageType.NOTIFICATION, username + " made the move Pawn to " + endPosString(move)));
                                break;
                            case QUEEN:
                                game1.broadcast(authToken, new Notification(ServerMessage.ServerMessageType.NOTIFICATION, username + " made the move Queen to " + endPosString(move)));
                                break;
                            case BISHOP:
                                game1.broadcast(authToken, new Notification(ServerMessage.ServerMessageType.NOTIFICATION, username + " made the move Bishop to " + endPosString(move)));
                                break;
                            case KNIGHT:
                                game1.broadcast(authToken, new Notification(ServerMessage.ServerMessageType.NOTIFICATION, username + " made the move Knight to " + endPosString(move)));
                                break;
                        }
                    }
                }
            }
        }
    }
    private void sendCheckMessage(GameData game, int gameID) throws IOException {
        for (SingleGame game1 : games.getGames()) {
            if (game1.getGameID() == gameID) {
                if(game.game().getTeamTurn() == ChessGame.TeamColor.WHITE) {
                    game1.broadcast(null, new Notification(ServerMessage.ServerMessageType.NOTIFICATION, game.whiteUsername() + " Is in check!"));
                } else if (game.game().getTeamTurn() == ChessGame.TeamColor.BLACK) {
                    game1.broadcast(null, new Notification(ServerMessage.ServerMessageType.NOTIFICATION, game.blackUsername() + " Is in check!"));

                }
            }
        }
    }
    private void sendCheckMateMessage(GameData game, int gameID) throws IOException {
        game.game().setGameOver(true);
        for (SingleGame game1 : games.getGames()) {
            if (game1.getGameID() == gameID) {
                if (game.game().getTeamTurn() == ChessGame.TeamColor.WHITE) {
                    game1.broadcast(null, new Notification(ServerMessage.ServerMessageType.NOTIFICATION, game.whiteUsername() + " Is in checkMate!"));
                } else if (game.game().getTeamTurn() == ChessGame.TeamColor.BLACK) {
                    game1.broadcast(null, new Notification(ServerMessage.ServerMessageType.NOTIFICATION, game.blackUsername() + " Is in checkMate!"));

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
            } else if (game.game().isGameOver()) {
                var error = new Error(ServerMessage.ServerMessageType.ERROR, "Cant resign twice");
                var lerror = new Gson().toJson(error);
                session.send(lerror);
            } else if (!(username.equals(game.blackUsername()) || username.equals(game.whiteUsername()))) {
                var error = new Error(ServerMessage.ServerMessageType.ERROR, "Observers cant Resign");
                var lerror = new Gson().toJson(error);
                session.send(lerror);
                
            } else {
                // mark game as over
                //change game
                game.game().setGameOver(true);
                //update database
                gameization.updateGame(game.game(), gameID);


                // send message that game is over by resignation
                for (SingleGame game1 : games.getGames()) {
                    if (game1.getGameID() == gameID) {
                        game1.broadcast(null, new Notification(ServerMessage.ServerMessageType.NOTIFICATION, username + " has resigned, the game is over."));
                    }
                }
            }
    }



    private String endPosString(ChessMove move){
        // make string for end pos
        String col = "";
        String row = "";
        switch (move.getEndPosition().getColumn()){
            case 0: col = "A";
            case 1: col = "B";
            case 2: col = "C";
            case 3: col = "D";
            case 4: col = "E";
            case 5: col = "F";
            case 6 : col = "G";
            case 7: col = "H";
        }
        switch (move.getEndPosition().getRow()){
            case 0: row = "1";
            case 1: row = "2";
            case 2: row = "3";
            case 3: row = "4";
            case 4: row = "5";
            case 5: row = "6";
            case 6 : row = "7";
            case 7: row = "8";
        }
        return col + row;

    }


}
