package carcassonne.core.tiles;

import carcassonne.core.Tile;
import carcassonne.core.Node;
import carcassonne.core.Position;
import carcassonne.core.Direction;

//10. city N, road connected W to S (3)

public class RoadTurnLeftCity extends Tile {
	private final String CARDS_CONTENT[] = {"CITY","CITY","CITY","PLAIN","ROAD","PLAIN","PLAIN","ROAD","PLAIN","PLAIN","PLAIN","PLAIN","ROAD"};

	public RoadTurnLeftCity () {
		super(Direction.NORTH, new Position(0, 0));
		this.id = 10;
		this.name = "ROAD_TURN_LEFT_CITY";

		for (int i = 0; i < Direction.NUMBER_OF_DIRECTIONS; ++i) 
			this.nodes[i] = new Node(CARDS_CONTENT[i], this);

		this.nodes[5].plainNextToCity = false;
		this.nodes[6].plainNextToCity = false;

		this.nodes[0].connection(this.nodes[1]);
		this.nodes[1].connection(this.nodes[2]);
		this.nodes[5].connection(this.nodes[6]);
		this.nodes[8].connection(this.nodes[9]);
		this.nodes[9].connection(this.nodes[10]);
		this.nodes[10].connection(this.nodes[11]);
		this.nodes[3].connection(this.nodes[11]);
		this.nodes[4].connection(this.nodes[12]);
		this.nodes[12].connection(this.nodes[7]);
	}
}
