package carcassonne.core.tiles;

import carcassonne.core.Tile;
import carcassonne.core.Node;
import carcassonne.core.Position;
import carcassonne.core.directionId;

//5. city connected E to W, shield (2)

public class CityTunnelShield extends Tile {
	private final String CARDS_CONTENT[] = {"PLAIN","PLAIN","PLAIN","CITY","CITY","CITY","PLAIN","PLAIN","PLAIN","CITY","CITY","CITY","CITY"};
	
	public CityTunnelShield () {
		super(directionId.NORTH, new Position(0, 0));
		this.name = "CityTunnelShield";
		
		for (int i = 0; i < Tile.NUMBER_OF_DIRECTIONS; ++i) 
			this.nodes[i] = new Node(CARDS_CONTENT[i]);

		Node.nodeConnection(this.nodes[0], this.nodes[1]);
		Node.nodeConnection(this.nodes[1], this.nodes[2]);
		Node.nodeConnection(this.nodes[3], this.nodes[4]);
		Node.nodeConnection(this.nodes[4], this.nodes[5]);
		Node.nodeConnection(this.nodes[6], this.nodes[7]);
		Node.nodeConnection(this.nodes[7], this.nodes[8]);
		Node.nodeConnection(this.nodes[9], this.nodes[10]);
		Node.nodeConnection(this.nodes[10], this.nodes[11]);
		Node.nodeConnection(this.nodes[11], this.nodes[12]);
		Node.nodeConnection(this.nodes[12], this.nodes[5]);
	}
}
