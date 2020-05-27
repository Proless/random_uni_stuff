package de.unistuttgart.dsass2017.ex03.p3;

public class BinarySearchTree<T extends Comparable<T>> implements IBinarySearchTree<T> {

	private BinaryTreeNode<T> root;

	public BinarySearchTree() {
		this.root = null;
	}

	@Override
	public void insert(T t) {
		// Case 1 : the tree is empty - allocate the head with the value
		if (root == null) {
			root = new BinaryTreeNode<>();
			root.setValue(t);
		} else {
			// Case 2 : the tree is not empty - find the right position to
			// insert using a recursive Method
			Add(root, t);
		}
	}

	@Override
	public IBinaryTreeNode<T> getRootNode() {
		return this.root;
	}

	@Override
	public boolean isFull() {
		return checkforFull(root);
	}

	/**
	 * a recursive Method to find the right location for the value and insert it
	 * if the value is equal to any existing value nothing will be inserted
	 * 
	 * @param node
	 *            the node to start searching from
	 */
	private void Add(IBinaryTreeNode<T> node, T val) {
		// create a node with the passed value to insert it later in the right
		// location
		BinaryTreeNode<T> newNode = new BinaryTreeNode<>();
		newNode.setValue(val);
		// Case 1 : the value is less than the current node Value then go left
		if (val.compareTo(node.getValue()) < 0) {
			// if there is no left then make this the new left
			if (node.getLeftChild() == null) {
				node.setLeftChild(newNode);
			} else {
				// else add the value to the left node
				Add(node.getLeftChild(), val);
			}
			// Case 2 : the value is greater than the current node Value then go
			// right
		} else if (val.compareTo(node.getValue()) > 0) {
			// if there is no right then make this the new right
			if (node.getRightChild() == null) {
				node.setRightChild(newNode);
			} else {
				// else add the value to the right node
				Add(node.getRightChild(), val);
			}
		}
	}

	/**
	 * a recursive Method to check if the Tree is full
	 * 
	 * @param parentNode
	 *            the node to start checking from
	 * @return true if the the Tree is full otherwise false
	 */
	private boolean checkforFull(IBinaryTreeNode<T> parentNode) {
		if (parentNode != null) {
			if (parentNode.getRightChild() == null && parentNode.getLeftChild() == null) {
				return true;
			}
			if (parentNode.getRightChild() != null && parentNode.getLeftChild() != null) {
				return checkforFull(parentNode.getLeftChild()) && checkforFull(parentNode.getRightChild());
			}
		}
		return false;
	}
}
