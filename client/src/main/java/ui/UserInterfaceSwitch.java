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
        String url = "http://localhost:" + "8081/";
        ServerFacade server = new ServerFacade(url);
        while (go) {
            Scanner input = new Scanner(System.in);
            String request = input.nextLine();
            out.print(EscapeSequences.SET_BG_COLOR_DARK_GREY);
            out.print(EscapeSequences.SET_TEXT_COLOR_WHITE);
            switch (request) {
                case "Help":
                    help(out);
                    break;
                case "Quit":
                    go = false;
                    break;
                case "Login":
                    if(loggedIN == false) {
                        login(server);
                        break;
                    }

                case "Register":
                    if(loggedIN == false) {
                        register(server);
                        break;
                    }
                case "Logout":
                    if(loggedIN == true) {
                        logout(server);
                        break;
                    }
                case "CreateGame":
                    if(loggedIN == true) {
                        createGame(server);
                        break;
                    }
                case "ListGames":
                    if(loggedIN == true) {
                        listGames(server);
                        break;
                    }
                case "JoinGame":
                    if(loggedIN == true) {
                        joinGame(server);
                        break;
                    }
                case "JoinObserver":
                    if(loggedIN == true) {
                        joinObserver(server);
                        break;
                    }
                case "RedrawBoard":
                    if(loggedIN && inGame){
                        redrawBoard(out);
                        break;
                    }
                case "Leave":
                    if(loggedIN && inGame){
                        leave();
                        break;
                    }
                case "MakeMove":
                    if(loggedIN && inGame){
                        makeMove();
                        break;
                    }
                case "Resign":
                    if(loggedIN && inGame){
                        resign();
                        break;
                    }
                case "HighlightMoves":
                    if(loggedIN && inGame){
                        higlightMoves(out);
                        break;
                    }
                default:
                    System.out.println("Not an option, type \"Help\" for a list of options");

            }

        }
    }
}
