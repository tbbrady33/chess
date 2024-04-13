package ui;

import CreateGame.CreateGameRequest;
import CreateGame.CreateGameResponce;
import JoinGame.JoinGameRequest;
import JoinGame.JoinGameResponce;
import ListGames.ListGamesRequest;
import ListGames.ListGamesResponce;
import Login.LoginRequest;
import Login.LoginResponce;
import Logout.LogoutRequest;
import Logout.LogoutResponce;
import Register.RegisterRequest;
import Register.RegisterResponce;
import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPiece;
import dataAccess.DataAccessException;
import Model.GameData;
import ui.Gameplay.ServerMessageHandler;
import ui.Gameplay.WebSocketCommunicator;
import webSocketMessages.userCommands.UserGameCommand;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Scanner;

public class UserInterface implements ServerMessageHandler {

    public boolean LoggedIN;

    public boolean inGame;
    public boolean mainUser = false;
    public String authtoken;
    public String username;

    private GameData gamePrivate;

    private WebSocketCommunicator websocket;

    private ChessGame.TeamColor teamColor = null;


    private String url = "http://localhost:" + "8081/";

    public Collection<GameData> games;
    public UserInterface(boolean loggedIN){
        try {
            this.websocket = new WebSocketCommunicator(url, this);
        }catch (DataAccessException e){
            System.out.print(e.getMessage());
        }
        this.LoggedIN = loggedIN;
    }

