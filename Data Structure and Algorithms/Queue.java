package test;

/**
 * @author Hasan Darwish, 3247569
 * @author Polina Jungblut, 3254837
 * @author Ina Vasileiadou, 3124938
 */
@SuppressWarnings("unchecked")
public class Queue<T> implements IQueue<T> {

	private T[] items = (T[]) new Object[0];
	// the number of items in the queue
	private int size = 0;
	// the index of the first (oldest) item in the queue
	private int head = 0;
	// the index of the last (newest) item in the queue
	private int tail = -1;

	/**
	 * Adds an item to the back of the queue
	 * 
	 * @param t
	 *            the item to place in the queue
	 */

	public void enqueue(T t) {

		// checks if the full queue capacity has been reached and therefore
		// determine if it needs to grow
		if (items.length == size) {

			// a new length for the new array making it 1 if this is the first
			// push
			// otherwise increase by 1
			int newLength = (size == 0) ? 1 : size * 2;

			// a new array with doubled length
			T[] newArray = (T[]) new Object[newLength];

			int targetIndex = 0;

			if (size > 0) {
				// copy the content of the array to the new array in the right
				// order
				// while keeping the first (oldest) item at index 0 and the last
				// (newest) at the last index
				if (tail < head) {
					// copy the items[head]...items[end] to the newArray[0]...newArray[N]
					for (int index = head; index < items.length; index++) {
						newArray[targetIndex] = items[index];
						targetIndex++;
					}
					// copy the items[0]..item[tail] to newArray[N+1]
					for (int index = 0; index <= tail; index++) {
						newArray[targetIndex] = items[index];
						targetIndex++;
					}

				} else {
				//	copy the item[0]...items[tail] to newArray[N] 
					for (int index = head; index <= tail; index++) {
						newArray[targetIndex] = items[index];
						targetIndex++;
					}
					// set head index to the beginning of the array
					head = 0;
					// set tail index to the end of the array
					tail = targetIndex - 1;
				}
			} else {
				// the default values if the queue is empty
				head = 0;
				tail = -1;
			}
			//assigning the newArray as the items array in the queue
			items = newArray;
		}

		if (tail == items.length - 1) {
			tail = 0;
		} else {
			tail++;
		}
		
		// adding the required item to the end of the queue
		items[tail] = t;
		// Increasing the size of the queue by 1
		size++;
		

	}

	@Override
	public T dequeue() {
		// if the queue is empty then return null
		if (size == 0) {
			return null;
		}
		// get the first item before removing it to return it later
		T tHead = items[head];
		// if the index of the head is at the last index in the array
		if (head == items.length - 1) {
			head = 0;
		} else {
			// move the head index to the next item to make it the new first item in the queue 
			head++;
		}
		
		size--;
		
		return tHead;
	}

	@Override
	public T front() {
		// if the queue is empty then return null otherwise
		// return the oldest (first) item 
		if (size == 0) {
			return null;
		}else {
			return items[head];
		}
		
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

}
