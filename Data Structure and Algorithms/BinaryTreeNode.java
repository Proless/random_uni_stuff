package de.unistuttgart.dsass2017.ex03.p3;

public class BinaryTreeNode<T extends Comparable<T>> implements IBinaryTreeNode<T> {
	/**
	 * place Holder for the value
	 */
	T val;
	/**
	 * place Holders for the left and right node
	 */
	BinaryTreeNode<T> left, right;

	public BinaryTreeNode() {
		this.val = null;
		this.left = null;
		this.right = null;
	}

	@Override
	public void setValue(T val) {
		this.val = val;
	}

	@Override
	public T getValue() {
		return this.val;
	}

	@Override
	public void setLeftChild(IBinaryTreeNode<T> left) {
		this.left = (BinaryTreeNode<T>) left;
	}

	@Override
	public IBinaryTreeNode<T> getLeftChild() {
		return this.left;
	}

	@Override
	public void setRightChild(IBinaryTreeNode<T> right) {
		this.right = (BinaryTreeNode<T>) right;
	}

	@Override
	public IBinaryTreeNode<T> getRightChild() {
		return this.right;
	}

}
