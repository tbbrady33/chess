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
import chess.*;
import DataAccess.DataAccessException;
import Model.GameData;
import ui.Gameplay.ServerMessageHandler;
import ui.Gameplay.WebSocketCommunicator;
import userCommands.UserGameCommand;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Scanner;

public class UserInterface implements ServerMessageHandler {

    public boolean loggedIN;

    public boolean inGame;
    public boolean mainUser = false;
    public String authtoken;
    public String username;

    private GameData gamePrivate;

    private WebSocketCommunicator websocket;

    private ChessGame.TeamColor teamColor = null;


    private String url = "http://localhost:" + "8081";

    public Collection<GameData> games;
    public UserInterface(boolean loggedIN){

        this.loggedIN = loggedIN;
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
            int id = Integer.parseInt(input.nextLine());

            boolean exists = false;
            for (GameData game : games) {
                if (game.gameID() == id) {
                    exists = true;
                }
            }
            if (exists == false){
                System.out.println("That didnt work sorry, that game doesnt exist");
            }
            else {
                this.websocket = new WebSocketCommunicator(url, this);
                websocket.joinObserver(UserGameCommand.CommandType.JOIN_OBSERVER, id, authtoken);
                inGame = true;
            }
//
        }catch(DataAccessException ex){

        }
    }
    public void joinGame(ServerFacade server){
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("What is the Game ID of the game you want to join: ");
            int id = Integer.parseInt(input.nextLine());

            boolean exists = false;
            for(GameData game: games){
                if (game.gameID() == id){
                    exists = true;
                }
            }

            if (exists){
                System.out.print("What team do you want to be on? Say either \"Black\" or \"White\": ");
                String team = input.nextLine();

                if (team.equals("Black")){

                    JoinGameResponce join = server.joinGame(new JoinGameRequest(ChessGame.TeamColor.BLACK,id), authtoken);


                    if(join.message().isEmpty()){
                        try {
                            this.websocket = new WebSocketCommunicator(url, this);
                        }catch (DataAccessException e){
                            System.out.print(e.getMessage());

                        }
                        websocket.joinPlayer(UserGameCommand.CommandType.JOIN_PLAYER,authtoken,id, ChessGame.TeamColor.BLACK);
                        inGame = true;
                        mainUser = true;
                        teamColor = ChessGame.TeamColor.BLACK;
                    }


                } else if (team.equals("White")){


                    JoinGameResponce join = server.joinGame(new JoinGameRequest(ChessGame.TeamColor.WHITE,id), authtoken);

                    if (join.message().isEmpty()) {
                        try {
                            this.websocket = new WebSocketCommunicator(url, this);
                        }catch (DataAccessException e){
                            System.out.print(e.getMessage());
                        }
                        websocket.joinPlayer(UserGameCommand.CommandType.JOIN_PLAYER,authtoken,id, ChessGame.TeamColor.WHITE);
                        inGame = true;
                        mainUser = true;
                        teamColor = ChessGame.TeamColor.WHITE;
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
        System.out.println("Success what next");

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
            loggedIN = false;
        }catch (DataAccessException ex){
            ex.printStackTrace();
        }
    }

    public void leave(){
        try {
            websocket.leave(UserGameCommand.CommandType.LEAVE, gamePrivate.gameID(), authtoken);
            inGame = false;
            mainUser = false;
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }

    public void makeMove(){
        try {
            boolean firstRow = false;
            String initalRow = "";
            while(firstRow == false) {
                Scanner input = new Scanner(System.in);
                System.out.print("What is the row of the piece you want to move: A-H");
                String row = input.nextLine();
                if(row.equals("A") || row.equals("B") || row.equals("C") || row.equals("D") || row.equals("E") || row.equals("F") || row.equals("G") || row.equals("H")){
                    initalRow = row;
                    firstRow = true;
                    break;
                }else {
                    System.out.println("That wasn't a row try again");
                }
            }
            boolean firstCol = false;
            int initialCol = 0;
            while(!firstCol){
                Scanner input = new Scanner(System.in);
                System.out.print("What is the col you want to move from: (1-8)");
                int col = Integer.parseInt(input.nextLine());
                if(1 <= col && col >= 8){
                    initialCol = col;
                    firstCol = true;
                    break;
                }else {
                    System.out.println("That's not a number between 1 and 8");
                }
            }
            boolean rowGood = false;
            String finalRow = "";
            while(rowGood == false){
                Scanner input = new Scanner(System.in);
                System.out.print("What is the row you want to move to: (A/B/C/D/E/F/G/H)");
                String row = input.nextLine();
                if(row.equals("A") || row.equals("B") || row.equals("C") || row.equals("D") || row.equals("E") || row.equals("F") || row.equals("G") || row.equals("H")){
                    finalRow = row;
                    rowGood = true;
                    break;
                }else {
                    System.out.println("That didnt work try selecting a row again.");
                }
            }
            boolean colGood = false;
            int actualCol = 0;
            while(!colGood){
                Scanner input = new Scanner(System.in);
                System.out.print("What is the col you want to move to: (1-8)");
                int col = Integer.parseInt(input.nextLine());
                if(1 <= col && col >= 8){
                    actualCol = col;
                    colGood = true;
                    break;
                }else {
                    System.out.println("That's not a number between 1 and 8");
                }
            }

            ChessPiece.PieceType piece = null;
            if(actualCol == 8){
                Scanner input = new Scanner(System.in);
                System.out.println("Is the piece moving here a pawn that is being promoted: (yes/no)");
                String answer = input.nextLine();

                if(answer.equals("yes")){
                    boolean goodPiece = false;
                    while(!goodPiece) {
                        Scanner input1 = new Scanner(System.in);
                        System.out.println("What piece do you want to promote to? (Queen/Rook/Bishop/Knight)");
                        String answer1 = input.nextLine();

                        switch (answer1){
                            case "Queen":
                                piece = ChessPiece.PieceType.QUEEN;
                                goodPiece = true;
                                break;
                            case "Rook":
                                piece = ChessPiece.PieceType.ROOK;
                                goodPiece = true;
                                break;
                            case "Bishop":
                                piece = ChessPiece.PieceType.BISHOP;
                                goodPiece = true;
                                break;
                            case "Knight":
                                piece = ChessPiece.PieceType.KNIGHT;
                                goodPiece = true;
                                break;
                            default:
                                System.out.println("Not a piece type try again");
                        }
                    }

                }
            }

            var move = new ChessMove(new ChessPosition(initialCol,letterToNum(initalRow)),new ChessPosition(actualCol,letterToNum(finalRow)), piece);

            websocket.makeMove(UserGameCommand.CommandType.MAKE_MOVE, gamePrivate.gameID(), authtoken,move);

        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }

    private int letterToNum(String letter){
        switch (letter){
            case "A":
                return 1;
            case "B":
                return 2;
            case "C":
                return 3;
            case "D":
                return 4;
            case "E":
                return 5;
            case "F":
                return 6;
            case "G":
                return 7;
            case "H":
                return 8;
        }
        return 0;
    }


    public void help(PrintStream out){
        if (loggedIN  && inGame== false) {
            new DifferntScreens().logedinScreen(out);
        } else if (!loggedIN) {
            new DifferntScreens().initialScreen(out);
        }else if(loggedIN && inGame == true){
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
                loggedIN = true;
                this.authtoken = login.authToken();
                this.username = login.username();
            }

        }catch (DataAccessException ex){
            System.out.println("Didnt work, Try again, type \"Login\" to try again");
        }
    }

    public void resign(){
        boolean resign = false;
        while(resign == false) {
            Scanner input1 = new Scanner(System.in);
            System.out.println("Are you sure you would like to resign?");
            String answer = input1.nextLine();
            if (answer.equals("Yes")) {
                try {

                    websocket.resign(UserGameCommand.CommandType.RESIGN, gamePrivate.gameID(), authtoken);
                } catch (DataAccessException e) {
                    e.printStackTrace();
                }
                resign = true;
            } else if (answer.equals("No")) {
                System.out.println("Ok pick another option then");
                resign = true;
            } else {
                System.out.println("That wasn't an option, tey again");
            }
        }

    }

    public void higlightMoves(PrintStream out){
        boolean goodRow = false;
        while (goodRow == false) {
            Scanner input1 = new Scanner(System.in);
            System.out.print("What is the row of the piece you want to highlight the moves of: (1-8)");
            String row = input1.nextLine();
//            if(1 <= row && row <= 8){
//
//            }
        }
        //gamePrivate.game().validMoves(new ChessPosition());
        //new MakeBoard(gamePrivate.game().getBoard().getChessarray(),teamColor).drawBoardHighlight(out, moves);


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
            loggedIN = true;
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

            makeBoard chess = new makeBoard(gamePrivate.game().getBoard().getChessarray(), ChessGame.TeamColor.WHITE);
            chess.MakeHeader(out);
            chess.drawBoard(out);
            makeBoard chess1 = new makeBoard(gamePrivate.game().getBoard().getChessarray(), ChessGame.TeamColor.BLACK);
            chess1.MakeHeader(out);
            chess1.drawBoard(out);
        } else if (teamColor == ChessGame.TeamColor.BLACK) {
            out.print(EscapeSequences.ERASE_SCREEN);


            makeBoard chess = new makeBoard(gamePrivate.game().getBoard().getChessarray(), ChessGame.TeamColor.BLACK);
            chess.MakeHeader(out);
            chess.drawBoard(out);
            makeBoard chess1 = new makeBoard(gamePrivate.game().getBoard().getChessarray(), ChessGame.TeamColor.WHITE);
            chess1.MakeHeader(out);
            chess1.drawBoard(out);
        }else if(teamColor == ChessGame.TeamColor.WHITE){
            out.print(EscapeSequences.ERASE_SCREEN);


            makeBoard chess = new makeBoard(gamePrivate.game().getBoard().getChessarray(), ChessGame.TeamColor.WHITE);
            chess.MakeHeader(out);
            chess.drawBoard(out);
            makeBoard chess1 = new makeBoard(gamePrivate.game().getBoard().getChessarray(), ChessGame.TeamColor.BLACK);
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
        if(teamColor == null){
            makeBoard chess = new makeBoard(board, ChessGame.TeamColor.WHITE);
            chess.MakeHeader(out);
            chess.drawBoard(out);
            // change colors back at the end of each of these
        }
        else if(teamColor == ChessGame.TeamColor.WHITE) {
            makeBoard chess = new makeBoard(board, ChessGame.TeamColor.WHITE);
            chess.MakeHeader(out);
            chess.drawBoard(out);
        }
        else if(teamColor == ChessGame.TeamColor.BLACK) {
            makeBoard chess1 = new makeBoard(board, ChessGame.TeamColor.BLACK);
            chess1.MakeHeader(out);
            chess1.drawBoard(out);
        }
    }

    @Override
    public void error(String errormessage) {
        System.out.println(errormessage);
    }
}
