# Data Structure
쉽게 배우는 자료구조 with 자바 참고

<details>
<summary>Array & ArrayList 배열</summary>
<div markdown="1">

# Array & ArrayList 배열

- 같은 종류의 데이터를 저장하기 위한 자료구조

index로 배열의 요소 참조 가능

크기가 고정 → overflow 위험

직관적으로 간단함

추가/제거 시 shift 필요

![Untitled](/img/array1.png)

## 삽입

```java
public void add(int index, E x) {
		if (this.numItems >= item.length || index < 0 || index > this.numItems) {
			// 에러 처리
		} else {
			for (int i = this.numItems - 1; i >= index; i--) {
				item[i + 1] = item[i]; // index에 값을 삽입하기 위해 오른쪽으로 한 칸씩 shift
			}
			item[index] = x;
			this.numItems++;
		}
	}
```

## 제거

```java
public E remove(int index) {
		if (isEmpty() || index > 0 || index > numItems - 1) {
			// 에러처리
			return null;
		} else {
			E tmp = item[index];
			for (int i = index; i < this.numItems; i++) {
				item[i] = item[i + 1]; // 한 칸씩 왼쪽으로 shift
			}
			numItems--;
			return tmp;
		}
	}
```

## get / set

```java
public E get(int index) {
		if (index >= 0 && index <= this.numItems - 1) {
			return item[index];
		} else {
			return null;
		}
	}

	public void set(int index, E x) {
		if (index >= 0 && index <= this.numItems - 1) {
			item[index] = x;
		}
	}
```

## indexOf

```java
public int indexOf(E x) {
		for (int i = 0; i < this.numItems; i++) {
			if (((Comparable) item[i]).compareTo(x) == 0)
				return i;
		}
		return -1;
	}
```

</div>
</details>

<details>
<summary>Linked List 연결 리스트 </summary>
<div markdown="1">

# Linked List 연결 리스트

삽입/제거 시 shift 필요 X

값을 찾을 때, 앞에서부터 순차적으로 탐색

![Untitled](img/linkedlist1.png)

- 기본 구조

```java
class Node<E> {
	public E item;
	public Node<E> next;

	public Node(E item) {
		this.item = item;
		this.next = null;
	}

	public Node(E item, Node<E> next) {

		this.item = item;
		this.next = next;
	}

}

public class MyLinkedList<E> {
	private Node<E> head;
	private int numItems;

	public MyLinkedList() {
		this.head = new Node(null);
		this.numItems = 0;
	}
}
```

## 삽입

![Untitled](img/linkedlist2.png)

이전의 Node가 가리키고 있던 next가 삽입 노드를 가리키게 하고, 삽입 노드의 next가 기존의 next값을 가리키게 만든다.

```java
public void add(int index, E item) {
		Node<E> curr = this.head;
		for (int i = 0; i < index; i++) {
			curr = curr.next; // 삽입 index의 바로 전 값 찾기
		}

		Node<E> newNode = new Node<E>(item, curr.next);
		curr.next = newNode;
		numItems++;

	}
```

## **삭제**

![Untitled](img/linkedlist3.png)

삭제할 Node의 이전 Node가 가리키고 있던 값을 삭제 Node의 next가 가리키고 있던 값으로 옮겨준다.

```java
public void remove(int index) {
		Node<E> curr = this.head;
		for (int i = 0; i < index; i++) {
			curr = curr.next; // 삭제할 값의 바로 전 값 찾기
		}
		curr.next = curr.next.next; // 삭제할 노드의 전 노드가, 삭제할 노드의 다음 노드를 가리키게 함
		numItems--;
	}
// 에러 처리 필요
```

</div>
</details>

<details>
<summary>ArrayList와 LinkedList의 비교 </summary>
<div markdown="1">


# ArrayList와 LinkedList의 비교

