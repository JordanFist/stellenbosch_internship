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

		this.nodes[0].nodeConnection(this.nodes[1]);
		this.nodes[1].nodeConnection(this.nodes[2]);
		this.nodes[3].nodeConnection(this.nodes[4]);
		this.nodes[4].nodeConnection(this.nodes[5]);
		this.nodes[5].nodeConnection(this.nodes[6]);
		this.nodes[8].nodeConnection(this.nodes[9]);
		this.nodes[11].nodeConnection(this.nodes[3]);
		this.nodes[7].nodeConnection(this.nodes[12]);
		this.nodes[12].nodeConnection(this.nodes[10]);
	}
}
