package carcassonne.core;
import java.util.Random;

public class Deck {
	protected int nextCard;
	protected int pile[] = new int[Data.numberOfCards];
	
	public Deck() {
		this.nextCard = 0;
		fillDeck(this);
		shuffle(this);
	}

	public boolean deckIsEmpty(Deck d) {
		return (d.nextCard == Data.numberOfCards);
	}

	public int drawCard(Deck d) {
		int res = d.pile[d.nextCard];
		if (d.nextCard < Data.numberOfCards) {
			++d.nextCard;
			return res;
		}
		return Data.error;
	}

	public void shuffle(Deck d) {
		int swap,n;
		Random rand = new Random(); 
		for (int i = 0; i < Data.numberOfCards; ++i) {
			n = rand.nextInt(Data.numberOfCards);	
			swap = d.pile[n];
			d.pile[n] = d.pile[i];
			d.pile[i] = swap;
		}
	}

	public void fillDeck(Deck d) {
		int k = 0;
		for (int i = 0; i < Data.cardId.size(); ++i) {
			for (int j = 0; j < Data.effective[i]; ++j) {
				d.pile[k] = i;
				++k;
			}
		}
	}
	/*
	public static void main(String[] args) {
		Data data = new Data();
		Deck d = new Deck();
		for (int i : d.pile)
			System.out.println(i);
	}	
	*/
}
