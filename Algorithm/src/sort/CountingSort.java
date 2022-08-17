package sort;

import java.util.Arrays;

public class CountingSort {
	public static void main(String[] args) {
		int[] arr = { 0, 4, 1, 2, 3, 3, 1, 1 };

		System.out.println(Arrays.toString(countingSort(arr)));

	}

	static int[] countingSort(int[] arr) {
		// max 값 찾기
		int max = 0;
		for (int i = 0; i < arr.length; i++)
			if (max < arr[i])
				max = arr[i];
		int[] count = new int[max + 1];

		// 각 수를 count
		for (int num : arr) {
			count[num]++;
		}

		// 누적합
		for (int i = 1; i < count.length; i++) {
			count[i] += count[i - 1];
		}

		// tmp 배열에 정렬
		int[] tmp = new int[arr.length];

		for (int i = arr.length - 1; i >= 0; i--) {
			int idx = --count[arr[i]];
			tmp[idx] = arr[i];
		}
		return tmp;
	}

}
