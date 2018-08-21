package carcassonne.core;

import java.util.Scanner;

public class Move {
	public Player player;
	public Tile tile;
	public Place place;

	public Move (Player player, Tile tile, Place place) {
		this.player = player;
		this.tile = tile;
		this.place = place;
	}

	public boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	
	public Move askMove(Player p, Deck deck) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Player " + p.id + " picks a card among remaining cards (only the number)");
		String card = scan.next();
		while (isInt(card) == false || Integer.parseInt(card) < 0 || Integer.parseInt(card) > 23) {
			System.out.println("This is not an integer between 0 and 23");
			System.out.println("Player " + p.id + " picks a card in remaining cards (only the number)");
			card = scan.next();
		}
		Tile t = deck.pickCard(Integer.parseInt(card));

		System.out.println("Where do you want to put this card\nGive x");
		String x = scan.next();
		while (isInt(x) == false) {
			System.out.println("This is not an integer\nGive x");
			x = scan.next();
		}

		System.out.println("Give y");
		String y = scan.next();
		while (isInt(y) == false) {
			System.out.println("This is not an integer\nGive y");
			y = scan.next();
		}
		Position pos = new Position(Integer.parseInt(x), Integer.parseInt(y));		
		
		System.out.println("Pick a direction for your card (example: W to rotate 90 degree on the left)");
		String direction = scan.next();

		if (t != null) {
			t.pos = pos;
			t.rotation(pickDirection(direction));
		}

		System.out.println("Where do you want to put your meeple ? (example: NE for NORTH_EAST - NM for no NO_MEEPLE - C for CENTER");
		String place = scan.next();

		return new Move(p, t, pickPlace(place));
	}

	public Direction pickDirection(String s) {
		if (s.equals("N") || s.equals("n"))
			return Direction.NORTH;
		if (s.equals("W") || s.equals("w"))
			return Direction.WEST;
		if (s.equals("S") || s.equals("s"))
			return Direction.SOUTH;
		if (s.equals("E") || s.equals("e"))
			return Direction.EAST;
		else {
			System.out.println("You have given a wrong direction - Default Direction NORTH applied");
			return Direction.NORTH;
 		}
	}

	public Place pickPlace(String s) {
		if (s.equals("NE") || s.equals("ne"))
			return Place.NORTH_EAST;
		if (s.equals("N") || s.equals("n"))
			return Place.NORTH;
		if (s.equals("NW") || s.equals("nw"))
			return Place.NORTH_WEST;


		if (s.equals("WN") || s.equals("wn"))
			return Place.WEST_NORTH;
		if (s.equals("W") || s.equals("w"))
			return Place.WEST;
		if (s.equals("WS") || s.equals("ws"))
			return Place.WEST_SOUTH;

		if (s.equals("SW") || s.equals("sw"))
			return Place.SOUTH_WEST;
			
		if (s.equals("S") || s.equals("s"))
			return Place.SOUTH;
			
		if (s.equals("SE") || s.equals("se"))
			return Place.SOUTH_EAST;


		if (s.equals("ES") || s.equals("es"))
			return Place.EAST_SOUTH;
		if (s.equals("E") || s.equals("e"))
			return Place.EAST;
		if (s.equals("EN") || s.equals("en"))
			return Place.EAST_NORTH;
		if (s.equals("C") || s.equals("c"))
			return Place.CENTER;
		if (s.equals("NM") || s.equals("nm"))
			return Place.NO_MEEPLE;
		else {
			System.out.println("You have given a wrong place - Default Place NO_MEEPLE applied");
			return Place.NO_MEEPLE;
		}
	}
}
