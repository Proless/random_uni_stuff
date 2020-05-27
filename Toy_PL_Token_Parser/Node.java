package task3;

/**
 * 
 */


import java.util.ArrayList;
import java.util.List;

/**
 * @author SOLA
 * Class represents each element of the tree
 */
public class Node {
//For grading, we evaluate the shape of the tree and the label fields associated with each 
//node, all remaining fields are provided for your convenience.

	private List<Node> children = null;
	private Node parent = null;
	private String label = "";
	private int id;
	
	public Node(int id) {
		this.id = id;
		this.children = new ArrayList<Node>(); 
	}
	
	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void addChild(Node child) {
		if (child != null) {
			this.children.add(child);
		}
	}
}

