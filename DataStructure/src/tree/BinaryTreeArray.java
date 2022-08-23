package tree;

import java.util.Arrays;
import java.util.Scanner;

public class BinaryTreeArray {
	static int[] tree;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int length = (int) Math.pow(2, V + 1);
		tree = new int[length]; // 최대 배열 크기

		for (int i = 0; i < V - 1; i++) {
			int parent = sc.nextInt();
			int child = sc.nextInt();
			if (tree[1] == 0)
				tree[1] = parent;
			for (int p = 1; p < length; p++) {
				if (tree[p] == parent) {
					if (tree[2 * p] == 0)
						tree[2 * p] = child;
					else
						tree[2 * p + 1] = child;

					break;
				}
			}
		} // 이진트리 배열로 만들기
		System.out.println(Arrays.toString(tree));

		preOrder(1); // 전위순회
		System.out.println();
		inOrder(1);
		System.out.println();
		postOrder(1);

	} // main

	static void preOrder(int i) {
		if (i < tree.length && tree[i] != 0) {
			System.out.print(tree[i] + " ");
			preOrder(i * 2);
			preOrder(i * 2 + 1);
		}
	}

	static void inOrder(int i) {
		if (i < tree.length && tree[i] != 0) {
			inOrder(i * 2);
			System.out.print(tree[i] + " ");
			inOrder(i * 2 + 1);
		}
	}

	static void postOrder(int i) {
		if (i < tree.length && tree[i] != 0) {
			postOrder(i * 2);
			postOrder(i * 2 + 1);
			System.out.print(tree[i] + " ");
		}
	}
}
