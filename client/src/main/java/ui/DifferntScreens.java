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

    public void inGameScreen(PrintStream out){
        out.println("Help - shows the screen");
        out.println("RedrawBoard - redraws the board");
        out.println("Leave - leaves the game you are in");
        out.println("MakeMove - makes a move in the game if you are a main user");
        out.println("resign - resigns the game");
        out.println("HighlightMoves - highlights all the legal moves");
    }
}

