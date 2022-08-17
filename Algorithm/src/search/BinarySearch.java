package search;

public class BinarySearch {
	static int[] arr = { 2, 6, 8, 13, 22, 30, 46 };

	public static void main(String[] args) {
		int successKey = 6;
		int failKey = 24;

		System.out.println(binarySearch(successKey));
		System.out.println(binarySearch(failKey));
	}

	static int binarySearch(int key) {
		// 성공하면 index를, 실패하면 -1을 반환
		int start = 0;
		int end = arr.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (key == arr[mid])
				return mid;
			else if (key < arr[mid]) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}

		}
		return -1;
	}

}
