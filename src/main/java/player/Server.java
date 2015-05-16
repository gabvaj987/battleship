package player;

import player.result.Answer;

/** Server implementation */
public class Server extends Player {

	static final String FIRE = "fire";
	static final String HELLO = "hello";
	static final String ERROR = "error";

	public Server(final Integer width, final Integer height) {
		super(width, height);
	}

	public Answer sayHello() {
		return new Answer("HELLO " + getWidth() + " " + getHeight());
	}
}
