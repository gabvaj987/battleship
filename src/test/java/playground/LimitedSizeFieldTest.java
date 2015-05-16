package playground;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ship.ShipShape;

public class LimitedSizeFieldTest {
	private static final int FIELD_SIZE = 10;
	private Field underTest;
	private ShipShape one, tetris;

	@BeforeMethod
	public void setUp() {
		underTest = new LimitedSizeField(FIELD_SIZE, FIELD_SIZE);
		final List<Position> onePoint = new ArrayList<>();
		onePoint.add(new Position(0, 0));
		one = new ShipShape(onePoint);

		final List<Position> tetrisPoints = new ArrayList<>();
		tetrisPoints.add(new Position(0, 1));
		tetrisPoints.add(new Position(1, 1));
		tetrisPoints.add(new Position(1, 0));
		tetrisPoints.add(new Position(2, 1));
		tetris = new ShipShape(tetrisPoints);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testFireOutsideLowerEndShouldThrowIae() {
		underTest.putShip(one, new Position(-1, -1));
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testFireOutsideUpperEndShouldThrowIae() {
		underTest.putShip(one, new Position(0, FIELD_SIZE));
	}

	@Test
	public void testFireShouldHitPreviouslyPlacedShip() {
		// GIVEN
		underTest.putShip(one, new Position(1, 1));
		// WHEN
		final Boolean isHit = underTest.fire(new Position(1, 1));
		// THEN
		assertEquals(isHit, Boolean.TRUE);
	}

	@Test
	public void testFireShouldHitPreviouslyPlacedTetrisShip() {
		// GIVEN
		underTest.putShip(tetris, new Position(1, 1));
		// WHEN
		final Boolean isHit = underTest.fire(new Position(1, 1));
		// THEN
		assertEquals(isHit, Boolean.FALSE);
	}

	@Test(expectedExceptions = PositionNotAvailableException.class)
	public void testPutShipShouldThrowPositionNotAavailableException() {
		// GIVEN
		underTest.putShip(one, new Position(1, 1));
		// WHEN
		underTest.putShip(one, new Position(1, 1));
		// THEN should throw PositionNotAvailableException
	}

	@Test
	public void testShapeFitsShouldReturnFalseForTetrisInTheBottomLeftCorner() {
		// GIVEN
		// WHEN
		final Boolean fits = underTest.shipFits(tetris, new Position(0, 9));
		// THEN
		assertEquals(fits, Boolean.FALSE);
	}
}
