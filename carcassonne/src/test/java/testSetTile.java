import org.junit.Test;
import org.junit.Assert;

import carcassonne.core.tiles.*;
import carcassonne.core.*;

public class testSetTile {
	@Test
	public void testAddSetTile() {
		setTile s = new setTile();
		CityThree t1 = new CityThree();
		t1.pos = new Position(0, 1);
		t1.dir = Direction.WEST;
		t1.rotation(Direction.WEST);
		s.addSetTile(t1);

		
		Assert.assertEquals(t1.neighbourTiles[2], s.tiles.get(0));
		Assert.assertEquals(t1.nodes[6].neighbourNodes.get(2), s.tiles.get(0).nodes[2]);
		Assert.assertEquals(t1.nodes[7].neighbourNodes.get(2), s.tiles.get(0).nodes[1]);
		Assert.assertEquals(t1.nodes[8].neighbourNodes.get(2), s.tiles.get(0).nodes[0]);
	}

	@Test
	public void testRemoveSetTile() {
		setTile s = new setTile();
		CityThree t1 = new CityThree();
		t1.pos = new Position(0, 1);
		t1.dir = Direction.WEST;
		t1.rotation(Direction.WEST);
		s.addSetTile(t1);
		s.removeSetTile(t1);

		Assert.assertNull(t1.neighbourTiles[2]);
		Assert.assertEquals(t1.nodes[6].neighbourNodes.size(), 2);
		Assert.assertEquals(t1.nodes[7].neighbourNodes.size(), 2);
		Assert.assertEquals(t1.nodes[8].neighbourNodes.size(), 2);
	}

	@Test
	public void testMatchCard() {
		setTile s = new setTile();
		CityOneSide t1 = new CityOneSide();
		RoadStraight t2 = new RoadStraight();
		PlainTwoCities t3 = new PlainTwoCities();

		t1.dir = Direction.WEST;
		t1.rotation(Direction.WEST);
		t1.pos = new Position(0, -1);
		t2.dir = Direction.WEST;
		t2.rotation(Direction.WEST);
		t2.pos = new Position(-1, 0);
		t3.pos = new Position(-1, -2);

		s.addSetTile(t1);
		s.addSetTile(t2);
		s.addSetTile(t3);

		PlainCity t4 = new PlainCity();
		t4.dir = Direction.SOUTH;
		t4.pos = new Position(-1, -1);
		t4.rotation(Direction.SOUTH);

		Assert.assertTrue(s.matchCard(t4));
		t4.pos = new Position(1, 0);
		Assert.assertFalse(s.matchCard(t4));
	}

	@Test
	public void testIsPlayable() {
		setTile s = new setTile();
		MonasteryRoad t1 = new MonasteryRoad();
		MonasteryRoad t2 = new MonasteryRoad();
		t1.dir = Direction.WEST;
		t1.rotation(Direction.WEST);
		t1.pos = new Position(-1, 0);
		t2.dir = Direction.EAST;
		t2.rotation(Direction.EAST);
		t2.pos = new Position(1, 0);

		s.addSetTile(t1);
		s.addSetTile(t2);

		JunctionFour t3 = new JunctionFour();
		CityOneSide t4 = new CityOneSide();
	
		Assert.assertFalse(s.isPlayable(t3));
		Assert.assertTrue(s.isPlayable(t4));
	}
	
	@Test
	public void testValidMove() {
		setTile s = new setTile();
		MonasteryRoad t1 = new MonasteryRoad();
		MonasteryRoad t2 = new MonasteryRoad();
		t1.dir = Direction.WEST;
		t1.rotation(Direction.WEST);
		t1.pos = new Position(-1, 0);
		t2.dir = Direction.EAST;
		t2.rotation(Direction.EAST);
		t2.pos = new Position(1, 0);

		s.addSetTile(t1);
		s.addSetTile(t2);

		RoadStraightCity t3 = new RoadStraightCity(); 
		t3.dir = Direction.SOUTH;
		t3.rotation(Direction.SOUTH);
		t3.pos = new Position(0, 1);

		Move m = new Move(new Player(0), t3, Place.SOUTH);
		Assert.assertTrue(s.validMove(m));
		Assert.assertEquals(m.player.meeples.size(), 1);
		Player p = new Player(1);
		s.tiles.get(0).nodes[0].meepleOwner = p;
		Assert.assertFalse(s.validMove(m));
	}
}
