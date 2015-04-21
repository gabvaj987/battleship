package app;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import app.SocketServer.Result;

public class SocketServerTest {
    private SocketServer underTest;

    @BeforeMethod
    public void init() {
        underTest = new SocketServer();
    }

    @Test
    public void testAcceptByeExits() {
        // GIVEN
        // WHEN
        Result response = underTest.accept("BYE");
        // THEN
        assertEquals(response, Result.TERMINATE);
    }
}
