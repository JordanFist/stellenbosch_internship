package carcassonne.core;

class Player {
	protected int idPlayer;
	protected boolean playerIsAlive;
	protected int numberOfMeeples;
	protected int points;

	public Player(int idPlayer) {
		this.idPlayer = idPlayer;
		this.playerIsAlive = true;
		this.numberOfMeeples = Data.startingNumberOfMeeples;
		this.points = 0;	
	}	
}

public class Players {
	int numberOfPlayers;
	

	public Players(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	protected Player players[] = new Player[numberOfPlayers];

	public int remainingPlayers(Players p) {
		int res = 0;
		for (int i = 0; i < p.numberOfPlayers; ++i) {
			if (p.players[i].playerIsAlive == true)
				++res;
		}
		return res;	
	}

	public int computeNextPlayer(Players players, Player player) {
		return 0;
		//TODO
	}

	public void ejectPlayer(Player p) {
		p.playerIsAlive = false;
	}
}
