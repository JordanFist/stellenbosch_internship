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
			this.nodes[i] = new Node(CARDS_CONTENT[i]);

		this.nodes[2].nodeConnection(this.nodes[3]);
		this.nodes[5].nodeConnection(this.nodes[6]);
		this.nodes[9].nodeConnection(this.nodes[10]);
		this.nodes[10].nodeConnection(this.nodes[11]);
		this.nodes[0].nodeConnection(this.nodes[8]);

	}
}
