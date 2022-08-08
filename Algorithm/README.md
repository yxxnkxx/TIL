# Algorithm


<details>
<summary>정렬</summary>
<div markdown="1">

# 정렬

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