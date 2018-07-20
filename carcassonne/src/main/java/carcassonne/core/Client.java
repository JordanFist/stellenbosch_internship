package carcassonne.core;

public class Client {

	public Move searchValidPositionMeeple(setTile s, Move m) {
		//TODO
		return m;
	}

	public Move searchValidPositionCard(setTile s, Move m) {
		Tile t = new Tile(m.card, directionId.NORTH, new Position(0, 0));
		for (int i = 0; i < s.tiles.size(); ++i) {
			for (directionId dir : directionId.values()) {
				t.pos = s.neighbourPosition(s.tiles.get(i).pos, dir);
				if (t.isEmptyTile(s.computeNeighbour(s, s.tiles.get(i), dir)) == true && s.isConnectable(s, t) == true) {
					m.onto = t.pos;
					m.dir = t.dir;;
					return m;
				}
			}
		}
		return m;
	}

	public Move clientMove(setTile s, Card card, Player p) {
		directionId dir = directionId.NORTH;
		Position pos = new Position(0, 0);
		Place place = Place.NO_MEEPLE;
		Move m = new Move(p.id, card, pos, dir, place);
		m = searchValidPositionCard(s, m);
		m = searchValidPositionMeeple(s, m);	
		return m;
	}
}
