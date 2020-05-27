package test;

/**
 * @author Hasan Darwish, 3247569
 * @author Polina Jungblut, 3254837
 * @author Ina Vasileiadou, 3124938
 */
public class SpeedList<T> implements ISpeedList<T> {

	// Is Used to store the number of elements in the List
	private int size;
	// the first Item in the List
	private Node<T> head;

	// Constructor
	public SpeedList() {
		this.size = 0;
		this.head = new Node<T>();
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public void prepend(T t) {
		// Initialize a new Node with the passed value
		Node<T> n = new Node<T>(t, head.getNext());
		// link the eight Next element to this new node
		if (this.size >= 8) {
			// a temporary variable to start the iteration from the head of the
			// List
			Node<T> tempN = head;
			for (int i = 0; i < 8; i++) {
				tempN = tempN.getNext();
				n.setEighthNext(tempN);
			}
		}
		// Add the new Node after the Head to the list
		head.setNext(n);
		this.size++;
	}

	@Override
	public T getElementAt(int pos) {
		Node<T> nodeAtPos = head.getNext();
		// checks if the passed position number is in range
		if (pos >= this.size) {
			throw new IndexOutOfBoundsException();
		} else if (pos < 8) {
			for (int i = 0; i < pos; i++) {
				nodeAtPos = nodeAtPos.getNext();
			}
		} else {
			// the first loop will determine how many times should be jumped
			// over to the eighth next element so that
			// it speeds up the process of finding the right element
			// the second loop will only go to the next element so many times,
			// depending on how many nodes remains to reach the required
			// position
			for (int j = 0; j < pos / 8; j++) {
				nodeAtPos = nodeAtPos.getEighthNext();
			}
			for (int i = 0; i < pos % 8; i++) {
				nodeAtPos = nodeAtPos.getNext();
			}
		}
		// return the element in the node
		return nodeAtPos.getElement();

	}

	@Override
	public T getNext8thElementOf(int pos) {
		Node<T> nodeAtPos = head.getNext();
		// checks if the passed position number is in range
		if (pos >= this.size) {
			throw new IndexOutOfBoundsException();
		} else if (pos < 8) {
			for (int i = 0; i < pos; i++) {
				nodeAtPos = nodeAtPos.getNext();
			}
		} else {
			// the first loop will determine how many times should be jumped
			// over to the eighth next element so that
			// it speeds up the process of finding the right element
			// the second loop will only go to the next element so many times,
			// depending on how many nodes remains to reach the required
			// position
			for (int j = 0; j < pos / 8; j++) {
				nodeAtPos = nodeAtPos.getEighthNext();
			}
			for (int i = 0; i < pos % 8; i++) {
				nodeAtPos = nodeAtPos.getNext();
			}
		}
		// return the element in the eight Next node if there is no more
		// elements it will return null
		T element = null;
		try {
			element = nodeAtPos.getEighthNext().getElement();
		} catch (NullPointerException e) {
		}
		return element;
	}

}
