//package carcassonne.core;

public class Tile {
	public static final int numberOfDirections = 13;
	
	protected Card card;
	protected Direction dir;
	protected Position pos;
	protected Tile neighbourTiles[] = new Tile[Direction.Sides];
	protected Node nodes[] = new Node[numberOfDirections];

	public Tile(Card card, Direction dir, Position pos) {
		this.card = card;
		this.dir = dir;
		this.pos = pos;
		for (int i = 0; i < numberOfDirections; ++i)
			this.nodes[i] = new Node(card, i);
		//init neighbourNodes
		//init neighbourTiles
		rotation(this, dir);
	}
	
	public void rotation(Tile t, Direction dir) {
		Node swap;
		for (int i = 0; i < 3 * dir.id; ++i) {
			swap = t.nodes[numberOfDirections - 2];
			for (int j = numberOfDirections - 2; j > 0; --j)
				t.nodes[j] = t.nodes[j - 1];
			t.nodes[0] = swap; 	
		}
	}

	public String getFace(Tile t, Direction dir) {
		return (t.nodes[3 * dir.id + 1].landType);
	}
	
	public String getOppositeFace(Tile t, Direction dir) {
		return (t.nodes[(3 * dir.id + 7) % (numberOfDirections - 1)].landType);	
	}
	
	// dir : the place of t2 in function of t1
	public boolean matchSide(Tile t1, Tile t2, Direction dir) {
		if (t2 == null)
			return true;
		String face1 = getFace(t1, dir);
		String face2 = getOppositeFace(t2, dir);
		return (face1.equals(face2));
	}
	/*
	public static void main(String[] args) {
		Card card = new Card(0, cardName.CARD_MONASTERY_ROAD);
		Position pos = new Position(0, 0);	
		Direction dir = new Direction(0, directionName.NORTH);
		Tile tile = new Tile(card, dir, pos);
		
		Position pos2 = new Position(-1, 0);
		Direction dir2 = new Direction(1, directionName.WEST);
		Tile tile2 = new Tile(card, dir, pos2);
		tile.rotation(tile, dir2);

		System.out.println(tile.matchSide(tile, tile2, dir2));
	
		for (int i = 0 ; i < 13; ++i) {
			System.out.println(tile.nodes[i].landType);
			//System.out.println(tile.neighbourTiles[i]);
		}
		System.out.println();
		for (int i = 0 ; i < 13; ++i) {
			System.out.println(tile2.nodes[i].landType);
		}
		
		
	}*/
}
