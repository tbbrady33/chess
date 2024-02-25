package DataAccess;

import Server.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

public class AuthinterfaceTests {
    MemoryAuthDAO object;
    Vector<UserData> expected;

    @BeforeEach
    public void setup() {
        object = new MemoryAuthDAO();
        expected = new Vector<>();
    }

    @Test
    public void clear() {
        try {
            object.createAuth("danny");
            object.createAuth("Ty");
            object.clear();
            assertEquals(expected, object.data, "Didt clear");

        } catch (dataAccess.DataAccessException e) {
            System.out.println("data access exeption");
        }
    }
}
