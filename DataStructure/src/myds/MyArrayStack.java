package myds;

public class MyArrayStack<E> {
	private E[] stack;
	private int topIndex;

	public void push(E item) {
		stack[++topIndex] = item;
	}

	public E pop() {
		E popItem = stack[topIndex--];
		return popItem;
	}
}
