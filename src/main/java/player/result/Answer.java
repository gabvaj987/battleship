package player.result;

public class Answer extends Result {
	public final String command;
	public boolean isLast;

	public Answer(final String command) {
		this.command = command;
	}

	public Answer(final String command, final boolean isLast) {
		this(command);
		this.isLast = isLast;
	}

	public String getCommand() {
		return command;
	}

	public boolean isLast() {
		return isLast;
	}
}
