package carcassonne.core;

import java.util.ArrayList;

public class Node {
	public static final int CONNEXIONS_PER_SIDE = 3;

	public String landType;
	public Player meepleOwner;
	public ArrayList<Node> neighbourNodes = new ArrayList<Node>();

	public Node(String landType) {
		this.landType = landType;
		this.meepleOwner = null;
	}

	public static void nodeConnection(Node n1, Node n2) {
		if (n1.neighbourNodes.size() < CONNEXIONS_PER_SIDE && n2.neighbourNodes.size() < CONNEXIONS_PER_SIDE) {
			n1.neighbourNodes.add(n2);
			n2.neighbourNodes.add(n1);
		} else
			System.out.println("ERROR neighbourNodes is full");
	}	
	
	public void nodeDisconnection(Node n1, Node n2) {
		for (int i = 0; i < Math.min(n1.neighbourNodes.size(), n2.neighbourNodes.size()); ++i) {
			if (n1.neighbourNodes.get(i) == n2)
				n1.neighbourNodes.remove(i);
			if (n2.neighbourNodes.get(i) == n1)
				n2.neighbourNodes.remove(i);	
		}
	}

	public boolean isMeepleInArea(Node n) {
		Node check;
		ArrayList<Node> stack = new ArrayList<Node>();
		ArrayList<Node> visited = new ArrayList<Node>();
		stack.add(n);
		visited.add(n);
		while (stack.size() != 0) {
			check = stack.get(stack.size() - 1);
			stack.remove(stack.size()- 1);
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
