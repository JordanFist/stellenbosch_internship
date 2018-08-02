package carcassonne.core;

import java.util.ArrayList;

public class Players {
	int numberOfPlayers;
	public ArrayList<Player> players = new ArrayList<Player>();

	public Players(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
		for (int i = 0; i < numberOfPlayers; ++i) 
			players.add(new Player(i));
	}
	
	/**
	* Return the first player at the beginning of the game
	**/
	public Player first() {
		return (players.get(0));
	}

	public int remaining() {
		int res = 0;
		for (int i = 0; i < players.size(); ++i) {
			if (players.get(i).isAlive == true)
				++res;
		}
		return res;	
	}

	/**
	* Return the next player who have to play
	**/
	public Player computeNext(Player player) {
		int id = player.id;
		if (id == numberOfPlayers - 1)
			id = 0;
		else
			++id;
		while (players.get(id).isAlive == false) {
			if (id == numberOfPlayers - 1)
				id = 0;
			else
				++id;
		}
		return players.get(id);
	}
	
	/**
	* Display the players score
	**/
	public void displayScore() {
		System.out.println("==============================================\n");
		for (Player p : players) {
			if (p.isAlive == true)
				System.out.printf("player %d scored %d points (alive)\n\n", p.id, p.points);
			else
				System.out.printf("player %d scored %d points (ejected)\n\n", p.id, p.points);
		}
	}
}
