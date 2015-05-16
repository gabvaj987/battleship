package playground;

import ship.ShipShape;

public class FieldPosition {
	private final Position position;
	private ShipShape shipShape;
	private final boolean hit;

	public FieldPosition(final Position position, final boolean hit) {
		this.position = position;
		this.hit = hit;
	}

	protected Position getPosition() {
		return position;
	}

	protected void setShipShape(final ShipShape shipShape) {
		this.shipShape = shipShape;
	}

	protected ShipShape getShipShape() {
		return shipShape;
	}

	protected boolean isHit() {
		return hit;
	}
}
