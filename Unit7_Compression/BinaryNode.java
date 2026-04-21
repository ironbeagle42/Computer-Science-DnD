
public class BinaryNode<Character, Integer> {

	private Character value;
	private Integer freq;
	private BinaryNode<Character, Integer> left;
	private BinaryNode<Character, Integer> right;
	private BinaryNode<Character, Integer> parent;
	private int height;
	
	public BinaryNode(Character str, Integer num) {
		this.value = str;
		this.freq = num;
		this.left = null;
		this.right = null;
		this.parent = null;
		this.height = 0;
	}
	
	public Character getValue() {
		return value;
	}

	public Integer getFreq() {
		return freq;
	}

	public BinaryNode<Character, Integer> getLeft() {
		return left;
	}

	public BinaryNode<Character, Integer> getRight() {
		return right;
	}

	public BinaryNode<Character, Integer> getParent() {
		return parent;
	}

	public int getHeight() {
		return height;
	}
	
	public void setValue(Character value) {
		this.value = value;
	}

	public void setFreq(Integer num) {
		this.freq = num;
	}

	public void setLeft(BinaryNode<Character, Integer> left) {
		if (left == null) {
			this.left = null;
			return;
		}
		this.left = left;
		this.left.setParent(this);
		this.left.setHeight(height + 1);
	}

	public void setRight(BinaryNode<Character, Integer> right) {
		if (right == null) {
			this.right = null;
			return;
		}
		this.right = right;
		this.right.setParent(this);
		this.right.setHeight(height + 1);
	}

	public void setParent(BinaryNode<Character, Integer> parent) {
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
