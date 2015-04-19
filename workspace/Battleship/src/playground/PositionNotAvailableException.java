package playground;

@SuppressWarnings("serial")
public class PositionNotAvailableException extends RuntimeException {
	private final Position position;

	public PositionNotAvailableException(Position position) {
		super();
		this.position = position;
	}

	@Override
	public String toString() {
		return "PositionNotAvailableException [position=" + position + "]";
	}

	public Position getPosition() {
		return position;
	}
}
