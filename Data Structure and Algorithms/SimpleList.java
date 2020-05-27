package de.unistuttgart.dsass2017.ex03.p1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SimpleList<T extends Comparable<T>> implements ISimpleListIterable<T> {
	/** the current Index of the Iterator */
	private int currentIndex = 0;
	/** the Index of the last skipped to Item */
	private int lastskippedToIndex = 0;
	/**
	 * the number of items to be skipped each time the next() method is called
	 */
	int skipNr;
	/** Do not modify the existing methods and signatures */
	private final List<T> list;

	public SimpleList() {
		list = new ArrayList<T>();
	}

	@Override
	public void append(T element) {
		list.add(element);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public T get(int index) {
		return list.get(index);
	}

	@Override
	public void swap(int i, int j) {
		T tmp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, tmp);
	}

	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
	}

	@Override
	public Iterator<T> skippingIterator(int n) {
		this.skipNr = n;
		return new skipIterator();
	}

	public class ListIterator implements Iterator<T> {

		public boolean hasNext() {
			if (currentIndex + 1 >= size()) {
				return false;
			} else {
				return get(currentIndex + 1) != null;
			}
		}

		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			} else {
				currentIndex++;
				return get(currentIndex - 1);
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public class skipIterator implements Iterator<T> {
		
		public boolean hasNext() {
			if (lastskippedToIndex + skipNr - 1 >= size()) {
				return false;
			} else {
				return get(lastskippedToIndex + skipNr - 1) != null;
			}
		}

		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			} else {
				lastskippedToIndex += skipNr - 1;
				return get(lastskippedToIndex - skipNr + 1);
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
