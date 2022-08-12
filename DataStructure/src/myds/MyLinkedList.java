package myds;

class Node<E> {
	public E item;
	public Node<E> next;

	public Node(E item) {
		this.item = item;
		this.next = null;
	}

	public Node(E item, Node<E> next) {

		this.item = item;
		this.next = next;
	}

}

public class MyLinkedList<E> {
	private Node<E> head;
	private int numItems;

	public MyLinkedList() {
		this.head = new Node<E>(null);
		this.numItems = 0;
	}

	public void add(int index, E item) {
		Node<E> curr = this.head;
		for (int i = 0; i < index; i++) {
			curr = curr.next; // 삽입 index의 바로 전 값 찾기
		}

		Node<E> newNode = new Node<E>(item, curr.next);
		curr.next = newNode;
		numItems++;

	}

	public void remove(int index) {
		Node<E> curr = this.head;
		for (int i = 0; i < index; i++) {
			curr = curr.next; // 삭제할 값의 바로 전 값 찾기
		}
		curr.next = curr.next.next; // 삭제할 노드의 전 노드가, 삭제할 노드의 다음 노드를 가리키게 함
		numItems--;
	}

}
