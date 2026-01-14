// Implements a BST with BinaryNode nodes

public class MyBST<E extends Comparable<E>> {

	private BinaryNode<E> root; // holds the root of this BST

	// Constructor: creates an empty BST.
	public MyBST() {
		root = null;
	}

	public BinaryNode<E> getRoot() {
		return root;
	}

	public int getHeight() {
		return root.getHeight();
	}

	// Returns true if this BST contains value; otherwise returns false.
	public boolean contains(E value) {
		return containsHelper(value, root);
	}

	public boolean containsHelper(E value, BinaryNode<E> start) {
		if (start == null) {
			return false;
		}
		if (start.getValue().equals(value)) {
			return true;
		}
		if (start.getValue().compareTo(value) > 0) {
			if (start.getLeft() == null) {
				return false;
			}
			return containsHelper(value, start.getLeft());
		}
		if (start.getRight() == null) {
			return false;
		}
		return containsHelper(value, start.getRight());
	}

	// Adds value to this BST, unless this tree already holds value.
	// Returns true if value has been added; otherwise returns false.
	public boolean add(E value) {
		if (root == null) {
			root = new BinaryNode<E>(value);
			return true;
		}
		if (contains(value)) {
			return false;
		}
		return addHelper(value, root);
	}

	public boolean addHelper(E value, BinaryNode<E> start) {
		BinaryNode<E> temp = new BinaryNode(value);
		temp.setHeight(start.getHeight() + 1);
		temp.setParent(start);
		if (start.getValue().compareTo(value) > 0) {
			if (start.getLeft() == null) {
				start.setLeft(temp);
				start.getLeft().setHeight(start.getHeight() + 1);
				return true;
			}
			return addHelper(value, start.getLeft());
		}
		if (start.getRight() == null) {
			start.setRight(temp);
			start.getRight().setHeight(start.getHeight() + 1);
			return true;
		}
		return addHelper(value, start.getRight());
	}

	// Removes value from this BST. Returns true if value has been
	// found and removed; otherwise returns false.
	// If removing a node with two children: replace it with the
	// smallest node in the right subtree
	public boolean remove(E value) {
		if (!contains(value)) {
			return false;
		}
		return removeHelper(value, root);
	}

	public boolean removeHelper(E value, BinaryNode<E> start) {
		if (start == null) {
			return false;
		}
		if (start.getValue().equals(value)) {
			return actuallyRemove(start);
		}
		if (start.getValue().compareTo(value) > 0) {
			return removeHelper(value, start.getLeft());
		}
		return removeHelper(value, start.getRight());
	}

	public boolean actuallyRemove(BinaryNode<E> start) {
		if (start.isLeaf()) {
			if (start.getParent() == null) {
				root = null;
				return true;
			}
			if (start.isLeft()) {
				start.getParent().setLeft(null);
			} else {
				start.getParent().setRight(null);
			}
			return true;
		}

		if (start.hasLeft() && !start.hasRight()) {
			// grab from left
			BinaryNode<E> temp = start;
			temp = temp.getLeft();
			while (temp.hasRight()) {
				temp = temp.getRight();
			}
			BinaryNode<E> tempNode = null;
			if (temp.hasLeft()) {
				tempNode = temp.getLeft();
			}
			start.setValue(temp.getValue());
			if (temp.isLeft()) {
				temp.getParent().setLeft(tempNode);
			} else {
				temp.getParent().setRight(tempNode);
			}
			return true;
		}

		// grab from right
		BinaryNode<E> temp = start;
		temp = temp.getRight();
		while (temp.hasLeft()) {
			temp = temp.getLeft();
		}
		BinaryNode<E> tempNode = null;
		if (temp.hasRight()) {
			tempNode = temp.getRight();
		}
		start.setValue(temp.getValue());
		if (temp.isLeft()) {
			temp.getParent().setLeft(tempNode);
		} else {
			temp.getParent().setRight(tempNode);
		}

		return true;
	}

	// Returns the minimum in the tree
	public E min() {
		return minHelper(root);
	}

	public E minHelper(BinaryNode<E> start) {
		if (start.hasLeft()) {
			return minHelper(start.getLeft());
		}
		return start.getValue();

	}

	// Returns the maximum in the tree.
	public E max() {
		return maxHelper(root);
	}

	public E maxHelper(BinaryNode<E> start) {
		if (start.hasRight()) {
			return maxHelper(start.getRight());
		}
		return start.getValue();
	}

	// Returns a bracket-surrounded, comma separated list of the contents of the nodes, in order
	// e.g. [Apple, Cranberry, Durian, Mango]
	public String toString() {
		String ret = "[";
		ret += toStringHelper(root);
		if (ret.length() >= 3) {
			ret = ret.substring(0, ret.length() - 2);
		}
		return ret + "]";
	}

	public String toStringHelper(BinaryNode<E> start) {
		if (start == null) {
			return "";
		}
		String ret = "";
		if (start.hasLeft()) {
			ret += toStringHelper(start.getLeft());
		}
		ret += start.getValue() + ", ";
		if (start.hasRight()) {
			ret += toStringHelper(start.getRight());
		}
		return ret;
	}


}
