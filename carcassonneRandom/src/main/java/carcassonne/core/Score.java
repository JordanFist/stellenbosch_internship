package carcassonne.core;

import java.util.Stack;
import java.util.ArrayList;
import java.util.HashSet;

class playerMeeple {
	Player player;
	ArrayList<Node> nodes = new ArrayList<Node>();
	
	public playerMeeple (Player player, Node node){
		this.player = player;
		this.nodes.add(node);
	}
}

public class Score {
	public static final int MAX_ABBEY_NEIGHBOUR = 8;

	public ArrayList<Tile> monasteries = new ArrayList<Tile>();
	public HashSet<HashSet> completeCities = new HashSet<HashSet>();

	/*
	* Update player points
	*/
	public void update(Move m, setTile s) {
		abbeyCurrent(m, s);
		roadCurrent(m);
		cityCurrent(m);
	}

	/**
	* Give the points for the meeples left in meeples player array
	**/
	public void updateEnd(setTile s, Players players) {
		Node n;
		for (Player p : players.players) {
			for (int i = 0; i < p.meeples.size(); ++i) {
				n = p.meeples.get(i);
				if (n.landType.equals("ABBEY") == true) 
					abbeyEnd(s, n, p);
				if (n.landType.equals("ROAD") == true) 
					roadEnd(n.sourceTile);	
				if (n.landType.equals("PLAIN") == true) 
					plainEnd(n, p);					
				if (n.landType.equals("CITY") == true) 
					cityEnd(n);		
			}
		}
	}

