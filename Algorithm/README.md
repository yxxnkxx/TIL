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

<details>
<summary>카운팅 정렬</summary>
<div markdown="1">

## 카운팅 정렬

데이터끼리 비교 없이 데이터의 개수를 세어 정렬하는 알고리즘

### 제한

정수나 정수로 표현할 수 있는 자료에 대해서만 한정

데이터의 입력 범위가 제한적인 경우에 효율적

- 데이터의 수는 적지만, 데이터 값의 범위가 큰 경우(ex. 1~10억) count 배열이 메모리를 과다하게 사용하여 비효율적!

### 시간복잡도

O(n+k)

- n: 배열의 길이
- k: 정수의 최대값

### 과정

1. 정렬할 배열에서 가장 큰 정수를 크기로 하는 count 배열을 선언한다..
2. 데이터 값이 i인 경우, count[i]를 1씩 증가시킨다.
3. count가 모두 끝나면, 앞에서부터 누적합하여 count배열을 수정한다.
    
![Untitled](./img/countingsort1.jpg))
    
4. 기존 배열의 맨 마지막 index부터 정렬을 시작한다. arr[n-1]이 k일 경우, count[k]의 값을 찾아, 그 값을 1감소 시킨 후 해당 값을 index로 하는 위치에 넣는다.
    
![Untitled](./img/countingsort2.jpg))
![Untitled](./img/countingsort3.jpg))
    

### 자바 구현

```java
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
```
</div>
</details>


<details>
<summary>삽입 정렬 </summary>
<div markdown="1">


## 삽입 정렬

0번째부터 i번째까지 정렬된 배열의 크기를 증가시키며 정렬하는 알고리즘

이미 정렬된 i개짜리 배열에 하나의 원소를 더하여 정렬된 i+1개짜리 배열 만들기!

시간 복잡도: $O(n^2)$

### 과정

i번째 원소를 정렬할 차례

1. i-1번째까지의 원소들은 정렬되어 있음
2. i-1부터 0까지의 원소들과 i번째 원소(key)를 비교하며, i번째 원소보다 작은 원소를 만나면 break, i번째 원소보다 크다면 해당 원소를 오른쪽으로 한 칸씩 shift
3. break한 원소 다음 자리에 i번째 원소를 삽입
4. 1부터 마지막 원소까지 반복

---

1 0번째 원소는 정렬된 상태

![Untitled](img/insert0.png)

i=1, 10 < 60이므로 60을 한 칸 shift

![Untitled](img/insert1.png)

i=2, 3 < 10, 3 < 60이므로 한 칸씩 shift

![Untitled](img/insert2.png)

i=3, 45 < 60, 45 > 10이므로 10에서 break, 45는 10 다음에 삽입

![Untitled](img/insert3.png)

i = 마지막까지 반복

![Untitled](img/insert4.png)

### 자바 구현

```java
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
```

</div>
</details>


</div>
</details>

---


<h1> 검색 </h1>
<details>
<summary>검색</summary>
<div markdown="1">

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

<h1> 조합론 </h1>
<details>
<summary>조합론</summary>
<div markdown="1">

<details>
<summary>이항계수</summary>
<div markdown="1">

## 이항계수

[https://shoark7.github.io/programming/algorithm/3-ways-to-get-binomial-coefficients](https://shoark7.github.io/programming/algorithm/3-ways-to-get-binomial-coefficients)

### 정의

이항 계수는 집합에서 원하는 개수만큼 순서없이 뽑는 조합의 가짓수를 의미한다. 즉 nCr을 구하는 알고리즘이다.

### 구현 1: 팩토리얼 이용

$$
nCk = \frac{n!}{{n-k}!*k!}
$$

첫 번째 정의는 팩토리얼 재귀함수를 이용하여 알고리즘으로 구현할 수 있다.

```java
public class MyBinoCo {

	public static void main(String[] args) {

		int N = 10;
		int K = 3;
		// 팩토리얼
		System.out.println(fact(N) / fact(N - K) / fact(K));
	}

	static int fact(int N) {

		if (N == 0 || N == 1)
			return 1;
		int tmp = N;
		for (int i = 2; i < N; i++)
			tmp *= i;
		return tmp;

	}
}
```

### 구현 2: DP, 재귀 함수 이용

그리고 n개에서 k개를 뽑는 가짓수는, n을 포함하지 않고 n-1개에서 k개를 뽑는 가짓수와 n을 포함하고 n-1개에서 k-1개를 뽑는 가짓수의 합과 같다.

$$
\binom{n}{k} = \binom{n-1}{k} + \binom{n-1}{k-1}
$$

두 번째는 다음 성질을 이용한다. 그런데 재귀를 활용해서 

```java
bino(N, K) = bino(N-1, K) + bino(N-1, K-1);
```

라고 구하면 가짓수가 많아져 메모리 낭비가 심하기 때문에 memoization을 이용한다.

```java
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
```

### 전체 코드
```java
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
```




</div>
</details>

</div>
</details>
