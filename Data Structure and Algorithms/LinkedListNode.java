package de.unistuttgart.dsass2017.ex04.p2;

public class LinkedListNode<T extends Comparable<T>> implements ILinkedListNode<T> {

	/**
	 * place holder
	 */
	T element;
	/**
	 * place holders for the next, previous
	 */
	private ILinkedListNode<T> nextNode, prevNode;

	// default Constructor
	public LinkedListNode() {
		this.element = null;
		this.nextNode = null;
		this.prevNode = null;
	}

	@Override
	public T getElement() {

		return this.element;
	}

	@Override
	public void setElement(T element) {
		this.element = element;
	}

	@Override
	public ILinkedListNode<T> getNext() {
		return this.nextNode;
	}

	@Override
	public void setNext(ILinkedListNode<T> next) {
		this.nextNode = next;
	}

	@Override
	public ILinkedListNode<T> getPrev() {
		return this.prevNode;
	}

	@Override
	public void setPrev(ILinkedListNode<T> prev) {
		this.prevNode = prev;
	}

}
