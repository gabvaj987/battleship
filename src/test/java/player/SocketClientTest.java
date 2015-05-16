package player;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import player.result.Answer;
import player.result.Result;

public class SocketClientTest {
	private Player underTest;

	@BeforeMethod
	public void init() {
		underTest = new Client();
		underTest.setWidth(20);
		underTest.setHeight(40);
	}

	@Test
	public void testAcceptErrorExits() {
		// GIVEN
		// WHEN
		final Result response = underTest.accept("ERROR You have already fired here");
		// THEN
		assertEquals(response, Result.TERMINATE);
	}

	@Test(enabled = false)
	public void testAcceptHelloContinues() {
		// GIVEN
		// WHEN
		final Result response = underTest.accept("HELLO 10 20");
		// THEN
		assertEquals(response, new Answer("FIRE"));
	}
}
