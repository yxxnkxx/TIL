package myds;

public class MyHeap<E> implements Comparable<E> {
	private E[] heap;
	private int numItems;

	public void insert(E newItem) {
		// A[0...n-1]에 newItem을 추가
		heap[numItems] = newItem;
		percolateUp(numItems);
		numItems++;

	}

	public void percolateUp(int i) {
		int parentIdx = (i - 1) / 2;
		if (parentIdx >= 0 && ((Comparable<E>) heap[parentIdx]).compareTo(heap[i]) < 0) {
			E tmp = heap[i];
			heap[i] = heap[parentIdx];
			heap[parentIdx] = tmp;
			percolateUp(parentIdx);
		}

	}

	@Override
	public int compareTo(E o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public E deleteMax() {
		E max = heap[0];
		heap[0] = heap[this.numItems - 1]; // 자리 교환
		this.numItems--; // max값은 배열에서 제외됨
		percolateDown(this.numItems);
		return max;
	}

	public void percolateDown(int i) {
		int child = 2 * i + 1;
		int rightChild = 2 * i + 2;
		if (child <= this.numItems - 1) {
			if (((Comparable<E>) heap[child]).compareTo(heap[rightChild]) < 0)
				child = rightChild; // 더 큰 값을 child로 지정
			if (((Comparable<E>) heap[i]).compareTo(heap[child]) < 0) {
				E tmp = heap[i];
				heap[i] = heap[child];
				heap[child] = tmp;
				percolateDown(child);
			}

		}
	}

	public void buildHeap() {
		if (this.numItems >= 2) {
			for (int i = (numItems - 2) / 2; i >= 0; i--)
				percolateDown(i);
		}
	}
}
