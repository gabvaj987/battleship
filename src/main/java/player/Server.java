package player;

import player.result.Answer;
import player.result.Result;

/** Server implementation */
public class Server extends Player {

	static final String FIRE = "fire";
	static final String HELLO = "hello";
	static final String ERROR = "error";
	private final Integer width;
	private final Integer height;

	public Server(final Integer width, final Integer height) {
		this.width = width;
		this.height = height;
	}

	public Result accept(final String command) {
		Result ret = null;
		if (checkInsensitive(command, ERROR)) {
			ret = Result.TERMINATE;
		} else if (checkInsensitive(command, FIRE) || checkInsensitive(command, HELLO)) {
			ret = new Answer("YOU WON");
		} else {
			throw new IllegalArgumentException();
		}
		return ret;
	}

	private boolean checkInsensitive(final String command, final String fire) {
		return command.toLowerCase().startsWith(fire);
	}

	public Answer sayHello() {
		return new Answer("HELLO " + width + " " + height);
	}
}
