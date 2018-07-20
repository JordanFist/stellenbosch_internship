package carcassonne;

import carcassonne.core.Deck;
import carcassonne.core.Players;
import carcassonne.core.Move;
import carcassonne.core.setTile;
import carcassonne.core.Client;
import carcassonne.core.Card;
import carcassonne.core.Player;

public class Main {

	public static void main(String[] args) {

		/* Initialisation of the game */
	
		Deck deck = new Deck();
		Players players = new Players(3);
		setTile board = new setTile();
		Client client = new Client();
		Move playerMove;
		Player playerTurn = players.firstPlayer(players);
		Card card;
		int round = 1;

		/* End of initialisation */
		
		/* Beginning of the game Loop */
		
		while (deck.deckIsEmpty(deck) == false && players.remainingPlayers(players) > 1) {
			System.out.printf("================= ROUND : %d =================%n", round);
			++round;
			card = deck.drawCard(deck);

			if (board.isPlayable(board, card) == true) {
				//playerMove.askMove(card, playerTurn, playerMove);
				playerMove = client.clientMove(board, card, playerTurn);
				
				if (board.validMove(board, playerMove) == true) {
					board.addSetTile(board, playerMove);
					System.out.println();
					System.out.printf("player: %d\n\ncard: %s \t dir: %s \t x: %d \t y: %d \n\n", playerTurn.id, card.id, playerMove.dir, playerMove.onto.x, playerMove.onto.y);
				} else
					players.ejectPlayer(playerTurn);
				playerTurn = players.computeNextPlayer(players, playerTurn); 	
			} else 
				System.out.println("This card is unplayable");
		}

		/* End of the game loop */
		
	}
}
