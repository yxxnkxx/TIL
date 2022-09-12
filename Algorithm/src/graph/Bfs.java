package graph;

import java.util.LinkedList;
import java.util.Queue;

public class Bfs {

	static boolean[] visited;
	static int[][] list;

	public static void main(String[] args) {
		// 구현
	}

	static void bfs(int v) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(v);
		visited[v] = true;

		while (!q.isEmpty()) {
			int temp = q.poll();
			System.out.println(temp);
			for (int i = 0; i < list[temp].length; i++) {
				int link = list[temp][i];
				if (!visited[link]) {
					visited[link] = true;
					q.offer(link);
				}
			}
		}
	}
}
