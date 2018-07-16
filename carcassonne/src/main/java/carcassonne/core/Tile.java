//package carcassonne.core;

class Position {
	protected int x;
	protected int y;
}

public class Tile {
	protected int card;
	protected int direction;
	protected Position pos;
	protected Node nodes[] = new Node[Data.place.size() - 1];

	public Tile(int card, int dir, Position pos) {
		this.card = card;
		this.direction = dir;
		this.pos = pos;
	}
	
	public void rotation(Tile t, int dir) {
		Node swap = new Node();
		for (int i = 0; i < 3 * dir; ++i) {
			swap = t.nodes[Data.place.size() - 3];
			for (int j = Data.place.size() - 3; j > 0; --j)
				t.nodes[j] = t.nodes[j - 1];
			t.nodes[0] = swap; 
				
		}
	}

	public int getFace(Tile t, int dir) {
		return (t.nodes[3 * dir + 1].landType);
	}

	public int getOppositeFace(Tile t, int dir) {
		return (getFace(t, (dir + 2) % Data.direction.size()));		
	}
	
	// dir : the place of t2 in function of t1
	public boolean matchSide(Tile t1, Tile t2, int dir) {
		if (t2 == null)
			return true;
		int face1 = getFace(t1, dir);
		int face2 = getOppositeFace(t2, dir);
		return (face1 == face2);
	}

	public static void main(String[] args) {
		Data data = new Data();
		Position pos = new Position();
		pos.x = 0;
		pos.y = 0;
		Tile t = new Tile(0, 0, pos);
		System.out.println(t.getFace(t, 0));
	}
}
