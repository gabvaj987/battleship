package ship;

public class ShipHitLeft {
	private int count;

	public ShipHitLeft(final int count) {
		this.count = count;
	}

	/**
	 * @return true if Ship has sank.
	 */
	public boolean decreaseAndCheck() {
		if (count > 0) {
			count--;
		}
		return count == 0;
	}
}
