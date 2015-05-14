package socket;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import player.Client;
import player.result.Answer;
import player.result.Result;

public class SocketClientTest {
	private Client underTest;

	@BeforeMethod
	public void init() {
		underTest = new Client();
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
	public void testAcceptHelloContinues() {
		// GIVEN
		// WHEN
		final Result response = underTest.accept("HELLO 10 20");
		// THEN
		assertEquals(response, new Answer("FIRE"));
	}
}
