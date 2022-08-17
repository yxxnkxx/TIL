package sort;

import java.util.Arrays;

public class InsertionSort {

	public static void main(String[] args) {
		int[] arr = new int[] { 60, 10, 3, 45, 7, 24, 1 };
		insertionSort(arr);
		System.out.println(Arrays.toString(arr));

	}

	static void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int key = arr[i];
			int j;
			for (j = i - 1; j >= 0; j--) {
				if (arr[j] <= key)
					break;
				else
					arr[j + 1] = arr[j];
			}
			arr[j + 1] = key;

		}

	}

}
