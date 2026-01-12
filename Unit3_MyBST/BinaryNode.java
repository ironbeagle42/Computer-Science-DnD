
public class BinaryNode<E extends Comparable<E>> {

	private E value;
	private BinaryNode<E> left;
	private BinaryNode<E> right;
	private BinaryNode<E> parent;
	private int height;
	
	public BinaryNode(E value) {
		this.value = value;
		this.left = null;
		this.right = null;
		this.parent = null;
		this.height = 0;
	}
	
	public E getValue() {
		return value;
	}

	public BinaryNode<E> getLeft() {
		return left;
	}

	public BinaryNode<E> getRight() {
		return right;
	}

	public BinaryNode<E> getParent() {
		return parent;
	}

	public int getHeight() {
		return height;
	}
	
	public void setValue(E value) {
		this.value = value;
	}

	public void setLeft(BinaryNode<E> left) {
		if (left == null) {
			this.left = null;
			return;
		}
		this.left = left;
		this.left.setParent(this);
		this.left.setHeight(height + 1);
	}

	public void setRight(BinaryNode<E> right) {
		if (right == null) {
			this.right = null;
			return;
		}
		this.right = right;
		this.right.setParent(this);
		this.right.setHeight(height + 1);
	}

	public void setParent(BinaryNode<E> parent) {
		this.parent = parent;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public boolean hasLeft() {
		return left != null;
	}
	
	public boolean hasRight() {
		return right != null;
	}
	
	public boolean isLeaf() {
		return !hasLeft() && !hasRight();
	}

	public String toString() {
		return value.toString();
		
	}

	public boolean isLeft() {
		//returns true if left, false if right
		if (parent.getLeft() != null) {//parent has left
			if (parent.getLeft().equals(this)) {
				return true;
			}
		}
		return false;
	}
	
}
