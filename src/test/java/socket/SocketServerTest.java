package socket;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import player.Result;
import player.Server;

public class SocketServerTest {
	private Server underTest;

	@BeforeMethod
	public void init() {
		underTest = new Server();
	}

	@Test
	public void testAcceptErrorExits() {
		// GIVEN
		// WHEN
		final Result response = underTest.accept("ERROR You have already fired here");
		// THEN
		assertEquals(response, Result.TERMINATE);
	}

	@Test
	public void testAcceptFireContinues() {
		// GIVEN
		// WHEN
		final Result response = underTest.accept("FIRE 5 10");
		// THEN
		assertEquals(response, Result.CONTINUE);
	}
}
