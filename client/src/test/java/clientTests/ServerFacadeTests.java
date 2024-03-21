package clientTests;

import Login.LoginRequest;
import Logout.LogoutRequest;
import Register.RegisterRequest;
import dataAccess.DataAccessException;
import org.junit.jupiter.api.*;
import server.Server;
import ui.ServerFacade;
import static org.junit.jupiter.api.Assertions.*;



public class ServerFacadeTests {

    static Server server;
    static ServerFacade facade;

    @BeforeAll
    public static void init() {
        server = new Server();
        var port = new Server().run(8080);
        System.out.println("Started test HTTP server on " + port);
       facade = new ServerFacade("http://localhost:8080/");
    }

    @BeforeEach
    public void clearServer(){
        try{
            var Clear = facade.clear();
        }catch (DataAccessException ex){
            ex.printStackTrace();
        }
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }


    @Test
    public void registerGood() {
        try {
            var authData = facade.register(new RegisterRequest("player1", "password", "p1@email.com"));
            assertTrue(authData.authToken().length() > 10);
        }
        catch (DataAccessException ex){
            ex.printStackTrace();
            System.out.print("Didnt work");
            assertEquals(1,2);
        }
    }

    @Test
    public void registerBad(){
        try {
            var autData = facade.register(new RegisterRequest("","","sd"));
            assertTrue(autData.authToken() == null);
        }
        catch (DataAccessException ex){
            ex.printStackTrace();
            assertEquals(1,2);
        }
    }

    @Test
    public void loginGood(){
        try {
            var authData = facade.register(new RegisterRequest("player1", "password", "p1@email.com"));
            var logData = facade.login(new LoginRequest("player1", "password"));
            assertTrue(logData.authToken().length() > 10);
        }
        catch (DataAccessException ex){
            System.out.print("Didnt work");
            assertEquals(1,2);
        }
    }

    @Test
    public void loginBad(){
        try{
            var authData = facade.register(new RegisterRequest("player1", "password", "p1@email.com"));
            var logData = facade.login((new LoginRequest("dfds", "sdf")));
            assertNull(logData.authToken());
        }
        catch (DataAccessException ex){
            System.out.print("Didnt work");
            assertEquals(1,2);
        }
    }

    @Test
    public void logoutGood(){
        try{
            var authData = facade.register(new RegisterRequest("player1", "password", "p1@email.com"));
            var logoutData = facade.logout(new LogoutRequest(authData.authToken()));
            assertTrue(logoutData.message().equals("Success"));
        }
        catch (DataAccessException ex){
            ex.printStackTrace();
            assertEquals(1,2);

        }
    }

    @Test
    public void logoutBad(){
        try{
            var authData = facade.register(new RegisterRequest("player1", "password", "p1@email.com"));
            var logoutData = facade.logout(new LogoutRequest("data.authToken())"));
            assertEquals(logoutData.message(),"Failed");
        }
        catch (DataAccessException ex){
            System.out.print("Didnt work");
            assertEquals(1,2);
        }
    }

}
