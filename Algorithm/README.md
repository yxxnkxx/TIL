# Algorithm

<h1> 정렬 </h1>
<details>
<summary>정렬</summary>
<div markdown="1">

# 정렬

<details>
<summary>버블 정렬 </summary>
<div markdown="1">

## 버블 정렬

### 개념

인접한 두 개의 원소를 비교하며 정렬하는 알고리즘

### 정렬 과정

배열이 {30, 15, 2, 8, 21, 7}일 때를 가정한다.

원소는 자신의 오른쪽 값과 비교하기 때문에, 첫 사이클에서 비교할 마지막 index는 n-2이다. n-1(마지막 원소)와 비교를 하면 한 사이클이 끝나기 때문이다.

그렇게 한 사이클이 지나면 가장 큰 값이 배열의 오른쪽에 위치하여 다음 사이클에서는 비교 대상에서 제외된다.

![Untitled](./img/bubblesort1.jpeg)

두 번째는 30을 제외하고 다섯개의 원소만 비교하며 같은 과정을 반복한다. 이 사이클이 끝나면 두 번째로 큰 원소인 21이 자신의 위치를 찾아간다.

![Untitled](./img/bubblesort2.jpeg))

![Untitled](./img/bubblesort3.jpeg)

![Untitled](./img/bubblesort4.jpeg)

이렇게 반복하다보면 i가 1일때, 두 번째로 작은 원소인 7이 정렬되고, 자동으로 가장 작은 원소인 2는 비교 대상이 없기 때문에 모든 정렬이 끝난다.

### 코드

```java
package sort;

import java.util.Arrays;

public class BubbleSort {
	public static void main(String[] args) {
		int[] arr = { 30, 15, 2, 8, 21, 7 };
		bubbleSort(arr);
		System.out.println(Arrays.toString(arr));

	}

	static void bubbleSort(int[] arr) {
		for (int i = arr.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
				}
			}
		}
	}

	static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;

	}
}
```

</div>
</details>

<details>
<summary>선택 정렬 </summary>
<div markdown="1">



## 선택 정렬

### 개념

주어진 자료들 중 가장 작은 값의 원소부터 차례대로 선택하여 위치를 교환하는 방식으로 정렬하는 알고리즘

시간 복잡도: O(n^2)

### 정렬 과정

1. 첫 번째 원소를 두 번째부터 마지막 원소까지 비교하여 가장 작은 값과 자리 교환

![selectionsort1.jpeg](./img/selectionsort1.jpeg)

2가 가장 작은 원소이고, 그 값의 index는 2이므로 arr[0]과 arr[1] 교환, arr[0]은 가장 작은 값으로 정렬 완료

1. 두 번째, 세 번째 … 끝에서 두 번째 원소까지 같은 과정을 반복함

![selectionsort2.jpeg](./img/selectionsort2.jpeg)

### 코드

```java
package sort;

import java.util.Arrays;

public class SelectionSort {
	public static void main(String[] args) {
		int[] arr = { 30, 15, 2, 8, 21, 7 };
		selectionSort(arr);
		System.out.println(Arrays.toString(arr));

	}

	static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int minIdx = i;
			int idx = i;
			while (idx < arr.length) {
				if (arr[idx] < arr[minIdx]) {
					minIdx = idx;
				}
				idx++;

			}
			swap(arr, i, minIdx);
		}
	}

	static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

}
```
</div>
</details>

</div>
</details>

---


<h1> 검색 </h1>
<details>
<summary>검색</summary>


# 검색

자료에서 원하는 항목을 찾는 작업

## 종류

순차 검색

이진 검색

인덱싱

<details>
<summary>순차 검색</summary>
<div>


## 순차 검색

일렬로 되어 있는 자료를 순서대로 검색

장점: 배열, 연결 리스트 등에서 유용

단점: 자료의 크기가 큰 경우에 비효율적

### 정렬X

첫 번째 원소부터 마지막 원소까지 키 값이 같은 원소가 있는지 검색

