package player.result;

public abstract class Result {
	public static final Terminate TERMINATE = new Terminate();
	public static class Terminate extends Result {

	}
}
