package math;

import java.util.Scanner;

public class Math조합 {
	static boolean[] check;
	static int[] result;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		check = new boolean[N + 1];
		result = new int[M + 1];

		dfs(N, M, 0, 1);

		System.out.print(sb);

	}

	static void dfs(int N, int M, int cnt, int k) {

		if (cnt == M) { // 배열의 개수가 M이 되면 출력하고 return
			for (int i = 0; i < M; i++) {
				sb.append(result[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for (int i = k; i <= N; i++) {
			if (!check[i]) { // 방문하지 않은 노드
				check[i] = true; // 방문 체크
				result[cnt] = i; // result값에 대입
				dfs(N, M, cnt + 1, i + 1); // 다시 재귀적으로 dfs (cnt 1 증가)
				check[i] = false;
			}

		}

	}

}
