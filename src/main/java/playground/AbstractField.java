package playground;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractField {

	protected final Integer xSize;
	protected final Integer ySize;

	public AbstractField(final Integer xSize2, final Integer ySize2) {
		xSize = xSize2;
		ySize = ySize2;
	}

	protected Collection<Position> getNeighbours(final Position absolutePosition) {
		final ArrayList<Position> neighbours = new ArrayList<>();
		neighbours.add(absolutePosition.add(new Position(-1, 0)));
		neighbours.add(absolutePosition.add(new Position(1, 0)));
		neighbours.add(absolutePosition.add(new Position(0, -1)));
		neighbours.add(absolutePosition.add(new Position(0, 1)));
		return neighbours;
	}

	public Integer getXSize() {
		return xSize;
	}

	public Integer getYSize() {
		return ySize;
	}

	protected boolean isNeighboring(final Collection<Position> shapePositions, final Position absolutePosition) {
		final Collection<Position> neighbours = getNeighbours(absolutePosition);
		boolean neighboring = false;
		for (final Position neighbour : neighbours) {
			if (shapePositions.contains(neighbour)) {
				neighboring = true;
			}
		}
		return neighboring;
	}

	protected boolean isOutside(final Position pos) {
		final int posX = pos.getX();
		final int posY = pos.getY();
		return posX < 0 || posX >= xSize || posY < 0 || posY >= ySize;
	}

}
