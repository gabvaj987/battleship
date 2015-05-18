package playground;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ship.ShipHitLeft;
import ship.ShipShape;

/**
 * The valid coordinates of this field are for x: between 0 and xSize-1
 * inclusively. For y between 0 and ySize-1 inclusively.
 * 
 * @author vajda.gabor
 */
public class LimitedSizeField extends AbstractField implements Field {
	private final Set<Position> shipPositions = new HashSet<>();
	private final Set<Position> hitPositions = new HashSet<>();
	private final Set<Position> firedPositions = new HashSet<>();
	private final Map<Position, ShipHitLeft> shipsHitsLeft = new HashMap<>();
	private int hitsLeft;

	/**
	 * @param xSize
	 *            the x size of the filed
	 * @param ySize
	 *            the y size of the field
	 */
	public LimitedSizeField(final Integer xSize, final Integer ySize) {
		super(xSize, ySize);
	}

	@Override
	public FireResult fire(final Position pos) throws IllegalArgumentException {
		validatePosition(pos);
		final FireResult ret;
		if (firedPositions.contains(pos)) {
			ret = FireResult.ERROR;
		} else if (shipPositions.contains(pos)) {
			hitPositions.add(pos);
			if (--hitsLeft == 0) {
				ret = FireResult.YOU_WON;
			} else if (shipsHitsLeft.get(pos).decreaseAndCheck()) {
				ret = FireResult.SUNK;
			} else {
				ret = FireResult.HIT;
			}
		} else {
			ret = FireResult.MISS;
		}
		firedPositions.add(pos);
		return ret;
	}

	@Override
	public void putShip(final ShipShape shape, final Position pos) throws IllegalArgumentException, PositionNotAvailableException {
		validatePosition(pos);
		final ShipHitLeft shipHitLeft = new ShipHitLeft();
		for (final Position relativePosition : shape.getPositions()) {
			final Position absolutePosition = pos.add(relativePosition);
			boolean success = false;
			success = shipPositions.add(absolutePosition);
			if (success == false) {
				throw new PositionNotAvailableException(pos);
			} else {
				shipHitLeft.increase();
				shipsHitsLeft.put(absolutePosition, shipHitLeft);
				hitsLeft++;
			}
		}
	}

	// Throws an IllegalArgumentException if the provided position is pointing
	// outside of the field area.
	private void validatePosition(final Position pos) throws IllegalArgumentException {
		if (isOutside(pos)) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Boolean shipFits(final ShipShape shape, final Position pos) {
		boolean fits = true;
		final List<Position> shapePositions = shape.getPositions();
		for (final Position shapePosition : shapePositions) {
			final Position wantedPosition = pos.add(shapePosition);
			if (shipPositions.contains(wantedPosition) || isOutside(wantedPosition) || isNeighboring(shipPositions, wantedPosition)) {
				fits = false;
			}
		}
		return Boolean.valueOf(fits);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		for (int j = 0; j < getYSize(); j++) {
			for (int i = 0; i < getXSize(); i++) {
				final Position currentPosition = new Position(i, j);
				if (hitPositions.contains(currentPosition)) {
					sb.append("* ");
				} else if (shipPositions.contains(currentPosition)) {
					sb.append("o ");
				} else if (firedPositions.contains(currentPosition)) {
					sb.append(", ");
				} else {
					sb.append(". ");
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
