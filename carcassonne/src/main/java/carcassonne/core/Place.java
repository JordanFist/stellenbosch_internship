package carcassonne.core;

enum Place {
	POS_NORTH_EAST(0),  //0.  {NE}
	POS_NORTH(1),       //1.  {N}
	POS_NORTH_WEST(2),  //2.  {NW}
	POS_WEST_NORTH(3),  //3.  {WN}
	POS_WEST(4),        //4.  {W}
	POS_WEST_SOUTH(5),  //5.  {WS}
	POS_SOUTH_WEST(8),  //6.  {SW}
	POS_SOUTH(7),       //7.  {S}
	POS_SOUTH_EAST(6),  //8.  {SE}
 	POS_EAST_SOUTH(11),  //9.  {ES}
 	POS_EAST(10),       //10. {E}
 	POS_EAST_NORTH(9), //11. {EN}  
 	POS_CENTER(12),     //12. {C}
	NO_MEEPLE(13); 	    //13. special place for not placing a meeple
	
	public final int id;

	private Place(int id) {
		this.id = id;
	}

	public int toInt() {
		return id;
	}
	
	public String toString() {
		return String.valueOf(id);
	}
}
