import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import playground.Position;

public class PositionTest {
	private Position underTest;

	@BeforeMethod
	public void setUp() {
		underTest = new Position(1, 2);
	}

	@Test
	public void testAddShouldReturnAddedPosition() {
		// GIVEN
		// WHEN
		Position added = underTest.add(new Position(3, 4));
		// THEN
		Assert.assertEquals(added, new Position(4, 6));
	}

	@Test
	public void testSubtractShouldReturnSubtractedPosition() {
		// GIVEN
		// WHEN
		Position added = underTest.subtract(new Position(3, 6));
		// THEN
		Assert.assertEquals(added, new Position(-2, -4));
	}
}
