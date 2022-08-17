package myds.list;

public class Test {
	public static void main(String[] args) {
		MyLinkedList<Integer> list = new MyLinkedList<>();
		list.addFirst(1);
		list.printAll();
		list.addFirst(2);
		list.printAll();
		list.addLast(3);
		list.printAll();
		list.remove(0);
		list.printAll();
//		System.out.println(list.get(3));

	}

}
