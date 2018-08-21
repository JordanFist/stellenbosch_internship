package carcassonne.core;

import java.util.ArrayList;

import carcassonne.ui.Window;
import carcassonne.core.tiles.RoadStraightCity;

public class setTile {
	public static ArrayList<Tile> tiles = new ArrayList<Tile>();

	public setTile() {
		tiles.add(new RoadStraightCity()); 
	}

	/**
	* Find a tile in tiles array according to pos
	**/
	public Tile findTile(Position pos) {
		for (Tile t : this.tiles) {
			if (t.pos.x == pos.x && t.pos.y == pos.y)	
				return t;
		}
		return null;
	}
	/**
	* Return the neighbour tile according to dir using tiles array
	**/
	public Tile computeNeighbour(Tile t, Direction dir) {
		Position pos = t.pos.neighbourPosition(dir);
		return findTile(pos);
	}
	
	/**
	* Add a tile in tiles array and make connection between cards and nodes
	**/
	public void addSetTile(Tile t) {
		tiles.add(t);
		for (Direction dir : Direction.values()) {
			t.connection(computeNeighbour(t, dir), dir);
			for (int j = Node.CONNEXIONS_PER_SIDE * dir.get(); j < Node.CONNEXIONS_PER_SIDE * (dir.get() + 1); ++j) {
				if (t.neighbour(dir) != null)
					t.nodes[j].connection(t.neighbour(dir).nodes[(Place.getOpposite(j))]);
			}	
		}
	}

	/**
	* Remove a tile in tiles array and make disconnection between cards and nodes
	**/
	public void removeSetTile(Tile t) {
		for (Direction dir : Direction.values()) {
			for (int j = Node.CONNEXIONS_PER_SIDE * dir.get(); j < Node.CONNEXIONS_PER_SIDE * (dir.get() + 1); ++j) {
				if (t.neighbour(dir) != null)
					t.nodes[j].disconnection(t.neighbour(dir).nodes[(Place.getOpposite(j))]);
			}	
			t.disconnection(computeNeighbour(t, dir), dir);
		}
		tiles.remove(t);
	}
	
	/**
	* Return true if all cards around can match with t
	**/
	public boolean matchCard(Tile t) {
		int count = 0;
		for (Direction dir : Direction.values()) {
			int tmp = count;
			count += t.matchSide(computeNeighbour(t, dir), dir);
			if (tmp == count)
				return false;
		}
		if (count == - Direction.SIDES)
			return false;
		return true;
	}
	
	/**
	* Return true if there is at least one rotation of the card which can match with cards around
	**/
	public boolean isConnectable(Tile t) {
		Direction rot = Direction.WEST;
		for (Direction dir : Direction.values()) {
			if (matchCard(t) == true) {
				t.dir = dir;
				return true;	
			}
			t.rotation(rot);
		}
		return false;
	}
	
	/**
	* Return true if among the tiles remaining at least one can be played
	**/
	public boolean isPlayable(Tile t) {   
		for (int i = 0; i < tiles.size(); ++i) {
			for (Direction dir : Direction.values()) {
				t.pos = tiles.get(i).pos.neighbourPosition(dir);
				if (t.isEmptyTile(computeNeighbour(tiles.get(i), dir)) == true && isConnectable(t) == true) {
					t.reInit();
					return true;
				}	
			}
		}
		t.reInit();
		return false;
	}
	
	/**
	* Return true if the card move player is valid
	**/
	public boolean validCardMove(Move m) {
		if (findTile(m.tile.pos) != null) 
			return false;
		if (matchCard(m.tile) == true)
			return true;
		return false;
	}

	/**
	* Return true if the meeple move player is valid
	**/
	public boolean validMeepleMove(Move m) {
		addSetTile(m.tile);
		if (m.place != Place.NO_MEEPLE) {
			Node startingNode = m.tile.nodes[m.place.get()];
			if (m.player.meeples.size() < m.player.NUMBER_OF_MEEPLES && startingNode.isMeepleInArea() == false) 
				return true;
			return false;
		}
		return true;
	}

	/**
	* Return true if the move player is valid 
	**/
	public boolean validMove(Move m) { 
		if (m.tile != null) {
			if (validCardMove(m) == true && validMeepleMove(m) == true) {
				m.player.update(m);
				removeSetTile(m.tile);
				return true;
			}
			removeSetTile(m.tile);
		}
		return false;
	}

	/**
	* Put the tile and meeple on the board
	**/
	public void update(Move m, int round) {
		if (m.place != Place.NO_MEEPLE) {
			m.tile.nodes[m.place.get()].meepleOwner = m.player;
			m.tile.nodes[m.place.get()].round = round;
		}
		addSetTile(m.tile);
	}	
}
