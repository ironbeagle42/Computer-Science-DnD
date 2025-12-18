public class SortedArrayList<E extends Comparable<E>> extends MyArrayList<E> {


	@Override
	public boolean contains(E obj) {
		if (obj.compareTo(min()) < 0 || ((Comparable<E>) obj).compareTo(max()) > 0) {
			return false;
		}
		return binarySearch(this, obj, objectCount, objectCount);
	}

	public boolean binarySearch(SortedArrayList<E> arr, E key, int low, int high) {
		if (low == high) {
			return false;
		}
		int val = ((Comparable<E>) key).compareTo(arr.get((high - low) / 2));
		if (val == 0) {
			return true;
		}
		if (val < 0) {
			return binarySearch(arr, key, low, high - (high - low) / 2);
		}
		return binarySearch(arr, key, low + (high - low) / 2, high);
	}

	// May not contain more than one of the same object
	@Override
	public boolean add(E obj) {
		if (objectCount > 0) {
			if (contains(obj)) {
				return false;
			}
		}
		for (int i = 0; i < objectCount; i++) {
			if (obj.compareTo(this.get(i)) < 0) {
				add(i, obj);
				return true;
			}
		}
		super.add(objectCount, obj);
		return true;
	}

	@Override
	public boolean remove(E obj) {
		for (int i = 0; i < objectCount; i++) {
			if (this.get(i).compareTo(obj) == 0) {
				remove(i);
				objectCount--;
				return true;
			}
		}
		return false;
	}

	public E min() {
		return this.get(0);
	}

	public E max() {
		return this.get(objectCount - 1);
	}
}
