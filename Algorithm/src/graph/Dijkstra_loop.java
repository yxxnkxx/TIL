package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Dijkstra_loop {

	static class Node {
		int v, weight;

		public Node(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}

	}

	static final int INF = Integer.MAX_VALUE;
	static int V, E;
	static List<Node>[] adjList;
	static int[] dist; // 최단 길이 저장

	static String input = "6 11\r\n" + "0 1 4\r\n" + "0 2 2\r\n" + "0 5 25\r\n" + "1 3 8\r\n" + "1 4 7\r\n"
			+ "2 1 1\r\n" + "2 4 4\r\n" + "3 0 3\r\n" + "3 5 6\r\n" + "4 3 5\r\n" + "4 5 12\r\n" + "";

	public static void main(String[] args) {
		Scanner sc = new Scanner(input);

		V = sc.nextInt();
		E = sc.nextInt();

		adjList = new ArrayList[V];
		for (int i = 0; i < V; i++)
			adjList[i] = new ArrayList<>();

		dist = new int[V];
		Arrays.fill(dist, INF);

		// 초기화

		for (int i = 0; i < E; i++) {
			int st = sc.nextInt();
			int ed = sc.nextInt();
			int w = sc.nextInt();

			// 유향 그래프
			adjList[st].add(new Node(ed, w));
		} // 입력

		dijkstra(0);

		System.out.println(Arrays.toString(dist));

	}

	private static void dijkstra(int st) {

		boolean[] visited = new boolean[V];

		dist[st] = 0; // 시작 노드
		for (int i = 0; i < V - 1; i++) {
			int min = INF;
			int idx = -1;

			// 선택하지 않은 정점 중 dist값이 최소인 경우
			for (int j = 0; j < V; j++) {
				if (!visited[j] && dist[j] < min) {
					min = dist[j];
					idx = j;
				}
			}

			if (idx == -1)
				break; // 더이상 연결된게 없음

			// idx를 뽑음
			visited[idx] = true;

			// 갱신
			for (int j = 0; j < adjList[idx].size(); j++) {
				Node curr = adjList[idx].get(j);
				if (!visited[curr.v] && dist[curr.v] > dist[idx] + curr.weight)
					dist[curr.v] = dist[idx] + curr.weight;

			}

		}

	}
}
