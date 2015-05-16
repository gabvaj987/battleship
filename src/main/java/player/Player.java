package player;

import player.result.Answer;
import player.result.Result;
import playground.Field;
import playground.LimitedSizeField;
import playground.Position;

public abstract class Player {

	private Integer width;
	private Integer height;
	private Position currentAttempt;
	private Field myField, otherField;

	public Player() {

	}

	public Player(final Integer width, final Integer height) {
		this.width = width;
		this.height = height;
	}

	public Result accept(final String command) {
		if (width == null || height == null) {
			throw new IllegalStateException("width and heigth have to be initialized");
		}
		Result ret = null;
		if (command == null) {
			ret = new Answer("ERROR command expected", true);
		} else if (command.startsWith("FIRE") || command.startsWith("MISS")) {
			final Position attempt = getAvailable();
			ret = new Answer("FIRE " + attempt.getX() + " " + attempt.getY());
		} else if (command.startsWith("HIT")) {
			// getCurrentAttempt()
			final Position attempt = getAvailable();
			ret = new Answer("FIRE " + attempt.getX() + " " + attempt.getY());
		} else if (command.startsWith("YOU WON")) {
			ret = Result.TERMINATE;
		} else if (command.startsWith("ERROR")) {
			ret = Result.TERMINATE;
		} else {
			ret = new Answer("ERROR command not recognized", true);
		}
		return ret;
	}

	public void place() {
		myField = new LimitedSizeField(width, height);
		otherField = new LimitedSizeField(width, height);
	}

	protected Position getAvailable() {
		// TODO
		return null;
	}

	protected Integer getWidth() {
		return width;
	}

	protected void setWidth(final Integer width) {
		this.width = width;
	}

	protected Integer getHeight() {
		return height;
	}

	protected void setHeight(final Integer height) {
		this.height = height;
	}

	protected Position getCurrentAttempt() {
		return currentAttempt;
	}

	protected void setCurrentAttempt(final Position currentAttempt) {
		this.currentAttempt = currentAttempt;
	}
}