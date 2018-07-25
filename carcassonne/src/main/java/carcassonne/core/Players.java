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
	/*
	public static void main(String[] args) {
		Players players = new Players(3);
		System.out.println(players.remaining());
		players.players.get(1).eject();
		System.out.println(players.remaining());
		System.out.println(players.computeNextPlayer(players.players.get(0)).id);
	}*/
}
