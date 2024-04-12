package ui;

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
        String url = "http://localhost:" + "8080/";
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
}
