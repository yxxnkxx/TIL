package combinatorics;

public class MyBinoCo {

	static int[][] memo;

	public static void main(String[] args) {

		int N = 10;
		int K = 3;
		// 팩토리얼
		System.out.println(fact(N) / fact(N - K) / fact(K));

		// dp
		memo = new int[N + 1][K + 1];
		System.out.println(bino(N, K));

	}

	static int fact(int N) {

		if (N == 0 || N == 1)
			return 1;
		int tmp = N;
		for (int i = 2; i < N; i++)
			tmp *= i;
		return tmp;

	}

	static int bino(int N, int K) {
		if (memo[N][K] > 0)
			return memo[N][K]; // memo가 되어있으면 바로 return
		if (N < K)
			return 0; // N보다 K(뽑는 수)가 더 크면 0
		if (K == 0 || N == K)
			return 1; // 정의 상 K가 0일 때 (안 뽑을 때), K가 N일 때(모든 가짓수를 다 뽑기)는 1
		memo[N][K] = bino(N - 1, K) + bino(N - 1, K - 1); // 성질 이용
		return memo[N][K];

	}

}
