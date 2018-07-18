//package carcassonne.core;
import java.util.Random;

public class Deck {
	public static final int numberOfCards = 71;
	public static final int effective[] = {2, 4, 1, 3, 5, 2, 1, 3, 2, 3, 3, 3, 2, 3, 2, 3, 1, 3, 2, 1, 8, 9, 4, 1};

	protected int nextCard;
	protected Card pile[] = new Card[numberOfCards];
	
	public Deck() {
		this.nextCard = 0;
		fillDeck(this);
		shuffle(this);
	}

	public boolean deckIsEmpty(Deck d) {
		return (d.nextCard == numberOfCards);
	}

	public Card drawCard(Deck d) {
		Card res = d.pile[d.nextCard];
		if (d.nextCard < numberOfCards) {
			++d.nextCard;
			return res;
		}
		return null;
	}

	public void shuffle(Deck d) {
		int n;
		Card swap;
		Random rand = new Random(); 
		for (int i = 0; i < numberOfCards; ++i) {
			n = rand.nextInt(numberOfCards);	
			swap = d.pile[n];
			d.pile[n] = d.pile[i];
			d.pile[i] = swap;
		}
	}

	public void fillDeck(Deck d) {
		int i = 0, j = 0;
		for (cardName name : cardName.values()) {
			for (int k = 0; k < effective[j]; ++k) {
				d.pile[i] = new Card(j, name);
				++i;
			}
			++j;
		}
	}
	/*
	public static void main(String[] args) {
		Deck deck = new Deck();
		for (int i = 0; i <numberOfCards; ++i) {
			System.out.println(deck.drawCard(deck).name);
			System.out.println(deck.deckIsEmpty(deck));
		}
	}*/
}
