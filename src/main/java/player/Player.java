package player;

import player.result.Answer;
import player.result.Result;

public abstract class Player {

	public Result accept(final String command) {
		Result ret = null;
		if (command == null) {
			ret = new Answer("ERROR", true);
		} else if (command.startsWith("FIRE")) {
			ret = new Answer("YOU WON", true);
		} else if (command.startsWith("YOU WON")) {
			ret = Result.TERMINATE;
		} else if (command.startsWith("ERROR")) {
			ret = Result.TERMINATE;
		} else {
			ret = new Answer("ERROR", true);
		}
		return ret;
	}

}