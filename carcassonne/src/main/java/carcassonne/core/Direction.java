package carcassonne.core;

enum directionId {
	NORTH(0),
	WEST(1),
	SOUTH(2),
	EAST(3);

	public final int id;

	private directionId(int id) {
		this.id = id;
	}

	public int toInt() {
		return id;
	}
	
	public String toString() {
		return String.valueOf(id);
	}
}

public class Direction {

	public static int getDirection(directionId dir) {
		return (3 * dir.toInt() + 1);
	}
	
	public static int getOppositeDirection(directionId dir) {
		return ((3 * dir.toInt() + 7) % (Tile.NUMBER_OF_DIRECTIONS - 1));
	}
}
