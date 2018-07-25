package carcassonne.core.tiles;

import carcassonne.core.Tile;
import carcassonne.core.Node;
import carcassonne.core.Position;
import carcassonne.core.directionId;

//22. plain N, 3 roads elsewhere connected at center (4)

public class JunctionThree extends Tile {
	private final String CARDS_CONTENT[] = {"PLAIN","PLAIN","PLAIN","PLAIN","ROAD","PLAIN","PLAIN","ROAD","PLAIN","PLAIN","ROAD","PLAIN","CROSSROAD"};

	public JunctionThree () {
		super(directionId.NORTH, new Position(0, 0));
		this.name = "JunctionThree";

		for (int i = 0; i < Tile.NUMBER_OF_DIRECTIONS; ++i) 
			this.nodes[i] = new Node(CARDS_CONTENT[i]);

		this.nodes[0].nodeConnection(this.nodes[1]);
		this.nodes[1].nodeConnection(this.nodes[2]);
		this.nodes[2].nodeConnection(this.nodes[3]);
		this.nodes[5].nodeConnection(this.nodes[6]);
		this.nodes[8].nodeConnection(this.nodes[9]);
		this.nodes[11].nodeConnection(this.nodes[0]);
	}
}
