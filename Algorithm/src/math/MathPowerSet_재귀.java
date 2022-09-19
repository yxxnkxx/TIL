package math;

public class MathPowerSet_재귀 {
	static int[] nums = { 1, 3, 4, 6 };
	static int N = 4;
	static boolean[] visited = new boolean[N];

	public static void main(String[] args) {
		powerset(0);
	}

	static void powerset(int idx) {
		if (idx == N) {
			for (int i = 0; i < N; i++)
				if (visited[i])
					System.out.print(nums[i] + " ");
			System.out.println();
			return;
		}

		visited[idx] = true;
		powerset(idx + 1);
		visited[idx] = false;
		powerset(idx + 1);
	}
}
