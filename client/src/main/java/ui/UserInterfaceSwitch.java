package ui;

import ui.Gameplay.WebSocketCommunicator;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UserInterfaceSwitch extends UserInterface {


    public UserInterfaceSwitch(boolean loggedIN ) {
        super(loggedIN);
    }

    public void request() {
        // Case statement and do the stuff that the user would like
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        boolean go = true;
        boolean inGame = false;
        String url = "http://localhost:" + "8081/";
        ServerFacade server = new ServerFacade(url);
        while (go) {
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
                    if(LoggedIN == false) {
                        login(server);
                        break;
                    }

                case "Register":
                    if(LoggedIN == false) {
                        register(server);
                        break;
                    }
                case "Logout":
                    if(LoggedIN == true) {
                        logout(server);
                        break;
                    }
                case "CreateGame":
                    if(LoggedIN == true) {
                        createGame(server);
                        break;
                    }
                case "ListGames":
                    if(LoggedIN == true) {
                        listGames(server);
                        break;
                    }
                case "JoinGame":
                    if(LoggedIN == true) {
                        joinGame(server);
                        break;
                    }
                case "JoinObserver":
                    if(LoggedIN == true) {
                        joinObserver(server);
                        break;
                    }
                case "RedrawBoard":
                    if(LoggedIN && inGame){
                        redrawBoard(out);
                        break;
                    }
                case "Leave":
                    if(LoggedIN && inGame){
                        leave();
                        break;
                    }
                case "MakeMove":
                    if(LoggedIN && inGame){
                        makeMove();
                        break;
                    }
                case "Resign":
                    if(LoggedIN && inGame){
                        resign();
                        break;
                    }
                case "HighlightMoves":
                    if(LoggedIN && inGame){
                        higlightMoves(out);
                        break;
                    }
                default:
                    System.out.println("Not an option, type \"Help\" for a list of options");

            }

        }
    }
}
