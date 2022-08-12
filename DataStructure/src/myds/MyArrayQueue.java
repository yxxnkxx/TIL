package myds;

public class MyArrayQueue<E> {
	// 원형 배열 이용
	private E[] queue;
	private int front;
	private int tail;
	private int numItems;

	// 삽입은 tail에
	public void enqueue(E item) {
		this.tail = (tail + 1) % this.queue.length;
		queue[tail] = item;
		numItems++;
	}

	// 삭제는 front부터
	public E dequeue() {
		E queueFront = queue[front];
		front = (front + 1) % this.queue.length;
		numItems--;
		return queueFront;
	}

}
