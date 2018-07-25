package carcassonne.core;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Collections;

public class Node {
	public static final int CONNEXIONS_PER_SIDE = 3;

	public String landType;
	public Player meepleOwner;
	public ArrayList<Node> neighbourNodes = new ArrayList<Node>();

	public Node(String landType) {
		this.landType = landType;
		this.meepleOwner = null;
	}

	public void nodeConnection(Node n) {
		if (neighbourNodes.size() < CONNEXIONS_PER_SIDE && n.neighbourNodes.size() < CONNEXIONS_PER_SIDE) {
			neighbourNodes.add(n);
			n.neighbourNodes.add(this);
		} else
			System.out.println("ERROR neighbourNodes is full");
	}	
	
	public void nodeDisconnection(Node n) {
		for (int i = 0; i < Math.min(neighbourNodes.size(), n.neighbourNodes.size()); ++i) {
			if (neighbourNodes.get(i) == n)
				neighbourNodes.remove(i);
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
			for (int i = 0; i < CONNEXIONS_PER_SIDE; ++i) {
				if (check.neighbourNodes.get(i) != null && visited.contains(check.neighbourNodes.get(i)) == false) {
					stack.add(check.neighbourNodes.get(i));
					visited.add(check.neighbourNodes.get(i));
				}
			}
		}
		return false;
	}
}
