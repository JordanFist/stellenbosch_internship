package carcassonne.core;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Collections;

public class Node {
	public static final int CONNEXIONS_PER_SIDE = 3;

	public String landType;
	public Player meepleOwner;
	public int round;
	public boolean endRoad = false;
	public boolean plainNextToCity = true;
	public Tile sourceTile;
	public ArrayList<Node> neighbourNodes = new ArrayList<Node>();

	public Node(String landType, Tile sourceTile) {
		this.landType = landType;
		this.sourceTile = sourceTile;
		this.meepleOwner = null;
	}

	/**
	* Connect 2 nodes and add them in neighbourNodes
	**/
	public void connection(Node n) {
		if (neighbourNodes.size() < CONNEXIONS_PER_SIDE && n.neighbourNodes.size() < CONNEXIONS_PER_SIDE) {
			neighbourNodes.add(n);
			n.neighbourNodes.add(this);
		} else
			System.out.println("ERROR neighbourNodes is full");
	}	
	
	/**
	* Disconnect 2 nodes and remove them from neighbourNodes
	**/
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

	/**
	* Return true if there is a meeple in the area
	**/
	public boolean isMeepleInArea() {
		Node check;
		Stack<Node> stack = new Stack<Node>();
		Stack<Node> visited = new Stack<Node>();
		stack.add(this);
		visited.add(this);
		while (stack.isEmpty() == false) {
			check = stack.remove(0);
			if (check.meepleOwner != null)
				return true;
			for (int i = 0; i < check.neighbourNodes.size(); ++i) {
				if (visited.contains(check.neighbourNodes.get(i)) == false) {
					stack.add(check.neighbourNodes.get(i));
					visited.add(check.neighbourNodes.get(i));
				}
			}
		}
		return false;
	}
}
