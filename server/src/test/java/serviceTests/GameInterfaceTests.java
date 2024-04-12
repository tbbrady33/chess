package serviceTests;

import dataAccess.MemoryGameDAO;
import Model.GameData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.Vector;

public class GameInterfaceTests {

    Vector<GameData> expected;

    MemoryGameDAO objec;

    @BeforeEach
    public void setUp(){
        objec = new MemoryGameDAO();
        expected = new Vector<>();
        expected.clear();
    }

    @Test
    public void addGame(){
        try{
            GameData game = objec.createGame("Hello");
            int ID = game.gameID();
            expected.add(new GameData(ID,null,null,"Hello",game.game()));
            assertEquals(expected,objec.data,"I dunno");
        }
        catch (dataAccess.DataAccessException e){
            System.out.println("Data access");
        }
    }

}
