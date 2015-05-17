package playground;

import java.util.List;
import java.util.Set;

import ship.ShipShape;

public class SearchField {
	private List<Position> orphanHits;
	private Set<Position> visited;
	private List<ShipShape> left;
}
