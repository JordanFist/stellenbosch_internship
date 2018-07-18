//package carcassonne.core;
import java.util.ArrayList;

public class setTile {
	protected static ArrayList<Tile> tiles = new ArrayList<Tile>();

	public setTile() {
		Card firstCard = new Card(3, cardName.CARD_ROAD_STRAIGHT_CITY);
		Direction firstDir = new Direction(0, directionName.NORTH);
		Position firstPos = new Position(0, 0);
		Tile firstTile = new Tile(firstCard, firstDir, firstPos);

		tiles.add(firstTile); 
	}

	public Tile findTile(setTile s, Position pos) {
		for (Tile t : s.tiles) {
			if (t.pos.x == pos.x && t.pos.y == pos.y)	
				return t;
		}
		return null;
	}

	public Position neighbourPosition(Position p, Direction dir) {
		Position pos = new Position(p.x, p.y);
		if (dir.name == directionName.NORTH) 
   			++pos.y;
  		if (dir.name == directionName.WEST) 
   			--pos.x;
  		if (dir.name == directionName.SOUTH) 
   			--pos.y;
  		if (dir.name == directionName.EAST) 
  		 	++pos.x;
 		return pos;
	}

	public Tile computeNeighbour(setTile s, Tile t, Direction dir) {
		Position pos = neighbourPosition(t.pos, dir);
		return findTile(s, pos);
	}

	public void tileConnection(setTile s, Tile t) {
		Direction dir = new Direction(0, directionName.NORTH);
		for (int i = 0; i < Direction.Sides; ++i) {
			t.neighbourTiles[i] = computeNeighbour(s, t, dir); 	
			++dir.id;
		}
	}

	public Tile neighbourTile(Tile t, Direction dir) {
		return t.neighbourTiles[dir.id];
	}

	public void addSetTile(setTile s, Tile t) {
		tiles.add(t);
		//connection between neighbour cards
		//connection between nodes
	}

	public void removeSetTile(setTile s, Tile t) {
		
	}
	
	public boolean matchCard(setTile s, Tile t) {
		//addSetTile(s, t);
		Direction dir = new Direction(0, directionName.NORTH);
		int count = 0;
		for (int i = 0; i < Direction.Sides; ++i) {
			if (t.matchSide(t, neighbourTile(t, dir), dir) == true) {
				++count;
				++dir.id;
			}
		}
		if (count == Direction.Sides)
			return true;
		return false;
	}

	public boolean isConnectable(setTile s, Tile t) {
		Direction dir = new Direction(1, directionName.WEST);
		for (int i = 0; i < Direction.Sides; ++i) {
			if (matchCard(s, t) == true) 
				return true;	
		t.rotation(t, dir);
		}
		return false;
	}

	public boolean isPlayable(setTile s, Card card) {
		Position pos = new Position(0, 0);
		Direction dir = new Direction(0, directionName.NORTH);
		Direction dir2 = new Direction(0, directionName.NORTH);
		Tile t = new Tile(card, dir, pos);
		for (int i = 0; i < s.tiles.size(); ++i) {
			for (int j = 0; j < Direction.Sides; ++j) {
			t.pos = neighbourPosition(s.tiles.get(i).pos, dir2);
			++dir2.id; 
			dir2.id %= Direction.Sides;
				if (t.isEmptyTile(neighbourTile(s.tiles.get(i), dir2)) == true && isConnectable(s, t) == true)
					return true;
			}
		}
		return false;
	}

	//validCardMove(setTile s, ) {}

	//validMeepleMove(setTile s, ) {}

	//validMove(setTile s, ) {}

	public static void main (String[] args) {
		
	}
}
