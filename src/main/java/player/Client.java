package player;

import player.result.Answer;
import player.result.Result;

public class Client extends Player {

	public Result accept(final String command) {
		Result ret = null;
		if (command.startsWith("ERROR")) {
			ret = Result.TERMINATE;
		} else if (command.startsWith("YOU WON")) {
			ret = Result.TERMINATE;
		} else if (command.startsWith("HELLO")) {
			ret = new Answer("FIRE");
		} else {
			throw new IllegalArgumentException();
		}
		return ret;
	}
}
