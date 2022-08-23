package tree;

import java.util.Scanner;

public class BinaryTreeLinkedList {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		MyBinaryTree tree = new MyBinaryTree();

		for (int i = 0; i < V - 1; i++) {
			int parent = sc.nextInt();
			int child = sc.nextInt();
			tree.insert(parent, child);
		}
		tree.preOrder(tree.root);
		System.out.println();
		tree.inOrder(tree.root);
		System.out.println();
		tree.postOrder(tree.root);

	}

}

class MyNode {
	int data;
	MyNode left;
	MyNode right;

	public MyNode(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
}

class MyBinaryTree {

	MyNode root;

	public MyBinaryTree() {
		this.root = null;
	}

	public void insert(int parent, int child) {
		MyNode parentNode = search(root, parent);
		if (parentNode == null) { // root가 비어있을 때
			parentNode = new MyNode(parent);
			this.root = parentNode;
		}

		MyNode childNode = new MyNode(child);
		if (parentNode.left == null)
			parentNode.left = childNode;
		else
			parentNode.right = childNode;

	}

	public MyNode search(MyNode curr, int key) {
		MyNode leftNode = null;
		MyNode rightNode = null;
		if (curr != null) {
			if (curr.data == key)
				return curr;
			if (curr.left != null)
				leftNode = search(curr.left, key);
			if (curr.right != null)
				rightNode = search(curr.right, key);
		}

		if (leftNode != null)
			return leftNode;
		else if (rightNode != null)
			return rightNode;
		return null;
	}

	public void preOrder(MyNode root) {
		if (root != null) {
			System.out.print(root.data + " ");
			preOrder(root.left);
			preOrder(root.right);
		}
	}

	public void inOrder(MyNode root) {
		if (root != null) {
			inOrder(root.left);
			System.out.print(root.data + " ");
			inOrder(root.right);
		}
	}

	public void postOrder(MyNode root) {
		if (root != null) {
			postOrder(root.left);
			postOrder(root.right);
			System.out.print(root.data + " ");
		}
	}

}
