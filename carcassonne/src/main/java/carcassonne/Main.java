package carcassonne;

import carcassonne.core.*;

public class Main {
	/*
	public static void display(Tile t) {
		for (int i = 0; i < 4; ++i) {
			if (t.neighbourTiles[i] != null)
				System.out.println(t.neighbourTiles[i].card.id);
			else 
				System.out.println("null");
		}
	}*/

	public static void main(String[] args) {

		/* Initialisation of the game */
	
		Deck deck = new Deck();
		Players players = new Players(3);
		setTile board = new setTile();
		Client client = new Client();
		Move playerMove;
		Player playerTurn = players.first();
		Tile tile;
		int round = 1;

		/* End of initialisation */
		
		/* Beginning of the game Loop */
		
		while (deck.isEmpty() == false && players.remaining() > 1) {
			System.out.printf("================= ROUND : %d =================%n", round);
			++round;
			tile = deck.drawCard();
			
			if (board.isPlayable(tile) == true) {
				//playerMove.askMove(card, playerTurn, playerMove);
				playerMove = client.clientMove(board, tile, playerTurn);
				
				if (board.validMove(playerMove) == true) {
					board.addMeeple(playerMove);
					board.addSetTile(playerMove.tile);
					System.out.println();
					System.out.printf("player: %d\n\ncard: %s \t dir: %s \t x: %d \t y: %d \t place: %s \n\n", playerTurn.id, tile.name, playerMove.tile.dir, playerMove.tile.pos.x, playerMove.tile.pos.y, playerMove.place);

				} else
					playerTurn.eject();
				playerTurn = players.computeNext(playerTurn); 	
			} else 
				System.out.printf("%s is unplayable\n", tile.name);
		}


		/* End of the game loop */
		
	}
}
