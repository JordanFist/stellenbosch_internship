package carcassonne.core.tiles;

import carcassonne.core.Tile;
import carcassonne.core.Node;
import carcassonne.core.Position;
import carcassonne.core.directionId;

//6. city connected N to S (1)

public class CityTunnel extends Tile {
	private final String CARDS_CONTENT[] = {"CITY","CITY","CITY","PLAIN","PLAIN","PLAIN","CITY","CITY","CITY","PLAIN","PLAIN","PLAIN","CITY"};

	public CityTunnel () {
		super(directionId.NORTH, new Position(0, 0));
		this.name = "CityTunnel";

		for (int i = 0; i < Tile.NUMBER_OF_DIRECTIONS; ++i) 
			this.nodes[i] = new Node(CARDS_CONTENT[i], this);

		this.nodes[0].connection(this.nodes[1]);
		this.nodes[1].connection(this.nodes[2]);
		this.nodes[3].connection(this.nodes[4]);
		this.nodes[4].connection(this.nodes[5]);
		this.nodes[6].connection(this.nodes[7]);
		this.nodes[7].connection(this.nodes[8]);
		this.nodes[9].connection(this.nodes[10]);
		this.nodes[10].connection(this.nodes[11]);
		this.nodes[0].connection(this.nodes[12]);
		this.nodes[12].connection(this.nodes[6]);
	}
}
