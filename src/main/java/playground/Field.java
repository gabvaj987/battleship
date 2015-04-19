package playground;

import ship.ShipShape;

/**
 * A battleship field that has battleships which can be hit and sunk.
 * 
 * @author vajda.gabor
 */
public interface Field {
	/**
	 * Fires at a position on the field.
	 * 
	 * @param pos the position where a torpedo is fired.
	 * @return true if a ship or part of a ship was hit. False otherwise
	 * @throws IllegalArgumentException if x or y are outside of the field area
	 */
	public Boolean fire(Position pos) throws IllegalArgumentException;

	/**
	 * Places a ship at a particular position being the ship's bottom left
	 * pixel.
	 * 
	 * @param shape the ship to be put
	 * @param pos the position where a ship is placed
	 * @throws IllegalArgumentException if x or y are outside the field area
	 */
	public void putShip(ShipShape shape, Position pos) throws IllegalArgumentException, PositionNotAvailableException;

	/**
	 * Checks if a ship fits at a position being the ship's bottom left pixel.
	 * 
	 * @param shape the battleship to be checked
	 * @param pos the position to check the ship at
	 * @return true if there is enough room, false otherwise
	 */
	public Boolean shipFits(ShipShape shape, Position pos);

	public abstract Integer getYSize();

	public abstract Integer getXSize();
}
