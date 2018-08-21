package carcassonne.core;

public class Position {
	public int x;
	public int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	} 
	
	/**
	* Return the neighbour coordinate in according to dir
	**/
	public Position neighbourPosition(Direction dir) {
		Position pos = new Position(this.x, this.y);
		if (dir == Direction.NORTH) 
   			++pos.y;
  		if (dir == Direction.WEST) 
   			--pos.x;
  		if (dir == Direction.SOUTH) 
   			--pos.y;
  		if (dir == Direction.EAST) 
  		 	++pos.x;
 		return pos;
	}
}
