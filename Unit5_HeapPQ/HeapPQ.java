
public class HeapPQ<E extends Comparable<E>> implements MyPriorityQueue<E> {

	private E[] heap;
	private int objectCount;

	public HeapPQ() {
		this.heap = (E[]) new Comparable[3];
		this.objectCount = 0;
	}

	// Returns the number of elements in the priority queue
	public int size() {
		return objectCount;
	}

	// DO NOT CHANGE MY JANKY TOSTRING!!!!!
	public String toString() {
		StringBuffer stringbuf = new StringBuffer("[");
		for (int i = 0; i < objectCount; i++) {
			stringbuf.append(heap[i]);
			if (i < objectCount - 1)
				stringbuf.append(", ");
		}
		stringbuf.append("]\nor alternatively,\n");

		for (int rowLength = 1, j = 0; j < objectCount; rowLength *= 2) {
			for (int i = 0; i < rowLength && j < objectCount; i++, j++) {
				stringbuf.append(heap[j] + " ");
			}
			stringbuf.append("\n");
			if (j < objectCount) {
				for (int i = 0; i < Math.min(objectCount - j, rowLength * 2); i++) {
					if (i % 2 == 0)
						stringbuf.append("/");
					else
						stringbuf.append("\\ ");
				}
				stringbuf.append("\n");
			}
		}
		return stringbuf.toString();
	}

	// Doubles the size of the heap array
	private void increaseCapacity() {
		E[] newHeap = (E[]) new Comparable[objectCount * 2];
		for (int i = 0; i < objectCount; i++) {
			newHeap[i] = heap[i];
		}
		heap = newHeap;
	}

	// Returns the index of the "parent" of index i
	private int parent(int i) {
		if (i == 0) {
			return 0;
		}
		if (i % 2 == 0) {
			return i / 2 - 1;
		} else {
			return (i - 1) / 2;
		}
	}

	// Returns the index of the *smaller child* of index i
	private int smallerChild(int i) {
		int num1 = i * 2 + 1;
		int num2 = i * 2 + 2;
		if (num2 > objectCount) {
			if (num1 > objectCount) {
				return objectCount + 1;
			} else {
				return num1;
			}
		}
		if (heap[i * 2 + 1].compareTo(heap[i * 2 + 2]) >= 0) {
			return i * 2 + 2;
		}
		return i * 2 + 1;
	}

	// Swaps the contents of indices i and j
	private void swap(int i, int j) {
		E val = heap[i];
		heap[i] = heap[j];
		heap[j] = val;
	}

	// Bubbles the element at index i upwards until the heap properties hold again.
	private void bubbleUp(int i) {
		while (heap[parent(i)].compareTo(heap[i]) > 0) {
			swap(parent(i), i);
			i = parent(i);
		}
	}

	// Bubbles the element at index i downwards until the heap properties hold again.
	private void bubbleDown(int i) {
		if (smallerChild(i) > objectCount) {
			return;
		}
		while (heap[smallerChild(i)].compareTo(heap[i]) < 0) {
			swap(smallerChild(i), i);
			i = smallerChild(i);
			if (smallerChild(i) > objectCount) {
				return;
			}
		}
	}

	public void add(E obj) {
		if (objectCount >= heap.length - 2) {
			increaseCapacity();
		}
		heap[objectCount] = obj;
		objectCount++;
		bubbleUp(objectCount - 1);

	}

	public E removeMin() {
		E val = heap[0];
		heap[0] = heap[objectCount - 1];
		bubbleDown(0);
		objectCount--;
		return val;
	}

	public E peek() {
		return heap[0];
	}

	public boolean isEmpty() {
		return objectCount == 0;
	}
}
