package player;


/** Server implementation */
public class Server extends Player {

	static final String FIRE = "fire";
	static final String HELLO = "hello";
	static final String ERROR = "error";

	public Server(final Integer width, final Integer height) {
		super(width, height);
	}

	public String sayHello() {
		return "HELLO " + getWidth() + " " + getHeight();
	}
}
