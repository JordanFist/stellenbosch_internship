package carcassonne.core.tiles;

import carcassonne.core.Tile;
import carcassonne.core.Node;
import carcassonne.core.Position;
import carcassonne.core.directionId;

//10. city E, road connected W to N (3)

public class RoadTurnLeftCity extends Tile {
	private final String CARDS_CONTENT[] = {"PLAIN","ROAD","PLAIN","PLAIN","ROAD","PLAIN","PLAIN","PLAIN","PLAIN","CITY","CITY","CITY","ROAD"};

	public RoadTurnLeftCity () {
		super(directionId.NORTH, new Position(0, 0));
		this.name = "RoadTurnLeftCity";

		for (int i = 0; i < Tile.NUMBER_OF_DIRECTIONS; ++i) 
			this.nodes[i] = new Node(CARDS_CONTENT[i], this);

		this.nodes[2].connection(this.nodes[3]);
		this.nodes[5].connection(this.nodes[6]);
		this.nodes[6].connection(this.nodes[7]);
		this.nodes[7].connection(this.nodes[8]);
		this.nodes[9].connection(this.nodes[10]);
		this.nodes[10].connection(this.nodes[11]);
		this.nodes[0].connection(this.nodes[8]);
		this.nodes[1].connection(this.nodes[12]);
		this.nodes[12].connection(this.nodes[4]);
	}
}
