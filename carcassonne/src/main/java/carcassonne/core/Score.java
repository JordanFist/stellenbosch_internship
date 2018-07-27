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

public class Score {
	public static final int MAX_ABBEY_NEIGHBOUR = 8;

	ArrayList<Tile> monasteries = new ArrayList<Tile>();
	
	// Return the first node found in the tile with landType = s
	public static Node findLandType(Tile t, String s) {
		for (Node n : t.nodes) {
			if (n.landType.equals(s) == true)
				return n;
		}
		return null;
	}
	
	// Return an array in which only these players can receive points
	public static void sort(ArrayList<playerMeeple> playerMeeple) {
		playerMeeple max = playerMeeple.get(0);
		for (playerMeeple pm : playerMeeple) {
			if (max.count < pm.count) 
				max = pm;
		}
		for (playerMeeple pm : playerMeeple) {
			if (pm.count < max.count)
				playerMeeple.remove(pm);
		}
	}

	// Return true if the player is in playerMeeple and add +1 if it is the case
	public boolean playerContains(ArrayList<playerMeeple> playerMeeple, Player p) {
		for (playerMeeple pm : playerMeeple) {
			if (pm.player == p) {
				++pm.count;
				return true;	
			}
		}
		return false;
	}

	// Return the number of cards (if a card is shield it counts twice)
	public int numberOfCards(ArrayList<Tile> Tiles) {
		int res = Tiles.size();
		for (Tile t : Tiles) {
			if (t.shield == true) 
				++res;		
		}
		return res;
	}
	/*
	public void scoreRoad(Tile t) {
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
				for (playerMeeple p : playersOnRoad) {
					p.player.meeples.remove(p.player.meeples.indexOf(p.node));	
					++p.count;
				}
				sort(playersOnRoad);
				int max = playersOnRoad.get(0).count;
				for (playerMeeple p : playersOnRoad) {
					if (p.count == max)
						p.player.points += visitedTiles.size();
				}				
			}
				
		}
	}*/












	/* Beginning ABBEY score */
	
	public int monasteryNeighbour(Tile t) {
		int res = 0;
		for (directionId dir : directionId.values()) 
			if (t.neighbour(dir) != null)
				++res;
		if (t.neighbour(directionId.NORTH) != null && t.neighbour(directionId.NORTH).neighbour(directionId.WEST) != null)
			++res;
		if (t.neighbour(directionId.WEST) != null && t.neighbour(directionId.WEST).neighbour(directionId.SOUTH) != null)
			++res;
		if (t.neighbour(directionId.SOUTH) != null && t.neighbour(directionId.SOUTH).neighbour(directionId.EAST) != null)
			++res;
		if (t.neighbour(directionId.EAST) != null && t.neighbour(directionId.EAST).neighbour(directionId.NORTH) != null)
			++res;
		return res;
	}

	public void abbeyCurrent(Move m) {
		int count;
		Player player;
		if (m.tile.nodes[placeId.POS_CENTER.toInt()].landType.equals("ABBEY") && m.place == placeId.POS_CENTER)
			monasteries.add(m.tile);
		for (int i = 0; i < monasteries.size(); ++i) {
			Tile t = monasteries.get(i);
			player = t.nodes[placeId.POS_CENTER.toInt()].meepleOwner;
			count = monasteryNeighbour(t);
			if (count == MAX_ABBEY_NEIGHBOUR) {
				player.points += (count + 1);
				player.giveBackMeeple(t.nodes[placeId.POS_CENTER.toInt()]);
				monasteries.remove(t);
			}
		}	
	}
	
	public void abbeyEnd(Tile t, Player player) {
		player.points += (monasteryNeighbour(t) + 1);
	}

	/* End ABBEY score */

	

	/* Beginning City score */

	public void neighbourCity (Tile t, ArrayList<Tile> neighbourCityTiles) {
		for (directionId dir : directionId.values()) {
			if (t.getFace(dir).equals("CITY") == true && t.neighbour(dir) != null) {
				neighbourCityTiles.add(t.neighbour(dir));
			}
		}
	}

	public boolean cityComplete(Tile t) {
		Tile check;
		Stack<Tile> stack = new Stack<Tile>();
		Stack<Tile> visited = new Stack<Tile>();
		stack.add(t);
		visited.add(t);
		while (stack.isEmpty() == false) {
			check = stack.remove(0);

			for (directionId dir : directionId.values()) {
				if (check.getFace(dir).equals("CITY") && visited.contains(check.neighbour(dir)) == false) {
					if (check.name != "PLAIN_TWO_CITIES" && check.name != "PLAIN_TUNNEL") {
						if (check.neighbour(dir) == null)
							return false;
						stack.add(check.neighbour(dir));
						visited.add(check.neighbour(dir));
					}
				}
			}
		}
		return true;
	}

	public void recoverPlayer(Node n, ArrayList<playerMeeple> playersInCity, ArrayList<Tile> visitedTiles) {
		Node check = n;
		Stack<Node> stack = new Stack<Node>();
		Stack<Node> visited = new Stack<Node>();
		stack.add(check);
		visited.add(check);
		visitedTiles.add(check.sourceTile);
		while (stack.isEmpty() == false) {
			check = stack.remove(0);

			if (check.meepleOwner != null) {
				if (playerContains(playersInCity, check.meepleOwner) == false)
					playersInCity.add(new playerMeeple(check.meepleOwner, check));
			}

			if (visitedTiles.contains(check.sourceTile) == false)
				visitedTiles.add(check.sourceTile);

			for (int i = 0; i < check.neighbourNodes.size(); ++i) {
				if (check.neighbourNodes.get(i) != null && visited.contains(check.neighbourNodes.get(i)) == false) {
					stack.add(check.neighbourNodes.get(i));
					visited.add(check.neighbourNodes.get(i));
				}
			}
		}
	}

	public void givePoints(ArrayList<playerMeeple> playerMeeple, ArrayList<Tile> Tiles) {
		sort(playerMeeple);
		int score = numberOfCards(Tiles);
		for (playerMeeple pm : playerMeeple) {
			pm.player.points += score;
		}
	}

	public void cityCurrent(Move m) {
		ArrayList<playerMeeple> playersInCity = new ArrayList<playerMeeple>();
		ArrayList<Tile> visitedTiles = new ArrayList<Tile>();
		Tile t = m.tile;
		if (t.name.equals("PLAIN_TWO_CITIES") != true && t.name.equals("PLAIN_TUNNEL") != true) {
			if (cityComplete(t) == true) {
				givePoints(playersInCity, visitedTiles);
			}
		} else {
			ArrayList<Tile> neighbourCityTiles = new ArrayList<Tile>();
			neighbourCity(t, neighbourCityTiles);
			for (Tile neighbour : neighbourCityTiles) {
				if (neighbour.name.equals("PLAIN_TWO_CITIES") == true || neighbour.name.equals("PLAIN_TUNNEL") == true)
					;//TODO
				else if (cityComplete(neighbour) == true) {
					givePoints(playersInCity, visitedTiles);
				}
			}			
		}
			
	}

	/* End City score */

	public void end(Players players) {
		for (Player p : players.players) {
			for (Node n : p.meeples) {
				if (n.landType == "ABBEY") 
					abbeyEnd(n.sourceTile, p);
				if (n.landType == "ROAD")
					;//roadEnd;					
			}
		}		
	}
}

