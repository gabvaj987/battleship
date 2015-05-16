package player;

import player.result.Answer;
import player.result.Result;
import playground.Position;

public class Client extends Player {

	@Override
	public Result accept(final String command) {
		Result ret;
		if (command != null && command.startsWith("HELLO")) {
			final Position size = parsePosition(command);
			setWidth(size.getX());
			setHeight(size.getY());
			place();
			final Position attempt = getAvailable();
			setCurrentAttempt(attempt);
			ret = new Answer("FIRE " + attempt.getX() + " " + attempt.getY());
		} else {
			ret = super.accept(command);
		}
		return ret;
	}

	private static Position parsePosition(final String command) {
		final String[] parameters = command.split(" ", 3);
		return new Position(Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]));
	}
}
