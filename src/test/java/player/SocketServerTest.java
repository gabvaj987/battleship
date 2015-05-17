package player;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import player.result.Answer;
import player.result.Result;

public class SocketServerTest {
	private Player underTest;

	@BeforeMethod
	public void init() {
		underTest = new Server(10, 20);
	}

	@Test
	public void testAcceptErrorExits() {
		// GIVEN
		// WHEN
		final Result response = underTest.accept("ERROR You have already fired here");
		// THEN
		assertEquals(response, new Result());
	}

	@Test(enabled = false)
	public void testAcceptFireContinues() {
		// GIVEN
		// WHEN
		final Result response = underTest.accept("FIRE 5 10");
		// THEN
		assertEquals(response, new Answer("YOU WON"));
	}
}
