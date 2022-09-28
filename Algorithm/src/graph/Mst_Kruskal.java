package graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Mst_Kruskal {
	static int[] p;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();

		int[][] edges = new int[E][3];
		for (int i = 0; i < E; i++) {
			edges[i][0] = sc.nextInt(); // 시작
			edges[i][1] = sc.nextInt(); // 도착
			edges[i][2] = sc.nextInt(); // 가중치
		}

		// 1. 정렬
		Arrays.sort(edges, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[2] - o2[2]; // 가중치 순서
			}

		});

		// 대표 저장
		p = new int[V];
		// make-set 1-나 자신을 대표로 초기화
		for (int i = 0; i < V; i++)
			makeSet(i);

		// 간선 선택
		int ans = 0;
		int pick = 0;
		for (int i = 0; i < E; i++) {

			if (findSet(edges[i][0]) != findSet(edges[i][1])) {
				union(findSet(edges[i][0]), findSet(edges[i][1]));
				ans += edges[i][2];
				pick++;
			}

			if (pick == V - 1)
				break;
		}
	}

	static void makeSet(int x) {
		p[x] = x;
	}

	static int findSet(int x) {
		// path compression
		if (x != p[x])
			p[x] = findSet(p[x]);
		return p[x];

	}

	static void union(int x, int y) {
		p[findSet(y)] = findSet(x);

	}
}
