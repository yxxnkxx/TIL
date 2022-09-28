package graph;

import java.util.Arrays;
import java.util.Scanner;

public class Mst_Prim_arr {

	static int[] p;

	static String input = "7 11\r\n" + "0 1 32\r\n" + "0 2 31\r\n" + "0 5 60\r\n" + "0 6 51\r\n" + "1 2 21\r\n"
			+ "2 4 46\r\n" + "2 6 25\r\n" + "3 4 34\r\n" + "3 5 18\r\n" + "4 5 40\r\n" + "4 6 51\r\n" + "";

	public static void main(String[] args) {
		Scanner sc = new Scanner(input);
		int V = sc.nextInt();
		int E = sc.nextInt();

		int[][] adjArr = new int[V][V];
		for (int i = 0; i < E; i++) {
			int st = sc.nextInt();
			int ed = sc.nextInt();
			int w = sc.nextInt();

			adjArr[st][ed] = adjArr[ed][st] = w;
		} // 입력

		boolean[] visited = new boolean[V];
		int[] dist = new int[V];
		int[] p = new int[V]; // 경로 저장

		// dist의 초기 값은 무한대
		Arrays.fill(dist, Integer.MAX_VALUE);

		// 0번부터 시작
		dist[0] = 0;
		p[0] = -1; // 시작점 표시

		int ans = 0;
		for (int i = 0; i < V - 1; i++) {
			int min = Integer.MAX_VALUE;
			int idx = -1;
			// 방문하지 않은 노드 중 가장 작은 값 extract_min
			for (int j = 0; j < V; j++) {
				if (!visited[j] && dist[j] < min) {
					min = dist[j];
					idx = j;
				}
			}
			// idx를 뽑음
			visited[idx] = true;

			// 인접 노드 갱신
			for (int j = 0; j < V; j++) {
				if (!visited[j] && adjArr[idx][j] != 0 && dist[j] > adjArr[idx][j]) {
					dist[j] = adjArr[idx][j];
					p[j] = idx;
				}
			}

		} // 프림 만들기

		for (int i = 0; i < V; i++)
			ans += dist[i];
		System.out.println(ans);

	}
}
