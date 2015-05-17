package app;

import java.util.List;

import playground.Field;
import playground.FireResult;
import playground.LimitedSizeField;
import playground.Position;
import ship.ShipFleet;

public class Battle {

	private static final int SIZE = 20;

	public static void main(final String[] args) {
		// Set up field
		final Field field = new LimitedSizeField(SIZE, SIZE);
		final ShipPositioner shipPositioner = new ShipPositioner(field);

		final ShipLoader shipLoader = new ShipLoader("/ships.txt");
		final List<ShipFleet> loadFleets = shipLoader.loadFleets();

		for (final ShipFleet shipFleet : loadFleets) {
			shipPositioner.addShips(shipFleet);
		}

		System.out.println(field);

		// hit ships
		final boolean hit;
		for (int i = 0; i < SIZE / 3; i++) {
			for (int j = 0; j < SIZE; j++) {
				final Position pos = new Position(i, j);
				if (field.fire(pos) == FireResult.HIT) {
					System.out.println("hit at: " + pos);
				}
			}
		}
		System.out.println(field);
	}
}
