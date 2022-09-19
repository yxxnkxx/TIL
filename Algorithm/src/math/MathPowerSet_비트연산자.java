package math;

public class MathPowerSet_비트연산자 {
	static int[] nums = { 1, 3, 4, 6 };
	static int N = 4;

	public static void main(String[] args) {
		for (int i = 0; i < (1 << 4); i++) {
			for (int j = 0; j < N; j++)
				if ((i & (1 << j)) > 0)
					System.out.print(nums[j] + " ");
			System.out.println();
		}
	}
}
