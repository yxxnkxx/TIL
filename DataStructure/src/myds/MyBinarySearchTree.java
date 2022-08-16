package myds;

public class MyBinarySearchTree {
	private Node root;

	public MyBinarySearchTree() {
		this.root = null;
	}

	// 검색, key는 int라고 가정
	public Node search(int key) {
		return searchItem(root, key);
	}

	private Node searchItem(Node tNode, int key) {
		if (tNode == null)
			return null; // 검색 실패
		else if (tNode.item == key)
			return tNode; // 검색 성공
		else if (key < tNode.item)
			return searchItem(tNode.left, key);
		else
			return searchItem(tNode.right, key);
	}

	public void insert(int key) {
		root = insertItem(root, key);
	}

	private Node insertItem(Node tNode, int key) {
		if (tNode == null)
			tNode = new Node(key);
		else if (key < tNode.item)
			tNode.left = insertItem(tNode.left, key);
		else
			tNode.right = insertItem(tNode.right, key);
		return tNode;

	}

	// 삭제
	public void delete(int key) {
		root = deleteItem(root, key);
	}

	private Node deleteItem(Node tNode, int key) {
		if (tNode == null)
			return null; // 삭제할 node 존재x
		else {
			if (tNode.item == key)
				tNode = deleteNode(tNode); // 자식의 유무에 따라 다른 노드가 반환
			else if (key < tNode.item)
				tNode.left = deleteItem(tNode.left, key);
			else {
				tNode.right = deleteItem(tNode.right, key);
			}
			return tNode;
		}
	}

	private Node deleteNode(Node tNode) {
		// case 1
		if (tNode.left == null && tNode.right == null)
			return null;
		// case 2
		else if (tNode.left == null)
			return tNode.right; // 오른쪽 자식만 o
		else if (tNode.right == null)
			return tNode.left; // 왼쪽 자식만 o
		else {
			MyPair pair = deleteMinItem(tNode.right); // r의 오른쪽에서 가장 작은 노드 찾기
			tNode.item = pair.item;
			tNode.right = pair.node;
			return tNode;

		}
	}

	private MyPair deleteMinItem(Node tNode) {
		if (tNode.left == null)
			return new MyPair(tNode.item, tNode.right);
		// 자리를 바꿀 노드의 right를 기존 부모의 left로 연결해줘야 함!
		else {
			MyPair pair = deleteMinItem(tNode.left);
			tNode.left = pair.node;
			pair.node = tNode;
			return pair;
		}
	}

	private class MyPair {
		private int item;
		private Node node;

		public MyPair(int item, Node node) {

			this.item = item;
			this.node = node;
		}

	}
}

class Node {
	int item; // key
	Node left;
	Node right;

	public Node(int item) {
		this.item = item;
		left = null;
		right = null;
	}

	public Node(int item, Node left, Node right) {
		this.item = item;
		this.left = left;
		this.right = right;
	}

}