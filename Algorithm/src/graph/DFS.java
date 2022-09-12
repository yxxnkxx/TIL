package graph;

public class DFS {
	static boolean[] visited;
	static int[][] list;

	public static void main(String[] args) {
		// 구현
	}

	static void dfs(int v) {

		visited[v] = true;
		System.out.println(v);
		for (int i = 0; i < list[v].length; i++) {
			int link = list[v][i];
			if (!visited[link]) {
				visited[link] = true;
				dfs(link);
			}
		}

	}
}
