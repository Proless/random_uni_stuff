package test;

@SuppressWarnings("unchecked")
public class Stack<T> implements IStack<T> {

	/**
	 * the array of items contained in the Stack will grow as needed using push
	 */
	private T[] items = (T[]) new Object[0];
	/**
	 * the numbers of items in the Stack
	 */
	private int size;

	@Override
	public void push(T t) {
		if (size == items.length) {

			// a new length for the new array making it 1 if this is the first
			// push
			// otherwise double the size
			int newLenght = (size == 0) ? 1 : size * 2;
			T[] newArray = (T[]) new Object[newLenght];

			// copy all items from the current array to the new array
			for (int i = 0; i < items.length; i++) {
				T item = items[i];
				newArray[i] = item;
			}
			// assign the new array
			items = newArray;
		}
		items[size] = t;
		size++;
	}

	@Override
	public T pop() {
		if (size == 0) {
			return null;
		} else {
			size--;
			T item = items[size];
			items[size] = null;
			return item;
		}
	}

	@Override
	public T top() {
		if (size == 0) {
			return null;
		} else {
			return items[size - 1];
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;

	}

}
