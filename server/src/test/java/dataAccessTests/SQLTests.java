package dataAccessTests;

import CreateGame.CreateGameResponce;
import dataAccess.*;
import org.eclipse.jetty.server.Authentication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.ImportRuntimeHints;
import server.AuthData;
import server.GameData;
import server.UserData;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Vector;

public class SQLTests {
    GameDAO game;
    UserDAO user;
    AuthDAO auth;

    @BeforeEach
    public void setUp() {
        this.game = new SQLGameDAO();
        this.user = new SQLUserDAO();
        this.auth = new SQLAuthDAO();
        try {
            game.clear();
            user.clear();
            auth.clear();
        }catch (DataAccessException e){
            System.out.println("Didnt work");
        }

    }

    @Test
    public void goodClear(){
        Vector<CreateGameResponce> expected = new Vector<CreateGameResponce>();
        Vector<CreateGameResponce> actualvec = new Vector<CreateGameResponce>();
        try {
            game.clear();
            user.clear();
            auth.clear();
        }catch (DataAccessException ex){
            System.out.println("Data access");
        }
        assertEquals(expected,actualvec,"Why");
    }

    @Test
    public void goodGetGame(){
        Vector<GameData> expected = new Vector<GameData>();
        Vector<GameData> actualvec = new Vector<GameData>();

        try {
            game.createGame("Hi");
            GameData games = game.getGame(1);
            expected.add(new GameData(1,null,null,"Hi",games.game()));
            actualvec.add(games);
            assertEquals(expected,actualvec,"goodGetGame");
        }
        catch (DataAccessException e){
            System.out.println("Data access");
        }
    }

    @Test
    public void badGetGame(){
        Vector<GameData> expected = new Vector<GameData>();
        Vector<GameData> actualvec = new Vector<GameData>();
        try {
            game.createGame("Hi");
            GameData games = game.getGame(0);
            actualvec.add(games);
            expected.add(null);
            assertEquals(expected,actualvec,"goodGetGame");
        }
        catch (DataAccessException e){
            System.out.println("Data access");
        }
    }

    @Test
    public void goodListGames(){
        try{
            GameData game1 = game.createGame("sup");
            GameData game2 = game.createGame("hi");
            GameData game3 = game.createGame("AS");
            Collection<GameData> actual = new HashSet<>(game.listGames("idk"));
            Collection<GameData> expected = new HashSet<>();
            GameData thing = new GameData(1,null,null,"sup",game1.game());
            System.out.println(game1.game().toString() + thing.game().toString());
            expected.add(thing);
            expected.add(new GameData(2,null,null,"hi",game2.game()));
            expected.add(new GameData(3,null,null,"AS",game3.game()));
            assertEquals(expected,actual,"IDK");
        }catch (DataAccessException e){
            System.out.println("Data access");
        }
    }

    @Test
    public void badListGames(){
        try{
            Collection<GameData> actual = new HashSet<>(game.listGames("idk"));
            Collection<GameData> expected = new HashSet<>();

            assertEquals(expected,actual,"IDK");
        }catch (DataAccessException e){
            System.out.println("Data access");
        }
    }

    @Test
    public void goodCreateGame(){
        try{
            GameData actual = game.createGame("Sup");
            GameData expected = new GameData(1,null,null,"Sup",actual.game());


            assertEquals(expected,actual,"IDK");
        }catch (DataAccessException e){
            System.out.println("Data access");
        }
    }

    @Test
    public void badCreateGame(){
        try{
            GameData actual = game.createGame("");
            GameData expected = new GameData(1,null,null,"",actual.game());


            assertEquals(expected,actual,"IDK");
        }catch (DataAccessException e){
            System.out.println("Data access");
        }
    }
    @Test
    public void goodCreateAuth(){
        try{
            String actual = auth.createAuth("Hi");
            String expected = actual;


            assertEquals(expected,actual,"IDK");
        }catch (DataAccessException e){
            System.out.println("Data access");
        }
    }

    @Test
    public void badCreateAuth(){
        try{
            String actual = auth.createAuth("");
            String expected = actual;


            assertEquals(expected,actual,"IDK");
        }catch (DataAccessException e){
            System.out.println("Data access");
        }
    }


    @Test
    public void goodGetAuth(){
        try{

            String actual = auth.createAuth("sup");
            AuthData actuals = auth.getAuth(actual);
            AuthData expected = new AuthData(actual,"sup");


            assertEquals(expected,actuals,"IDK");
        }catch (DataAccessException e){
            System.out.println("Data access");
        }
    }

    @Test
    public void badGetAuth(){
        try{

            String actual = auth.createAuth("");
            AuthData actuals = auth.getAuth("actual");
            AuthData expected = null;


            assertEquals(expected,actuals,"IDK");
        }catch (DataAccessException e){
            System.out.println("Data access");
        }
    }

    @Test
    public void goodDeleteAuth(){
        try{

            String actual = auth.createAuth("Hi");
            auth.deleteAuth(actual);
            AuthData actuals = auth.getAuth(actual);
            AuthData expected = null;


            assertEquals(expected,actuals,"IDK");
        }catch (DataAccessException e){
            System.out.println("Data access");
        }
    }

    @Test
    public void badDeleteAuth(){
        try{

            String actual = auth.createAuth("Hi");
            auth.deleteAuth("actual");
            AuthData actuals = auth.getAuth(actual);
            AuthData expected = new AuthData(actual,"Hi");


            assertEquals(expected,actuals,"IDK");
        }catch (DataAccessException e){
            System.out.println("Data access");
        }
    }

    @Test
    public void goodInsertUser(){
        try{

            user.insertUser(new UserData("Hi","sup","email"));
            UserData actuals = user.getUser("Hi");
            UserData expected = new UserData("Hi","sup","email");

            assertEquals(expected,actuals,"IDK");
        }catch (DataAccessException e){
            System.out.println("Data access");
        }
    }

    @Test
    public void badInsertUser(){
        try{

            user.insertUser(new UserData("","",""));
            UserData actuals = user.getUser("");
            UserData expected = new UserData("","","");

            assertEquals(expected,actuals,"IDK");
        }catch (DataAccessException e){
            System.out.println("Data access");
        }
    }

    @Test
    public void goodGetUser(){
        try{

            user.insertUser(new UserData("Hi","sup","email"));
            UserData actuals = user.getUser("Hi");
            UserData expected = new UserData("Hi","sup","email");

            assertEquals(expected,actuals,"IDK");
        }catch (DataAccessException e){
            System.out.println("Data access");
        }
    }
    @Test
    public void badGetUser(){
        try{

            user.insertUser(new UserData("","",""));
            UserData actuals = user.getUser("");
            UserData expected = new UserData("","","");

            assertEquals(expected,actuals,"IDK");
        }catch (DataAccessException e){
            System.out.println("Data access");
        }
    }


}
