package carcassonne.core;

class Player {
	protected int id;
	protected boolean isAlive;
	protected int numberOfMeeples;
	protected int points;

	public Player(int idPlayer) {
		this.id = idPlayer;
		this.isAlive = true;
		this.numberOfMeeples = Data.startingNumberOfMeeples;
		this.points = 0;	
	}	
}

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
}
