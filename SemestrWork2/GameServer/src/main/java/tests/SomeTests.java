package tests;

import org.junit.Test;
import server.part.GameServer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class SomeTests {

    @Test
    public void checkIfPlayersAreGood() {
        GameServer server = new GameServer();
        assertNotNull(server.getPlayers());
    }

    @Test
    public void checkIfIdIsOk() {
        GameServer server = new GameServer();
        assertEquals(server.getUserId(),"1");
    }
}
