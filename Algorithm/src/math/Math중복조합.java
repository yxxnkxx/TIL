package math;

import java.util.Scanner;

public class Math중복조합 {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 중복 조합
		int N = sc.nextInt();

		int M = sc.nextInt();
		int[] out = new int[M];
		comb(N, out, 1, 0, M);
		System.out.print(sb.toString());

	}

	static void comb(int N, int[] out, int start, int depth, int M) {
		if (depth == M) {
			for (int i = 0; i < out.length; i++) {
				sb.append(out[i] + " ");
			}
			sb.append("\n");
			return;
		}

		for (int i = start; i <= N; i++) {
			out[depth] = i;
			comb(N, out, i, depth + 1, M);
		}
	}

}
