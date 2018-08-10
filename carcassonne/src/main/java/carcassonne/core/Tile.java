package carcassonne.core;

public class Tile {
	public int id;
	public String name;
	public boolean shield = false;
	public Direction dir = Direction.NORTH;
	public Position pos = new Position(0, 0);
	public Tile neighbourTiles[] = new Tile[Direction.SIDES];
	public Node nodes[] = new Node[Direction.NUMBER_OF_DIRECTIONS];

	public Tile(Direction dir, Position Pos) {
		this.dir = dir;
		this.pos = pos;
		rotation(dir);
	}
	
	/**
	* Return true if the tile is empty(=null)
	**/
	public boolean isEmptyTile(Tile t) {
		return (t == null);
	}

	/**
	* Return the neighbour tile according to dir using neighbourTiles array
	**/
	public Tile neighbour(Direction dir) {
		return (neighbourTiles[dir.get()]);
	}
	
	/**
	* Make a rotation of the card namely nodes array
	**/
	public void rotation(Direction dir) {
		Node swap;
		for (int i = 0; i < 3 * dir.get(); ++i) {
			swap = nodes[dir.NUMBER_OF_DIRECTIONS - 2];
			for (int j = dir.NUMBER_OF_DIRECTIONS - 2; j > 0; --j)
				nodes[j] = nodes[j - 1];
			nodes[0] = swap; 	
		}
	}
	
	/**
	* Return the type of the central face (PLAIN, ROAD, ...) according to dir
	**/
	public String getFace(Direction dir) {
		return nodes[dir.getExtend()].landType;
	}

	/**
	* Return the type of the opposite central face according to dir
	**/
	public String getOppositeFace(Direction dir) {
		return nodes[dir.getExtendOpposite()].landType;
	}
	
	/**
	* Return true if 2 tiles can be nearby according to dir
	* dir = position of t according to this
	**/
	public boolean matchSide(Tile t, Direction dir) {
		if (t == null)
			return true;
		String face1 = getFace(dir);
		String face2 = t.getOppositeFace(dir);
		return (face1.equals(face2));
	}

	/**
	* Connect 2 tiles according to dir
	**/
	public void connection(Tile t, Direction dir) {
		if (t != null) {
			neighbourTiles[dir.get()] = t;
			t.neighbourTiles[dir.getOpposite()] = this;
		}
	}

	/**
	* Disconnect 2 tiles
	**/
	public void disconnection(Tile t, Direction dir) {
		if (t != null) {
			neighbourTiles[dir.get()] = null;
			t.neighbourTiles[dir.getOpposite()] = null;
		}
	}

	/**
	* Reinit a tile namely as defined in tiles directory
	**/
	public void reInit() {
		if (dir == Direction.WEST)
			rotation(Direction.EAST);
		if (dir == Direction.SOUTH)
			rotation(Direction.SOUTH);
		if (dir == Direction.EAST)
			rotation(Direction.WEST);	
		dir = Direction.NORTH;
		pos.x = 0; pos.y = 0;
	}
}