    public void createGame(ServerFacade server){
        try{
            Scanner input = new Scanner(System.in);
            System.out.print("Give me a game name: ");
            String name = input.nextLine();
            CreateGameResponce create = server.createGame(new CreateGameRequest(name), authtoken);
            if(create.message() != null) {
                System.out.println(create.message());
            }
        }catch (DataAccessException ex){
            ex.printStackTrace();
        }
    }
    public void joinObserver(ServerFacade server){
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("What is the Game ID of the game you want to join: ");
            int ID = Integer.parseInt(input.nextLine());

            boolean exists = false;
            for (GameData game : games) {
                if (game.gameID() == ID) {
                    exists = true;
                }
            }
            if (exists == false){
                System.out.println("That didnt work sorry, that game doesnt exist");
            }
            websocket.Join_Observer(UserGameCommand.CommandType.JOIN_OBSERVER, ID, authtoken);
            inGame = true;
//
        }catch(DataAccessException ex){

        }
    }
    public void joinGame(ServerFacade server){
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("What is the Game ID of the game you want to join: ");
            int ID = Integer.parseInt(input.nextLine());

            boolean exists = false;
            for(GameData game: games){
                if (game.gameID() == ID){
                    exists = true;
                }
            }

            if (exists){
                System.out.print("What team do you want to be on? Say either \"Black\" or \"White\": ");
                String team = input.nextLine();

                if (team.equals("Black")){


//                    JoinGameResponce join = server.joinGame(new JoinGameRequest(ChessGame.TeamColor.BLACK,ID), authtoken);
                      websocket.Join_Player(UserGameCommand.CommandType.JOIN_PLAYER,authtoken,ID, ChessGame.TeamColor.BLACK);
                      inGame = true;
                      mainUser = true;
                      teamColor = ChessGame.TeamColor.BLACK;

//                    if(join.message().isEmpty()){
//                        ChessBoard board = new ChessBoard();
//                        board.resetBoard();
//                        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
//                        out.print(EscapeSequences.ERASE_SCREEN);
//
//
//                        MakeBoard chess = new MakeBoard(board.getChessarray(), ChessGame.TeamColor.BLACK);
//                        chess.MakeHeader(out);
//                        chess.drawBoard(out);
//                        MakeBoard chess1 = new MakeBoard(board.getChessarray(), ChessGame.TeamColor.WHITE);
//                        chess1.MakeHeader(out);
//                        chess1.drawBoard(out);
//                    }


                } else if (team.equals("White")){

//                    JoinGameResponce join = server.joinGame(new JoinGameRequest(ChessGame.TeamColor.WHITE,ID), authtoken);
                    websocket.Join_Player(UserGameCommand.CommandType.JOIN_PLAYER,authtoken,ID, ChessGame.TeamColor.WHITE);
                    inGame = true;
                    mainUser = true;
                    teamColor = ChessGame.TeamColor.WHITE;
//                    if (join.message().isEmpty()) {
//                        ChessBoard board = new ChessBoard();
//                        board.resetBoard();
//                        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
//                        out.print(EscapeSequences.ERASE_SCREEN);
//
//
//                        MakeBoard chess = new MakeBoard(board.getChessarray(), ChessGame.TeamColor.WHITE);
//                        chess.MakeHeader(out);
//                        chess.drawBoard(out);
//                        MakeBoard chess1 = new MakeBoard(board.getChessarray(), ChessGame.TeamColor.BLACK);
//                        chess1.MakeHeader(out);
//                        chess1.drawBoard(out);
//                    }


                } else{
                    System.out.print("not a team color your gonna have to try agian");
                }


            }
            else {
                System.out.print("Game doesnt exist your gonna have to try agian");
            }

        }catch (DataAccessException ex){
            ex.printStackTrace();
        }

    }
    public void listGames(ServerFacade server){
        try{
            if(authtoken != null) {
                ListGamesResponce list = server.listGames(new ListGamesRequest(authtoken));
                games = list.games();
                System.out.println(list.games());
            }
            else {
                throw new Exception("Not authorized");
            }
        }catch (DataAccessException ex){
            System.out.println("Data access exception, quiting the program");
        }catch (Exception e){
            System.out.println("Not authorized, quiting the program");
        }
    }
    public void logout(ServerFacade server){
        try{
            LogoutResponce logout = server.logout(new LogoutRequest(authtoken));
            System.out.format("%s: type help for your new options", logout.message());
            System.out.println();
            LoggedIN = false;
        }catch (DataAccessException ex){
            ex.printStackTrace();
        }
    }

    public void leave(){
        try {
            websocket.Leave(UserGameCommand.CommandType.LEAVE, gamePrivate.gameID(), authtoken);
            inGame = false;
            mainUser = false;
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }

    public void makeMove(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("What is the Game ID of the game you want to join: ");
            int ID = Integer.parseInt(input.nextLine());
            websocket.Make_Move(UserGameCommand.CommandType.MAKE_MOVE, gamePrivate.gameID(), authtoken,move);

        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }


    public void help(PrintStream out){
        if (LoggedIN  && inGame== false) {
            new DifferntScreens().logedinScreen(out);
        } else if (!LoggedIN) {
            new DifferntScreens().initialScreen(out);
        }else if(LoggedIN && inGame == true){
            new DifferntScreens().inGameScreen(out);
        }

    }

    public void login(ServerFacade server){
        Scanner input = new Scanner(System.in);
        System.out.print("Username: ");
        String username = input.nextLine();
        System.out.print("Password: ");
        String password = input.nextLine();
        try {
            LoginResponce login = server.login(new LoginRequest(username, password));
            if (login.authToken() != null){
                System.out.println("Successfull, type help to list the options");
                LoggedIN = true;
                this.authtoken = login.authToken();
                this.username = login.username();
            }

        }catch (DataAccessException ex){
            System.out.println("Didnt work, Try again, type \"Login\" to try again");
        }
    }

    public void register(ServerFacade server){
        Scanner input = new Scanner(System.in);
        System.out.print("Username: ");
        String username1 = input.nextLine();
        System.out.print("Password: ");
        String password1 = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();
        try {
            RegisterResponce register = server.register(new RegisterRequest(username1,password1,email));
            LoggedIN = true;
            System.out.println("Successfull, type help to list the options");
            this.authtoken = register.authToken();
            this.username = register.username();

        }catch (DataAccessException ex){
            System.out.println("Data access exeption, quiting the program");
        }
    }
    public static String[][] intialBoard(String[][] chessarray){
        for (int i = 1; i <= 8; i++) {
            chessarray[2][i - 1] = " ";
        }
        for (int i = 1; i <= 8; i++) {
            chessarray[3][i - 1] = " ";
        }
        for (int i = 1; i <= 8; i++) {
            chessarray[4][i - 1] = " ";
        }
        for (int i = 1; i <= 8; i++) {
            chessarray[5][i - 1] = " ";
        }

        for (int i = 1; i <= 8; i++){
            chessarray[1][i-1] = "P White";
        }
        // Black pawns
        for (int i = 1; i <= 8; i++){
            chessarray[6][i-1] = "P Black";
        }
        // White Rooks
        chessarray[0][0] = "R White";
        chessarray[0][7] = "R White";

        // Black Rooks
        chessarray[7][0] = "R Black";
        chessarray[7][7] = "R Black";

        // White Knights
        chessarray[0][1] = "N White";
        chessarray[0][6] = "N White";

        // Black Knights
        chessarray[7][1] ="N Black";
        chessarray[7][6] = "N Black";

        //White Bishops
        chessarray[0][2] = "B White";
        chessarray[0][5] = "B White";

        //Black Bishops
        chessarray[7][2] = "B Black";
        chessarray[7][5] = "B Black";

        // White Queen
        chessarray[0][3] = "Q White";

        //Black Queen
        chessarray[7][3] = "Q Black";

        //White King
        chessarray[0][4] = "K White";

        // Black King
        chessarray[7][4] = "K Black";
        return chessarray;
    }

    public void redrawBoard(PrintStream out){
        if(teamColor == null){
            out.print(EscapeSequences.ERASE_SCREEN);

            MakeBoard chess = new MakeBoard(gamePrivate.game().getBoard().getChessarray(), ChessGame.TeamColor.WHITE);
            chess.MakeHeader(out);
            chess.drawBoard(out);
            MakeBoard chess1 = new MakeBoard(gamePrivate.game().getBoard().getChessarray(), ChessGame.TeamColor.BLACK);
            chess1.MakeHeader(out);
            chess1.drawBoard(out);
        } else if (teamColor == ChessGame.TeamColor.BLACK) {
            out.print(EscapeSequences.ERASE_SCREEN);


            MakeBoard chess = new MakeBoard(gamePrivate.game().getBoard().getChessarray(), ChessGame.TeamColor.BLACK);
            chess.MakeHeader(out);
            chess.drawBoard(out);
            MakeBoard chess1 = new MakeBoard(gamePrivate.game().getBoard().getChessarray(), ChessGame.TeamColor.WHITE);
            chess1.MakeHeader(out);
            chess1.drawBoard(out);
        }else if(teamColor == ChessGame.TeamColor.WHITE){
            out.print(EscapeSequences.ERASE_SCREEN);


            MakeBoard chess = new MakeBoard(gamePrivate.game().getBoard().getChessarray(), ChessGame.TeamColor.WHITE);
            chess.MakeHeader(out);
            chess.drawBoard(out);
            MakeBoard chess1 = new MakeBoard(gamePrivate.game().getBoard().getChessarray(), ChessGame.TeamColor.BLACK);
            chess1.MakeHeader(out);
            chess1.drawBoard(out);
        }
    }
    @Override
    public void notifyy(String message) {
        System.out.println(message);
    }

    @Override
    public void laodGame(GameData game) {
        ChessPiece[][] board = new ChessPiece[8][8];
        board = game.game().getBoard().getChessarray();
        this.gamePrivate = game;
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        out.print(EscapeSequences.ERASE_SCREEN);


        MakeBoard chess = new MakeBoard(board, ChessGame.TeamColor.WHITE);
        chess.MakeHeader(out);
        chess.drawBoard(out);
        MakeBoard chess1 = new MakeBoard(board, ChessGame.TeamColor.BLACK);
        chess1.MakeHeader(out);
        chess1.drawBoard(out);
    }

    @Override
    public void error(String errormessage) {
        System.out.println(errormessage);
    }
}
