//package carcassonne.core;

public class Players {
	protected int numberOfPlayers;
	protected Player players[];

	public Players(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
		players = new Player[numberOfPlayers];
		for (int i = 0; i < numberOfPlayers; ++i) 
			players[i] = new Player(i);
	}

	public int remainingPlayers(Players p) {
		int res = 0;
		for (int i = 0; i < p.numberOfPlayers; ++i) {
			if (p.players[i].isAlive == true)
				++res;
		}
		return res;	
	}

	public int computeNextPlayer(Players players, Player player) {
		int id = player.id;
		if (id == players.numberOfPlayers - 1)
			id = 0;
		else
			++id;
		while (players.players[id].isAlive == false) {
			if (id == players.numberOfPlayers - 1)
				id = 0;
			else
				++id;
		}	
		return id;
	}

	public void ejectPlayer(Player p) {
		p.isAlive = false;
	}
	/*
	public static void main(String[] args) {
		Players players = new Players(3);
		System.out.println(players.remainingPlayers(players));
		players.ejectPlayer(players.players[1]);
		System.out.println(players.remainingPlayers(players));
		System.out.println(players.computeNextPlayer(players, players.players[0]));
	}*/
}
