package ui;

import ListGames.ListGamesRequest;
import ListGames.ListGamesResponce;
import Login.LoginRequest;
import Login.LoginResponce;
import Logout.LogoutRequest;
import Logout.LogoutResponce;
import Register.RegisterRequest;
import Register.RegisterResponce;
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
        ServerFacade server = new ServerFacade("http://localhost:8080/");
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
    private void joinObserver(ServerFacade server){

    }
    private void joinGame(ServerFacade server){

    }
    private void listGames(ServerFacade server){
        try{
            if(authtoken != null) {
                ListGamesResponce list = server.listGames(new ListGamesRequest(authtoken));
                games = list.games();
                System.out.print(list.games());
            }
            else {
                throw new Exception("Not authorized");
            }
        }catch (DataAccessException ex){
            System.out.println("Data access exeption, quiting the program");
        }catch (Exception e){
            System.out.println("Not authorized, quiting the program");
        }
    }
    private void logout(ServerFacade server){
        try{
            LogoutResponce logout = server.logout(new LogoutRequest(authtoken));
            System.out.format("%s: type help for your new options", logout.message());
        }catch (DataAccessException ex){
            System.out.println("Data access exeption, quiting the program");
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
}
