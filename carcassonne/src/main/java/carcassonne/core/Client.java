package carcassonne.core;

public class Client {

	public Move searchValidPositionMeeple(setTile s, Move m) {
		//TODO
		return m;
	}

	public Move searchValidPositionCard(setTile s, Move m) {
		for (int i = 0; i < s.tiles.size(); ++i) {
			for (directionId dir : directionId.values()) {
				m.tile.pos = s.neighbourPosition(s.tiles.get(i).pos, dir);
				if (m.tile.isEmptyTile(s.computeNeighbour(s, s.tiles.get(i), dir)) == true && s.isConnectable(s, m.tile) == true) {
					return m;
				}
			}
		}
		return m;
	}

	public Move clientMove(setTile s, Tile tile, Player p) {
		Move m = new Move(p.id, tile, Place.NO_MEEPLE);
		/*m = searchValidPositionCard(s, m);*/
		m = searchValidPositionMeeple(s, m);	
		return m;
	}
}
