package ui;

import java.io.PrintStream;

public class DifferntScreens {
    public void initialScreen(PrintStream out){
        out.println("Quit - Exits the program");
        out.println("Login - Login to the program");
        out.println("Register - Register to use the program");
    }

    public void logedinScreen(PrintStream out){
        out.println("Logout - logs out");
        out.println("Create game - make a new game");
        out.println("List games - Lists all the games in the database");
        out.println("Join Game - joins an existing game");
        out.println("Join Obsverver - Joins a game as an observer");
    }
}
