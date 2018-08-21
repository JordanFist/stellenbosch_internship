import org.junit.Test;
import org.junit.Assert;

import carcassonne.core.Tile;
import carcassonne.core.Direction;
import carcassonne.core.Position;
import carcassonne.core.tiles.*;

public class testTile {
	@Test
	public void testConstructeur() {
		Tile t = new Tile (Direction.NORTH, new Position(1, 1));
	}

	@Test
	public void testRotation() {
		CityTunnel t1 = new CityTunnel();
		t1.rotation(Direction.WEST);
		String expected1[] = {"CITY","CITY","CITY","PLAIN","PLAIN","PLAIN","CITY","CITY","CITY","PLAIN","PLAIN","PLAIN","CITY"};

		for (int i = 0; i < t1.nodes.length; ++i)
			Assert.assertEquals(expected1[i], t1.nodes[i].landType);	

		JunctionThree t2 = new JunctionThree();
		t2.rotation(Direction.SOUTH);
		String expected2[] = {"PLAIN","ROAD","PLAIN","PLAIN","ROAD","PLAIN","PLAIN","PLAIN","PLAIN","PLAIN","ROAD","PLAIN","CROSSROAD"};
		
		for (int i = 0; i < t2.nodes.length; ++i)
			Assert.assertEquals(expected2[i], t2.nodes[i].landType);	
	}

	@Test
	public void testGetFace() {
		RoadTurn t1 = new RoadTurn();
		String expected = "PLAIN";
		String actual = t1.getFace(Direction.NORTH);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetOppositeFace() {
		RoadTurn t1 = new RoadTurn();
		String expected = "ROAD";
		String actual = t1.getOppositeFace(Direction.NORTH);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testMatchSide() {
		JunctionFour t1 = new JunctionFour();
		RoadTurnLeftCity t2 = new RoadTurnLeftCity();

		Assert.assertEquals(t1.matchSide(t2, Direction.EAST), 1);
		Assert.assertEquals(t1.matchSide(t2, Direction.WEST), 0);
	}

	@Test
	public void testConnection() {
		MonasteryAlone t1 = new MonasteryAlone();
		CityThree t2 = new CityThree();
		t1.connection(t2, Direction.NORTH);

		Assert.assertEquals(t1.neighbourTiles[Direction.NORTH.get()], t2);
		Assert.assertEquals(t2.neighbourTiles[Direction.NORTH.getOpposite()], t1);
	}

	@Test
	public void TestDisconnection() {
		MonasteryAlone t1 = new MonasteryAlone();
		CityThree t2 = new CityThree();
		t1.connection(t2, Direction.NORTH);
		t1.disconnection(t2, Direction.NORTH);

		Assert.assertEquals(t1.neighbourTiles[Direction.NORTH.get()], null);
		Assert.assertEquals(t2.neighbourTiles[Direction.NORTH.getOpposite()], null);
	}
}
