// Implements a singly-linked list.


public class SinglyLinkedList<E> {
	private ListNode<E> head;
	private ListNode<E> tail;
	private int nodeCount;

	// Constructor: creates an empty list
	public SinglyLinkedList() {
		this.head = null;
		this.tail = null;
		this.nodeCount = 0;
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	public SinglyLinkedList(Object[] values) {
		if (values.length == 0) {
			return;
		}
		this.tail = new ListNode(values[values.length - 1]);
		ListNode temp = tail;
		for (int i = values.length - 2; i > 0; i--) {
			temp = new ListNode(values[i], temp);
		}
		this.head = new ListNode(values[0], temp);
		this.nodeCount = values.length;
	}

	public ListNode<E> getHead() {
		return head;
	}

	public ListNode<E> getTail() {
		return tail;
	}

	// Returns true if this list is empty; otherwise returns false.
	public boolean isEmpty() {
		return (nodeCount == 0);
	}

	// Returns the number of elements in this list.
	public int size() {
		return nodeCount;
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	public boolean contains(E obj) {
		boolean isNull = false;
		if (obj == null) {
			isNull = true;
		}
		ListNode temp = head;
		for (int i = 0; i < nodeCount; i++) {
			if (temp.getValue() == null) {
				if (isNull) {
					return true;
				}
			} else {
				if (temp.getValue().equals(obj)) {
					return true;
				}
			}
			temp = temp.getNext();
		}
		return false;
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(E obj) {
		boolean isNull = false;
		if (obj == null) {
			isNull = true;
		}
		ListNode temp = head;
		for (int i = 0; i < nodeCount; i++) {
			if (temp.getValue() == null) {
				if (isNull) {
					return i;
				} else {
					if (temp.getValue().equals(obj)) {
						return i;
					}
				}
			}
			temp = temp.getNext();
		}
		return -1;
	}

	// Adds obj to this collection. Returns true if successful;
	// otherwise returns false.
	public boolean add(E obj) {
		ListNode temp = new ListNode<E>(obj);
		nodeCount++;
		if (head == null) {
			head = temp;
			tail = head;
			return true;
		}
		tail.setNext(temp);
		tail = tail.getNext();
		return true;
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(E obj) {
		if (!contains(obj)) {
			return false;
		}
		boolean isNull = false;
		if (obj == null) {
			isNull = true;
		}
		ListNode temp = head;
		if (temp.getValue() != null) {
			if (temp.getValue().equals(obj)) {
				if (!temp.equals(tail)) {
					head = temp.getNext();
				} else {
					head = null;
				}
				nodeCount--;
				return true;
			}
		} else {
			if (isNull) {
				if (nodeCount == 1) {
					head = null;
				} else {
					head = head.getNext();
				}
				nodeCount--;
				return true;
			}
		}
		if (!isNull) {
			while (!obj.equals(temp.getNext().getValue())) {
				temp = temp.getNext();
			}
		} else {
			while (temp.getNext().getValue() != null) {
				temp = temp.getNext();
			}
		}
		if (temp.getNext().equals(tail)) {
			tail = temp;
		} else {
			temp.setNext(temp.getNext().getNext());
		}
		nodeCount--;
		return true;

	}

	// Returns the i-th element.
	public E get(int i) {
		if (i >= nodeCount || i < 0) {
			throw new IndexOutOfBoundsException();
		}
		ListNode temp = head;
		for (int j = 0; j < i; j++) {
			temp = temp.getNext();
		}
		if (temp.getValue() == null) {
			return null;
		}
		return (E) temp.getValue();
	}

	// Replaces the i-th element with obj and returns the old value.
	public E set(int i, Object obj) {
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		ListNode temp = head;
		for (int j = 0; j < i; j++) {
			temp = temp.getNext();
		}
		E ret = (E) temp.getValue();
		temp.setValue(obj);
		return ret;
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, Object obj) {
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		if (i == nodeCount - 1) {
			add((E) obj);
			return;
		}
		ListNode temp = head;
		for (int j = 0; j < i - 1; j++) {
			temp = temp.getNext();
		}
		ListNode temp2 = new ListNode(obj, temp.getNext());
		temp.setNext(temp2);
		nodeCount++;
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public E remove(int i) {
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		if (i == 0) {
			E ret = head.getValue();
			head = head.getNext();
			nodeCount--;
			return ret;
		}
		ListNode temp = head;
		for (int j = 0; j < i - 1; j++) {
			temp = temp.getNext();
		}
		E ret = (E) temp.getNext().getValue();
		if (temp.getNext().equals(tail)) {
			tail = temp;
		} else {
			temp.setNext(temp.getNext().getNext());
		}
		nodeCount--;
		return ret;
	}

	// Returns a string representation of this list exactly like that for MyArrayList.
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		ListNode temp = head;
		while (temp != tail && temp != null) {
			str.append(temp.getValue());
			str.append(", ");
			temp = temp.getNext();
		}
		if (head != null) {
			str.append(tail.getValue());
		}
		str.append("]");
		return str.toString();
	}


}
