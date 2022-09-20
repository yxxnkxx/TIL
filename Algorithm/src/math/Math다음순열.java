package math;

import java.util.Arrays;

public class Math다음순열 {
	static int[] arr;

	public static void main(String[] args) {
		arr = new int[] { 1, 2, 3, 4 };

		do {
			System.out.println(Arrays.toString(arr));
		} while (np());
	}

	static boolean np() {
		int a = -1; // 꼭대기
		int c = 0; // a-1보다 큰 수 찾기
		for (int i = 1; i < arr.length; i++) {
			if (arr[i - 1] < arr[i])
				a = i;
		}
		if (a == -1) {
			return false;
		}

		for (int i = arr.length - 1; i >= 0; i--) {
			if (arr[i] > arr[a - 1]) {
				c = i;
				break;
			}
		}
		// a-1과 c를 swap
		int tmp = arr[a - 1];
		arr[a - 1] = arr[c];
		arr[c] = tmp;
		// a부터 끝까지 거꾸로
		Arrays.sort(arr, a, arr.length);
		return true;
	}

}
