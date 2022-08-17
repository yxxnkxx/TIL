package myds;

import myds.list.MyLinkedList;
import myds.list.MyNode;

public class MyLinkedStack<E> extends MyLinkedList<E> {

	MyNode<E> top = null;

	public E pop() {
		if (top == null)
			return null;
		E data = top.item;
		top = top.next;
		numItems--;
		return data;

	}

	public void push(E data) {
		MyNode<E> newNode = new MyNode<E>(data);
		newNode.next = top;
		top = newNode;
		numItems++;
	}

}
