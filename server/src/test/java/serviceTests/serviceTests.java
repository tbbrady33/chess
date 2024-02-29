package serviceTests;
import CreateGame.*;
import DataAccess.*;
import JoinGame.*;
import ListGames.*;
import Login.*;
import Logout.*;
import Register.*;
import chess.ChessGame;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.ClearService;
import server.GameData;
import server.GameService;
import server.UserService;
import spark.Request;
import spark.Response;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class serviceTests {

    GameDAO game;
    UserDAO user;
    AuthDAO auth;

    @BeforeEach
    public void setUp() {
        this.game = new MemoryGameDAO();
        this.user = new MemoryUserDAO();
        this.auth = new MemoryAuthDAO();


    }

    @Test
    public void badCreateGame() {
        Vector<CreateGameResponce> expected = new Vector<CreateGameResponce>();
        Vector<CreateGameResponce> actualvec = new Vector<CreateGameResponce>();
        CreateGameRequest request = new CreateGameRequest("Hello");
        String authToken = "rand0m3uff";
        CreateGameResponce actual = new GameService(auth, game).createGame(request, authToken);
        actualvec.add(actual);
        expected.add(new CreateGameResponce(null, "Error: unauthorized"));
        assertEquals(actualvec, expected, "able to create game when i shouldnt");
    }

    @Test
    public void goodCreateGame() {
        Vector<CreateGameResponce> expected = new Vector<CreateGameResponce>();
        Vector<CreateGameResponce> actualvec = new Vector<CreateGameResponce>();
        RegisterRequest req = new RegisterRequest("Username", "Password", "Email");
        RegisterResponce authToken = new UserService(user, auth).register(req);
        String authT = authToken.authToken();
        CreateGameRequest request = new CreateGameRequest("Hello");
        CreateGameResponce actual = new GameService(auth, game).createGame(request, authT);
        int iD = actual.gameID();
        actualvec.add(actual);
        expected.add(new CreateGameResponce(iD, null));
        assertEquals(actualvec, expected, "Cant create game");

    }

    @Test
    public void clearapp() {
        Vector<CreateGameResponce> expected = new Vector<CreateGameResponce>();
        Vector<CreateGameResponce> actualvec = new Vector<CreateGameResponce>();
        RegisterRequest req = new RegisterRequest("Username", "Password", "Email");
        RegisterResponce authToken = new UserService(user, auth).register(req);
        String authT = authToken.authToken();
        CreateGameRequest request = new CreateGameRequest("Hello");
        CreateGameResponce actual = new GameService(auth, game).createGame(request, authT);
        int iD = actual.gameID();
        new ClearService(auth, game, user).clear();
        assertEquals(expected, actualvec);
    }

    @Test
    public void goodJoinGame() {
        Vector<CreateGameResponce> expected = new Vector<CreateGameResponce>();
        Vector<CreateGameResponce> actualvec = new Vector<CreateGameResponce>();
        RegisterRequest req = new RegisterRequest("Username", "Password", "Email");
        RegisterResponce authToken = new UserService(user, auth).register(req);
        String authT = authToken.authToken();
        CreateGameRequest request = new CreateGameRequest("Hello");
        CreateGameResponce actual = new GameService(auth, game).createGame(request, authT);
        int iD = actual.gameID();
        JoinGameRequest join = new JoinGameRequest(ChessGame.TeamColor.BLACK, iD);
        JoinGameResponce cresponce = new GameService(auth, game).joinGame(join, authT);
        JoinGameResponce thing = new JoinGameResponce("Success");
        assertEquals(cresponce, thing, "couldnt join");

    }

    @Test
    public void badjoin() {
        Vector<CreateGameResponce> expected = new Vector<CreateGameResponce>();
        Vector<CreateGameResponce> actualvec = new Vector<CreateGameResponce>();
        RegisterRequest req = new RegisterRequest("Username", "Password", "Email");
        RegisterResponce authToken = new UserService(user, auth).register(req);
        String authT = authToken.authToken();
        CreateGameRequest request = new CreateGameRequest("Hello");
        CreateGameResponce actual = new GameService(auth, game).createGame(request, authT);
        int iD = actual.gameID();
        JoinGameRequest join = new JoinGameRequest(ChessGame.TeamColor.BLACK, iD);
        JoinGameResponce cresponce = new GameService(auth, game).joinGame(join, "authT");
        JoinGameResponce thing = new JoinGameResponce("Error: unauthorized");
        assertEquals(cresponce, thing, "couldnt join");
    }

    @Test
    public void badList() {
        Vector<CreateGameResponce> expected = new Vector<CreateGameResponce>();
        Vector<CreateGameResponce> actualvec = new Vector<CreateGameResponce>();
        RegisterRequest req = new RegisterRequest("Username", "Password", "Email");
        RegisterResponce authToken = new UserService(user, auth).register(req);
        String authT = authToken.authToken();
        CreateGameRequest request = new CreateGameRequest("Hello");
        CreateGameResponce actual = new GameService(auth, game).createGame(request, authT);
        int iD = actual.gameID();
        ListGamesRequest listRequest = new ListGamesRequest("5");
        ListGamesResponce list = new GameService(auth, game).listGames(listRequest.authToken());
        ListGamesResponce wrong = new ListGamesResponce(null, "Error: unauthorized");
        assertEquals(list, wrong);
    }

    @Test
    public void goodList() {
        Vector<CreateGameResponce> expected = new Vector<CreateGameResponce>();
        Vector<CreateGameResponce> actualvec = new Vector<CreateGameResponce>();
        RegisterRequest req = new RegisterRequest("Username", "Password", "Email");
        RegisterResponce authToken = new UserService(user, auth).register(req);
        String authT = authToken.authToken();
        CreateGameRequest request = new CreateGameRequest("Hello");
        CreateGameResponce actual = new GameService(auth, game).createGame(request, authT);
        int iD = actual.gameID();
        ListGamesRequest listRequest = new ListGamesRequest(authT);
        ListGamesResponce list = new GameService(auth, game).listGames(listRequest.authToken());
        Vector<GameData> l = new Vector<GameData>();
        try {
            l.add(new GameData(actual.gameID(), null, null, request.gameName(), game.getGame(actual.gameID()).game()));
        } catch (dataAccess.DataAccessException e) {
        }
        ListGamesResponce wrong = new ListGamesResponce(l, null);
        assertEquals(list, wrong);
    }

    @Test
    public void goodLogin() {
        Vector<CreateGameResponce> expected = new Vector<CreateGameResponce>();
        Vector<CreateGameResponce> actualvec = new Vector<CreateGameResponce>();
        RegisterRequest req = new RegisterRequest("Username", "Password", "Email");
        RegisterResponce authToken = new UserService(user, auth).register(req);

        LoginRequest log = new LoginRequest("Username", "Password");
        LoginResponce res = new UserService(user, auth).login(log);
        String authT = res.authToken();

        LoginResponce expec = new LoginResponce("Username", authT, null);
        assertEquals(expec, res, "bad log");
    }

    @Test
    public void badLogin(){
        Vector<CreateGameResponce> expected = new Vector<CreateGameResponce>();
        Vector<CreateGameResponce> actualvec = new Vector<CreateGameResponce>();
        RegisterRequest req = new RegisterRequest("Username", "Password", "Email");
        RegisterResponce authToken = new UserService(user, auth).register(req);

        LoginRequest log = new LoginRequest("Usename", "Password");
        LoginResponce res = new UserService(user, auth).login(log);
        String authT = res.authToken();

        LoginResponce expec = new LoginResponce(null, null, "Error: ");
        assertEquals(expec, res, "bad log");
    }

    @Test
    public void goodLogout(){
        Vector<CreateGameResponce> expected = new Vector<CreateGameResponce>();
        Vector<CreateGameResponce> actualvec = new Vector<CreateGameResponce>();
        RegisterRequest req = new RegisterRequest("Username", "Password", "Email");
        RegisterResponce authToken = new UserService(user, auth).register(req);

        LoginRequest log = new LoginRequest("Username", "Password");
        LoginResponce res = new UserService(user, auth).login(log);
        String authT = res.authToken();

        LogoutRequest out = new LogoutRequest(authT);
        LogoutResponce resout = new UserService(user,auth).logout(authT);

        LogoutResponce should = new LogoutResponce("Success");
        assertEquals(should,resout,"logout");


    }

    @Test
    public void badlogout(){
        Vector<CreateGameResponce> expected = new Vector<CreateGameResponce>();
        Vector<CreateGameResponce> actualvec = new Vector<CreateGameResponce>();
        RegisterRequest req = new RegisterRequest("Username", "Password", "Email");
        RegisterResponce authToken = new UserService(user, auth).register(req);

        LoginRequest log = new LoginRequest("Username", "Password");
        LoginResponce res = new UserService(user, auth).login(log);
        String authT = res.authToken();

        LogoutRequest out = new LogoutRequest(authT);
        LogoutResponce resout = new UserService(user,auth).logout("authT");

        LogoutResponce should = new LogoutResponce("Error: unauthorized");
        assertEquals(should,resout,"logout");
    }

    @Test
    public void goodRegister(){
        Vector<CreateGameResponce> expected = new Vector<CreateGameResponce>();
        Vector<CreateGameResponce> actualvec = new Vector<CreateGameResponce>();
        RegisterRequest req = new RegisterRequest("Username", "Password", "Email");
        RegisterResponce authToken = new UserService(user, auth).register(req);
        String auth = authToken.authToken();
        RegisterResponce done = new RegisterResponce("Username",auth,null);

        assertEquals(done,authToken,"register");
    }

    @Test
    public void badResiter(){
        Vector<CreateGameResponce> expected = new Vector<CreateGameResponce>();
        Vector<CreateGameResponce> actualvec = new Vector<CreateGameResponce>();
        RegisterRequest req = new RegisterRequest(null, "", "Email");
        RegisterResponce authToken = new UserService(user, auth).register(req);
        String auth = authToken.authToken();
        RegisterResponce done = new RegisterResponce(null,null,"Error: ");

        assertEquals(done,authToken,"register");
    }
}