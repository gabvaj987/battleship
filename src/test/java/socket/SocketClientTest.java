package socket;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import socket.SocketServer.Result;

public class SocketClientTest {
    private SocketClient underTest;

    @BeforeMethod
    public void init() {
        underTest = new SocketClient();
    }

    @Test
    public void testAcceptErrorExits() {
        // GIVEN
        // WHEN
        Result response = underTest.accept("ERROR You have already fired here");
        // THEN
        assertEquals(response, Result.TERMINATE);
    }
    
    @Test
    public void testAcceptHelloContinues() {
        // GIVEN
        // WHEN
        Result response = underTest.accept("HELLO 10 20");
        // THEN
        assertEquals(response, Result.CONTINUE);
    }
}
