package carcassonne.core;

import java.util.ArrayList;

public class Player {
	public static final int NUMBER_OF_MEEPLES = 8;
	
	public int id;
	public boolean isAlive;
	public ArrayList<Node> meeples = new ArrayList<Node>();
	public ArrayList<Node> meeplesRemoveWindow = new ArrayList<Node>();
	public int points;

	public Player(int id) {
		this.id = id;
		this.isAlive = true;
		this.points = 0;	
	}	

	/**
	* Remove a meeple in the meeples array
	**/
	public void giveBackMeeple(Node n) {
		n.meepleOwner = null;
		meeples.remove(n);
		meeplesRemoveWindow.add(n);
		System.out.printf("player %d gets back a meeple, he has now %d meeple(s)\n", id, NUMBER_OF_MEEPLES - meeples.size());
	}
	
	/**
	* Add a meeple in the meeples array
	**/
	public void update(Move m) {
		if (meeples.size() < NUMBER_OF_MEEPLES && m.place != Place.NO_MEEPLE)
			meeples.add(m.tile.nodes[m.place.get()]);
	}

	/**
	* Eject a player of the game
	**/
	public void eject() {
		isAlive = false;
	}
}
