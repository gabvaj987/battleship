package playground;

public class Position {
	private final int x, y;

	public Position(final int x, final int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Position add(final Position other) {
		return new Position(x + other.getX(), y + other.getY());
	}

	public Position subtract(final Position other) {
		return new Position(x - other.getX(), y - other.getY());
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "{x=" + x + ", y=" + y + "}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Position)) {
			return false;
		}
		final Position other = (Position) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}
}
