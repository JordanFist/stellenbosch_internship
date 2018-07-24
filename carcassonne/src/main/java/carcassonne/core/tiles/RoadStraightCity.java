package carcassonne.core.tiles;

import carcassonne.core.Tile;
import carcassonne.core.Node;
import carcassonne.core.Position;
import carcassonne.core.directionId;

//3. road connected N to S, city E, initial card (4 - 1(starting))

public class RoadStraightCity extends Tile {
	private final String CARDS_CONTENT[] = {"PLAIN","ROAD","PLAIN","PLAIN","PLAIN","PLAIN","PLAIN","ROAD","PLAIN","CITY","CITY","CITY","ROAD"};

	public RoadStraightCity () {
		super(directionId.NORTH, new Position(0, 0));
		this.name = "RoadStraightCity";

		for (int i = 0; i < Tile.NUMBER_OF_DIRECTIONS; ++i) 
			this.nodes[i] = new Node(CARDS_CONTENT[i]);

		Node.nodeConnection(this.nodes[2], this.nodes[3]);
		Node.nodeConnection(this.nodes[3], this.nodes[4]);
		Node.nodeConnection(this.nodes[4], this.nodes[5]);
		Node.nodeConnection(this.nodes[5], this.nodes[6]);
		Node.nodeConnection(this.nodes[9], this.nodes[10]);
		Node.nodeConnection(this.nodes[10], this.nodes[11]);
		Node.nodeConnection(this.nodes[12], this.nodes[7]);
		Node.nodeConnection(this.nodes[1], this.nodes[12]);
		Node.nodeConnection(this.nodes[0], this.nodes[8]);
	}
}
