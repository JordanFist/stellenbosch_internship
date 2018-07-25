package carcassonne.core.tiles;

import carcassonne.core.Tile;
import carcassonne.core.Node;
import carcassonne.core.Position;
import carcassonne.core.directionId;

//23. 4 roads connected at the center (1)

public class JunctionFour extends Tile {
	private final String CARDS_CONTENT[] = {"PLAIN","ROAD","PLAIN","PLAIN","ROAD","PLAIN","PLAIN","ROAD","PLAIN","PLAIN","ROAD","PLAIN","CROSSROAD"};

	public JunctionFour () {
		super(directionId.NORTH, new Position(0, 0));
		this.name = "JunctionFour";

		for (int i = 0; i < Tile.NUMBER_OF_DIRECTIONS; ++i) 
			this.nodes[i] = new Node(CARDS_CONTENT[i]);


		this.nodes[2].nodeConnection(this.nodes[3]);
		this.nodes[5].nodeConnection(this.nodes[6]);
		this.nodes[8].nodeConnection(this.nodes[9]);
		this.nodes[11].nodeConnection(this.nodes[0]);
	}
}
