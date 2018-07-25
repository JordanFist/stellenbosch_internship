package carcassonne.core.tiles;

import carcassonne.core.Tile;
import carcassonne.core.Node;
import carcassonne.core.Position;
import carcassonne.core.directionId;

//2. connected cities on all sides (1)

public class CityAllSides extends Tile {
	private final String CARDS_CONTENT[] = {"CITY","CITY","CITY","CITY","CITY","CITY","CITY","CITY","CITY","CITY","CITY","CITY","CITY"};

	public CityAllSides () {
		super(directionId.NORTH, new Position(0, 0));
		this.name = "CityAllSides";

		for (int i = 0; i < Tile.NUMBER_OF_DIRECTIONS; ++i) 
			this.nodes[i] = new Node(CARDS_CONTENT[i]);

		this.nodes[0].nodeConnection(this.nodes[1]);
		this.nodes[1].nodeConnection(this.nodes[2]);
		this.nodes[2].nodeConnection(this.nodes[3]);
		this.nodes[3].nodeConnection(this.nodes[4]);
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
