package player;

import java.util.List;

import player.result.Answer;
import player.result.Result;
import playground.Field;
import playground.FireResult;
import playground.LimitedSizeField;
import playground.Position;
import playground.SearchField;
import ship.ShipFleet;
import app.ShipLoader;
import app.ShipPositioner;

public abstract class Player {
	private Integer width;
	private Integer height;
	private Position currentAttempt;
	private Field myField;
	private SearchField otherField;

	public Player() {

	}

	public Player(final Integer width, final Integer height) {
		this.width = width;
		this.height = height;
	}

	private void checkInitialized() {
		if (width == null || height == null) {
			throw new IllegalStateException("width and heigth have to be initialized");
		}
	}

	public Result accept(final String command) {
		checkInitialized();
		Result ret = null;
		if (command == null) {
			ret = new Answer(true, "ERROR command cannot be null");
		} else if (command.startsWith("FIRE")) {
			currentAttempt = otherField.fire();
			ret = createAnswer(handleOpponentAttempt(command), currentAttempt);
		} else if (command.startsWith("MISS")) {
			currentAttempt = otherField.missed(currentAttempt);
			ret = createAnswer(handleOpponentAttempt(command), currentAttempt);
		} else if (command.startsWith("HIT")) {
			currentAttempt = otherField.hit(currentAttempt);
			ret = createAnswer(handleOpponentAttempt(command), currentAttempt);
		} else if (command.startsWith("SUNK")) {
			currentAttempt = otherField.sunk(currentAttempt);
			ret = createAnswer(handleOpponentAttempt(command), currentAttempt);
		} else if (command.startsWith("ERROR")) {
			ret = new Result();
		} else if (command.startsWith("YOU WON")) {
			ret = new Result(true);
		} else {
			ret = new Answer(true, "ERROR command not recognized");
		}
		System.out.println(myField);
		return ret;
	}

	private Answer createAnswer(final FireResult fireResult, final Position currentAttempt) {
		if (fireResult == FireResult.ERROR) {
			return new Answer(fireResult.getCommand(), "FIRE " + currentAttempt.getX() + " " + currentAttempt.getY());
		} else if (fireResult == FireResult.YOU_WON) {
			return new Answer(true, fireResult.getCommand());
		} else {
			return new Answer(fireResult.getCommand() + " " + currentAttempt.getX() + " " + currentAttempt.getY());
		}
	}

	private FireResult handleOpponentAttempt(final String command) {
		final Position opponentAttempt = parsePosition(command);
		return myField.fire(opponentAttempt);
	}

	public void place() {
		checkInitialized();
		myField = new LimitedSizeField(width, height);
		otherField = new SearchField(width, height);
		final ShipPositioner shipPositioner = new ShipPositioner(myField);

		final ShipLoader shipLoader = new ShipLoader("/ships.txt");
		final List<ShipFleet> loadFleets = shipLoader.loadFleets();

		for (final ShipFleet shipFleet : loadFleets) {
			shipPositioner.addShips(shipFleet);
			otherField.addShips(shipFleet);
		}
	}

	protected static Position parsePosition(final String command) {
		final String[] parameters = command.split(" ", 3);
		return new Position(Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]));
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

	protected SearchField getOtherField() {
		return otherField;
	}

	protected void setCurrentAttempt(final Position currentAttempt) {
		this.currentAttempt = currentAttempt;
	}
}