package carcassonne.core;
import java.util.ArrayList;

import carcassonne.core.tiles.RoadStraightCity;

public class setTile {
	public static ArrayList<Tile> tiles = new ArrayList<Tile>();

	public setTile() {
		RoadStraightCity firstTile = new RoadStraightCity();

		tiles.add(firstTile); 
	}

	public Tile findTile(setTile s, Position pos) {
		for (Tile t : s.tiles) {
			if (t.pos.x == pos.x && t.pos.y == pos.y)	
				return t;
		}
		return null;
	}

	public Position neighbourPosition(Position p, directionId dir) {
		Position pos = new Position(p.x, p.y);
		if (dir == directionId.NORTH) 
   			++pos.y;
  		if (dir == directionId.WEST) 
   			--pos.x;
  		if (dir == directionId.SOUTH) 
   			--pos.y;
  		if (dir == directionId.EAST) 
  		 	++pos.x;
 		return pos;
	}

	public Tile computeNeighbour(setTile s, Tile t, directionId dir) {
		Position pos = neighbourPosition(t.pos, dir);
		return findTile(s, pos);
	}

	public Tile neighbourTile(Tile t, directionId dir) {
		return (t.neighbourTiles[dir.toInt()]);
	}
	
	public void addSetTile(setTile s, Move m) {
		Tile t = m.tile;
		s.tiles.add(t);
		for (directionId dir : directionId.values()) 
			t.tileConnection(t, computeNeighbour(s, t, dir), dir);
		//TODO
		//connection between nodes
	}

	public void removeSetTile(setTile s, Tile t) {
		for (directionId dir : directionId.values()) 
			t.tileDisconnection(t, computeNeighbour(s, t, dir), dir);
		s.tiles.remove(t);
		//TODO
		//disconnection between nodes
	}

	public boolean matchCard(setTile s, Tile t) {
		int count = 0;
		for (directionId dir : directionId.values()) {
			if (t.matchSide(t, computeNeighbour(s, t, dir), dir) == true) {
				++count;
			}
		}
		if (count == t.SIDES)
			return true;
		return false;
	}
	
	public boolean isConnectable(setTile s, Tile t) {
		directionId rot = directionId.WEST;
		for (directionId dir : directionId.values()) {
			if (matchCard(s, t) == true) {
				t.dir = dir;
				return true;	
			}
		t.rotation(t, rot);
		}
		return false;
	}
	
	public boolean isPlayable(setTile s, Tile t) {   
		for (int i = 0; i < s.tiles.size(); ++i) {
			for (directionId dir : directionId.values()) {
				t.pos = neighbourPosition(s.tiles.get(i).pos, dir);
				if (t.isEmptyTile(computeNeighbour(s, s.tiles.get(i), dir)) == true && isConnectable(s, t) == true) {
					//t.reInitTile(t);
					return true;
				}	
			}
		}
		return false;
	}

	public boolean validCardMove(setTile s, Move m) {
		if (matchCard(s, m.tile) == true)
			return true;
		return false;
	}

	public boolean validMeepleMove(setTile s, Move m) {
		//TODO
		return true;
	}

	public boolean validMove(setTile s, Move m) { 
		if (validCardMove(s, m) == true && validMeepleMove(s, m) == true) 
			return true;
		return false;
	}
	/*
	public static void main (String[] args) {
		setTile s = new setTile();
		MonasteryRoad c = new MonasteryRoad();
		c.pos = new Position(1, 0);
		c.dir = directionId.NORTH;
		
		Move m = new Move(0, c, Place.NO_MEEPLE);
		System.out.println(s.validMove(s, m));

	}*/
}
