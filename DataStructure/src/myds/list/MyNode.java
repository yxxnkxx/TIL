package myds.list;

public class MyNode<E> {
	public E item;
	public MyNode<E> next;

	public MyNode(E item) {
		this.item = item;
		this.next = null;
	}

	public MyNode(E item, MyNode<E> next) {
		this.item = item;
		this.next = next;
	}

}