package myds.list;

class DoubleNode<E> {
	public E data;
	public DoubleNode<E> prev;
	public DoubleNode<E> next;

	public DoubleNode(E data) {
		this.data = data;
		this.prev = null;
		this.next = null;
	}

}

public class DoublyLinkedList<E> {
	private DoubleNode<E> head;
	private DoubleNode<E> tail;
	private int size;

	// 해도그만 안해도 그만
	public DoublyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	// 조회
	public DoubleNode<E> get(int idx) {
		if (idx < 0 || idx >= size)
			return null;
		DoubleNode<E> curr;
		// 뒤에서부터
		if (idx > size / 2) {
			curr = tail;
			for (int i = size - 1; i > idx; i--)
				curr = curr.prev;
		}
		// 앞에서부터
		else {
			curr = head;
			for (int i = 0; i < idx; i++)
				curr = curr.next;
		}
		return curr;

	}

	// 첫번쨰 노드 삽입
	public void addFirst(E data) {
		DoubleNode<E> node = new DoubleNode(data);
		node.next = head;
		if (head != null)
			head.prev = node;
		head = node;
		size++;

		// 원래 비어있었을 경우
		if (head.next == null)
			tail = head;

	}

	// 마지막 노드 삽입
	public void addLast(E data) {

		if (size == 0) {
			addFirst(data);
			return;
		}
		DoubleNode<E> node = new DoubleNode(data);
		tail.next = node;
		node.prev = tail;
		tail = node;
		size++;

	}

	// 중간 노드 삽입
	public void add(int idx, E data) {
		if (idx == 0) {
			addFirst(data);
			return;
		}
		if (idx == size) {
			addLast(data);
			return;
		}

		if (idx < 0 || idx > size) {
			System.out.println("범위 벗어남");
			return;
		}
		DoubleNode<E> newNode = new DoubleNode<E>(data);
		DoubleNode<E> prevNode = get(idx - 1);
		DoubleNode<E> nextNode = prevNode.next.next;
		prevNode.next = newNode;
		newNode.prev = prevNode;
		nextNode.prev = newNode;
		newNode.next = nextNode;
		size++;
	}

	// 첫번째 노드 삭제
	public E remove() {
		if (size == 0)
			return null;
		E data = head.data;
		if (head.next != null)
			head.next.prev = null;
		head = head.next;
		size--;
		if (size == 0)
			tail = null;

		return data;
	}

	// 중간 노드 삭제
	public E remove(int idx) {
		if (idx == 0)
			return remove();

		if (idx < 0 || idx >= size)
			return null; // 예외처리

		DoubleNode<E> prevNode = get(idx - 1);
		size--;
		E data = prevNode.next.data;
		if (prevNode.next.next == null) {
			prevNode.next = null;
			tail = prevNode;
		} else {
			DoubleNode<E> nextNode = prevNode.next.next;
			prevNode.next = nextNode;
			nextNode.prev = prevNode;
		}
		return data;
	}

	// 출력
	public void printList() {
		DoubleNode<E> curr = head;
		if (curr == null) {
			System.out.println("비어 있음");
			return;
		}

		while (curr != null) {
			System.out.print(curr.data + " ");
			curr = curr.next;
		}
		System.out.println();

	}

}
