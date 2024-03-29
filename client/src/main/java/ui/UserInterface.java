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
import chess.ChessGame;
import dataAccess.DataAccessException;
import server.GameData;

import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Scanner;

public class UserInterface {

    public boolean LoggedIN;
    public String authtoken;
    public String username;
    public Collection<GameData> games;
    public UserInterface(boolean loggedIN){
        this.LoggedIN = loggedIN;
    }

    public void request(){
        // Case statement and do the stuff that the user would like
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        boolean go = true;
        String url = "http://localhost:" + "8080/";
        ServerFacade server = new ServerFacade(url);
        while(go) {
            Scanner input = new Scanner(System.in);
            String request = input.nextLine();
            switch (request) {
                case "Help":
                    help(out);
                    break;
                case "Quit":
                    go = false;
                    break;

                case "Login":
                    login(server);
                    break;

                case "Register":
                    register(server);
                    break;
                case "Logout":
                    logout(server);
                    break;
                case "CreateGame":
                    createGame(server);
                    break;
                case "ListGames":
                    listGames(server);
                    break;
                case "JoinGame":
                    joinGame(server);
                    break;
                case "JoinObserver":
                    joinObserver(server);
                    break;
                default:
                    System.out.println("Not an option, type \"Help\" for a list of options");

            }

        }
    }
    private void createGame(ServerFacade server){
        try{
            Scanner input = new Scanner(System.in);
            System.out.print("Give me a game name: ");
            String name = input.nextLine();
            CreateGameResponce create = server.createGame(new CreateGameRequest(name), authtoken);
            System.out.println(create.message());
        }catch (DataAccessException ex){
            ex.printStackTrace();
        }
    }
    private void joinObserver(ServerFacade server){
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
            JoinGameResponce join = server.joinGame(new JoinGameRequest(ChessGame.TeamColor.BLACK, ID), authtoken);
            String[][] board = new String[8][8];
            intialBoard(board);
            var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
            out.print(EscapeSequences.ERASE_SCREEN);


            MakeBoard chess = new MakeBoard(board, ChessGame.TeamColor.BLACK);
            chess.MakeHeader(out);
            chess.drawBoard(out);
            MakeBoard chess1 = new MakeBoard(board, ChessGame.TeamColor.WHITE);
            chess1.MakeHeader(out);
            chess1.drawBoard(out);
        }catch(DataAccessException ex){

        }
    }
    private void joinGame(ServerFacade server){
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
                    JoinGameResponce join = server.joinGame(new JoinGameRequest(ChessGame.TeamColor.BLACK,ID), authtoken);
                    if(join.message().isEmpty()){
                        String[][] board = new String[8][8];
                        intialBoard(board);
                        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
                        out.print(EscapeSequences.ERASE_SCREEN);


                        MakeBoard chess = new MakeBoard(board, ChessGame.TeamColor.BLACK);
                        chess.MakeHeader(out);
                        chess.drawBoard(out);
                        MakeBoard chess1 = new MakeBoard(board, ChessGame.TeamColor.WHITE);
                        chess1.MakeHeader(out);
                        chess1.drawBoard(out);
                    }


                } else if (team.equals("White")){

                    JoinGameResponce join = server.joinGame(new JoinGameRequest(ChessGame.TeamColor.WHITE,ID), authtoken);
                    if (join.message().isEmpty()) {
                        String[][] board = new String[8][8];
                        intialBoard(board);
                        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
                        out.print(EscapeSequences.ERASE_SCREEN);


                        MakeBoard chess = new MakeBoard(board, ChessGame.TeamColor.WHITE);
                        chess.MakeHeader(out);
                        chess.drawBoard(out);
                        MakeBoard chess1 = new MakeBoard(board, ChessGame.TeamColor.BLACK);
                        chess1.MakeHeader(out);
                        chess1.drawBoard(out);
                    }


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
    private void listGames(ServerFacade server){
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
    private void logout(ServerFacade server){
        try{
            LogoutResponce logout = server.logout(new LogoutRequest(authtoken));
            System.out.format("%s: type help for your new options", logout.message());
            System.out.println();
            LoggedIN = false;
        }catch (DataAccessException ex){
            ex.printStackTrace();
        }
    }

    private void help(PrintStream out){
        if (LoggedIN) {
            new DifferntScreens().logedinScreen(out);
        } else if (!LoggedIN) {
            new DifferntScreens().initialScreen(out);
        }

    }

    private void login(ServerFacade server){
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

    private void register(ServerFacade server){
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
    private static String[][] intialBoard(String[][] chessarray){
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
}
