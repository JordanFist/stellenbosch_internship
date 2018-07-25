package carcassonne.core;

public class Position {
	public int x;
	public int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	} 
	
	public Position neighbourPosition(directionId dir) {
		Position pos = new Position(this.x, this.y);
		if (dir == directionId.NORTH) 
   			++pos.y;
  		if (dir == directionId.WEST) 
   			--pos.x;
  		if (dir == directionId.SOUTH) 
   			--pos.y;
  		if (dir == directionId.EAST) 
  		 	++pos.x;
 		return pos;
	}
}
