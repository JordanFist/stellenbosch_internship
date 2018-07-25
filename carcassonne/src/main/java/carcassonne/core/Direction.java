package carcassonne.core;

public class Direction {
	public static int getDirection(directionId dir) {
		return (3 * dir.toInt() + 1);
	}
	
	public static int getOppositeDirection(directionId dir) {
		return ((3 * dir.toInt() + 7) % (Tile.NUMBER_OF_DIRECTIONS - 1));
	}
}
