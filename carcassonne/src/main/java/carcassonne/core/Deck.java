package carcassonne.core;
import java.util.Random;
import java.util.ArrayList;

import carcassonne.core.tiles.*;

public class Deck {
	public static final int NUMBER_OF_CARDS = 71;

	public int nextCard;
	public ArrayList<Tile> pile = new ArrayList<Tile>();
	
	public Deck() {
		this.nextCard = 0;
		fillDeck(this);
		shuffle(this);
	}

	public boolean deckIsEmpty(Deck d) {
		return (d.nextCard == NUMBER_OF_CARDS);
	}

	public Tile drawCard(Deck d) {
		if (d.nextCard < NUMBER_OF_CARDS) {
			Tile t = d.pile.get(d.nextCard);
			++d.nextCard;
			return t;
		}
		return null;
	}
	
	public void shuffle(Deck d) {
		int n;
		Tile swap;
		Random rand = new Random(); 
		for (int i = 0; i < NUMBER_OF_CARDS - 1; ++i) {
			n = rand.nextInt(NUMBER_OF_CARDS);	
			
			swap = d.pile.get(n);
			d.pile.remove(n);
			d.pile.add(n, d.pile.get(i));
			d.pile.remove(i);
			d.pile.add(i, swap);
		}
	}

	public void fillDeck(Deck d) {
		int j = 0;
		for (int i = 0; i < 1; ++i) {
			d.pile.add(new CityAllSides());
			d.pile.add(new CityTunnel());
			d.pile.add(new CityThreeShield());
			d.pile.add(new CityThreeRoad());
			d.pile.add(new JunctionFour());
		}
		for (int i = 0; i < 2; ++i) {
			d.pile.add(new MonasteryRoad());
			d.pile.add(new CityTunnelShield());
			d.pile.add(new PlainTwoCities());
			d.pile.add(new PlainCityShield());
			d.pile.add(new PlainCityRoadShield());
			d.pile.add(new CityThreeRoadShield());
		}
		for (int i = 0; i < 3; ++i) {
			d.pile.add(new RoadStraightCity());
			d.pile.add(new PlainTunnel());
			d.pile.add(new RoadTurnRightCity());
			d.pile.add(new RoadTurnLeftCity());
			d.pile.add(new JunctionCity());
			d.pile.add(new PlainCity());
			d.pile.add(new PlainCityRoad());
			d.pile.add(new CityThree());
		}
		for (int i = 0; i < 4; ++i) {
			d.pile.add(new MonasteryAlone());
			d.pile.add(new JunctionThree());
		}
		for (int i = 0; i < 5; ++i) 
			d.pile.add(new CityOneSide());
		for (int i = 0; i < 8; ++i) 
			d.pile.add(new RoadStraight());
		for (int i = 0; i < 9; ++i) 
			d.pile.add(new RoadTurn());
	}
	/*
	public static void main(String[] args) {
		Deck deck = new Deck();
		for (int i = 0; i < NUMBER_OF_CARDS; ++i) {
			Tile t = deck.drawCard(deck);
			System.out.print(i + "  ");
			System.out.println(t.name);
			System.out.println(deck.deckIsEmpty(deck));
		}
		System.out.println(deck.drawCard(deck));
	}*/
}