|  | ArrayList | LinkedList |
| --- | --- | --- |
| 크기 | 고정=정적 | 변동=동적 |
| 공간의 연속성 | O | X |
| 정렬 | 빠름 | 느림 |
| 접근(k번째 원소) | index로 즉시 접근→빠름 Θ(1) | 시작 노드부터 접근 → 느림 Θ(k) |
| 원소 하나당 필요한 공간 | 적음 | 큼 (링크를 위한 공간 필요) |
| 공간 낭비 | 충분한 크기로 선언 → 낭비O | 낭비X |
| 검색 | Θ(n) (크기 순 정렬시 Θ(log n) | Θ(n) |
| 추가/삭제 | 느림 | 빠름 |

</div>
</details>

<details>
<summary>Stack 스택 </summary>
<div markdown="1">

# Stack 스택

물건을 쌓아 올리듯 자료를 쌓아 올린 형태의 자료구조

Last in First out: 마지막에 삽입한 자료를 가장 먼저 꺼냄

선형구조(자료 간의 관계가 1대 1의 관계)

맨 위의 원소만 접근 가능(top)

![Untitled](img/stack1.png)

응용: 문자열 뒤집기, postfix 계산

### 메소드

push: 스택에 값을 추가

pop: 스택의 마지막 값을 삭제하고 반환

peek: 스택의 마지막 값을 반환(삭제x)

isEmpty: 스택이 비어있는지 확인

## push, pop

배열 스택을 이용

```java
package myds;

public class MyArrayStack {
	private static int[] stack = new int[100];
	private static int topIndex = -1;;

	public static void main(String[] args) {

	}

	public boolean isFull() {
		return topIndex == stack.length - 1;

	}

	public boolean isEmpty() {
		return topIndex == -1;
	}

	public void push(int item) {
		if (isFull()) {
			System.out.println("가득 참");
			return;
		}
		stack[++topIndex] = item;
	}

	public int pop() {
		if (isEmpty()) {
			System.out.println("비어있음");
			return -999;
		}

		int popItem = stack[topIndex--];
		return popItem;
	}

}
```

### 활용1. 괄호 검사

### 활용2. 계산기

1. 중위 표현식 → 후위 표현식
2. 후위 표현식을 계산
   
</div>
</details>

<details>
<summary>Queue 큐 </summary>
<div markdown="1">

# Queue 큐

First in First out (스택과 비교)

줄 서기

### 메소드

add, remove, element: 예외 발생

offer, poll, peek: 값을 반환

![Untitled](img/queue1.png)

front: 맨 먼저 큐에 들어온 원소, tail: 맨 나중에 큐에 들어온 원소

### 구현

원형 큐

선형 큐는 자료가 삭제될 때마다 front의 위치를 바꿔야 함 → 비효율적

원형큐는 배열을 원형의 형태로 생각하며 front와 rear의 상대적 위치로 큐의 상태를 파악

맨 처음 index에는 값을 담지 않기로 정함! (full과 empty의 차이를 구분하기 위해서)

```java
package myds;

public class MyArrayQueue<E> {
	// 원형 배열 이용
	private int size = 10;
	private E[] queue;
	private int front;
	private int tail;

	public boolean isEmpty() {
		return front == tail;
	}

	public boolean isFull() {
		return front == (tail + 1) % size;
	}
```

## 삽입


![Untitled](img/queue2.png)

tail 뒤에 새로운 원소 삽입

```java
// 삽입은 tail에
	public void enqueue(E item) {
		if (isFull()) {
			return;
		} else {
			this.tail = (tail + 1) % this.queue.length;
			queue[tail] = item;
		}

	}
```

## 삭제

![Untitled](img/queue3.png)

맨 앞의 원소 삭제 → 어떤 원소를 삭제할지 묻지 않아도 됨

```java
// 삭제는 front부터
	public E dequeue() {
		if (isEmpty()) {
			return null;
		}

		E queueFront = queue[front];
		front = (front + 1) % this.queue.length;

		return queueFront;
	}
```


</div>
</details>

<details>
<summary>Heap 힙 </summary>
<div markdown="1">


# Heap 힙

## 우선순위 큐

우선순위를 가진 원소를 삽입할 수 있고, 우선순위가 가장 큰 원소를 빼내줄 수 있는 자료구조

## 힙

대표적인 우선순위 큐, 완전 이진 트리(Complete Binary Tree) 구조 사용

포화 이진 트리(Full Binary Tree): 루트부터 시작해 모든 노드가 정확히 자식 노드를 2개씩 가지면서 꽉 채워진 트리, 노드의 총 수는 $2^k$-1개

완전 이진 트리(Complete Binary Tree)


![Untitled](img/heap1.png)

## 힙의 조건

1. 완전 이진 트리
2. 힙 특성: 모든 노드는 값을 갖고, 자식 노드(들) 값보다 크거나 같다. (최대 힙)

## 힙 배열

![Untitled](img/heap2.png)
![Untitled](img/heap3.png)

루트 노드부터 순서대로 배열에 담아 관리

## 삽입

A[8]에 원소 8 삽입

1. 배열의 맨 끝에 8을 삽입

![Untitled](img/heap4.png)

1. 8을 자신의 부모 3과 비교 → 8이 더 크므로 자리 교환

![Untitled](img/heap5.png)

1. 다시 자신의 부모 7과 비교 → 8이 더 크므로 자리 교환

![Untitled](img/heap6.png)

1. 자신의 부모 9와 비교 → 9가 더 크므로 자리 확정
- **스며오르기(percolateUp) → 힙 성질을 충족하도록 자리를 교환하는 작업**

```java
public void insert(E newItem) {
		// A[0...n-1]에 newItem을 추가
		heap[numItems] = newItem;
		percolateUp(numItems);
		numItems++;

	}

	public void percolateUp(int i) {
		int parentIdx = (i - 1) / 2;
		if (parentIdx >= 0 && ((Comparable<E>) heap[parentIdx]).compareTo(heap[i]) < 0) {
			E tmp = heap[i];
			heap[i] = heap[parentIdx];
			heap[parentIdx] = tmp;
			percolateUp(parentIdx);
		} 

	}
```

## 삭제

힙은 우선순위 큐이므로, 가장 우선순위가 큰 (최대힙에서는 값이 제일 큰) 원소 A[0]가 삭제됨

그러나 바로 A[0]을 삭제하면 힙의 모양이 깨지기 때문에, A[n-1]와 A[0]을 바꿔준 후 A[n-1]을 삭제하고, 다시 힙의 성질을 충족할 수 있도록 원소를 교환하는 과정을 반복한다.

이 과정을 **percolateDown**이라고 한다. 

percolateDown은 percolateUp과 유사하게, 자신의 자식 노드들 중 큰 값과 자신을 비교하며, 자식 노드가 더 클 경우 자리를 바꾸는 것이다. 이 과정은 리프 노드에 이를 때까지 계속된다.

1. A[n-1]과 A[0]을 교환
2. percolateDown()

---

```java
public E deleteMax() {
		E max = heap[0];
		heap[0] = heap[this.numItems - 1]; // 자리 교환
		this.numItems--; // max값은 배열에서 제외됨
		percolateDown(this.numItems);
		return max;
	}

	public void percolateDown(int i) {
		int child = 2 * i + 1;
		int rightChild = 2 * i + 2;
		if (child <= this.numItems - 1) {
			if (((Comparable<E>) heap[child]).compareTo(heap[rightChild]) < 0)
				child = rightChild; // 더 큰 값을 child로 지정
			if (((Comparable<E>) heap[i]).compareTo(heap[child]) < 0) {
				E tmp = heap[i];
				heap[i] = heap[child];
				heap[child] = tmp;
				percolateDown(child);
			}

		}
	}
```

## 힙 생성

리프노드가 아닌 노드부터 루트노드까지 힙 조건을 만족하도록 수정함(percolateDown)

최초의 리프노드가 아닌 노드: 맨 마지막 노드의 부모 노드 → 마지막 index가 k라면 (k-1)/2

```java
public void buildHeap() {
		if (this.numItems >= 2) {
			for (int i = (numItems - 2) / 2; i >= 0; i--)
				percolateDown(i);
		}
	}
```

## 힙 수행시간

`buildHeap()`: Θ(n)

`insert()`: O(log n) - 한 번의 percolateUp (트리의 높이)

`deleteMax()`: O(log n) - 한 번의 percolateDown

</div>
</details>

---

<details>
<summary>트리 & 이진 탐색 트리</summary>
<div markdown="1">

# 트리 & 이진 탐색 트리

# 트리 (Tree)

### 비-선형 자료 구조 (Non-Linear)

- 선형 자료 구조: 구조에 저장될 데이터들이 순차적으로 저장되는 형태
    
    **ArrayList, LinkedList, Map, Stack, Queue** 등
    
- 비-선형 자료 구조: 복수의 데이터들이 복수의 데이터들과 연결될 수 있는 구조
    
    **Tree**
    

# 검색 트리

### 내장 vs 외장 검색 트리

- 내장 검색 트리: 메인 메모리 내에 존재
- 외장 검색 트리: 검색 트리가 외부(주로 디스크)에 존재-검색 트리 전체를 메인 메모리에 올려놓고 쓸 수 없는 경우

# 이진 검색 트리(Binary Search Tree)

## 특성

1. 각 노드는 키값을 하나씩 갖는다. 이 키값은 모두 다르다.
2. 최상위 레벨에 루트 노드가 있고, 각 노드는 최대 2개의 자식 노드를 갖는다.
3. 임의 노드의 키값은 자기 왼쪽 아래에 있는 모든 노드의 키값보다 크고, 오른쪽 아래에 있는 모든 노드의 키값보다 작다.
4. 좌우 서브 트리도 모두 이진 탐색 트리여야 한다.

![Untitled](img/binarytree1.png)

### 노드 객체의 구조

- item: 키 저장
- right: 오른쪽 자식
- left: 왼쪽 자식

```java
public class Node {
    int item;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
        left = null;
        right = null;
    }
}
```

## 이진 탐색 트리 알고리즘

### 검색

1. 루트 노드의 키와 찾고자 하는 값을 비교한다. 찾고자 하는 값이라면 탐색을 종료한다.
2. 찾고자 하는 값이 루트 노드의 키보다 작다면 왼쪽 서브 트리로 탐색을 진행한다.
3. 찾고자 하는 값이 루트 노드의 키보다 크다면 오른쪽 서브 트리로 탐색을 진행한다.
4. 검색에 실패하면 null을 return

### 삽입

1. 원소를 삽입할 자리 찾기 (실패하는 검색 1회)
2. 임의의 리프 노드에서 더 이상 내려갈 곳이 없다는 것을 확인 → x를 그 리프 노드의 자식으로 매단다
    
    ![Untitled](img/binarytree2.png)
    
    ![Untitled](img/binarytree3.png)
    
    ![Untitled](img/binarytree4.png)
    
    ![Untitled](img/binarytree5.png)
    
    구조적으로 검색 알고리즘과 동일함
    
    ### 삭제
    
    case 1: 삭제할 노드(r)가 리프 노드
    
    r을 삭제
    
    ![Untitled](img/binarytree6.png)
    
    리프 노드라서 그냥 삭제
    
    ![Untitled](img/binarytree7.png)
    
    case 2: 삭제할 노드의 자식 노드가 1개인 경우
    
    r의 부모가 r의 자식을 직접 가리키도록 함
    
    ![Untitled](img/binarytree8.png)
    
    10을 삭제하고 20이 5를 가리키도록 바꿈
    
    ![Untitled](img/binarytree9.png)
    
    case 3: 삭제할 노드의 자식 노드가 2개인 경우
    
    r의 오른쪽 서브 트리의 최소 원소 노드 s를 찾는다.
    
    s를 r자리로 복사하고 s를 삭제한다.
    
    ![Untitled](img/binarytree10.png)
    
    ![Untitled](img/binarytree11.png)
    

삭제가 된 후에도 이진 트리의 성질을 유지하고 있다.

### 코드

```java
package myds;

public class MyBinarySearchTree {
	private Node root;

	public MyBinarySearchTree() {
		this.root = null;
	}

	// 검색, key는 int라고 가정
	public Node search(int key) {
		return searchItem(root, key);
	}

	private Node searchItem(Node tNode, int key) {
		if (tNode == null)
			return null; // 검색 실패
		else if (tNode.item == key)
			return tNode; // 검색 성공
		else if (tNode.item < key)
			return searchItem(tNode.left, key);
		else
			return searchItem(tNode.right, key);
	}

	public void insert(int key) {
		root = insertItem(root, key);
	}

	private Node insertItem(Node tNode, int key) {
		if (tNode == null)
			tNode = new Node(key);
		else if (key < tNode.item)
			tNode.left = insertItem(tNode.left, key);
		else
			tNode.right = insertItem(tNode.right, key);
		return tNode;

	}

	// 삭제
	public void delete(int key) {
		root = deleteItem(root, key);
	}

	private Node deleteItem(Node tNode, int key) {
		if (tNode == null)
			return null; // 삭제할 node 존재x
		else {
			if (tNode.item == key)
				tNode = deleteNode(tNode); // 자식의 유무에 따라 다른 노드가 반환
			else if (key < tNode.item)
				tNode.left = deleteItem(tNode.left, key);
			else {
				tNode.right = deleteItem(tNode.right, key);
			}
			return tNode;
		}
	}

	private Node deleteNode(Node tNode) {
		// case 1
		if (tNode.left == null && tNode.right == null)
			return null;
		// case 2
		else if (tNode.left == null)
			return tNode.right; // 오른쪽 자식만 o
		else if (tNode.right == null)
			return tNode.left; // 왼쪽 자식만 o
		else {
			MyPair pair = deleteMinItem(tNode.right); // r의 오른쪽에서 가장 작은 노드 찾기
			tNode.item = pair.item;
			tNode.right = pair.node;
			return tNode;

		}
	}

	private MyPair deleteMinItem(Node tNode) {
		if (tNode.left == null)
			return new MyPair(tNode.item, tNode.right); 
		// 자리를 바꿀 노드의 right를 기존 부모의 left로 연결해줘야 함!
		else {
			MyPair pair = deleteMinItem(tNode.left);
			tNode.left = pair.node; 
			pair.node = tNode;
			return pair;
		}
	}

	private class MyPair {
		private int item;
		private Node node;

		public MyPair(int item, Node node) {

			this.item = item;
			this.node = node;
		}

	}
}

class Node {
	int item; // key
	Node left;
	Node right;

	public Node(int item) {
		this.item = item;
		left = null;
		right = null;
	}

	public Node(int item, Node left, Node right) {
		this.item = item;
		this.left = left;
		this.right = right;
	}

}
```

### 성질

1. 노드가 하나로 이루어진 이진 트리는 높이가 1
2. 높이가 h인 포화 이진 트리 리프 노드의 총 수는 2^(h-1)
3. 높이가 h인 포화 이진 트리의 총 노드 수는 2^h-1
4. 총 n개의 노드를 가진 이진 트리의 높이는 적어도 h≥log2(n+1)
5. 총 n개의 노드를 가진 이진 트리의 최대 높이 = n (일자형 이진 트리)
6. 노드가 n개인 이진 검색 트리의 점근적 평균 검색 시간: θ(logN)

## 순회

모든 노드를 방문하는 경우

### 전위 순회

루트 → 좌서브트리 → 우서브트리 방문

```java
preOrder(r):
	if (r!=null)
		r.visited = true;
		preOrder(r.left)
		preOrder(r.right)
```

### 중위 순회

좌서브트리 → 루트 → 우서브트리

```java
inOrder(r)
	if (r!=null)
		inOrder(r.left)
		r.visited=true
		inOrder(r.right)
```

### 후위 순회

좌서브트리 → 우서브트리 → 루트

```java
poserOrder(r)
	if (r!=null)
		postOrder(r.left)
		poserOrder(r.right)
		r.visited=true
```


</div>
</details>


<details>
<summary>B-트리 & B+트리</summary>
<div markdown="1">

# B-트리

내장 색인: 메모리에 올려서 사용 (이진 검색 트리)

외장 색인: 메인 메모리 외부에 놓고 사용하는 색인 (B-트리)

색인의 규모가 클 경우 or 메인 메모리가 충분하지 않을 때 디스크에 두고 사용

디스크 접근 시간으로 인한 비효율을 최대한 줄여야 함

- **B-트리: 디스크 환경에서 사용하기 적합한 외장 다진 검색 트리**

**최대 *M*개의 자식**을 가질 수 있는 B트리=*M*차 B트리

![Untitled](img/btree1.png)

$key_{i-1} < T_i < key_i$

## 성질

1. 루트를 제외한 모든 노드는 $\frac k 2$ ~k개의 키를 갖는다. 
    a. k: 한 블록이 수용할 수 있는 최대 키의 개수
    b. 예외: 루트 노드는 적어도 2개 이상의 자식을 가짐
2. 모든 리프 노드는 같은 깊이를 가진다.
3. 노드의 자료수가 N이면, 자식 수는 N+1이어야 함
    a. 노드의 자료수(key)가 3개라면, 그 노드의 자식 수는 4개
        
        $key_1, key_2, key_3$
        
        $node_1 < key_1$
        
        $key_1 < node_2 < key_2$
        
        $key_2 < node_3 < key_3$
        
        $node_4 > key_3$
        
4. 각 노드의 자료는 정렬된 상태여야함
5. 입력 자료는 중복 될 수 없음

![81DE1DD8-84D1-4BF1-819F-18C5076644E5.jpeg](img/btree2.jpeg)

## 알고리즘

### 검색

기본적으로 이진 검색 트리의 검색과 같음

1. 노드의 여러 키 중 검색 키와 일치하는 것이 있는지 확인
2. $key_{i-1}$ < x < $key_i$ 인 두 키 $key_{i-1}$ 과 $key_i$ 를 찾아 분기해야 할 자식 노드를 찾음
3. 자식으로 분기하고 나면 깊이만 하나 내려간 똑같은 검색 문제(다시 자식 노드를 찾음) → 재귀적 과정

### 삽입

ex) 각 노드가 최대 5개의 키를 가질 수 있다고 가정

