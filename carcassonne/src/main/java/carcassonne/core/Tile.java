package carcassonne.core;

public class Tile {
	public static final int NUMBER_OF_DIRECTIONS = 13;
	public static final int SIDES = 4;
	
	public String name;
	public directionId dir = directionId.NORTH;
	public Position pos = new Position(0, 0);
	public Tile neighbourTiles[] = new Tile[SIDES];
	public Node nodes[] = new Node[NUMBER_OF_DIRECTIONS];

	public Tile(directionId dir, Position Pos) {
		this.dir = dir;
		this.pos = pos;
		rotation(this, dir);
	}
	
	public boolean isEmptyTile(Tile t) {
		return (t == null);
	}
	
	public void rotation(Tile t, directionId dir) {
		Node swap;
		for (int i = 0; i < 3 * dir.toInt(); ++i) {
			swap = t.nodes[NUMBER_OF_DIRECTIONS - 2];
			for (int j = NUMBER_OF_DIRECTIONS - 2; j > 0; --j)
				t.nodes[j] = t.nodes[j - 1];
			t.nodes[0] = swap; 	
		}
	}

	public String getFace(Tile t, directionId dir) {
		return t.nodes[3 * dir.toInt() + 1].landType;
	}

	public String getOppositeFace(Tile t, directionId dir) {
		return t.nodes[(3 * dir.toInt() + 7) % (NUMBER_OF_DIRECTIONS - 1)].landType;
	}
	
	// dir : position of t2 compared to t1
	public boolean matchSide(Tile t1, Tile t2, directionId dir) {
		if (t2 == null)
			return true;
		String face1 = getFace(t1, dir);
		String face2 = getOppositeFace(t2, dir);
		return (face1.equals(face2));
	}

	public void tileConnection(Tile t1, Tile t2, directionId dir) {
		if (t2 != null) {
			t1.neighbourTiles[dir.toInt()] = t2;
			t2.neighbourTiles[(dir.toInt() + 2) % SIDES] = t1;
		}
	}

	public void tileDisconnection(Tile t1, Tile t2, directionId dir) {
		if (t2 != null) {
			t1.neighbourTiles[dir.toInt()] = null;
			t2.neighbourTiles[(dir.toInt() + 2) % SIDES] = null;
		}
	}

	public void reInitTile(Tile t) {
		if (t.dir == directionId.WEST)
			rotation(t, directionId.EAST);
		if (t.dir == directionId.SOUTH)
			rotation(t, directionId.SOUTH);
		if (t.dir == directionId.EAST)
			rotation(t, directionId.WEST);	
		t.dir = directionId.NORTH;
		t.pos.x = 0; t.pos.y = 0;
	}

	/*
	public static void main(String[] args) {
		
		Card card = new Card(cardId.CARD_MONASTERY_ROAD);
		Position pos = new Position(0, 0);	
		directionId dir = directionId.NORTH;
		Tile tile = new Tile(card, dir, pos);
		
		Position pos2 = new Position(-1, 0);
		directionId dir2 = directionId.NORTH;
		Tile tile2 = new Tile(card, dir2, pos2);
		

		directionId dir3 = directionId.WEST;
		tile.rotation(tile2, dir3);
		tile.rotation(tile2, dir3);

		//System.out.println(tile.matchSide(tile, tile2, dir3));
		
		for (int i = 0 ; i < 13; ++i) {
			System.out.println(tile.nodes[i].landType);
			//System.out.println(tile.neighbourTiles[i]);
		}
		System.out.println();
		for (int i = 0 ; i < 13; ++i) {
			System.out.println(tile2.nodes[i].landType);
		}

		tile.tileConnection(tile, tile2, dir3);
		tile.tileDisconnection(tile, tile2, dir3);
		
		for (int i = 0; i < 4; ++i) {
			System.out.println(tile.neighbourTiles[i]);
			System.out.println(tile2.neighbourTiles[i]);
		}
	}*/
}

