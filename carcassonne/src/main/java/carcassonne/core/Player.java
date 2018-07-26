package carcassonne.core;

import java.util.ArrayList;

public class Player {
	public static final int NUMBER_OF_MEEPLES = 8;
	
	public int id;
	public boolean isAlive;
	public ArrayList<Node> meeples = new ArrayList<Node>();
	public int points;

	public Player(int id) {
		this.id = id;
		this.isAlive = true;
		this.points = 0;	
	}	

	public void update(Move m) {
		if (meeples.size() < NUMBER_OF_MEEPLES && m.place != placeId.NO_MEEPLE)
			meeples.add(m.tile.nodes[m.place.toInt()]);
	}

	public void eject() {
		isAlive = false;
	}
}
