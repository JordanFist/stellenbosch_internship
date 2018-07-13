package carcassonne;
import carcassonne.core.Data;
import carcassonne.core.Deck;
import carcassonne.core.Players;

public class Main {
	public static void main(String[] args) {
		Data data = new Data();
		Deck deck = new Deck();
		Players players = new Players(2);
		
		int round = 1;
		int card;
		
		/* Game Loop */
		System.out.println(players.remainingPlayers(players));
		//while (deck.deckIsEmpty(deck) == false && players.remainingPlayers(players) > 1) {
			//System.out.println("test");
			//card = deck.drawCard(deck);
			/*if (isPlayable() == true) {
				ask player his move
				if (move valid) {
					do
				else
					eject player
				compute next player				
				}
			}
			System.out.println(This card is unplayable);
			*/
		//}
		
	}
}
