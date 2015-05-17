package player.result;

public class Result {
	private final boolean isLast;

	public Result() {
		this(false);
	}

	public Result(final boolean isLast) {
		this.isLast = isLast;
	}

	public boolean isLast() {
		return isLast;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isLast ? 1231 : 1237);
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
		final Result other = (Result) obj;
		if (isLast != other.isLast)
			return false;
		return true;
	}
}
