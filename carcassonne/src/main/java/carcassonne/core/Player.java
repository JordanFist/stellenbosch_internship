package carcassonne.core;

public class Player {
	public static final int STARTING_NUMBER_OF_MEEPLES = 8;
	
	public int id;
	public boolean isAlive;
	public int numberOfMeeples;
	public int points;

	public Player(int id) {
		this.id = id;
		this.isAlive = true;
		this.numberOfMeeples = STARTING_NUMBER_OF_MEEPLES;
		this.points = 0;	
	}	
}
