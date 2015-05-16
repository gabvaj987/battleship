package player;

import player.result.Answer;
import player.result.Result;

public class Client extends Player {

	@Override
	public Result accept(final String command) {
		Result ret = super.accept(command);
		if (command != null && command.startsWith("HELLO")) {
			ret = new Answer("FIRE");
		}
		return ret;
	}
}
