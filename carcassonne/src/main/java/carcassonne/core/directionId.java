package carcassonne.core;

public enum directionId {
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
