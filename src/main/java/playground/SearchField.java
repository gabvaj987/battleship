package playground;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import ship.ShipFleet;
import ship.ShipShape;
import app.PositionsNormalizer;

public class SearchField {
	private final Integer width;
	private final Integer height;
	private final PositionsNormalizer normalizer = new PositionsNormalizer();

	public SearchField(final Integer width, final Integer height) {
		this.width = width;
		this.height = height;
	}

	private final List<Position> orphanHits = new ArrayList<>();
	private final Set<Position> visited = new HashSet<>();
	private final List<ShipShape> left = new ArrayList<>();
	private final Random random = new Random();;

	public Position fire() {
		return getAttempt();
	}

	public Position missed(final Position currentAttempt) {
		visited.add(currentAttempt);
		return getAttempt();
	}

	public Position hit(final Position currentAttempt) {
		visited.add(currentAttempt);
		orphanHits.add(currentAttempt);
		return getAttempt();
	}

	public Position sunk(final Position currentAttempt) {
		visited.add(currentAttempt);
		orphanHits.add(currentAttempt);
		boolean found = true;
		for (final Iterator<ShipShape> shipIterator = left.iterator(); shipIterator.hasNext();) {
			final List<Position> shipPositions = shipIterator.next().getPositions();
			if (shipPositions.size() == orphanHits.size()) {
				final List<Position> normalizedOrphans = normalizer.normalize(orphanHits);
				for (final Position shipPosition : shipPositions) {
					final List<Position> offsetOrphans = normalizer.add(normalizedOrphans, shipPosition);
					if (offsetOrphans.containsAll(shipPositions)) {
						found = true;
						break;
					}
				}
				if (found) {
					shipIterator.remove();
					break;
				}
			}
		}
		orphanHits.clear();
		return getAttempt();
	}

	private Position getAttempt() {
		return new Position(random.nextInt(width), random.nextInt(height));
	}

	public void addShips(final ShipFleet shipFleet) {
		final ShipShape shipShape = shipFleet.getShipShape();
		for (int i = 0; i < shipFleet.getCount(); i++) {
			left.add(shipShape);
		}
	}
}
