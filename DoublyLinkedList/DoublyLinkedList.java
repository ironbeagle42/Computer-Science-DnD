
public class DoublyLinkedList {
	// Implements a circular doubly-linked list.

	private final ListNode2<Nucleotide> SENTINEL = new ListNode2<Nucleotide>(null);
	private int nodeCount;

	// Constructor: creates an empty list
	public DoublyLinkedList() {
		SENTINEL.setNext(SENTINEL);
		SENTINEL.setPrevious(SENTINEL);
		nodeCount = 0;
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	public DoublyLinkedList(Nucleotide[] values) {
		SENTINEL.setNext(SENTINEL);
		SENTINEL.setPrevious(SENTINEL);
		for (Nucleotide i : values) {
			add(i);
		}
	}

	public ListNode2<Nucleotide> getSentinel() {
		return SENTINEL;
	}

	public ListNode2<Nucleotide> getHead() {
		return SENTINEL.getNext();
	}

	public ListNode2<Nucleotide> getTail() {
		return SENTINEL.getPrevious();
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
	public boolean contains(Nucleotide obj) {
		ListNode2<Nucleotide> temp = SENTINEL;
		for (int i = 0; i < nodeCount; i++) {
			if (temp.getValue().equals(obj)) {
				return true;
			}
			temp = temp.getNext();
		}
		return false;
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(Nucleotide obj) {
		ListNode2<Nucleotide> temp = SENTINEL;
		for (int i = 0; i < nodeCount; i++) {
			if (temp.equals(obj)) {
				return i;
			}
			temp = temp.getNext();
		}
		return -1;
	}

	// Adds obj to this collection. Returns true if successful;
	// otherwise returns false.
	public boolean add(Nucleotide obj) {
		ListNode2<Nucleotide> temp = new ListNode2(obj, SENTINEL.getPrevious(), SENTINEL);
		SENTINEL.getPrevious().setNext(temp);
		SENTINEL.setPrevious(temp);
		nodeCount++;
		return true;
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(Nucleotide obj) {
		ListNode2<Nucleotide> temp = SENTINEL;
		for (int i = 0; i < nodeCount; i++) {
			temp = temp.getNext();
			if (temp.equals(obj)) {
				temp.getPrevious().setNext(temp.getNext());
				temp.getNext().setPrevious(temp.getPrevious());
				nodeCount--;
				return true;
			}
		}
		return false;
	}

	// Returns the i-th element.
	public Nucleotide get(int i) {
		if (i >= nodeCount || i < 0) {
			throw new IndexOutOfBoundsException();
		}
		ListNode2<Nucleotide> temp = SENTINEL;
		for (int j = 0; j <= i; j++) {
			temp = temp.getNext();
		}
		return temp.getValue();
	}

	// Replaces the i-th element with obj and returns the old value.
	public Nucleotide set(int i, Nucleotide obj) {
		if (i >= nodeCount || i < 0) {
			throw new IndexOutOfBoundsException();
		}
		ListNode2<Nucleotide> temp = SENTINEL;
		for (int j = 0; j <= i; j++) {
			temp = temp.getNext();
		}
		Nucleotide ret = temp.getValue();
		temp.setValue(obj);
		return ret;
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, Nucleotide obj) {
		if (i > nodeCount || i < 0) {
			throw new IndexOutOfBoundsException();
		}
		ListNode2<Nucleotide> temp = SENTINEL;
		for (int j = 0; j <= i; j++) {
			temp = temp.getNext();
		}
		nodeCount++;
		ListNode2<Nucleotide> temp2 = new ListNode2<Nucleotide>(obj, temp.getPrevious(), temp);
		temp.getPrevious().setNext(temp2);
		temp.setPrevious(temp2);
		nodeCount++;
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public Nucleotide remove(int i) {
		if (i >= nodeCount || i < 0) {
			throw new IndexOutOfBoundsException();
		}
		ListNode2<Nucleotide> temp = SENTINEL;
		for (int j = 0; j <= i; j++) {
			temp = temp.getNext();
		}
		Nucleotide ret = temp.getValue();
		temp.getPrevious().setNext(temp.getNext());
		temp.getNext().setPrevious(temp.getPrevious());
		return ret;
	}

	// Returns a string representation of this list exactly like that for MyArrayList.
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		ListNode2 temp = SENTINEL.getNext();
		while (temp != SENTINEL) {
			str.append(temp.getValue());
			str.append(", ");
			temp = temp.getNext();
		}
		str.delete(str.length() - 2, str.length());
		str.append("]");
		return str.toString();
	}

	

	// Like question 7 on the SinglyLinkedList test:
	// Add a "segment" (another list) onto the end of this list
	public void addSegmentToEnd(DoublyLinkedList seg) {
		SENTINEL.getPrevious().setNext(seg.SENTINEL.getNext());
		SENTINEL.setPrevious(seg.SENTINEL.getPrevious());
	}

	// Like question 8 on the SinglyLinkedList test:
	// You are to remove the next 16 nodes from the list, after the node nodeBefore.
	// (on the test these nodes were assumed to contain CCCCCCCCGGGGGGGG, but here
	// you do not need to assume or check for that)
	public void removeCCCCCCCCGGGGGGGG(ListNode2<Nucleotide> nodeBefore) {
		ListNode2<Nucleotide> temp = SENTINEL;
		while (!temp.equals(nodeBefore)) {
			temp = temp.getNext();
		}
		temp.setNext(temp.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext());
		temp.getNext().setPrevious(temp.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious());
	}

	// Like question 9 on the SinglyLinkedList test:
	// You are to find and delete the first instance of seg in the list.
	// If seg is not in the list, return false, otherwise return true.
	public boolean deleteSegment(DoublyLinkedList seg) {

	}

	// Like question 10 on the SinglyLinkedList test:
	// Delete the last three nodes in the list
	// If there are not enough nodes, return false
	public boolean deleteLastThree() {

	}

	// Like question 11 on the SinglyLinkedList test:
	// Replaces every node containing "A" with three nodes containing "T" "A" "C"
	public void replaceEveryAWithTAC() {

	}

}
