package carcassonne.core.tiles;

import carcassonne.core.Tile;
import carcassonne.core.Node;
import carcassonne.core.Position;
import carcassonne.core.directionId;

//9. city N, road connected S to E (3)

public class RoadTurnRightCity extends Tile {
	private final String CARDS_CONTENT[] = {"CITY","CITY","CITY","PLAIN","PLAIN","PLAIN","PLAIN","ROAD","PLAIN","PLAIN","ROAD","PLAIN","ROAD"};

	public RoadTurnRightCity () {
		super(directionId.NORTH, new Position(0, 0));
		this.name = "RoadTurnRightCity";

		for (int i = 0; i < Tile.NUMBER_OF_DIRECTIONS; ++i) 
			this.nodes[i] = new Node(CARDS_CONTENT[i]);

		Node.nodeConnection(this.nodes[0], this.nodes[1]);
		Node.nodeConnection(this.nodes[1], this.nodes[2]);
		Node.nodeConnection(this.nodes[3], this.nodes[4]);
		Node.nodeConnection(this.nodes[4], this.nodes[5]);
		Node.nodeConnection(this.nodes[5], this.nodes[6]);
		Node.nodeConnection(this.nodes[8], this.nodes[9]);
		Node.nodeConnection(this.nodes[11], this.nodes[3]);
		Node.nodeConnection(this.nodes[7], this.nodes[12]);
		Node.nodeConnection(this.nodes[12], this.nodes[10]);
	}
}
