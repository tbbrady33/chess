package serviceTests;

import DataAccess.DataAccessException;
import dataAccess.MemoryUserDAO;
import Model.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class UserInterfaceTests {
    MemoryUserDAO objec;
    Vector<UserData> expected;
    @BeforeEach
    public void setUp(){
        objec = new MemoryUserDAO();
        expected = new Vector<>();
        expected.clear();
    }
    @Test
    public void addclearset(){
        try {
            objec.insertUser(new UserData("HI","Hi","Hi"));
            objec.insertUser(new UserData("NO","NO","NO"));
            objec.clear();
        }
        catch (DataAccessException e ){
            System.out.println("Data acess error");
        }

        assertEquals(objec.data, expected, "Could not clear");
    }

    @Test
    public void addtovec(){
        try{
            objec.insertUser(new UserData("HI","HI","HI"));
        }
        catch (DataAccessException e){
            System.out.println("Data acess error");
        }
        expected.add(new UserData("HI","HI","HI"));
        assertEquals(objec.data,expected, "couldnt add");
    }

    @Test
    public void finduse(){
        try {
            objec.insertUser(new UserData("HI","HI","HI"));
            objec.insertUser(new UserData("NO","NO","NO"));
            UserData result = objec.getUser("NO");

            expected.add(new UserData("NO","NO","NO"));
            assertEquals(result,expected.elementAt(0), "failed to find user");
        }
        catch (DataAccessException e){
            System.out.println("Data acess error");
        }

    }
    @Test
    public void nulluse(){
        try {
            objec.insertUser(new UserData("HI","HI","HI"));
            objec.insertUser(new UserData("NO","NO","NO"));
            UserData result = objec.getUser("ys");

            expected.add(null);
            assertEquals(result,expected.elementAt(0), "failed to find user");
        }
        catch (DataAccessException e){
            System.out.println("Data acess error");
        }
    }
}
