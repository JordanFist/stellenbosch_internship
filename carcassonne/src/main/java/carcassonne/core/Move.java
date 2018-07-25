package carcassonne.core;
import java.util.Scanner;

public class Move {
	public Player player;
	public Tile tile;
	public placeId place;

	public Move (Player player, Tile tile, placeId place) {
		this.player = player;
		this.tile = tile;
		this.place = place;
	}
	/*
	public void askMove(Card card, Player p, Move m) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Turn of player %d:", p.id)
		m.idPlayer = p.id;
		m.card = card;
		// clic with the mouse to choose where put the card on a map


		System.out.println()
	}*/
}
