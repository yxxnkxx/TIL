package myds.list;

public class MyLinkedList<E> {
	private MyNode<E> head;
	public int numItems;

	public MyLinkedList() {
		this.head = null;
		this.numItems = 0;
	}

	public MyNode<E> get(int index) {
		if (index < 0 || index > numItems) {
			System.out.println("범위 밖");
			return null;
		}

		MyNode<E> curr = head;
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}

		return curr;

	}

	public void printAll() {
		MyNode<E> curr = head;
		while (curr != null) {
			System.out.print(curr.item + " ");
			curr = curr.next;
		}
		System.out.println();

	}

	public void addFirst(E item) {
		MyNode<E> newNode = new MyNode<>(item);
		newNode.next = head;
		this.head = newNode;
		numItems++;

	}

	public void addLast(E item) {
		MyNode<E> curr = get(this.numItems - 1);
		MyNode<E> newNode = new MyNode<>(item);
		curr.next = newNode;
		numItems++;
	}

	public void add(int index, E item) {
		if (index < 0 || index >= numItems)
			return;

		MyNode<E> curr = this.head;
		for (int i = 0; i < index; i++) {
			curr = curr.next; // 삽입 index의 바로 전 값 찾기
		}

		MyNode<E> newNode = new MyNode<E>(item, curr.next);
		curr.next = newNode;
		numItems++;

	}

	public E removeFirst() {
		E data = this.head.item;
		head = head.next;
		numItems--;
		return data;
	}

	public E remove(int index) {

		if (index == 0) {
			return removeFirst();
		}
		if (index < 0 || index >= numItems)
			return null;

		MyNode<E> prev = get(index - 1);

		E data = prev.next.item;
		prev.next = prev.next.next; // 삭제할 노드의 전 노드가, 삭제할 노드의 다음 노드를 가리키게 함
		numItems--;

		return data;
	}

}
