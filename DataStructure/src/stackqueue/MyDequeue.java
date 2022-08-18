package stackqueue;

public class MyDequeue<E> {
	private DNode<E> head;
	private DNode<E> tail;
	private int size;

	public MyDequeue() {

		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public void offerFirst(E data) {
		// offerFirst
		DNode<E> newNode = new DNode<E>(data);
		newNode.next = head;
		if (head != null)
			head.prev = newNode;
		head = newNode;
		if (head.next == null)
			tail = head;
		size++;

	}

	public void offer(E data) {
		if (size == 0)
			offerFirst(data);
		DNode<E> newNode = new DNode<E>(data);
		newNode.prev = tail;
		tail = newNode;
		size++;
		// offerLast
	}

	public E poll() {
		// pollFirst
		if (head == null)
			return null;
		E data = head.data;
		head = head.next;
		if (head != null)
			head.prev = null;
		size--;

		if (size == 0)
			tail = null;
		return data;
	}

	public E pollLast() {
		if (tail == null)
			return null;
		E data = tail.data;
		tail = tail.prev;
		if (tail != null)
			tail.next = null;
		size--;

		if (size == 0)
			head = null;
		return data;

	}

	public E peek() {
		if (head == null)
			return null;
		return head.data;

	}

	public E peekLast() {
		if (tail == null)
			return null;
		return tail.data;

	}

}

class DNode<E> {
	E data;
	DNode<E> prev;
	DNode<E> next;

	public DNode(E data) {
		this.data = data;
		this.prev = null;
		this.next = null;
	}

	public DNode(E data, DNode<E> prev, DNode<E> next) {
		this.data = data;
		this.prev = prev;
		this.next = next;
	}

}
