import org.junit.Test;
import org.junit.Assert;

import carcassonne.core.Player;
import carcassonne.core.Players;

public class testPlayers {
	@Test
	public void testConstructor() {
		Players p = new Players(3);
	}

	@Test
	public void testComputeNext() {
		Players p = new Players(3);
		p.players.get(0).eject();
		Player expected = p.players.get(1); 
		Player actual = p.computeNext(p.players.get(2));
		Assert.assertEquals(expected, actual);	

		Players p2 = new Players(3);
		p2.players.get(2).eject();
		expected = p2.players.get(0); 
		actual = p2.computeNext(p.players.get(1));
		Assert.assertEquals(expected, actual);		
	}
}
