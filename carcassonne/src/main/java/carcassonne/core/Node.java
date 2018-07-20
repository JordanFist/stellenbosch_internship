package carcassonne.core;

public class Node {
	public static final int CONNEXIONS_PER_SIDE = 3;

	public String landType;
	public Player meepleOwner;
	//public Node neighbourNodes[] = new Node[CONNEXIONS_PER_SIDE];   Array?

	public Node(Card card, int i) {
		this.meepleOwner = null;
		this.landType = Landscape.CARDS_CONTENT[card.id.toInt()][i];
	}
}
