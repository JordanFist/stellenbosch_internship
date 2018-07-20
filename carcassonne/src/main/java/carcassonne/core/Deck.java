package carcassonne.core;
import java.util.Random;

public class Deck {
	public static final int NUMBER_OF_CARDS = 71;
	public static final int EFFECTIVE[] = {2, 4, 1, 3, 5, 2, 1, 3, 2, 3, 3, 3, 2, 3, 2, 3, 1, 3, 2, 1, 8, 9, 4, 1};

	public int nextCard;
	public Card pile[] = new Card[NUMBER_OF_CARDS];
	
	public Deck() {
		this.nextCard = 0;
		fillDeck(this);
		shuffle(this);
	}

	public boolean deckIsEmpty(Deck d) {
		return (d.nextCard == NUMBER_OF_CARDS);
	}

	public Card drawCard(Deck d) {
		if (d.nextCard < NUMBER_OF_CARDS) {
			Card res = d.pile[d.nextCard];
			++d.nextCard;
			return res;
		}
		return null;
	}

	public void shuffle(Deck d) {
		int n;
		Card swap;
		Random rand = new Random(); 
		for (int i = 0; i < NUMBER_OF_CARDS; ++i) {
			n = rand.nextInt(NUMBER_OF_CARDS);	
			swap = d.pile[n];
			d.pile[n] = d.pile[i];
			d.pile[i] = swap;
		}
	}

	public void fillDeck(Deck d) {
		int i = 0, j = 0;
		for (cardId id : cardId.values()) {
			for (int k = 0; k < EFFECTIVE[j]; ++k) {
				d.pile[i] = new Card(id);
				++i;
			}
			++j;
		}
	}
	/*
	public static void main(String[] args) {
		Deck deck = new Deck();
		for (int i = 0; i < NUMBER_OF_CARDS; ++i) {
			System.out.print(i + "  ");
			System.out.println(deck.drawCard(deck).id);
			System.out.println(deck.deckIsEmpty(deck));
		}
		System.out.println(deck.drawCard(deck));
	}*/
}
