package carcassonne.core.tiles;

import carcassonne.core.Tile;
import carcassonne.core.Node;
import carcassonne.core.Position;
import carcassonne.core.Direction;

//3. road connected E to W, city N, initial card (4 - 1(starting))

public class RoadStraightCity extends Tile {
	private final String CARDS_CONTENT[] = {"CITY","CITY","CITY","PLAIN","ROAD","PLAIN","PLAIN","PLAIN","PLAIN","PLAIN","ROAD","PLAIN","ROAD"};

	public RoadStraightCity () {
		super(Direction.NORTH, new Position(0, 0));
		this.id = 3;
		this.name = "ROAD_STRAIGHT_CITY";

		for (int i = 0; i < Direction.NUMBER_OF_DIRECTIONS; ++i) 
			this.nodes[i] = new Node(CARDS_CONTENT[i], this);

		this.nodes[5].plainNextToCity = false;	
		this.nodes[6].plainNextToCity = false;
		this.nodes[7].plainNextToCity = false;
		this.nodes[8].plainNextToCity = false;
		this.nodes[9].plainNextToCity = false;

		this.nodes[0].connection(this.nodes[1]);
		this.nodes[1].connection(this.nodes[2]);
		this.nodes[5].connection(this.nodes[6]);
		this.nodes[6].connection(this.nodes[7]);
		this.nodes[7].connection(this.nodes[8]);
		this.nodes[8].connection(this.nodes[9]);
		this.nodes[3].connection(this.nodes[11]);
		this.nodes[4].connection(this.nodes[12]);
		this.nodes[12].connection(this.nodes[10]);
	}
}
