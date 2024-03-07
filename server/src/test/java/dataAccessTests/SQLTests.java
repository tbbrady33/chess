package dataAccessTests;

import CreateGame.CreateGameResponce;
import dataAccess.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.AuthData;
import server.GameData;

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
}
