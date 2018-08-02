package carcassonne.core.tiles;

import carcassonne.core.Tile;
import carcassonne.core.Node;
import carcassonne.core.Position;
import carcassonne.core.Direction;

//23. 4 roads connected at the center (1)

public class JunctionFour extends Tile {
	private final String CARDS_CONTENT[] = {"PLAIN","ROAD","PLAIN","PLAIN","ROAD","PLAIN","PLAIN","ROAD","PLAIN","PLAIN","ROAD","PLAIN","CROSSROAD"};

	public JunctionFour () {
		super(Direction.NORTH, new Position(0, 0));
		this.name = "JUNCTION_FOUR";	

		for (int i = 0; i < Direction.NUMBER_OF_DIRECTIONS; ++i) 
			this.nodes[i] = new Node(CARDS_CONTENT[i], this);

		this.nodes[1].endRoad = true;
		this.nodes[4].endRoad = true;
		this.nodes[7].endRoad = true;
		this.nodes[10].endRoad = true;

		this.nodes[2].connection(this.nodes[3]);
		this.nodes[5].connection(this.nodes[6]);
		this.nodes[8].connection(this.nodes[9]);
		this.nodes[11].connection(this.nodes[0]);
	}
}
