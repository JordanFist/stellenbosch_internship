package carcassonne.core.tiles;

import carcassonne.core.Tile;
import carcassonne.core.Node;
import carcassonne.core.Position;
import carcassonne.core.directionId;

//4. city N (5)

public class CityOneSide extends Tile {
		private final String CARDS_CONTENT[] = {"CITY","CITY","CITY","PLAIN","PLAIN","PLAIN","PLAIN","PLAIN","PLAIN","PLAIN","PLAIN","PLAIN","PLAIN"};

	public CityOneSide () {
		super(directionId.NORTH, new Position(0, 0));
		this.name = "CityOneSide";

		for (int i = 0; i < Tile.NUMBER_OF_DIRECTIONS; ++i) 
			this.nodes[i] = new Node(CARDS_CONTENT[i]);

		this.nodes[0].nodeConnection(this.nodes[1]);
		this.nodes[1].nodeConnection(this.nodes[2]);
		this.nodes[2].nodeConnection(this.nodes[3]);
		this.nodes[4].nodeConnection(this.nodes[5]);
		this.nodes[5].nodeConnection(this.nodes[6]);
		this.nodes[6].nodeConnection(this.nodes[7]);
		this.nodes[7].nodeConnection(this.nodes[8]);
		this.nodes[8].nodeConnection(this.nodes[9]);
		this.nodes[9].nodeConnection(this.nodes[10]);
		this.nodes[10].nodeConnection(this.nodes[11]);
		this.nodes[11].nodeConnection(this.nodes[12]);
	}
}
