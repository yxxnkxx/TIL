package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ExhaustiveSearch_babygin {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[6];
		for (int i = 0; i < 6; i++) {
			arr[i] = sc.nextInt();

		}
		System.out.println(makePermut(arr));

	}

	static boolean makePermut(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int i1 = arr[i];

			for (int j = 0; j < arr.length; j++) {
				if (j != i) {
					int i2 = arr[j];

					for (int k = 0; k < arr.length; k++) {
						if (k != i && k != j) {
							int i3 = arr[k];
							int num = i1 * 100 + i2 * 10 + i3;
							if (isRun(num) || isTriplet(num)) {
								for (int l = 0; l < arr.length; l++) {
									if (l != i && l != j && l != k) {
										int o1 = arr[l];
										for (int m = 0; m < arr.length; m++) {
											if (m != i && m != j && m != k && m != l) {
												int o2 = arr[m];
												for (int n = 0; n < arr.length; n++) {
													if (n != i && n != j && n != k && n != l && n != m) {
														int o3 = arr[n];
														int other = o1 * 100 + o2 * 10 + o3;
														if (isRun(other) || isTriplet(other)) {
															return true;
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	static boolean isRun(int N) {

		ArrayList<Integer> run = new ArrayList<>();
		int i1 = N / 100;
		int i2 = (N / 10) % 10;
		int i3 = (N % 10) % 10;
		run.add(i1);
		run.add(i2);
		run.add(i3);
		Collections.sort(run);

		if (run.get(1) - run.get(0) == 1 && run.get(2) - run.get(1) == 1) {
			return true;
		}

		return false;

	}

	static boolean isTriplet(int N) {
		int i1 = N / 100;
		int i2 = (N / 10) % 10;
		int i3 = (N % 10) % 10;
		if (i1 == i2 && i2 == i3) {

			return true;
		}
		return false;
	}

}
