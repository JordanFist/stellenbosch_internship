package carcassonne.core;

public class Place {
	/*
	public static int getPlace(placeId p) {
		return p.toInt();
	}
	
	public static int getOppositePlace(placeId p) {
		if (p == placeId.POS_NORTH_WEST)
			return placeId.POS_SOUTH_WEST.toInt();
		if (p == placeId.POS_NORTH)
			return placeId.POS_SOUTH.toInt();
		if (p == placeId.POS_NORTH_EAST)
			return placeId.POS_SOUTH_EAST.toInt();


		if (p == placeId.POS_WEST_NORTH)
			return placeId.POS_EAST_NORTH.toInt();
		if (p == placeId.POS_WEST)
			return placeId.POS_EAST.toInt();
		if (p == placeId.POS_WEST_SOUTH)
			return placeId.POS_EAST_SOUTH.toInt();


		if (p == placeId.POS_SOUTH_EAST)
			return placeId.POS_NORTH_EAST.toInt();
		if (p == placeId.POS_SOUTH)
			return placeId.POS_NORTH.toInt();
		if (p == placeId.POS_SOUTH_WEST)
			return placeId.POS_NORTH_WEST.toInt();


		if (p == placeId.POS_EAST_SOUTH)
			return placeId.POS_WEST_SOUTH.toInt();
		if (p == placeId.POS_EAST)
			return placeId.POS_WEST.toInt();
		if (p == placeId.POS_EAST_NORTH)
			return placeId.POS_WEST_NORTH.toInt();
		else {
			System.out.println("ERROR in place functions");
			return -1;		
		}
	}*/

	public static int getOppositePlace(int p) {
		if (p == 0)
			return 8;
		if (p == 1)
			return 7;
		if (p == 2)
			return 6;


		if (p == 3)
			return 11;
		if (p == 4)
			return 10;
		if (p == 5)
			return 9;


		if (p == 6)
			return 2;
		if (p == 7)
			return 1;
		if (p == 8)
			return 0;


		if (p == 9)
			return 5;
		if (p == 10)
			return 4;
		if (p == 11)
			return 3;
		else {
			System.out.println("ERROR in place functions");
			return -1;		
		}
	}
}
