package math;

import java.util.Scanner;

public class Math중복순열 {

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 중복 순열
		int N = sc.nextInt();

		int M = sc.nextInt();
		int[] out = new int[M];
		perm(N, out, 0, M);
		System.out.print(sb.toString());

	}

	static void perm(int N, int[] out, int depth, int r) {

		if (depth == r) {

			for (int i = 0; i < out.length; i++) {
				sb.append(out[i] + " ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 1; i <= N; i++) {
			out[depth] = i;
			perm(N, out, depth + 1, r);

		}
	}

}
