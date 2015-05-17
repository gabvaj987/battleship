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
			ret = new Answer("FIRE " + 0 + " " + 0);
		} else {
			ret = super.accept(command);
		}
		return ret;
	}
}
