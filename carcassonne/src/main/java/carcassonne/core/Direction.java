package carcassonne.core;

public enum Direction {
	NORTH(0),
	WEST(1),
	SOUTH(2),
	EAST(3);

	public static final int NUMBER_OF_DIRECTIONS = 13;
	public static final int SIDES = 4;
	public final int id;

	private Direction(int id) {
		this.id = id;
	}

	public int toInt() {
		return id;
	}
	
	public String toString() {
		return String.valueOf(id);
	}

	/**
	* Return the right index for the Tile.nodes array according to dir
	**/
	public int getExtend() {
		return (3 * this.toInt() + 1);
	}

	/**
	* Return the right opposite index for the Tile.nodes array according to dir
	**/
	public int getExtendOpposite() {
		return ((3 * this.toInt() + 7) % (NUMBER_OF_DIRECTIONS - 1));
	}

	/**
	* Return the value of the enumeration
	**/
	public int get() {
		return (this.toInt());
	}
	
	/**
	* Return the opposite value of the enumeration
	**/
	public int getOpposite() {
		return ((this.toInt() + 2) % SIDES);
	}

}
