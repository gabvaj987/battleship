package player;

import java.util.ArrayList;
import java.util.List;

import player.result.Answer;
import player.result.Result;
import playground.Field;
import playground.FireResult;
import playground.LimitedSizeField;
import playground.Position;
import ship.ShipFleet;
import ship.ShipShape;
import app.PositionsNormalizer;
import app.ShipLoader;
import app.ShipPositioner;

public abstract class Player {
	private Integer width;
	private Integer height;
	private Position currentAttempt;
	private final List<Position> orphanHits = new ArrayList<>();
	private final List<Position> misses = new ArrayList<>();
	private Field myField, otherField;
	private final PositionsNormalizer normalizer = new PositionsNormalizer();

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
		final boolean opponentAttempted;
		Result ret = null;
		if (command == null) {
			ret = new Answer("ERROR command expected", true);
		} else if (command.startsWith("FIRE")) {
			final Position attempt = getAvailable();
			handleOpponentAttempt(command);
		} else if (command.startsWith("MISS")) {
			handleOpponentAttempt(command);
			final Position attempt = getAvailable();
			misses.add(currentAttempt);
		} else if (command.startsWith("HIT")) {
			handleOpponentAttempt(command);
			orphanHits.add(getCurrentAttempt());
			final Position attempt = getAvailable();
			ret = new Answer("FIRE " + attempt.getX() + " " + attempt.getY());
		} else if (command.startsWith("SUNK")) {
			handleOpponentAttempt(command);
			handleSunk();
		} else if (command.startsWith("YOU WON")) {
			ret = Result.TERMINATE;
		} else if (command.startsWith("ERROR")) {
			ret = Result.TERMINATE;
		} else {
			ret = new Answer("ERROR command not recognized", true);
		}
		return ret;
	}

	private void handleSunk() {
		orphanHits.add(getCurrentAttempt());
		otherField.putShip(new ShipShape(normalizer.normalize(orphanHits)), orphanHits.get(0));
		orphanHits.clear();
	}

	private void handleOpponentAttempt(final String command) {
		final Position opponentAttempt = parsePosition(command);
		final FireResult fireResult = myField.fire(opponentAttempt);
	}

	public void place() {
		myField = new LimitedSizeField(width, height);
		otherField = new LimitedSizeField(width, height);
		final ShipPositioner shipPositioner = new ShipPositioner(myField);

		final ShipLoader shipLoader = new ShipLoader("/ships.txt");
		final List<ShipFleet> loadFleets = shipLoader.loadFleets();

		for (final ShipFleet shipFleet : loadFleets) {
			shipPositioner.addShips(shipFleet);
		}
	}

	protected Position getAvailable() {
		// TODO
		return null;
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

	protected Position getCurrentAttempt() {
		return currentAttempt;
	}

	protected void setCurrentAttempt(final Position currentAttempt) {
		this.currentAttempt = currentAttempt;
	}
}