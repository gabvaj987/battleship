package socket;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import socket.SocketServer.Result;

public class SocketServerTest {
    private SocketServer underTest;

    @BeforeMethod
    public void init() {
        underTest = new SocketServer();
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
    public void testAcceptFireContinues() {
        // GIVEN
        // WHEN
        Result response = underTest.accept("FIRE 5 10");
        // THEN
        assertEquals(response, Result.CONTINUE);
    }
}
