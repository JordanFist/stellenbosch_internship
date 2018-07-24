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
		Player playerTurn = players.firstPlayer(players);
		Tile tile;
		int round = 1;

		/* End of initialisation */
		
		/* Beginning of the game Loop */
		
		while (deck.deckIsEmpty(deck) == false && players.remainingPlayers(players) > 1) {
			System.out.printf("================= ROUND : %d =================%n", round);
			++round;
			tile = deck.drawCard(deck);

			if (round == 60) {
				for (int i = 0; i < board.tiles.size(); ++i) {
					System.out.print(board.tiles.get(i).pos.x);	
					System.out.println(board.tiles.get(i).pos.y);
					System.out.println();
				}
				System.exit(1);
			}
			
			if (board.isPlayable(board, tile) == true) {
				//playerMove.askMove(card, playerTurn, playerMove);
				playerMove = client.clientMove(board, tile, playerTurn);
				
				if (board.validMove(board, playerMove) == true) {
					board.addSetTile(board, playerMove);
					System.out.println();
					System.out.printf("player: %d\n\ncard: %s \t dir: %s \t x: %d \t y: %d \n\n", playerTurn.id, tile.name, playerMove.tile.dir, playerMove.tile.pos.x, playerMove.tile.pos.y);

				} else
					players.ejectPlayer(playerTurn);
				playerTurn = players.computeNextPlayer(players, playerTurn); 	
			} else 
				System.out.printf("%s is unplayable\n", tile.name);
		}


		/* End of the game loop */
		
	}
}