루트 노드를 제외하고 2~5개의 키를 가져야 한다.

1. x를 삽입할 리프 노드 r을 찾는다.
2. 노드 r에 공간의 여유가 있으면 키를 삽입하고 끝낸다. (case 1)
3. 노드 r에 여유가 없으면 형제 노드에 공간의 여유가 있는지 살펴본다. 
    1. 형제 노드에 공간의 여유가 있으면 키를 하나 넘기고 끝낸다. (case 2)
        
        ![F71E7351-1E16-471D-B785-DEDDAFFA7516.jpeg](img/btree3.jpeg))
        
    2. 형제 노드에 여유가 없으면 노드를 2개로 분리한다. 분리 작업은 부모 노드로 키를 하나 넘기는 작업을 포함한다. (case 3)
        
        ![61DF2746-7FA3-4F2E-B0FA-DE347E6B84A6.jpeg](img/btree4.jpeg)
        

### 삭제

1. x를 키로 갖고 있는 노드를 찾는다.
2. 리프노드인지 아닌지 확인
    1. 리프노드라면 바로 삭제 (case 1)
        
        ![30404C10-87A5-413C-8556-BA00CCE9CB0E.jpeg](img/btree5.jpeg)
        
    2. 리프노드가 아니라면 x의 직후 원소 r과 x를 교환한 후, 리프 노드 x 제거 (case 2)
        
        ![7731022E-111F-4D4F-A047-137FFD2B2626.jpeg](img/btree6.jpeg)
        
