package carcassonne.core.tiles;

import carcassonne.core.Tile;
import carcassonne.core.Node;
import carcassonne.core.Position;
import carcassonne.core.directionId;

//15. city connected W to N, road connected S to E (3)

public class PlainCityRoad extends Tile {
	private final String CARDS_CONTENT[] = {"CITY","CITY","CITY","CITY","CITY","CITY","PLAIN","ROAD","PLAIN","PLAIN","ROAD","PLAIN","PLAIN"};

	public PlainCityRoad () {
		super(directionId.NORTH, new Position(0, 0));
		this.name = "PlainCityRoad";

		for (int i = 0; i < Tile.NUMBER_OF_DIRECTIONS; ++i) 
			this.nodes[i] = new Node(CARDS_CONTENT[i]);

		Node.nodeConnection(this.nodes[0], this.nodes[1]);
		Node.nodeConnection(this.nodes[1], this.nodes[2]);
		Node.nodeConnection(this.nodes[2], this.nodes[3]);
		Node.nodeConnection(this.nodes[3], this.nodes[4]);
		Node.nodeConnection(this.nodes[4], this.nodes[5]);
		Node.nodeConnection(this.nodes[8], this.nodes[9]);
		Node.nodeConnection(this.nodes[7], this.nodes[10]);
		Node.nodeConnection(this.nodes[11], this.nodes[12]);
		Node.nodeConnection(this.nodes[12], this.nodes[6]);
	}
}
