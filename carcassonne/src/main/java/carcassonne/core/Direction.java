//package carcassonne.core;

enum directionName {
	NORTH,
	WEST,
	SOUTH,
	EAST,
};

public class Direction {
	public static final int Sides = 4;
	
	protected int id;
	protected directionName name;

	public Direction(int id, directionName name) {
		this.id = id;
		this.name = name;
	}
}
