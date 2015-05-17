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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((positions == null) ? 0 : positions.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ShipShape other = (ShipShape) obj;
		if (positions == null) {
			if (other.positions != null)
				return false;
		} else if (!positions.equals(other.positions))
			return false;
		return true;
	}
}
