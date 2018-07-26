package carcassonne.core.tiles;

import carcassonne.core.Tile;
import carcassonne.core.Node;
import carcassonne.core.Position;
import carcassonne.core.directionId;

//11. city E, 3 roads elsewhere connected at center (3)

public class JunctionCity extends Tile {
	private final String CARDS_CONTENT[] = {"PLAIN","ROAD","PLAIN","PLAIN","ROAD","PLAIN","PLAIN","ROAD","PLAIN","CITY","CITY","CITY","CROSSROAD"};

	public JunctionCity () {
		super(directionId.NORTH, new Position(0, 0));
		this.name = "JunctionCity";

		for (int i = 0; i < Tile.NUMBER_OF_DIRECTIONS; ++i) 
			this.nodes[i] = new Node(CARDS_CONTENT[i], this);

		this.nodes[1].endRoad = true;
		this.nodes[4].endRoad = true;
		this.nodes[7].endRoad = true;

		this.nodes[2].connection(this.nodes[3]);
		this.nodes[5].connection(this.nodes[6]);
		this.nodes[9].connection(this.nodes[10]);
		this.nodes[10].connection(this.nodes[11]);
		this.nodes[0].connection(this.nodes[8]);

	}
}
