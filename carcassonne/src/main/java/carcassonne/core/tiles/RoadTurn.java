package carcassonne.core.tiles;

import carcassonne.core.Tile;
import carcassonne.core.Node;
import carcassonne.core.Position;
import carcassonne.core.directionId;

//21. road connected W to S (9) 

public class RoadTurn extends Tile {
	private final String CARDS_CONTENT[] = {"PLAIN","PLAIN","PLAIN","PLAIN","ROAD","PLAIN","PLAIN","ROAD","PLAIN","PLAIN","PLAIN","PLAIN","ROAD"};

	public RoadTurn () {
		super(directionId.NORTH, new Position(0, 0));
		this.name = "RoadTurn";

		for (int i = 0; i < Tile.NUMBER_OF_DIRECTIONS; ++i) 
			this.nodes[i] = new Node(CARDS_CONTENT[i]);

		this.nodes[0].nodeConnection(this.nodes[1]);
		this.nodes[1].nodeConnection(this.nodes[2]);
		this.nodes[2].nodeConnection(this.nodes[3]);
		this.nodes[5].nodeConnection(this.nodes[6]);
		this.nodes[8].nodeConnection(this.nodes[9]);
		this.nodes[9].nodeConnection(this.nodes[10]);
		this.nodes[10].nodeConnection(this.nodes[11]);
		this.nodes[11].nodeConnection(this.nodes[0]);
		this.nodes[4].nodeConnection(this.nodes[12]);
		this.nodes[12].nodeConnection(this.nodes[7]);
	}
}
