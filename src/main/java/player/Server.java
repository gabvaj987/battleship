package player;

import player.result.Answer;

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

	public Answer sayHello() {
		return new Answer("HELLO " + width + " " + height);
	}
}