3. x를 제거한 후 노드에 언더플로우가 발생하면 해소한다. (case 3)

![8D6CA31D-7CC6-4469-AD8C-59592B26DBBE.jpeg](img/btree7.jpeg)

### 작업 성능

d진 검색 트리가 균형을 잘 맞추면 높이가 $log_dn$ 에 근접

B-트리에서 임의의 노드가 최대 d개의 자식을 가질 수 있다면, 최소한 $\frac {d-1} 2$+1개에서 $\frac d 2$개의 자식을 가져야 함

→ 높이는 $O(log n)$

- 작업 수행시간은 디스크 접근 횟수를 기준으로 함

**검색**

$O(log n)$ (높이)

**삽입**

실패하는 검색을 한 번 수행 $O(log n)$

오버플로우가 최대한 발생하더라도 높이에 비례하는 시간 $O(log n)$ 

→ $O(log n)$

**삭제**

삭제 원소 검색 + 직후 원소를 찾는 작업 $O(log n)$

언더플로우 최대 $O(log n)$

→ $O(log n)$

→ 두 작업 모두 이진 검색 트리에 비해 빠름


---

# B+Tree

- 브랜치 노드(리프 노드가 아닌 노드): key를 저장
- 리프 노드: data 저장 (data가 key일 수도 있음!)
- 리프 노드끼리 Linked List로 연결