	/** 
	* Return true if the type is in the card
	**/
	public boolean isTypeOnCard(Tile t, String type) {
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
	* Return an array in which only these players can receive points - the others get back their
	* meeples
	**/	
	public ArrayList<playerMeeple> sort(ArrayList<playerMeeple> playerMeeple) {
		ArrayList<playerMeeple> winners = new ArrayList<playerMeeple>();
		if (playerMeeple.size() > 0) {
			playerMeeple max = playerMeeple.get(0);
			for (playerMeeple pm : playerMeeple) {
				if (max.nodes.size() < pm.nodes.size()) 
					max = pm;
			}
			for (playerMeeple pm : playerMeeple) {
				if (pm.nodes.size() < max.nodes.size()) {    
					for (Node n : pm.nodes) 
						pm.player.giveBackMeeple(n);
				} else
					winners.add(pm);
			}
		}
		return winners;		
	}

	/**
	* Return an array in which only these players can receive points - the others are removed
	* of the map
	**/	
	public ArrayList<playerMeeple> sortEnd(ArrayList<playerMeeple> playerMeeple) {
		ArrayList<playerMeeple> winners = new ArrayList<playerMeeple>();
		if (playerMeeple.size() > 0) {
			playerMeeple max = playerMeeple.get(0);
			for (playerMeeple pm : playerMeeple) {
				if (max.nodes.size() < pm.nodes.size()) 
					max = pm;
			}
			for (playerMeeple pm : playerMeeple) {
				if (pm.nodes.size() < max.nodes.size()) {    
					for (Node n : pm.nodes) 
						n.meepleOwner = null;
				} else
					winners.add(pm);
			}
		}
		return winners;		
	}

	/**
	* Return true if the player is in the playerMeeple array and add its node in nodes	
	**/	
	public boolean playerContains(ArrayList<playerMeeple> playerMeeple, Node n) {
		for (playerMeeple pm : playerMeeple) {
			if (pm.player == n.meepleOwner) {
				pm.nodes.add(n);
				return true;	
			}
		}
		return false;
	}

	/**
	* Return the number of cards (if a card is shield it counts twice)
	**/	
	public int numberOfCardsWithShield(HashSet<Tile> Tiles) {
		int res = Tiles.size();
		for (Tile t : Tiles) {
			if (t.shield == true) 
				++res;		
		}
		return res;
	}

	/**
	* Give back Meeples for players in the playerMeeple array
	**/
	public void giveBackMeeple(ArrayList<playerMeeple> playerMeeple) {
		for (playerMeeple pm : playerMeeple) { 
			for (Node n : pm.nodes) 
				pm.player.giveBackMeeple(n);
		}
	}

	/**
	* Remove Meeples on the map for players in the playerMeeple array
	**/
	public void giveBackMeepleEnd(ArrayList<playerMeeple> playerMeeple) {
		for (playerMeeple pm : playerMeeple) { 
			for (Node n : pm.nodes) 
				n.meepleOwner = null;
		}
	}

	/* =========================================================== Begin Road score =========================================================== */

	/**
	* Give the points to the players on the road
	**/
	public void givePointsRoad(ArrayList<playerMeeple> playersOnRoad, HashSet<Tile> visitedTiles) {
		playersOnRoad = sort(playersOnRoad);
		int score = visitedTiles.size();
		for (playerMeeple pm : playersOnRoad) {
			int points = pm.player.points;
			pm.player.points += score;
			System.out.println("Road finished -> player " + pm.player.id + " scored " + (pm.player.points - points));
		}
	}

	/**
	* Give the points to the players on the road for the end game
	**/
	public void givePointsRoadEnd(ArrayList<playerMeeple> playersOnRoad, HashSet<Tile> visitedTiles) {
		playersOnRoad = sortEnd(playersOnRoad);
		int score = visitedTiles.size();
		for (playerMeeple pm : playersOnRoad) {
			int points = pm.player.points;
			pm.player.points += score;
			System.out.println("Road -> player " + pm.player.id + " scored " + (pm.player.points - points));
		}
	}

	/**
	* Return true if the road is completed - gets back players on the road - gets back the number 
	* of road tiles 
	**/
	public boolean completeRoad(Node n, ArrayList<playerMeeple> playersOnRoad, HashSet<Tile> visitedTiles) {
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
				if (playerContains(playersOnRoad, check) == false)
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
	* Main function - check if a road is completed and give the points
	**/
	public void roadCurrent(Move m) {	
		ArrayList<playerMeeple> playersOnRoad = new ArrayList<playerMeeple>();
		HashSet<Tile> visitedTiles = new HashSet<Tile>();
		Tile t = m.tile;
		if (isTypeOnCard(t, "ROAD") == true) {
			if (t.name.equals("JUNCTION_CITY") == true || t.name.equals("JUNCTION_THREE") == true || t.name.equals("JUNCTION_FOUR") == true) {
				for (Direction dir : Direction.values()) {
					if (t.getFace(dir).equals("ROAD") == true && completeRoad(t.nodes[dir.getExtend()], playersOnRoad, visitedTiles) == true) {
						givePointsRoad(playersOnRoad, visitedTiles);
						giveBackMeeple(playersOnRoad);		
					}
					playersOnRoad = new ArrayList<playerMeeple>();	
					visitedTiles = new HashSet<Tile>();	
				}
			} else if (isCycle(findLandType(t, "ROAD"), playersOnRoad, visitedTiles) == true) {
				completeRoad(findLandType(t, "ROAD"), playersOnRoad, visitedTiles);
				givePointsRoad(playersOnRoad, visitedTiles);
				giveBackMeeple(playersOnRoad);
			}else if (completeRoad(findLandType(t, "ROAD"), playersOnRoad, visitedTiles) == true) {
				givePointsRoad(playersOnRoad, visitedTiles);
				giveBackMeeple(playersOnRoad);	
			}
		}
	}

	/**
	* Give the points for incompleted road for the end game
	**/
	public void roadEnd(Tile t) {
		ArrayList<playerMeeple> playersOnRoad = new ArrayList<playerMeeple>();
		HashSet<Tile> visitedTiles = new HashSet<Tile>();
		if (t.name.equals("JUNCTION_CITY") == true || t.name.equals("JUNCTION_THREE") == true || t.name.equals("JUNCTION_FOUR") == true) {
			for (Direction dir : Direction.values()) {
				if (t.getFace(dir).equals("ROAD") == true) {
					completeRoad(t.nodes[dir.getExtend()], playersOnRoad, visitedTiles);
					givePointsRoadEnd(playersOnRoad, visitedTiles);
					giveBackMeepleEnd(playersOnRoad);		
				}
				playersOnRoad = new ArrayList<playerMeeple>();	
				visitedTiles = new HashSet<Tile>();
			}
		} else {
			completeRoad(findLandType(t, "ROAD"), playersOnRoad, visitedTiles);
			givePointsRoadEnd(playersOnRoad, visitedTiles);
			giveBackMeepleEnd(playersOnRoad);	
		}
	}

	/**
	* Return true if the road is a cycle
	**/
	public boolean isCycle (Node n, ArrayList<playerMeeple> playersOnRoad, HashSet<Tile> visitedTiles) {
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
				if (playerContains(playersOnRoad, check) == false)
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

	/* ============================================================ End ROAD score ============================================================= */

	/* =========================================================== Begin ABBEY score =========================================================== */
	
	/**
	* Return the number of cards around the monastery
	**/
	public int monasteryNeighbour(Tile t, setTile s) {
		int res = 0;
		for (Direction dir : Direction.values()) 
			if (t.neighbour(dir) != null)
				++res;
		if (s.findTile(new Position(t.pos.x - 1, t.pos.y + 1)) != null)
			++res;
		if (s.findTile(new Position(t.pos.x - 1, t.pos.y - 1)) != null)
			++res;
		if (s.findTile(new Position(t.pos.x + 1, t.pos.y + 1)) != null)
			++res;
		if (s.findTile(new Position(t.pos.x + 1, t.pos.y - 1)) != null)
			++res;
		return res;
	}

	/**
	* Main function - check is a monastery is completed and give the points
	**/
	public void abbeyCurrent(Move m, setTile s) {
		int count;
		Player player;
		if (m.tile.nodes[Place.CENTER.get()].landType.equals("ABBEY") && m.place == Place.CENTER)
			monasteries.add(m.tile);
		for (int i = 0; i < monasteries.size(); ++i) {
			Tile t = monasteries.get(i);
			player = t.nodes[Place.CENTER.get()].meepleOwner;
			count = monasteryNeighbour(t, s);
			if (count == MAX_ABBEY_NEIGHBOUR) {
				int points = player.points;
				player.points += (count + 1);
				System.out.println("Abbey finished -> player " + player.id + " scored " + (player.points - points));
				player.giveBackMeeple(t.nodes[Place.CENTER.get()]);
				monasteries.remove(t);
			}
		}	
	}
	
	/**
	* Give the points for incompleted monastery at the end
	**/
	public void abbeyEnd(setTile s, Node n, Player player) {
		int points = player.points;
		player.points += (monasteryNeighbour(n.sourceTile, s) + 1);
		System.out.println("Abbey -> player " + player.id + " scored " + (player.points - points));
		monasteries.remove(n.sourceTile);
		n.meepleOwner = null;
	}

	/* ============================================================ End ABBEY score ============================================================= */

	/* =========================================================== Begin PLAIN score ============================================================ */
	
	/**
	* Give the points to the players on the plain for the end game
	**/
	public void givePointsPlainEnd(ArrayList<playerMeeple> playersInPlain, int numberOfCompleteCities) {
		playersInPlain = sortEnd(playersInPlain);
		for (playerMeeple pm : playersInPlain) {
			int points = pm.player.points;
			pm.player.points += 4 * numberOfCompleteCities;
			if (pm.player.points - points != 0)
				System.out.println("Plain -> player " + pm.player.id + " scored " + (pm.player.points - points));
		}
	}

	/**
	* Return true if Tile t belongs to at least one array of completeCities
	**/
	public boolean isCompleteCity(Tile t) {
		for (HashSet h : completeCities) {
			if (h.contains(t) == true) {
				completeCities.remove(h); 
				return true;			
			}
		}
		return false;
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
				if (playerContains(playersInPlain, check) == false)
					playersInPlain.add(new playerMeeple(check.meepleOwner, check));
			}
			
			if (isTypeOnCard(check.sourceTile, "CITY") == true && check.plainNextToCity == true) {
				if (isCompleteCity(check.sourceTile) == true)
					++res;
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
	* Main function - Give the points for plain for the end game
	**/
	public void plainEnd(Node n, Player p) {
		ArrayList<playerMeeple> playersInPlain = new ArrayList<playerMeeple>();
		int numberOfCompleteCities = findCompleteCities(n, playersInPlain);
		givePointsPlainEnd(playersInPlain, numberOfCompleteCities);
		giveBackMeepleEnd(playersInPlain);
	}

	/* ============================================================ End PLAIN score ============================================================= */

	/* ============================================================ Begin CITY score ============================================================ */

	/**
	* Give the points to players in the city for the current game
	**/
	public void givePointsCity(ArrayList<playerMeeple> playersInCity, HashSet<Tile> visitedTiles) {
		playersInCity = sort(playersInCity);
		int score = numberOfCardsWithShield(visitedTiles);
		for (playerMeeple pm : playersInCity) {
			int points = pm.player.points;
			pm.player.points += 2 * score;
			if (visitedTiles.size() == 2)
				pm.player.points -= 2;
			System.out.println("City finished -> player " + pm.player.id + " scored " + (pm.player.points - points));
		}
	}

	/**
	* Give the points for incompleted city for the end game
	**/
	public void givePointsCityEnd(ArrayList<playerMeeple> playersInCity, HashSet<Tile> visitedTiles) {
		playersInCity = sortEnd(playersInCity);
		int score = numberOfCardsWithShield(visitedTiles);
		for (playerMeeple pm : playersInCity) {
			int points = pm.player.points;
			pm.player.points += score;
			System.out.println("City -> player " + pm.player.id + " scored " + (pm.player.points - points));
		}
	}

	/*
	* Return the node next to neighbour
	*/
	public Node neighbourNode(Tile t, Tile neighbour) {
		for (Direction dir : Direction.values()) {
			if (t.getFace(dir).equals("CITY") && t.neighbour(dir) == neighbour)
				return t.nodes[dir.getExtend()];
		}
		return null;
	}

	/**
	* Fill an array in which there are all the city card around
	**/
	public void neighbourCity (Tile t, HashSet<Tile> neighbourCityTiles) {
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
					if (check.name.equals("PLAIN_TWO_CITIES") == false && check.name.equals("PLAIN_TUNNEL") == false) {
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
	public void collectPlayer(Node n, ArrayList<playerMeeple> playersInCity, HashSet<Tile> visitedTiles) {
		Node check = n;
		Stack<Node> stack = new Stack<Node>();
		Stack<Node> visited = new Stack<Node>();
		stack.add(check);
		visited.add(check);
		visitedTiles.add(check.sourceTile);
		while (stack.isEmpty() == false) {
			check = stack.remove(0);

			if (check.meepleOwner != null) {
				if (playerContains(playersInCity, check) == false)
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
	* Main function - Check if a city is completed - give the points for city
	**/
	public void cityCurrent(Move m) {
		Tile t = m.tile;
		if (isTypeOnCard(t, "CITY") == true) {
			ArrayList<playerMeeple> playersInCity = new ArrayList<playerMeeple>();
			HashSet<Tile> visitedTiles = new HashSet<Tile>();
			if (t.name.equals("PLAIN_TWO_CITIES") != true && t.name.equals("PLAIN_TUNNEL") != true) {
				if (cityComplete(t) == true) {
					collectPlayer(findLandType(t, "CITY"), playersInCity, visitedTiles);
					completeCities.add(visitedTiles);
					givePointsCity(playersInCity, visitedTiles);
					giveBackMeeple(playersInCity);
				}
			} else {
				HashSet<Tile> neighbourCityTiles = new HashSet<Tile>();
				neighbourCity(t, neighbourCityTiles);
				for (Tile neighbour : neighbourCityTiles) {
					playersInCity = new ArrayList<playerMeeple>();
					visitedTiles = new HashSet<Tile>();
					if (neighbour.name.equals("PLAIN_TWO_CITIES") == true || neighbour.name.equals("PLAIN_TUNNEL") == true) {
						collectPlayer(neighbourNode(t, neighbour), playersInCity, visitedTiles);
						completeCities.add(visitedTiles);
						givePointsCity(playersInCity, visitedTiles);
						giveBackMeeple(playersInCity);
					} else if (cityComplete(neighbour) == true) {
						collectPlayer(findLandType(neighbour, "CITY"), playersInCity, visitedTiles);
						completeCities.add(visitedTiles);
						givePointsCity(playersInCity, visitedTiles);
						giveBackMeeple(playersInCity);
					}
				}			
			}
		}	
	}
	
	/**
	* Give the points for city at the end
	**/
	public void cityEnd(Node n) {
		ArrayList<playerMeeple> playersInCity = new ArrayList<playerMeeple>();
		HashSet<Tile> visitedTiles = new HashSet<Tile>();
		collectPlayer(n, playersInCity, visitedTiles);
		givePointsCityEnd(playersInCity, visitedTiles);
		giveBackMeepleEnd(playersInCity);
	}

	/* ============================================================ End CITY score ============================================================= */
}

