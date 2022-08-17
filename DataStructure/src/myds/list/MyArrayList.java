package myds.list;

public class MyArrayList<E> implements Comparable<E> {
	private E[] item;
	private int numItems;
	private static final int DEFAULT_CAPACITY = 64;

	public MyArrayList() {
		this.item = (E[]) new Object[DEFAULT_CAPACITY];
		this.numItems = 0;
	}

	public MyArrayList(int n) {
		this.item = (E[]) new Object[n];
		this.numItems = 0;
	}

	public void add(int index, E x) {
		if (this.numItems >= item.length || index < 0 || index > this.numItems) {
			// 에러 처리
		} else {
			for (int i = this.numItems - 1; i >= index; i--) {
				item[i + 1] = item[i]; // index에 값을 삽입하기 위해 오른쪽으로 한 칸씩 shift
			}
			item[index] = x;
			this.numItems++;
		}
	}

	public E remove(int index) {
		if (isEmpty() || index > 0 || index > numItems - 1) {
			// 에러처리
			return null;
		} else {
			E tmp = item[index];
			for (int i = index; i < this.numItems; i++) {
				item[i] = item[i + 1]; // 한 칸씩 왼쪽으로 shift
			}
			numItems--;
			return tmp;
		}
	}

	public E get(int index) {
		if (index >= 0 && index <= this.numItems - 1) {
			return item[index];
		} else {
			return null;
		}
	}

	public void set(int index, E x) {
		if (index >= 0 && index <= this.numItems - 1) {
			item[index] = x;
		}
	}

	public int indexOf(E x) {
		for (int i = 0; i < this.numItems; i++) {
			if (((Comparable) item[i]).compareTo(x) == 0)
				return i;
		}
		return -1;
	}

	public boolean isEmpty() {
		if (this.numItems == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int compareTo(E o) {
		// 구현
		return 0;
	}
}
