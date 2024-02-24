package DataAccess;

import Server.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class UserInterfaceTests {
    memoryUserDAO objec;
    Vector<UserData> expected;
    @BeforeEach
    public void setUp(){
        objec = new memoryUserDAO();
        expected = new Vector<>();
    }
    @Test
    public void addclearset(){
        objec.insertUser(new UserData("HI","Hi","Hi"));
        objec.insertUser(new UserData("NO","NO","NO"));
        assertEquals(objec.data, expected);
    }

    @Test
    public void addtovec(){
        objec.insertUser(new UserData("HI","HI","HI"));
        expected.add(new UserData("HI","HI","HI"));
        assertEquals(objec.data,expected);
    }
}
