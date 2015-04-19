package app;

import playground.CannotAddShipException;
import playground.Field;
import playground.Position;
import playground.PositionNotAvailableException;
import ship.ShipFleet;
import ship.ShipShape;

public class ShipPositioner {
	private final Field field;

	public ShipPositioner(Field field) {
		this.field = field;
	}

	/**
	 * Tries to add ships.
	 * 
	 * @param ship the shape to be added
	 * @param count the number of ships to be added of the given shape
	 * @throws CannotAddShipException when there is not enough space to place
	 *             all ships.
	 */
	public void addShips(ShipFleet fleet) {
		for (int i = 0; i < fleet.getCount(); i++) {
			addShip(fleet.getShipShape());
		}
	}

	private void addShip(ShipShape ship) {
		boolean fits = false;
		Position foundPosition = null;
		final int xSize = field.getXSize();
		final int ySize = field.getYSize();
		int j = 0;
		while (j < ySize && fits == false) {
			int i = 0;
			while (i < xSize && fits == false) {
				Position currentPosition = new Position(i, j);
				fits = field.shipFits(ship, currentPosition);
				if (fits == true) {
					foundPosition = currentPosition;
				}
				i++;
			}
			j++;
		}
		if (fits == true) {
			try {
				field.putShip(ship, foundPosition);
			} catch (PositionNotAvailableException e) {
				System.out.println("cannot add " + ship);
				throw new PositionNotAvailableException(e.getPosition());
			}
		} else {
			throw new CannotAddShipException();
		}
	}
}
