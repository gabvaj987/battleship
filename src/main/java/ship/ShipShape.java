package ship;

import java.util.ArrayList;
import java.util.List;

import playground.Position;

public class ShipShape {
	private final List<Position> positions;

	public ShipShape(final List<Position> positions) {
		this.positions = new ArrayList<>(positions);
	}

	public List<Position> getPositions() {
		return new ArrayList<>(positions);
	}
}
