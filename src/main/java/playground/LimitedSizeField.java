package playground;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ship.ShipShape;

/**
 * The valid coordinates of this field are for x: between 0 and xSize-1
 * inclusively. For y between 0 and ySize-1 inclusively.
 * 
 * @author vajda.gabor
 */
public class LimitedSizeField implements Field {
	private final Integer xSize, ySize;
	private final Set<Position> placesUsed = new HashSet<>();
	private final Set<Position> placesHit = new HashSet<>();

	@Override
	public Integer getXSize() {
		return xSize;
	}

	@Override
	public Integer getYSize() {
		return ySize;
	}

	/**
	 * @param xSize the x size of the filed
	 * @param ySize the y size of the field
	 */
	public LimitedSizeField(Integer xSize, Integer ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
	}

	@Override
	public Boolean fire(Position pos) throws IllegalArgumentException {
		validatePosition(pos);
		boolean hit = placesUsed.contains(pos);
		if (hit == true) {
			placesHit.add(pos);
		}
		return hit;
	}

	@Override
	public void putShip(ShipShape shape, Position pos) throws IllegalArgumentException, PositionNotAvailableException {
		validatePosition(pos);
		for (Position relativePosition : shape.getPositions()) {
			Position absolutePosition = pos.add(relativePosition);
			boolean success = false;
			success = placesUsed.add(absolutePosition);
			if (success == false) {
				throw new PositionNotAvailableException(pos);
			}
		}
	}

	private boolean isEnabled(Position absolutePosition) {
		Collection<Position> neighbours = getNeighbours(absolutePosition);
		boolean enabled = true;
		for (Position neighbour : neighbours) {
			if (placesUsed.contains(neighbour)) {
				enabled = false;
			}
		}
		return enabled;
	}

	private Collection<Position> getNeighbours(Position absolutePosition) {
		ArrayList<Position> neighbours = new ArrayList<>();
		neighbours.add(absolutePosition.add(new Position(-1, 0)));
		neighbours.add(absolutePosition.add(new Position(1, 0)));
		neighbours.add(absolutePosition.add(new Position(0, -1)));
		neighbours.add(absolutePosition.add(new Position(0, 1)));
		return neighbours;
	}

	// Throws an IllegalArgumentException if the provided position is pointing
	// outside of the field area.
	private void validatePosition(Position pos) throws IllegalArgumentException {
		if (isOutside(pos)) {
			throw new IllegalArgumentException();
		}
	}

	private boolean isOutside(Position pos) {
		int posX = pos.getX();
		int posY = pos.getY();
		return posX < 0 || posX >= xSize || posY < 0 || posY >= ySize;
	}

	@Override
	public Boolean shipFits(ShipShape shape, Position pos) {
		boolean fits = true;
		List<Position> shapePositions = shape.getPositions();
		for (Position shapePosition : shapePositions) {
			Position wantedPosition = pos.add(shapePosition);
			if (placesUsed.contains(wantedPosition) || isOutside(wantedPosition) || !isEnabled(wantedPosition)) {
				fits = false;
			}
		}
		return Boolean.valueOf(fits);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < getYSize(); j++) {
			for (int i = 0; i < getXSize(); i++) {
				Position currentPosition = new Position(i, j);
				if (placesHit.contains(currentPosition)) {
					sb.append("* ");
				} else if (placesUsed.contains(currentPosition)) {
					sb.append("o ");
				} else {
					sb.append(". ");
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