동일한 원소를 찾으면 검색을 중지하고 그 인덱스를 반환

마지막 원소까지 키를 찾지 못하면 실패

시간 복잡도: O(n)

### 정렬O

첫 번째 원소부터 키 값보다 큰 원소가 나올 때까지 검색

동일한 원소를 찾으면 반환, 키 값보다 큰 원소가 나왔는데 찾지 못하면 검색 실패

시간 복잡도: O(n)

그러나 정렬되지 않았을 때보다 평균 비교 횟수가 절반으로 줄어든다. 


</div>
</details>

<details>
<summary>이진 검색</summary>
<div>

## 이진 검색

자료의 가운데에 있는 항목의 키 값과 비교하여 다음 검색의 위치를 결정하고 계속해서 검색을 진행

검색을 수행할 때마다 범위가 반으로 줄어들어 효율적인 알고리즘

시간 복잡도: O(log n)

조건: 자료가 정렬되어 있어야 함

### 과정

1. 자료의 중앙에 있는 원소 선택
2. key값과 중앙 원소 비교
3. key값이 더 작으면 중앙 원소의 왼쪽에서, key 값이 더 크면 중앙 원소의 오른쪽에서 검색 수행

### 코드

```java
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
		while (start <= end) { // start가 end보다 커지면 검색 종료
			int mid = (start + end) / 2;
			if (key == arr[mid])
				return mid;
			else if (key < arr[mid]) {
				end = mid - 1; // mid의 왼쪽
			} else {
				start = mid + 1; // mid의 오른쪽
			}

		}
		return -1;
	}

}
```

</div>
</details>

<details>
<summary>완전 검색</summary>
<div>

## 완전 검색

가능한 모든 경우의 수를 확인하는 기법

Brute-force, Generate-and-Test 기법

경우의 수가 작을 때 유용함

### Baby-Gin

임의의 숫자 6개를 뽑아 run과 triplet으로만 구성된 카드 = baby-gin

- run: 3장의 카드가 연속적인 번호를 갖는 경우
- triplet: 3장의 카드가 동일한 번호를 갖는 경우

**모든 경우의 수 구하기 (순열)**

6개의 숫자는 6!개의 순열이 가능

1. 먼저 6개 숫자에서 가능한 세 자리 순열(6P3)을 구하여 run 또는 triplet에 해당하는지 검사
2. true를 반환한다면, 다시 남은 3가지 수 중에서 순열을 구하여 run 또는 triplet에 해당하는지 검사

```java
for (int i = 0; i < arr.length; i++) {
			int i1 = arr[i]; // 첫 번째 자리
			for (int j = 0; j < arr.length; j++) {
				if (j != i) {
					int i2 = arr[j]; // 두 번째 자리
					for (int k = 0; k < arr.length; k++) {
						if (k != i && k != j) {
							int i3 = arr[k]; // 세 번째 자리
							int num = i1 * 100 + i2 * 10 + i3; // run과 triplet을 체크할 세 자리 수
							if (isRun(num) || isTriplet(num)) { // 이 수가 해당한다면
								for (int l = 0; l < arr.length; l++) {
									if (l != i && l != j && l != k) {
										int o1 = arr[l]; // 네 번째 자리
										for (int m = 0; m < arr.length; m++) {
											if (m != i && m != j && m != k && m != l) {
												int o2 = arr[m]; // 다섯 번째 자리
												for (int n = 0; n < arr.length; n++) {
													if (n != i && n != j && n != k && n != l && n != m) {
														int o3 = arr[n]; // 여섯 번재 자리
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
```

**run 확인**

정렬 후 값들의 차이가 1이면 true

```java
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
```

**triplet 확인**

세 수가 같으면 true 반환

```java
static boolean isTriplet(int N) {
		int i1 = N / 100;
		int i2 = (N / 10) % 10;
		int i3 = (N % 10) % 10;
		if (i1 == i2 && i2 == i3) {

			return true;
		}
		return false;
	}
```

전체 코드

```java
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
```


</div>
</details>

</div>
</details>


---