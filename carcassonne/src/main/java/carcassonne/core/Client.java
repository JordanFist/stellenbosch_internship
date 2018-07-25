package carcassonne.core;

import java.util.Stack;

public class Client {
	
	public boolean searchValidPositionMeeple(setTile s, Move m) {
		Tile t = m.tile;
		Node nodeInit = t.nodes[m.place.toInt()];
		s.addSetTile(t);
		Node check;
		Stack<Node> stack = new Stack<Node>();
		Stack<Node> visited = new Stack<Node>();
		stack.add(nodeInit);
		visited.add(nodeInit);
		while (stack.isEmpty() == false) {
			check = stack.remove(0);
			if (check != null && check.meepleOwner != null) {
				s.removeSetTile(t);
				return false;
			}
			for (int i = 0; i < check.neighbourNodes.size(); ++i) {
				if (check.neighbourNodes.get(i) != null && visited.contains(check.neighbourNodes.get(i)) == false) {
					stack.add(check.neighbourNodes.get(i));
					visited.add(check.neighbourNodes.get(i));
				}
			}
		}
		s.removeSetTile(t);
		return true;
	}

	public Move searchValidPositionCard(setTile s, Move m) {
		for (int i = 0; i < s.tiles.size(); ++i) {
			for (directionId dir : directionId.values()) {
				m.tile.pos = s.tiles.get(i).pos.neighbourPosition(dir);
				if (m.tile.isEmptyTile(s.computeNeighbour(s.tiles.get(i), dir)) == true && s.isConnectable(m.tile) == true) {
					return m;
				}
			}
		}
		return m;
	}

	public Move clientMove(setTile s, Tile tile, Player p) {
		Move m = new Move(p, tile, placeId.NO_MEEPLE);
		m = searchValidPositionCard(s, m);
		
		if (p.numberOfMeeples > 0) {
			for (placeId place : placeId.values()) {
				if (place != placeId.NO_MEEPLE) {
					m.place = place;
					if (searchValidPositionMeeple(s, m) == true) {
						--p.numberOfMeeples;
						break;
					}
				}
			}
		}
		return m;
	}
}
