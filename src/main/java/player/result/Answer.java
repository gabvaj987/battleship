package player.result;

import java.util.ArrayList;
import java.util.List;

public class Answer extends Result {
	public final List<String> commands = new ArrayList<>();

	public Answer(final String... commands) {
		this(false, commands);
	}

	public Answer(final boolean isLast, final String... commands) {
		super(isLast);
		for (final String command : commands) {
			this.commands.add(command);
		}
	}

	public List<String> getCommands() {
		return commands;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((commands == null) ? 0 : commands.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Answer other = (Answer) obj;
		if (commands == null) {
			if (other.commands != null)
				return false;
		} else if (!commands.equals(other.commands))
			return false;
		return true;
	}

}
