//package carcassonne.core;

public class Node {
	public static final int connexionsPerSide = 3;

	protected String landType;
	protected Player meepleOwner;
	//protected Node neighbourNodes[] = new Node[connexionsPerSide];

	public Node(Card card, int i) {
		this.meepleOwner = null;
		this.landType = Landscape.cardsContent[card.id][i];
	}

	
}
