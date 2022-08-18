package stackqueue;

public class MyArrayStack {
	private static int[] stack = new int[100];
	private static int topIndex = -1;;

	public static void main(String[] args) {

	}

	public boolean isFull() {
		return topIndex == stack.length - 1;

	}

	public boolean isEmpty() {
		return topIndex == -1;
	}

	public void push(int item) {
		if (isFull()) {
			System.out.println("가득 참");
			return;
		}
		stack[++topIndex] = item;
	}

	public int pop() {
		if (isEmpty()) {
			System.out.println("비어있음");
			return -999;
		}

		int popItem = stack[topIndex--];
		return popItem;
	}

}