→ key인 data는 리프 노드와 브랜치 노드에 중복 저장 가능 

→ 리프 노드(데이터가 담긴 노드)에서는 데이터가 중복X

[https://zorba91.tistory.com/293](https://zorba91.tistory.com/293)

### 장점

- B-Tree의 순회 작업 단점 보완 → 모든 데이터를 탐색할 때 순차 탐색이 가능 ( 리프 노드에 모든 데이터가 저장)
- 리프 노드를 제외한 브랜치 노드에 key 값만 담기 때문에 차지하는 메모리가 적음 → 더 많은 key 배치 가능(트리의 높이가 낮아짐) → **key 탐색 시 더 빠른 성능**

### 단점

- B-Tree의 경우 데이터가 루트 노드에 가깝게 위치해 있을 경우 검색이 빠르지만, B+Tree는 특정 데이터 검색을 위해 리프 노드까지 도달해야 함

[https://rebro.kr/167](https://rebro.kr/167)

## 알고리즘

삽입, 삭제는 B-Tree와 유사하지만 일부 과정에서 차이

### 삽입

[http://egloos.zum.com/sweeper/v/899699](http://egloos.zum.com/sweeper/v/899699)

5 삽입 

![Untitled](img/bplustree1.png)

한 노드당 최대 데이터: 3개 → 오버 플로우

![Untitled](img/bplustree2.png)

리프 노드 분열 → 5가 새로운 키가 됨

![Untitled](img/bplustree3.png)

cf. 리프 노드(데이터)가 아닌 키에서 분열할 때는 값 복사x 

![Untitled](img/bplustree4.png)

9, 13, 16, 18 블록: 오버플로우

그러나 data 노드가 아니기 때문에 일반 B-Tree처럼 분열

![Untitled](img/bplustree5.png)

---

### 삭제

1. 9 삭제

![Untitled](img/bplustree6.png)

1. 9는 data인 동시에 key → 그러나 key는 다른 값을 찾는 데에 필요하기 때문에 data(리프 노드)만 삭제!

9를 삭제하면 underflow → 병합

![Untitled](img/bplustree7.png)

1. 이때 브랜치노드에 있던 9는 key 였고, (3,4,6) 블럭과 (9) 블럭이 병합되었기 때문에 더이상 키의 기능x → 삭제 

![Untitled](img/bplustree8.png)

1. 9가 없어져서 브랜치 노드에 underflow → 병합

![Untitled](img/bplustree9.png)

- 요약

1) 재배치와 합병이 필요하지 않을 때는 leaf 노드에서만 삭제된다.

2) Index 부분은 다른 key 값을 찾는데 사용될 수 있기 때문에 leaf node의 값이 삭제되어도 삭제하지 않는다.

3) 재배치할 경우 index 부분의 node의 key 값은 변하지만 tree 구조는 변하지 않는다.

4) 합병을 할 경우 index 부분에서도 key 값을 삭제한다.

[http://egloos.zum.com/sweeper/v/899699](http://egloos.zum.com/sweeper/v/899699)

|  | B-tree | B+tree |
| --- | --- | --- |
| 데이터 저장 | 리프 노드, 브랜치 노드 모두 가능 | 리프 노드에만 데이터 저장 |
| 트리의 높이 | 높음 | 낮음(한 노드당 key를 많이 담을 수 있음) |
| 검색 속도(full) | 모든 노드 탐색(오래 걸림) | 리프 노드에서 선형 탐색(빠름) |
| 키 중복 | 없음 | 있음(리프 노드에 모든 데이터가 있어서 key와 중복) |
| 검색(one) | 루트 노드에 가까울 경우 빠름 | 리프 노드까지 가야 데이터 존재 → 오래 걸릴 수 있음 |

[https://zorba91.tistory.com/293](https://zorba91.tistory.com/293)


</div>
</details>