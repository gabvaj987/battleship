package playground;

public enum FireResult {
	HIT("HIT"), MISS("MISS"), SUNK("SUNK"), YOU_WON("YOU WON"), ERROR("ERROR already hit");
	private final String command;

	FireResult(final String command) {
		this.command = command;
	}

	public String getCommand() {
		return command;
	}
}
