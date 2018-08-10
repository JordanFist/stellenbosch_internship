package carcassonne.core;

import java.util.Stack;
import java.util.ArrayList;
import carcassonne.ui.Window;

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

	public ArrayList<Tile> monasteries = new ArrayList<Tile>();
	public ArrayList<ArrayList> completeCities = new ArrayList<ArrayList>();

	/** 
	* Return true if the type is in the card
	**/
	public boolean typeOnCard(Tile t, String type) {
		for (Node n : t.nodes) {
			if (n.landType.equals(type) == true)
				return true;
		}
		return false;
	}
	
	/**
	* Return the first node found in the tile with landType == s
	**/
	public Node findLandType(Tile t, String s) {
		for (Node n : t.nodes) {
			if (n.landType.equals(s) == true)
				return n;
		}
		return null;
	}
	
	/**
	* Return an array in which only these players can receive points the others get back their
	* meeples
	**/	
	public void sort(ArrayList<playerMeeple> playerMeeple, Window window) {
		if (playerMeeple.size() > 0) {
			playerMeeple max = playerMeeple.get(0);
			for (playerMeeple pm : playerMeeple) {
				if (max.count < pm.count) 
					max = pm;
			}
			for (int i = 0; i < playerMeeple.size(); ++i) {
				if (playerMeeple.get(i).count < max.count) {
					playerMeeple.get(i).player.giveBackMeeple(playerMeeple.get(i).node, window);
					playerMeeple.remove(playerMeeple.get(i));
				}
			}
		}
	}

	/**
	* Return true if the player is in playerMeeple array and add +1 if it is the case	
	**/	
	public boolean playerContains(ArrayList<playerMeeple> playerMeeple, Player p) {
		for (playerMeeple pm : playerMeeple) {
			if (pm.player == p) {
				++pm.count;
				return true;	
			}
		}
		return false;
	}

	/**
	* Return the number of cards (if a card is shield it counts twice)
	**/	
	public int numberOfCardsWithShield(ArrayList<Tile> Tiles) {
		int res = Tiles.size();
		for (Tile t : Tiles) {
			if (t.shield == true) 
				++res;		
		}
		return res;
	}

	/**
	* Give back Meeples for players in playerMeeple array
	**/
	public void giveBackMeeple(ArrayList<playerMeeple> playerMeeple, Window window) {
		for (playerMeeple pm : playerMeeple) 
			pm.player.giveBackMeeple(pm.node, window);
	}

	/* Beginning ROAD score */

	/**
	* Return true if the road is completed - gets back players on the road - gets back the number 
	* of road tiles 
	**/
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

	/**
	* Give the points to the players on the road
	**/
	public void givePointsRoad(ArrayList<playerMeeple> playersOnRoad, ArrayList<Tile> visitedTiles, Window window) {
		sort(playersOnRoad, window);
		int score = visitedTiles.size();
		for (playerMeeple pm : playersOnRoad)
			pm.player.points += score;
	}
	
	/**
	* Main function - check if a road is completed and give the points
	**/
	public void roadCurrent(Move m, Window window) {	
		ArrayList<playerMeeple> playersOnRoad = new ArrayList<playerMeeple>();
		ArrayList<Tile> visitedTiles = new ArrayList<Tile>();
		Tile t = m.tile;
		if (typeOnCard(t, "ROAD") == true) {
			if (t.name.equals("JUNCTION_CITY") == true || t.name.equals("JUNCTION_THREE") == true || t.name.equals("JUNCTION_FOUR") == true) {
				for (Direction dir : Direction.values()) {
					if (t.getFace(dir).equals("ROAD") == true && completeRoad(t.nodes[dir.getExtend()], playersOnRoad, visitedTiles) == true) {
						givePointsRoad(playersOnRoad, visitedTiles, window);
						giveBackMeeple(playersOnRoad, window);	
						playersOnRoad = new ArrayList<playerMeeple>();	
						visitedTiles = new ArrayList<Tile>();		
					}
				}
			} else if (isCycle(findLandType(t, "ROAD"), playersOnRoad, visitedTiles) == true) {
				completeRoad(findLandType(t, "ROAD"), playersOnRoad, visitedTiles);
				givePointsRoad(playersOnRoad, visitedTiles, window);
				giveBackMeeple(playersOnRoad, window);
			}else if (completeRoad(findLandType(t, "ROAD"), playersOnRoad, visitedTiles) == true) {
				givePointsRoad(playersOnRoad, visitedTiles, window);
				giveBackMeeple(playersOnRoad, window);	
			}
		}
	}
	/**
	* Give the points for incompleted road at the end
	**/
	public void roadEnd(Tile t, Window window) {
		ArrayList<playerMeeple> playersOnRoad = new ArrayList<playerMeeple>();
		ArrayList<Tile> visitedTiles = new ArrayList<Tile>();
		if (t.name.equals("JUNCTION_CITY") == true || t.name.equals("JUNCTION_THREE") == true || t.name.equals("JUNCTION_FOUR") == true) {
			for (Direction dir : Direction.values()) {
				if (t.getFace(dir).equals("ROAD") == true) {
					completeRoad(t.nodes[dir.getExtend()], playersOnRoad, visitedTiles);
					givePointsRoad(playersOnRoad, visitedTiles, window);
					giveBackMeeple(playersOnRoad, window);	
					playersOnRoad = new ArrayList<playerMeeple>();	
					visitedTiles = new ArrayList<Tile>();	
				}
			}
		} else {
			completeRoad(findLandType(t, "ROAD"), playersOnRoad, visitedTiles);
			givePointsRoad(playersOnRoad, visitedTiles, window);
			giveBackMeeple(playersOnRoad, window);	
		}
	}

	/**
	* Return true if the road is a cycle
	**/
	public boolean isCycle (Node n, ArrayList<playerMeeple> playersOnRoad, ArrayList<Tile> visitedTiles) {
		Node check = n;
		Stack<Node> stack = new Stack<Node>();
		Stack<Node> visited = new Stack<Node>();
		stack.add(check);
		while (stack.isEmpty() == false) {
			check = stack.remove(0);
			if (visited.contains(check) == true) 
				return true;
			else
				visited.add(check);

			if (check.meepleOwner != null) {
				if (playerContains(playersOnRoad, check.meepleOwner) == false)
					playersOnRoad.add(new playerMeeple(check.meepleOwner, check));
			}

			if (visitedTiles.contains(check.sourceTile) == false)
				visitedTiles.add(check.sourceTile);

			for (int i = 0; i < check.neighbourNodes.size(); ++i) {
				if (check.neighbourNodes.get(i) != null && visited.contains(check.neighbourNodes.get(i)) == false) {
					stack.add(check.neighbourNodes.get(i));
				}
			}
		}
		return false;
	}


	/* End ROAD score */




	/* Beginning ABBEY score */
	
	/**
	* Return the number of cards around the monastery
	**/
	public int monasteryNeighbour(Tile t) {
		int res = 0;
		for (Direction dir : Direction.values()) 
			if (t.neighbour(dir) != null)
				++res;
		if (t.neighbour(Direction.NORTH) != null && t.neighbour(Direction.NORTH).neighbour(Direction.WEST) != null)
			++res;
		if (t.neighbour(Direction.WEST) != null && t.neighbour(Direction.WEST).neighbour(Direction.SOUTH) != null)
			++res;
		if (t.neighbour(Direction.SOUTH) != null && t.neighbour(Direction.SOUTH).neighbour(Direction.EAST) != null)
			++res;
		if (t.neighbour(Direction.EAST) != null && t.neighbour(Direction.EAST).neighbour(Direction.NORTH) != null)
			++res;
		return res;
	}

	/**
	* Main function - check is a monastery is completed and give the points
	**/
	public void abbeyCurrent(Move m, Window window) {
		int count;
		Player player;
		if (m.tile.nodes[Place.CENTER.get()].landType.equals("ABBEY") && m.place == Place.CENTER)
			monasteries.add(m.tile);
		for (int i = 0; i < monasteries.size(); ++i) {
			Tile t = monasteries.get(i);
			player = t.nodes[Place.CENTER.get()].meepleOwner;
			count = monasteryNeighbour(t);
			if (count == MAX_ABBEY_NEIGHBOUR) {
				player.points += (count + 1);
				player.giveBackMeeple(t.nodes[Place.CENTER.get()], window);
				monasteries.remove(t);
			}
		}	
	}
	
	/**
	* Give the points for incompleted monastery at the end
	**/
	public void abbeyEnd(Node n, Player player, Window window) {
		player.points += (monasteryNeighbour(n.sourceTile) + 1);
		monasteries.remove(n.sourceTile);
		player.giveBackMeeple(n, window);
	}

	/* End ABBEY score */


	
	/* Beginning PLAIN score */

	/**
	* if t is a tile owned to a completed city it returns all the tiles of the completed city in
	* an array
	**/
	public int isCompleteCity(Tile t) {
		for (int i = 0; i < completeCities.size(); ++i) {
			if (completeCities.get(i).contains(t) == true)
				return i;
		}
		return -1;
	}

	/**
	* Return the number of complete cities in the plain	
	**/
	public int findCompleteCities(Node n, ArrayList<playerMeeple> playersInPlain) {
		int res = 0;
		Node check = n;
		Stack<Node> stack = new Stack<Node>();
		Stack<Node> visited = new Stack<Node>();
		stack.add(check);
		visited.add(check);
		while (stack.isEmpty() == false) {
			check = stack.remove(0);

			if (check.meepleOwner != null) {
				if (playerContains(playersInPlain, check.meepleOwner) == false)
					playersInPlain.add(new playerMeeple(check.meepleOwner, check));
			}
			
			if (typeOnCard(check.sourceTile, "CITY") == true && check.plainNextToCity == true) {  //TO IMPROVE
				int tmp = isCompleteCity(check.sourceTile);
				if (tmp != -1) {
					completeCities.remove(tmp);
					++res;
				}
			}

			for (int i = 0; i < check.neighbourNodes.size(); ++i) {
				if (check.neighbourNodes.get(i) != null && visited.contains(check.neighbourNodes.get(i)) == false) {
					stack.add(check.neighbourNodes.get(i));
					visited.add(check.neighbourNodes.get(i));
				}
			}
		}
		return res;
	}

	/**
	* Give the points to the players on the plain
	**/
	public void givePointsPlain(ArrayList<playerMeeple> playersInPlain, int numberOfCompleteCities, Window window) {
		sort(playersInPlain, window);
		for (playerMeeple pm : playersInPlain) {
			pm.player.points += 4 * numberOfCompleteCities;
		}
	}

	/**
	* Main function - Give the points for plain at the end
	**/
	public void plainEnd(Node n, Player p, Window window) {
		p.giveBackMeeple(n, window);
		ArrayList<playerMeeple> playersInPlain = new ArrayList<playerMeeple>();
		int numberOfCompleteCities = findCompleteCities(n, playersInPlain);
		givePointsPlain(playersInPlain, numberOfCompleteCities, window);
		giveBackMeeple(playersInPlain, window);
	}

	/* End PLAIN score */

	

	/* Beginning CITY score */

	/**
	* Fill an array in which there are all the city card around
	**/
	public void neighbourCity (Tile t, ArrayList<Tile> neighbourCityTiles) {
		for (Direction dir : Direction.values()) {
			if (t.getFace(dir).equals("CITY") == true && t.neighbour(dir) != null) {
				neighbourCityTiles.add(t.neighbour(dir));
			}
		}
	}

	/**
	* Return true if a city is completed
	**/
	public boolean cityComplete(Tile t) {
		Tile check;
		Stack<Tile> stack = new Stack<Tile>();
		Stack<Tile> visited = new Stack<Tile>();
		stack.add(t);
		visited.add(t);
		while (stack.isEmpty() == false) {
			check = stack.remove(0);

			for (Direction dir : Direction.values()) {
				if (check.getFace(dir).equals("CITY") && visited.contains(check.neighbour(dir)) == false) {
					if (check.name.equals("PlainTwoCities") == false && check.name.equals("PlainTunnel") == false) {
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

	/**
	* Get back the players in the city
	**/
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

	/**
	* Give the points to players in the city in the current game
	**/
	public void givePointsCity(ArrayList<playerMeeple> playerMeeple, ArrayList<Tile> visitedTiles, Window window) {
		sort(playerMeeple, window);
		int score = numberOfCardsWithShield(visitedTiles);
		for (playerMeeple pm : playerMeeple) {
			pm.player.points += 2 * score;
			if (visitedTiles.size() == 2)
				pm.player.points -= 2;
		}
	}

	/**
	* Main function - Check if a city is completed - give the points for city
	**/
	public void cityCurrent(Move m, Window window) {
		Tile t = m.tile;
		if (typeOnCard(t, "CITY") == true) {
			ArrayList<playerMeeple> playersInCity = new ArrayList<playerMeeple>();
			ArrayList<Tile> visitedTiles = new ArrayList<Tile>();
			if (t.name.equals("PlainTwoCities") != true && t.name.equals("PlainTunnel") != true) {
				if (cityComplete(t) == true) {
					collectPlayer(findLandType(t, "CITY"), playersInCity, visitedTiles);
					completeCities.add(visitedTiles);
					givePointsCity(playersInCity, visitedTiles, window);
					giveBackMeeple(playersInCity, window);
				}
			} else {
				ArrayList<Tile> neighbourCityTiles = new ArrayList<Tile>();
				neighbourCity(t, neighbourCityTiles);
				for (Tile neighbour : neighbourCityTiles) {
					playersInCity = new ArrayList<playerMeeple>();
					visitedTiles = new ArrayList<Tile>();
					if (neighbour.name.equals("PlainTwoCities") == true || neighbour.name.equals("PlainTunnel") == true) {
						collectPlayer(findLandType(t, "CITY"), playersInCity, visitedTiles);
						completeCities.add(visitedTiles);
						givePointsCity(playersInCity, visitedTiles, window);
						giveBackMeeple(playersInCity, window);
					} else if (cityComplete(neighbour) == true) {
						collectPlayer(findLandType(t, "CITY"), playersInCity, visitedTiles);
						completeCities.add(visitedTiles);
						givePointsCity(playersInCity, visitedTiles, window);
						giveBackMeeple(playersInCity, window);
					}
				}			
			}
		}	
	}
	
	/**
	* Give the points for incompleted city at the end
	**/
	public void givePointsCityEnd(ArrayList<playerMeeple> playerMeeple, ArrayList<Tile> visitedTiles, Window window) {
		sort(playerMeeple, window);
		int score = numberOfCardsWithShield(visitedTiles);
		for (playerMeeple pm : playerMeeple) {
			pm.player.points += score;
		}
	}
	
	/**
	* Give the points for city at the end
	**/
	public void cityEnd(Node n, Window window) {
		ArrayList<playerMeeple> playersInCity = new ArrayList<playerMeeple>();
		ArrayList<Tile> visitedTiles = new ArrayList<Tile>();
		collectPlayer(n, playersInCity, visitedTiles);
		givePointsCityEnd(playersInCity, visitedTiles, window);
		giveBackMeeple(playersInCity, window);
	}

	/* End CITY score */

	/**
	* Give the points for the meeples left in meeples player array
	**/
	public void end(Players players, Window window) {
		Node n;
		for (Player p : players.players) {
			for (int i = 0; i < p.meeples.size(); ++i) {
				n = p.meeples.get(i);
				if (n.landType.equals("ABBEY") == true) {
					abbeyEnd(n, p, window);
					--i;	// when a node is removed of meeples array, the array is shifted on the left, thus --i
				}
				if (n.landType.equals("ROAD") == true) {
					roadEnd(n.sourceTile, window);	
					--i;
				}
				if (n.landType.equals("PLAIN") == true) {
					plainEnd(n, p, window);		
					--i;				
				}		
				if (n.landType.equals("CITY") == true) {
					cityEnd(n, window);		
					--i;
				}		
			}		
		}
	}
}

