package ship;

public class ShipFleet {
	private final ShipShape shipShape;
	private final int count;

	public ShipFleet(ShipShape shipShape, int count) {
		super();
		this.shipShape = shipShape;
		this.count = count;
	}

	public ShipShape getShipShape() {
		return shipShape;
	}

	public int getCount() {
		return count;
	}
}
