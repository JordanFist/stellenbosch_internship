import org.junit.Test;
import org.junit.Assert;

import carcassonne.core.setTile;
import carcassonne.core.Position;
import carcassonne.core.Direction;
import carcassonne.core.Node;
import carcassonne.core.Player;
import carcassonne.core.tiles.*;

public class testNode {
	@Test
	public void testConstructor() {
		MonasteryRoad t = new MonasteryRoad(); 
		Node node = new Node("ROAD", t);
	}
	
	@Test
	public void testConnection() {
		MonasteryRoad t1 = new MonasteryRoad(); 
		Node n1 = new Node("ROAD", t1);
		MonasteryRoad t2 = new MonasteryRoad(); 
		Node n2 = new Node("ROAD", t2);

		n1.connection(n2);
		Assert.assertEquals(n1.neighbourNodes.get(0), n2);
		Assert.assertEquals(n2.neighbourNodes.get(0), n1);
	}

	@Test
	public void testDisonnection() {
		MonasteryRoad t1 = new MonasteryRoad(); 
		Node n1 = new Node("ROAD", t1);
		MonasteryRoad t2 = new MonasteryRoad(); 
		Node n2 = new Node("ROAD", t2);

		n1.connection(n2);
		n1.disconnection(n2);
		Assert.assertEquals(n1.neighbourNodes.size(), 0);
		Assert.assertEquals(n2.neighbourNodes.size(), 0);
	}

	@Test 
	public void testIsMeepleInArea() {
		setTile s1 = new setTile();
		CityThreeRoad t1 = new CityThreeRoad();
		RoadTurnRightCity t2 = new RoadTurnRightCity();
		MonasteryRoad t3 = new MonasteryRoad();
		
		t1.pos = new Position(-1, 1);
		t2.pos = new Position(-1, 0);
		t2.dir = Direction.WEST;
		t2.rotation(Direction.WEST);
		t3.pos = new Position(1, 0);
		t3.dir = Direction.EAST;
		t3.rotation(Direction.EAST);
		
		s1.addSetTile(t1);
		s1.addSetTile(t2);
		s1.addSetTile(t3);
		
		Player p1 = new Player(0);

		Assert.assertEquals(t1.nodes[8].isMeepleInArea(), false);
		t1.nodes[6].meepleOwner = p1;	
		Assert.assertEquals(t1.nodes[8].isMeepleInArea(), true);	
	}
}
