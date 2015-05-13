import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import playground.Field;
import playground.Position;
import ship.ShipFleet;
import ship.ShipShape;
import app.ShipPositioner;

public class ShipPositionerTest {
	private ShipPositioner underTest;
	@Mock
	private Field field;

	private ShipShape one;

	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		underTest = new ShipPositioner(field);

		List<Position> onePoint = new ArrayList<>();
		onePoint.add(new Position(0, 0));
		one = new ShipShape(onePoint);

	}

	@Test
	public void testAddShipShouldTryPuttingInSomeShips() {
		// GIVEN
		when(field.getXSize()).thenReturn(10);
		when(field.getYSize()).thenReturn(10);
		when(field.shipFits(any(ShipShape.class), any(Position.class))).thenReturn(Boolean.TRUE);
		// WHEN
		underTest.addShips(new ShipFleet(one, 1));
		// Then
		verify(field, atLeastOnce()).shipFits(any(ShipShape.class), any(Position.class));
	}
}
