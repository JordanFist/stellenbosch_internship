package carcassonne.core;

import java.util.Stack;
import java.util.ArrayList;

class playerMeeple {
	int count;
	Player player;
	Node node;
	
	public playerMeeple (Player player, Node node){
		this.player = player;
		this.node = node;
	}
}

public class Score {/*
	public static Node findLandType(Tile t, String s) {
		for (Node n : t.nodes) {
			if (n.landType.equals(s) == true)
				return n;
		}
		return null;
	}

	public int findNode() {
		return .indexOf();
	}

	public static void scoreRoad(Tile t) {
		ArrayList<playerMeeple> playersOnRoad = new ArrayList<playerMeeple>();
		int res = 0;
		Node check = findLandType(t, "ROAD");
		if (check != null) {
			Stack<Node> stack = new Stack<Node>();
			Stack<Node> visited = new Stack<Node>();
			Stack<Tile> visitedTiles = new Stack<Tile>();
			stack.add(check);
			visited.add(check);
			visitedTiles.add(check.sourceTile);
			while (stack.isEmpty() == false) {
				check = stack.remove(0);
				if (check != null && check.endRoad == true)
					++res;
				if (check != null && check.meepleOwner != null)
					playersOnRoad.add(new playerMeeple(check.meepleOwner, check));
				if (check != null && visitedTiles.contains(check.sourceTile) == false)
					visitedTiles.add(check.sourceTile);
				for (int i = 0; i < check.neighbourNodes.size(); ++i) {
					if (check.neighbourNodes.get(i) != null && visited.contains(check.neighbourNodes.get(i)) == false) {
						stack.add(check.neighbourNodes.get(i));
						visited.add(check.neighbourNodes.get(i));
					}
				}
			}
			if (res == 2 && playersOnRoad.size() > 0) {
				for (PlayerMeeple p : playersOnRoad) {
					p.player.meeples.remove(p.player.meeples.indexOf(p.node));	
					++p.count;
				}
				Player max1 = playersOnRoad.get(0);
				Player max2 = playersOnRoad.get(0);
				for (PlayerMeeple p : playersOnRoad) {
					if (max1.count < p.player.count) {
						max2 = max1;
						max1 = p.player;
					}				
				}
							
			}
				
		}
	}*/
}
