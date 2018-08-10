import org.junit.Test;
import org.junit.Assert;

import carcassonne.core.*;
import carcassonne.core.tiles.*;

public class testScore {
	/*@Test
	public void testRoadCurrent() {
		setTile s = new setTile();
		Score score = new Score();
		MonasteryRoad t1 = new MonasteryRoad();
		RoadStraight t2 = new RoadStraight();
		JunctionFour t3 = new JunctionFour();
		MonasteryRoad t4 = new MonasteryRoad();

		t1.dir = Direction.WEST;
		t1.pos = new Position(-1, 0);
		t1.rotation(Direction.WEST);
		t2.dir = Direction.WEST;	
		t2.pos = new Position(1, 0);
		t2.rotation(Direction.WEST);
		t3.pos = new Position(2, 0);
		t4.dir = Direction.EAST;	
		t4.pos = new Position(2, 0);
		t4.rotation(Direction.EAST);

		Player p1 = new Player(1);
		Player p2 = new Player(2);
		Player p3 = new Player(3);

		s.addSetTile(t1);
		s.addSetTile(t2);

		t2.nodes[12].meepleOwner = p1;		
		//score.roadCurrent(new Move(p1, t2, Place.CENTER));

		Assert.assertEquals(p1.points, 0);
		Assert.assertEquals(t2.nodes[12].meepleOwner, p1);

		s.addSetTile(t4);
		score.roadCurrent(new Move(p1, t4, Place.NO_MEEPLE));
		Assert.assertEquals(p1.points, 0);
		Assert.assertEquals(t2.nodes[12].meepleOwner, p1);
		//Assert.assertEquals(t2.nodes[12].meepleOwner, null);
	
		//TODO
	}*/
}
