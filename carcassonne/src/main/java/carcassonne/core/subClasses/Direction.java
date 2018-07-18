//package carcassonne.core.subClass;

enum DirectionName {
	NORTH,
	WEST,
	SOUTH,
	EAST,
};

public class Direction {
	public static final int Sides = 4;
	
	protected int id;
	protected DirectionName name;

	public Direction(int id, DirectionName name) {
		this.id = id;
		this.name = name;
	}
}
