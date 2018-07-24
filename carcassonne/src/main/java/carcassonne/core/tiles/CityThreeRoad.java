package carcassonne.core.tiles;

import carcassonne.core.Tile;
import carcassonne.core.Node;
import carcassonne.core.Position;
import carcassonne.core.directionId;

//19. road S, connected city elsewhere (1)

public class CityThreeRoad extends Tile {
	private final String CARDS_CONTENT[] = {"CITY","CITY","CITY","CITY","CITY","CITY","PLAIN","ROAD","PLAIN","CITY","CITY","CITY","CITY"};

	public CityThreeRoad () {
		super(directionId.NORTH, new Position(0, 0));
		this.name = "CityThreeRoad";

		for (int i = 0; i < Tile.NUMBER_OF_DIRECTIONS; ++i) 
			this.nodes[i] = new Node(CARDS_CONTENT[i]);

		Node.nodeConnection(this.nodes[0], this.nodes[1]);
		Node.nodeConnection(this.nodes[1], this.nodes[2]);
		Node.nodeConnection(this.nodes[2], this.nodes[3]);
		Node.nodeConnection(this.nodes[3], this.nodes[4]);
		Node.nodeConnection(this.nodes[4], this.nodes[5]);
		Node.nodeConnection(this.nodes[9], this.nodes[10]);
		Node.nodeConnection(this.nodes[10], this.nodes[11]);
		Node.nodeConnection(this.nodes[11], this.nodes[0]);
		Node.nodeConnection(this.nodes[5], this.nodes[12]);
	}
}
