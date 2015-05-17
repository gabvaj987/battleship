package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import playground.CannotAddShipException;
import playground.Field;
import playground.Position;
import ship.ShipFleet;
import ship.ShipShape;

public class ShipPositioner {
	private final Field field;

	public ShipPositioner(final Field field) {
		this.field = field;
	}

	/**
	 * Tries to add ships.
	 * 
	 * @param ship
	 *            the shape to be added
	 * @param count
	 *            the number of ships to be added of the given shape
	 * @throws CannotAddShipException
	 *             when there is not enough space to place all ships.
	 */
	public void addShips(final ShipFleet fleet) {
		for (int i = 0; i < fleet.getCount(); i++) {
			addShip(fleet.getShipShape());
		}
	}

	private void addShip(final ShipShape ship) {
		final List<Position> potentialPositions = new ArrayList<>();
		final int xSize = field.getXSize();
		final int ySize = field.getYSize();
		for (int j = 0; j < ySize; j++) {
			for (int i = 0; i < xSize; i++) {
				final Position currentPosition = new Position(i, j);
				if (field.shipFits(ship, currentPosition)) {
					potentialPositions.add(currentPosition);
				}
			}
		}
		final int foundMatches = potentialPositions.size();
		if (foundMatches > 0) {
			field.putShip(ship, potentialPositions.get(new Random().nextInt(foundMatches)));
		} else {
			throw new CannotAddShipException();
		}
	}
}
