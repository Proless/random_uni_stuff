package de.unistuttgart.dsass2017.ex04.p2;

public class CircularLinkedList<T extends Comparable<T>> implements ICircularLinkedList<T> {

	int size;
	LinkedListNode<T> head;

	public CircularLinkedList() {
		this.size = 0;
		this.head = new LinkedListNode<>();
	}

	@Override
	public void append(T element) {
		// Create a new node and assign this element to it
		LinkedListNode<T> newNode = new LinkedListNode<>();
		newNode.setElement(element);

		// Case 1 : the List is empty.
		if (size == 0) {
			head.setNext(newNode);
			size++;

			// Case 2 : the List has only on Node.
		} else if (size == 1) {
			newNode.setPrev(head.getNext());
			head.getNext().setNext(newNode);
			// set the previous node of the first one to point to the new node,
			// which means creating a pointer to the last node in the list
			head.getNext().setPrev(newNode);
			size++;

			// Case 3 : the List has two or more Nodes.
		} else { 
			// set the next node of the new on to point to the first node
			newNode.setNext(head.getNext());
			// set the previous node of the new node.
			newNode.setPrev(head.getNext().getPrev());
			// add the node to the end of the List
			head.getNext().getPrev().setNext(newNode);
			// set the previous node of the first one to point to the new node
			head.getNext().setPrev(newNode);
			size++;
		}
	}

	@Override
	public T get(int index) {
		LinkedListNode<T> temp = (LinkedListNode<T>) head.getNext();
		// Case 1 : Index is 0 (the first element index)
		if (index == 0) {
			return this.head.getNext().getElement();
			//Case 2 : the index is positive
		} else if (index > 0) {
			if (index <= size) {
				for (int i = 0; i < index; i++) {
					temp = (LinkedListNode<T>) temp.getNext();
				}
			} else {
				for (int i = 0; i < index % size; i++) {
					temp = (LinkedListNode<T>) temp.getNext();
				}
			}
			return temp.getElement();
			
			// Case 3 : the index is negative
		} else {
			if (index <= size) {
				for (int i = 0; i < -1*index; i++) {
					temp = (LinkedListNode<T>) temp.getPrev();
				}
			} else {
				for (int i = 0; i < -1 * index % size; i++) {
					temp = (LinkedListNode<T>) temp.getPrev();
				}
			}
			return temp.getElement();
		}
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public ILinkedListNode<T> getHead() {
		return this.head.getNext();
	}

}
