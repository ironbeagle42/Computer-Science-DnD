/*
 * See ArrayList documentation here:
 * http://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
 */

/*
 * Your indexed functions should throw IndexOutOfBoundsException if index is invalid!
 */

public class MyArrayList<E> {

	/* Internal Object counter */
	protected int objectCount;

	/* Internal Object array */
	protected E[] internalArray;

	/* Constructor: Create it with whatever capacity you want? */
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		this.internalArray = (E[]) new Object[100];
		objectCount = 0;
	}

	/* Constructor with initial capacity */
	@SuppressWarnings("unchecked")
	public MyArrayList(int initialCapacity) {
		this.internalArray = (E[]) new Object[initialCapacity];
		objectCount = 0;
	}

	/* Return the number of active slots in the array list */
	//O(1)
	public int size() {
		// for (int i = internalArray.length - 1; i >= 0; i--) {
		// if (internalArray[i] != null) {
		// objectCount = i + 1;
		// return objectCount;
		// }
		// }
		// objectCount = 0;
		return objectCount;
		/* ---- YOUR CODE HERE ---- */
	}

	/* Are there zero objects in the array list? */
	//O(1)
	public boolean isEmpty() {
		return objectCount == 0;
	}

	/* Get the index-th object in the list. */
	//O(1)
	public E get(int index) {
		if (index >= objectCount) {
			throw new IndexOutOfBoundsException();
		}
		return internalArray[index];
		/* ---- YOUR CODE HERE ---- */
	}

	/* Replace the object at index with obj. returns object that was replaced. */
		//O(1)
	public E set(int index, E obj) {
		E ret = internalArray[index];
		internalArray[index] = obj;
		if (ret == null) {
			objectCount++;
		}
		return ret;
		/* ---- YOUR CODE HERE ---- */
	}

	/*
	 * Returns true if this list contains an element equal to obj; otherwise returns false.
	 */
	//O(n)
	public boolean contains(E obj) {
		for (int i = 0; i < internalArray.length; i++) {
			if (internalArray[i].equals(obj)) {
				return true;
			}
		}
		return false;
	}

	/* Insert an object at index */
	//O(n)
	@SuppressWarnings("unchecked")
	public void add(int index, E obj) {
		if (objectCount == internalArray.length) {
			E[] temp = (E[]) new Object[internalArray.length * 2];
			for (int j = 0; j < index; j++) {
				temp[j] = internalArray[j];
			}
			for (int i = index; i < internalArray.length; i++) {
				temp[i + 1] = internalArray[i];
			}
			temp[index] = obj;
			internalArray = temp;
		}  else {
			for (int i = objectCount; i > index; i--) {
				internalArray[i] = internalArray[i - 1];
			}
			internalArray[index] = obj;
		}
		objectCount++;
	}

	/* Add an object to the end of the list; returns true */
	@SuppressWarnings("unchecked")
	public boolean add(E obj) {
		// if (objectCount == internalArray.length) {
		// 	E[] temp = (E[]) new Object[internalArray.length * 2];
		// 	for (int i = 0; i < objectCount; i++) {
		// 		temp[i] = internalArray[i];
		// 		internalArray = temp;
		// 	}
		// }
		// internalArray[objectCount] = obj;
		// objectCount++;
		add(objectCount, obj);
		return true;
	}

	/* Remove the object at index and shift. Returns removed object. */
	public E remove(int index) {
		E ret = internalArray[index];
		internalArray[index] = null;
		for (int i = index; i < objectCount - 1; i++) {
			internalArray[i] = internalArray[i + 1];
		}
		if (ret != null) {
			objectCount--;
		}
		return ret;
	}

	/*
	 * Removes the first occurrence of the specified element from this list, if it is present. If
	 * the list does not contain the element, it is unchanged. More formally, removes the element
	 * with the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))) (if such an
	 * element exists). Returns true if this list contained the specified element (or equivalently,
	 * if this list changed as a result of the call).
	 */
	public boolean remove(E obj) {
		for (int i = 0; i < objectCount; i++) {
			if (internalArray[i].equals(obj)) {
				remove(i);
				return true;
			}
		}
		return false;
	}


	/*
	 * For testing; your string should output as "[X, X, X, X, ...]" where X, X, X, X, ... are the
	 * elements in the ArrayList. If the array is empty, it should return "[]". If there is one
	 * element, "[X]", etc. Elements are separated by a comma and a space.
	 */
	public String toString() {
		String temp = "[";
		for (int i = 0; i < objectCount - 1; i++) {
			temp += internalArray[i] + ", ";
		}
		if (objectCount != 0) {
			temp += internalArray[objectCount - 1];
		}
		return temp + "]";
	}

}
