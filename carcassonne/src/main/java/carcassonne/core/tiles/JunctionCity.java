package carcassonne.core.tiles;

import carcassonne.core.Tile;
import carcassonne.core.Node;
import carcassonne.core.Position;
import carcassonne.core.Direction;

//11. city E, 3 roads elsewhere connected at center (3)

public class JunctionCity extends Tile {
	private final String CARDS_CONTENT[] = {"PLAIN","ROAD","PLAIN","PLAIN","ROAD","PLAIN","PLAIN","ROAD","PLAIN","CITY","CITY","CITY","CROSSROAD"};

	public JunctionCity () {
		super(Direction.NORTH, new Position(0, 0));
		this.name = "JUNCTION_CITY";

		for (int i = 0; i < Direction.NUMBER_OF_DIRECTIONS; ++i) 
			this.nodes[i] = new Node(CARDS_CONTENT[i], this);

		this.nodes[1].endRoad = true;
		this.nodes[4].endRoad = true;
		this.nodes[7].endRoad = true;

		this.nodes[2].plainNextToCity = false;
		this.nodes[3].plainNextToCity = false;
		this.nodes[5].plainNextToCity = false;
		this.nodes[6].plainNextToCity = false;

		this.nodes[2].connection(this.nodes[3]);
		this.nodes[5].connection(this.nodes[6]);
		this.nodes[9].connection(this.nodes[10]);
		this.nodes[10].connection(this.nodes[11]);
		this.nodes[0].connection(this.nodes[8]);

	}
}
