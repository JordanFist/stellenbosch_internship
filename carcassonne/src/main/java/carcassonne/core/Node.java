package carcassonne.core;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Collections;

public class Node {
	public static final int CONNEXIONS_PER_SIDE = 3;

	public String landType;
	public Player meepleOwner;
	public boolean endRoad = false;
	public boolean plainNextToCity = true;
	public Tile sourceTile;
	public ArrayList<Node> neighbourNodes = new ArrayList<Node>();

	public Node(String landType, Tile sourceTile) {
		this.landType = landType;
		this.sourceTile = sourceTile;
		this.meepleOwner = null;
	}

	public void connection(Node n) {
		if (neighbourNodes.size() < CONNEXIONS_PER_SIDE && n.neighbourNodes.size() < CONNEXIONS_PER_SIDE) {
			neighbourNodes.add(n);
			n.neighbourNodes.add(this);
		} else
			System.out.println("ERROR neighbourNodes is full");
	}	
	
	public void disconnection(Node n) {
		for (int i = 0; i < neighbourNodes.size(); ++i) {
			if (neighbourNodes.get(i) == n)
				neighbourNodes.remove(i);
		}
		for (int i = 0; i < n.neighbourNodes.size(); ++i) {
			if (n.neighbourNodes.get(i) == this)
				n.neighbourNodes.remove(i);
		}
	}

	public boolean isMeepleInArea() {
		Node check;
		Stack<Node> stack = new Stack<Node>();
		Stack<Node> visited = new Stack<Node>();
		stack.add(this);
		visited.add(this);
		while (stack.isEmpty() == false) {
			check = stack.remove(0);
			if (check != null && check.meepleOwner != null)
				return true;
			for (int i = 0; i < check.neighbourNodes.size(); ++i) {
				if (check.neighbourNodes.get(i) != null && visited.contains(check.neighbourNodes.get(i)) == false) {
					stack.add(check.neighbourNodes.get(i));
					visited.add(check.neighbourNodes.get(i));
				}
			}
		}
		return false;
	}
	/*
	public static void main(String[] args) {
		Player p = new Player(0);
		setTile s = new setTile();
		
		for (int i = 0; i < 12; ++i) {
			for (int j = 0; j < s.tiles.get(0).nodes[i].neighbourNodes.size(); ++j) {
				System.out.println(s.tiles.get(0).nodes[i].neighbourNodes.get(j));
			}
			System.out.println();
		} System.out.println("test");

		RoadStraightCity t1 = new RoadStraightCity();	
		t1.pos = new Position(0, 1);
		s.addSetTile(t1);
	
		RoadStraightCity t2 = new RoadStraightCity();	
		t2.pos = new Position(1, 0);
		t2.rotation(directionId.SOUTH);
		t2.dir = directionId.SOUTH;
		s.addSetTile(t2);
	
		RoadStraightCity t3 = new RoadStraightCity();	
		t3.pos = new Position(1, 1);
		t3.rotation(directionId.SOUTH);
		t3.dir = directionId.SOUTH;
		s.addSetTile(t3);
		
		MonasteryRoad t4 = new MonasteryRoad();
		t4.pos = new Position(0, -1);
		t4.rotation(directionId.SOUTH);
		t4.dir = directionId.SOUTH;	
		s.addSetTile(t4);
		
		s.tiles.get(1).nodes[11].meepleOwner = p;
		//System.out.println(s.tiles.get(3).nodes[5].isMeepleInArea());

		s.removeSetTile(t1);
		s.removeSetTile(t2);
		s.removeSetTile(t3);
		s.removeSetTile(t4);

		for (int i = 0; i < 12; ++i) {
			for (int j = 0; j < s.tiles.get(0).nodes[i].neighbourNodes.size(); ++j) {
				System.out.println(s.tiles.get(0).nodes[i].neighbourNodes.get(j));
			}
			System.out.println();
		}
	}*/
}
