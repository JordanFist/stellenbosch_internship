package carcassonne.core;

public class Players {
	public int numberOfPlayers;
	public Player players[];

	public Players(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
		players = new Player[numberOfPlayers];
		for (int i = 0; i < numberOfPlayers; ++i) 
			players[i] = new Player(i);
	}
	
	public Player firstPlayer(Players p) {
		return (p.players[0]);
	}

	public int remainingPlayers(Players p) {
		int res = 0;
		for (int i = 0; i < p.numberOfPlayers; ++i) {
			if (p.players[i].isAlive == true)
				++res;
		}
		return res;	
	}

	public Player computeNextPlayer(Players players, Player player) {
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
		return players.players[id];
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
