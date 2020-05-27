package de.unistuttgart.dsass2017.ex06.p4;

public class Sorter {

	public static <T extends Comparable<T>> void heapSort(ISimpleList<T> list) {
		// Build Max Heap
		for (int i = list.size() / 2; i >= 0; i--) {
			percolate(list, i, list.size() - 1);
		}
		// Sort the Heap
		for (int j = list.size() - 1; j > 0; j--) {
			list.swap(0, j);
			percolate(list, 0, j - 1);
		}
	}

	/**
	 * a method to create a Max heap from a given array and can be used to sort
	 * this heap as well in ascending order
	 * 
	 * @param list
	 *            a list to create a Max-Heap from
	 * @param idx
	 *            a starting index
	 * @param last
	 *            index of the last object
	 */
	private static <T extends Comparable<T>> void percolate(ISimpleList<T> list, int idx, int last) {
		int i = idx, j;
		/* do as long as the parent node has left child */
		while ((2 * i) + 1 <= last) {
			j = (2 * i) + 1; // index of the left child
			if (j + 1 <= last) {
				/*
				 * checks if the parent node has right child if /* so then
				 * compare the tow child nodes and set j as the index of the
				 * largest one
				 * 
				 */
				if (list.get(j).compareTo(list.get(j + 1)) < 0) {
					j++;
				}
			}
			/*
			 * compare the parent node with the biggest child and swap if
			 * necessary
			 * 
			 */
			if (list.get(i).compareTo(list.get(j)) < 0) {
				list.swap(i, j);
				i = j;
			} else {
				/* stop the loop because of the Heap Property */
				break;
			}
		}
	}

}
