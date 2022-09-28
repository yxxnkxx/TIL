package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BellmanFord {
// 백준 11657
	static class Edge {
		int start;
		int end;
		long weight;

		public Edge(int start, int end, long weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

	}

	static int N, M;
	static List<Edge> adjList;
	static final long INF = Long.MAX_VALUE;
	static long[] dis;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		adjList = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			int st = sc.nextInt();
			int ed = sc.nextInt();
			long weight = sc.nextLong();
			adjList.add(new Edge(st, ed, weight));
		}

		dis = new long[N + 1];
		Arrays.fill(dis, INF);

		boolean check = bellmanFord(1);

		if (!check)
			System.out.println("cycle");
		else {
			StringBuilder sb = new StringBuilder();
			for (int i = 2; i <= N; i++) {
				if (dis[i] == INF)
					dis[i] = -1; // 도달 불가
				sb.append(dis[i]).append("\n");
			}
			System.out.print(sb);
		}

	}

	static boolean bellmanFord(int r) {
		dis[r] = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < adjList.size(); j++) {
				int st = adjList.get(j).start;
				int ed = adjList.get(j).end;
				long weight = adjList.get(j).weight;
				if (dis[st] == INF)
					continue;

				if (dis[ed] > dis[st] + weight) {
					dis[ed] = dis[st] + weight;
					if (i == N - 1)
						return false;
				}
			}
		}

		return true;

	}

}
