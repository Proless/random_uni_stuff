package test;

/**
 * @author Hasan Darwish, 3247569
 * @author Polina Jungblut, 3254837
 * @author Ina Vasileiadou, 3124938
 */
public class Node<T> {

	// obj is a place holder to store an object
	private T obj;
	// next is a place holder for the next Item after this Node
	private Node<T> next;
	// eightNext is a place holder for the eight Next Item after this Node
	private Node<T> eighthNext;

	// Constructor
	public Node() {
		this.obj = null;
		this.next = null;
		this.eighthNext = null;
	}

	// Constructor
	public Node(T obj, Node<T> next) {
		this.obj = obj;
		this.next = next;
	}

	/**
	 * this method is used to set the Object to be stored as the value contained
	 * in this Node
	 * 
	 * @param obj
	 *            the object to pass to the constructor
	 */
	public void setElement(T obj) {
		this.obj = obj;
	}

	/**
	 * @return the stored object
	 */
	public T getElement() throws NullPointerException {
		return obj;
	}

	/**
	 * set the Next node after this
	 * 
	 * @param next
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}

	/**
	 * @return the Next node
	 */
	public Node<T> getNext() throws NullPointerException {
		return next;
	}

	/**
	 * set the eighth Next node after this
	 * 
	 * @param eightnext
	 */
	public void setEighthNext(Node<T> eNext) {
			this.eighthNext = eNext;
		}

	/**
	 * 
	 * @return the eighth Next node
	 */
	public Node<T> getEighthNext() throws NullPointerException {
		return eighthNext;
	}
}
