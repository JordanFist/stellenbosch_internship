package carcassonne.core.tiles;

import carcassonne.core.Tile;
import carcassonne.core.Node;
import carcassonne.core.Position;
import carcassonne.core.Direction;

//9. city N, road connected S to E (3)

public class RoadTurnRightCity extends Tile {
	private final String CARDS_CONTENT[] = {"CITY","CITY","CITY","PLAIN","PLAIN","PLAIN","PLAIN","ROAD","PLAIN","PLAIN","ROAD","PLAIN","ROAD"};

	public RoadTurnRightCity () {
		super(Direction.NORTH, new Position(0, 0));
		this.name = "ROAD_TURN_RIGHT_CITY";

		for (int i = 0; i < Direction.NUMBER_OF_DIRECTIONS; ++i) 
			this.nodes[i] = new Node(CARDS_CONTENT[i], this);

		this.nodes[8].plainNextToCity = false;
		this.nodes[9].plainNextToCity = false;

		this.nodes[0].connection(this.nodes[1]);
		this.nodes[1].connection(this.nodes[2]);
		this.nodes[3].connection(this.nodes[4]);
		this.nodes[4].connection(this.nodes[5]);
		this.nodes[5].connection(this.nodes[6]);
		this.nodes[8].connection(this.nodes[9]);
		this.nodes[11].connection(this.nodes[3]);
		this.nodes[7].connection(this.nodes[12]);
		this.nodes[12].connection(this.nodes[10]);
	}
}
