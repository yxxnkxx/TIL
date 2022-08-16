package myds;

public class MyArrayQueue<E> {
	// 원형 배열 이용
	private int size = 10;
	private E[] queue;
	private int front;
	private int tail;

	public boolean isEmpty() {
		return front == tail;
	}

	public boolean isFull() {
		return front == (tail + 1) % size;
	}

	// 삽입은 tail에
	public void enqueue(E item) {
		if (isFull()) {
			return;
		} else {
			this.tail = (tail + 1) % this.queue.length;
			queue[tail] = item;
		}

	}

	// 삭제는 front부터
	public E dequeue() {
		if (isEmpty()) {
			return null;
		}

		E queueFront = queue[front];
		front = (front + 1) % this.queue.length;

		return queueFront;
	}

}
