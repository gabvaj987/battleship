package player;

/** Server implementation */
public class Server extends Player {

	static final String FIRE = "fire";
	static final String HELLO = "hello";
	static final String ERROR = "error";

	public Result accept(final String command) {
		Result ret = null;
		if (checkInsensitive(command, ERROR)) {
			ret = Result.TERMINATE;
		} else if (checkInsensitive(command, FIRE) || checkInsensitive(command, HELLO)) {
			ret = Result.CONTINUE;
		} else {
			throw new IllegalArgumentException();
		}
		return ret;
	}

	private boolean checkInsensitive(final String command, final String fire) {
		return command.toLowerCase().startsWith(fire);
	}
}
