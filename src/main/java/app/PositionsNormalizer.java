package app;

import java.util.ArrayList;
import java.util.List;

import playground.Position;

public class PositionsNormalizer {
	public List<Position> normalize(final List<Position> positions) {
		final Position first = positions.get(0);
		final List<Position> normalPositions = new ArrayList<>();
		for (final Position position : positions) {
			normalPositions.add(position.subtract(first));
		}
		return normalPositions;
	}
}