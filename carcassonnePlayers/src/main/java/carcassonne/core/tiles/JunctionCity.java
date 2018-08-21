package carcassonne.core.tiles;

import carcassonne.core.Tile;
import carcassonne.core.Node;
import carcassonne.core.Position;
import carcassonne.core.Direction;

//11. city E, 3 roads elsewhere connected at center (3)

public class JunctionCity extends Tile {
	private final String CARDS_CONTENT[] = {"CITY","CITY","CITY","PLAIN","ROAD","PLAIN","PLAIN","ROAD","PLAIN","PLAIN","ROAD","PLAIN","CROSSROAD"};

	public JunctionCity () {
		super(Direction.NORTH, new Position(0, 0));
		this.id = 11;
		this.name = "JUNCTION_CITY";

		for (int i = 0; i < Direction.NUMBER_OF_DIRECTIONS; ++i) 
			this.nodes[i] = new Node(CARDS_CONTENT[i], this);

		this.nodes[4].endRoad = true;
		this.nodes[7].endRoad = true;
		this.nodes[10].endRoad = true;

		this.nodes[5].plainNextToCity = false;
		this.nodes[6].plainNextToCity = false;
		this.nodes[8].plainNextToCity = false;
		this.nodes[9].plainNextToCity = false;

		this.nodes[0].connection(this.nodes[1]);
		this.nodes[1].connection(this.nodes[2]);
		this.nodes[5].connection(this.nodes[6]);
		this.nodes[8].connection(this.nodes[9]);
		this.nodes[3].connection(this.nodes[11]);

	}
}
