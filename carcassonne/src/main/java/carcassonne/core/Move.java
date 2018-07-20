package carcassonne.core;
import java.util.Scanner;

public class Move {
	public int idPlayer;
	public Card card;
	public Position onto;
	public directionId dir;
	public Place place;

	public Move (int idPlayer, Card card, Position onto, directionId dir, Place place) {
		this.idPlayer = idPlayer;
		this.card = card;
		this.onto = onto;
		this.dir = dir;
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
