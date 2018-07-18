//package carcassonne.core;

public class Player {
	public static final int startingNumberOfMeeples = 8;
	
	protected int id;
	protected boolean isAlive;
	protected int numberOfMeeples;
	protected int points;

	public Player(int idPlayer) {
		this.id = idPlayer;
		this.isAlive = true;
		this.numberOfMeeples = startingNumberOfMeeples;
		this.points = 0;	
	}	
}
