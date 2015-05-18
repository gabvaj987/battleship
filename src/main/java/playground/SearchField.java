package playground;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ship.ShipFleet;
import ship.ShipShape;
import app.PositionsNormalizer;

public class SearchField extends AbstractField {
	private final PositionsNormalizer normalizer = new PositionsNormalizer();

	public SearchField(final Integer width, final Integer height) {
		super(width, height);
	}

	private final List<Position> hits = new ArrayList<>();
	private final List<Position> orphanHits = new ArrayList<>();
	private final Set<Position> visited = new HashSet<>();
	private final List<ShipShape> left = new ArrayList<>();

	public Position fire() {
		return getAttempt();
	}

	public Position error(final Position currentAttempt) {
		return getAttempt();
	}

	public Position missed(final Position currentAttempt) {
		visited.add(currentAttempt);
		return getAttempt();
	}

	public Position hit(final Position currentAttempt) {
		visited.add(currentAttempt);
		orphanHits.add(currentAttempt);
		hits.add(currentAttempt);
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
		System.out.println("Ships left to find:" + left);
		orphanHits.clear();
		return getAttempt();
	}

	private Position getAttempt() {
		Position attempt = null;
		if (orphanHits.size() > 0) {
			attempt = targeting();
		} else {
			attempt = seeking();
		}
		return attempt;
	}

	private Position seeking() {
		final List<Position> allPositions = new ArrayList<>();
		for (int i = 0; i < xSize; i++) {
			for (int j = 0; j < ySize; j++) {
				allPositions.add(new Position(i, j));
			}
		}
		Position attempt = null;
		boolean goodPosition = false;
		for (final Position possiblePosition : new RandomPicker<>(allPositions)) {
			for (final ShipShape ship : new RandomPicker<>(left)) {
				for (final Position shipPoint : new RandomPicker<>(ship.getPositions())) {
					final List<Position> shipOnField = normalizer.add(ship.getPositions(), possiblePosition.subtract(shipPoint));
					goodPosition = true;
					for (final Position shipPosition : shipOnField) {
						if (isOutside(shipPosition) || visited.contains(shipPosition) || isNeighboring(hits, shipPosition)) {
							goodPosition = false;
							break;
						}
					}
					if (goodPosition) {
						break;
					}
				}
				if (goodPosition) {
					break;
				}
			}
			if (goodPosition) {
				attempt = possiblePosition;
				break;
			}
		}
		return attempt;
	}

	private Position targeting() {
		Position attempt = null;
		for (final ShipShape ship : new RandomPicker<>(left)) {
			for (final Position orphanPoint : new RandomPicker<>(orphanHits)) {
				for (final Position shipPoint : new RandomPicker<>(ship.getPositions())) {
					final List<Position> shipOnField = normalizer.add(ship.getPositions(), orphanPoint.subtract(shipPoint));
					if (shipOnField.containsAll(orphanHits)) {
						boolean fits = true;
						shipOnField.removeAll(orphanHits);
						for (final Position shipPosition : shipOnField) {
							if (isOutside(shipPosition) || visited.contains(shipPosition)) {
								fits = false;
								break;
							}
						}
						if (!fits) {
							continue;
						}
						for (final Position possibleAttempt : new RandomPicker<>(shipOnField)) {
							if (isNeighboring(orphanHits, possibleAttempt)) {
								attempt = possibleAttempt;
								break;
							}
						}
					}
					if (attempt != null) {
						break;
					}
				}
				if (attempt != null) {
					break;
				}
			}
			if (attempt != null) {
				break;
			}
		}
		return attempt;
	}

	public void addShips(final ShipFleet shipFleet) {
		final ShipShape shipShape = shipFleet.getShipShape();
		for (int i = 0; i < shipFleet.getCount(); i++) {
			left.add(shipShape);
		}
	}
}
