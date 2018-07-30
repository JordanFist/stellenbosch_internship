package carcassonne.core;

import java.util.Stack;
import java.util.ArrayList;

class playerMeeple {
	int count = 1;
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
	ArrayList<ArrayList> completeCities = new ArrayList<ArrayList>();

	// Retrun true if the type is in the card
	public boolean typeOnCard(Tile t, String type) {
		for (Node n : t.nodes) {
			if (n.landType.equals(type) == true)
				return true;
		}
		return false;
	}
	
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
		if (playerMeeple.size() > 0) {
			playerMeeple max = playerMeeple.get(0);
			for (playerMeeple pm : playerMeeple) {
				if (max.count < pm.count) 
					max = pm;
			}
			for (int i = 0; i < playerMeeple.size(); ++i) {
				if (playerMeeple.get(i).count < max.count) {
					playerMeeple.get(i).player.giveBackMeeple(playerMeeple.get(i).node);
					playerMeeple.remove(playerMeeple.get(i));
				}
			}
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
	public int numberOfCardsWithShield(ArrayList<Tile> Tiles) {
		int res = Tiles.size();
		for (Tile t : Tiles) {
			if (t.shield == true) 
				++res;		
		}
		return res;
	}

	// Give back Meeples for players
	public void giveBackMeeple(ArrayList<playerMeeple> playerMeeple) {
		for (playerMeeple pm : playerMeeple) 
			pm.player.giveBackMeeple(pm.node);
	}

	/* Beginning ROAD score */

	public boolean completeRoad(Node n, ArrayList<playerMeeple> playersOnRoad, ArrayList<Tile> visitedTiles) {
		int numberOfEndRoads = 0;
		Node check = n;
		Stack<Node> stack = new Stack<Node>();
		Stack<Node> visited = new Stack<Node>();
		stack.add(check);
		visited.add(check);
		visitedTiles.add(check.sourceTile);
		while (stack.isEmpty() == false) {
			check = stack.remove(0);
			if (check.endRoad == true)
				++numberOfEndRoads;

			if (check.meepleOwner != null) {
				if (playerContains(playersOnRoad, check.meepleOwner) == false)
					playersOnRoad.add(new playerMeeple(check.meepleOwner, check));
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
		if (numberOfEndRoads == 2)
			return true;
		return false;
	}

	public void givePointsRoad(ArrayList<playerMeeple> playersOnRoad, ArrayList<Tile> visitedTiles) {
		sort(playersOnRoad);
		int score = visitedTiles.size();
		for (playerMeeple pm : playersOnRoad)
			pm.player.points += score;
	}
	
	public void roadCurrent(Move m) {	
		ArrayList<playerMeeple> playersOnRoad = new ArrayList<playerMeeple>();
		ArrayList<Tile> visitedTiles = new ArrayList<Tile>();
		Tile t = m.tile;
		if (typeOnCard(t, "ROAD") == true) {
			if (t.name.equals("JUNCTION_CITY") == true || t.name.equals("JUNCTION_THREE") == true || t.name.equals("JUNCTION_FOUR") == true) {
				for (directionId dir : directionId.values()) {
					if (t.getFace(dir).equals("ROAD") == true && completeRoad(t.nodes[Direction.getDirection(dir)], playersOnRoad, visitedTiles) == true) {
						givePointsRoad(playersOnRoad, visitedTiles);
						giveBackMeeple(playersOnRoad);	
						playersOnRoad = new ArrayList<playerMeeple>();	
						visitedTiles = new ArrayList<Tile>();		
					}
				}
			} else if (completeRoad(findLandType(t, "ROAD"), playersOnRoad, visitedTiles) == true) {
				givePointsRoad(playersOnRoad, visitedTiles);
				giveBackMeeple(playersOnRoad);	
			}
		}
	}

	public void roadEnd(Tile t) {
		ArrayList<playerMeeple> playersOnRoad = new ArrayList<playerMeeple>();
		ArrayList<Tile> visitedTiles = new ArrayList<Tile>();
		if (t.name.equals("JUNCTION_CITY") == true || t.name.equals("JUNCTION_THREE") == true || t.name.equals("JUNCTION_FOUR") == true) {
			for (directionId dir : directionId.values()) {
				if (t.getFace(dir).equals("ROAD") == true) {
					completeRoad(t.nodes[Direction.getDirection(dir)], playersOnRoad, visitedTiles);
					givePointsRoad(playersOnRoad, visitedTiles);
					giveBackMeeple(playersOnRoad);	
					playersOnRoad = new ArrayList<playerMeeple>();	
					visitedTiles = new ArrayList<Tile>();	
				}
			}
		} else {
			completeRoad(findLandType(t, "ROAD"), playersOnRoad, visitedTiles);
			givePointsRoad(playersOnRoad, visitedTiles);
			giveBackMeeple(playersOnRoad);	
		}
	}


	/* End ROAD score */




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


	
	/* Beginning PLAIN score */

	public int findCompleteCities(Node n, ArrayList<playerMeeple> playersInPlain) {
		//TODO
		return 0;
	}

	public void givePointsPlain(ArrayList<playerMeeple> playersInPlain, int numberOfCompleteCities) {
		sort(playersInPlain);
		for (playerMeeple pm : playersInPlain) {
			pm.player.points += 4 * numberOfCompleteCities;
		}
	}

	public void plainEnd(Node n, Player p) {
		ArrayList<playerMeeple> playersInPlain = new ArrayList<playerMeeple>();
		int numberOfCompleteCities = findCompleteCities(n, playersInPlain);
		givePointsPlain(playersInPlain, numberOfCompleteCities);
		giveBackMeeple(playersInPlain);
	}

	/* End PLAIN score */

	

	/* Beginning CITY score */

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
					if (check.name != "PlainTwoCities" && check.name != "PlainTunnel") {
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

	public void collectPlayer(Node n, ArrayList<playerMeeple> playersInCity, ArrayList<Tile> visitedTiles) {
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

	public void givePointsCity(ArrayList<playerMeeple> playerMeeple, ArrayList<Tile> Tiles) {
		sort(playerMeeple);
		int score = numberOfCardsWithShield(Tiles);
		for (playerMeeple pm : playerMeeple) {
			pm.player.points += 2 * score;
			if (Tiles.size() == 2)
				pm.player.points -= 2;
		}
	}

	public void cityCurrent(Move m) {
		Tile t = m.tile;
		if (typeOnCard(t, "CITY") == true) {
			ArrayList<playerMeeple> playersInCity = new ArrayList<playerMeeple>();
			ArrayList<Tile> visitedTiles = new ArrayList<Tile>();
			if (t.name.equals("PlainTwoCities") != true && t.name.equals("PlainTunnel") != true) {
				if (cityComplete(t) == true) {
					completeCities.add(visitedTiles);
					collectPlayer(findLandType(t, "CITY"), playersInCity, visitedTiles);
					givePointsCity(playersInCity, visitedTiles);
					giveBackMeeple(playersInCity);
				}
			} else {
				ArrayList<Tile> neighbourCityTiles = new ArrayList<Tile>();
				//neighbourCity(t, neighbourCityTiles);
				for (Tile neighbour : neighbourCityTiles) {
					if (neighbour.name.equals("PlainTwoCities") == true || neighbour.name.equals("PlainTunnel") == true)
						;//TODO
					else if (cityComplete(neighbour) == true) {
						//completeCities.add(visitedTiles);
						//collectPlayer(findLandType(t, "CITY"), playersInCity, visitedTiles);
						//givePointsCity(playersInCity, visitedTiles);
						//giveBackMeeple(playersInCity);
					}
				}			
			}
		}	
	}

	/* End CITY score */

	public void end(Players players) {
		Node n;
		for (Player p : players.players) {
			for (int i = 0; i < p.meeples.size(); ++i) {
				n = p.meeples.get(i);
				if (n.landType.equals("ABBEY") == true) 
					;//abbeyEnd(n.sourceTile, p);
				if (n.landType.equals("ROAD") == true) 
					;//roadEnd(n.sourceTile);	
				if (n.landType.equals("PLAIN") == true)
					;//plainEnd(n, p);				
				if (n.landType.equals("CITY") == true)
					;//cityEnd();				
			}		
		}
	}
}

