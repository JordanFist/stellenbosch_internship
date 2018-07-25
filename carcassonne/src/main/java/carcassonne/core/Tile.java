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
		rotation(dir);
	}
	
	public boolean isEmptyTile(Tile t) {
		return (t == null);
	}

	public Tile neighbour(directionId dir) {
		return (neighbourTiles[dir.toInt()]);
	}
	
	public void rotation(directionId dir) {
		Node swap;
		for (int i = 0; i < 3 * dir.toInt(); ++i) {
			swap = nodes[NUMBER_OF_DIRECTIONS - 2];
			for (int j = NUMBER_OF_DIRECTIONS - 2; j > 0; --j)
				nodes[j] = nodes[j - 1];
			nodes[0] = swap; 	
		}
	}

	public String getFace(directionId dir) {
		return nodes[Direction.getDirection(dir)].landType;
	}

	public String getOppositeFace(directionId dir) {
		return nodes[Direction.getOppositeDirection(dir)].landType;
	}
	
	// dir : position of t compared to this
	public boolean matchSide(Tile t, directionId dir) {
		if (t == null)
			return true;
		String face1 = getFace(dir);
		String face2 = t.getOppositeFace(dir);
		return (face1.equals(face2));
	}

	public void connection(Tile t, directionId dir) {
		if (t != null) {
			neighbourTiles[dir.toInt()] = t;
			t.neighbourTiles[(dir.toInt() + 2) % SIDES] = this;
		}
	}

	public void disconnection(Tile t, directionId dir) {
		if (t != null) {
			neighbourTiles[dir.toInt()] = null;
			t.neighbourTiles[(dir.toInt() + 2) % SIDES] = null;
		}
	}

	public void reInit() {
		if (dir == directionId.WEST)
			rotation(directionId.EAST);
		if (dir == directionId.SOUTH)
			rotation(directionId.SOUTH);
		if (dir == directionId.EAST)
			rotation(directionId.WEST);	
		dir = directionId.NORTH;
		pos.x = 0; pos.y = 0;
	}
	/*
	public static void main(String[] args) {
		
		MonasteryRoad t1 = new MonasteryRoad();
		MonasteryRoad t2 = new MonasteryRoad();
		t2.pos = new Position(-1, 0);

		directionId dir = directionId.WEST;
		t2.rotation(dir);

		System.out.println(t1.matchSide(t2, dir));
		
		for (int i = 0 ; i < 13; ++i) {
			System.out.println(t1.nodes[i].landType);
			//System.out.println(tile.neighbourTiles[i]);
		}

		System.out.println();

		for (int i = 0 ; i < 13; ++i) {
			System.out.println(t2.nodes[i].landType);
		}

		System.out.println();
		t1.connection(t2, dir);

		for (int i = 0; i < 4; ++i) {
			System.out.println(t1.neighbourTiles[i]);
			System.out.println(t2.neighbourTiles[i]);
		}
		
		t1.disconnection(t2, dir);
		System.out.println();
		
		for (int i = 0; i < 4; ++i) {
			System.out.println(t1.neighbourTiles[i]);
			System.out.println(t2.neighbourTiles[i]);
		}
	}*/
}

