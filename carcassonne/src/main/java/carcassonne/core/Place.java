package carcassonne.core;

enum Place {
	NORTH_EAST(0),  // {NE}
	NORTH(1),       // {N}
	NORTH_WEST(2),  // {NW}
	WEST_NORTH(3),  // {WN}
	WEST(4),        // {W}
	WEST_SOUTH(5),  // {WS}
	SOUTH_WEST(6),  // {SW}
	SOUTH(7),       // {S}
	SOUTH_EAST(8),  // {SE}
 	EAST_SOUTH(9),  // {ES}
 	EAST(10),       // {E}
 	EAST_NORTH(11), // {EN}  
 	CENTER(12),     // {C}
	NO_MEEPLE(13); 	// special place for not placing a meeple
	
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

	/**
	* Return the value of the enumeration
	**/
	public int get() {
		return this.toInt();
	}

	/**
	* Return the opposite value of the enumeration
	**/
	public static int getOpposite(int place) {
		if (place == Place.NORTH_EAST.get())
			return Place.SOUTH_EAST.get();
		if (place == Place.NORTH.get())
			return Place.SOUTH.get();
		if (place == Place.NORTH_WEST.get())
			return Place.SOUTH_WEST.get();


		if (place == Place.WEST_NORTH.get())
			return Place.EAST_NORTH.get();
		if (place == Place.WEST.get())
			return Place.EAST.get();
		if (place == Place.WEST_SOUTH.get())
			return Place.EAST_SOUTH.get();

		if (place == Place.SOUTH_WEST.get())
			return Place.NORTH_WEST.get();
		if (place == Place.SOUTH.get())
			return Place.NORTH.get();
		if (place == Place.SOUTH_EAST.get())
			return Place.NORTH_EAST.get();


		if (place == Place.EAST_SOUTH.get())
			return Place.WEST_SOUTH.get();
		if (place == Place.EAST.get())
			return Place.WEST.get();
		if (place == Place.EAST_NORTH.get())
			return Place.WEST_NORTH.get();
		else {
			System.out.println("ERROR in getOpposite Place function");
			return -1;
		}
	}
}
