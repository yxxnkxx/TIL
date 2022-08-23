# Data Structure

# Array& ArrayList 배열

- 같은 종류의 데이터를 저장하기 위한 자료구조

index로 배열의 요소 참조 가능

크기가 고정 → overflow 위험

직관적으로 간단함

추가/제거 시 shift 필요

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled.png)

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

# Linked List 연결 리스트

삽입/제거 시 shift 필요 X

값을 찾을 때, 앞에서부터 순차적으로 탐색

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%201.png)

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

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%202.png)

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

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%203.png)

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

# Stack 스택

물건을 쌓아 올리듯 자료를 쌓아 올린 형태의 자료구조

Last in First out: 마지막에 삽입한 자료를 가장 먼저 꺼냄

선형구조(자료 간의 관계가 1대 1의 관계)

맨 위의 원소만 접근 가능(top)

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%204.png)

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

# Queue 큐

First in First out (스택과 비교)

줄 서기

### 메소드

add, remove, element: 예외 발생

offer, poll, peek: 값을 반환

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%205.png)

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

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%206.png)

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

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%207.png)

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

# 트리 & 이진 탐색 트리

# 트리 (Tree)

### 비-선형 자료 구조 (Non-Linear)

- 선형 자료 구조: 구조에 저장될 데이터들이 순차적으로 저장되는 형태
    
    **ArrayList, LinkedList, Map, Stack, Queue** 등
    
- 비-선형 자료 구조: 복수의 데이터들이 복수의 데이터들과 연결될 수 있는 구조
    
    **Tree**
    

## 용어 정리

- 노드: 트리의 원소
- 간선(edge): 노드를 연결하는 선
- 루트 노드: 트리의 시작 노드

---

- 형제 노드: **같은 부모 노드**의 자식 노드들
- 조상 노드: 간선을 따라 루트 노드까지 이르는 경로에 있는 모든 노드들
- 자손 노드: 서브 트리에 있는 하위 레벨의 노드들

---

- 차수
    - 노드의 차수: 노드에 연결된 자식 노드의 수
    - 트리의 차수: 트리에 있는 노드의 차수 중에서 가장 큰 값
    - 리프 노드: 차수가 0인 노드(자식 노드가 없는 노드)
- 높이
    - 루트 노드의 높이는 0
    - 노드의 높이: 루트에서 노드에 이르는 간선의 수
    - 트리의 높이: 트리에 있는 노드의 높이 중 가장 큰 값

## 이진 트리 구현

### 1. 배열

### 1-1. 트리 구현

배열로 이진 트리를 구현할 때는 배열의 index를 통해 부모와 자식 노드를 확인할 수 있다.

루트 노드의 index를 1로 하고, 같은 층위의 노드들을 순서대로 배열에 삽입하면 부모 노드의 인덱스가 i일 때 자식 노드의 인덱스는 i*2, i*2+1이 된다.

전체 정점의 개수를 입력받고 그 다음 줄에 각 간선의 parent와 child 값을 입력받는다.

```
13
1 2 1 3 2 4 3 5 3 6 4 7 5 8 5 9 6 10 6 11 7 12 11 13
```

위 케이스에서 1 2는 부모가 1, 자식 노드가 2임을 의미한다. 이때 루트노드는 부모 노드가 없으니 첫 간선을 삽입할 때는 루트 노드를 처리해줘야 한다.

```java
		for (int i = 0; i < V - 1; i++) {
			int parent = sc.nextInt();
			int child = sc.nextInt();
			if (tree[1] == 0)
				tree[1] = parent;
			for (int p = 1; p < length; p++) {
				if (tree[p] == parent) {
					if (tree[2 * p] == 0)
						tree[2 * p] = child;
					else
						tree[2 * p + 1] = child;

					break;
				}
			}
		}// 이진트리 배열로 만들기
```

루트 노드를 처리하기 위해 tree[1]이 0인 경우 (아직 루트 노드가 삽입되지 않은 경우)에는 해당 노드를 parent로 설정하였다.

### 1-2. 전위 순회, 중위 순회, 후위 순회

전위 순회: 자기 자신 - 왼쪽 자식 - 오른쪽 자식

중위 순회: 왼쪽 자식 - 자기 자신 - 오른쪽 자식

후위 순회: 왼쪽 자식 - 오른쪽 자식 - 자기 자신

순서로 순회하는 것을 의미한다.

**전위 순회**

```java
	static void preOrder(int i) {
		if (i < tree.length && tree[i] != 0) {
			System.out.print(tree[i] + " ");
			preOrder(i * 2);
			preOrder(i * 2 + 1);
		}
	}
```

배열의 index를 사용하기 때문에 배열이 꽉 차 있을 경우를 대비하여 tree.length보다 index가 작고, 0이 아닐 때에만 순회하도록 하였다.

int 배열을 선언하였기 때문에 초기값이 0이고, String이나 다른 타입이라면 null로 조건을 만들면 된다.

**중위 순회**

```java
	static void inOrder(int i) {
		if (i < tree.length && tree[i] != 0) {
			inOrder(i * 2);
			System.out.print(tree[i] + " ");
			inOrder(i * 2 + 1);
		}
	}
```

**후위 순회**

```java
	static void postOrder(int i) {
		if (i < tree.length && tree[i] != 0) {
			postOrder(i * 2);
			postOrder(i * 2 + 1);
			System.out.print(tree[i] + " ");
		}
	}
```

### 1-3. 전체 코드

```java
package tree;

import java.util.Arrays;
import java.util.Scanner;

public class BinaryTreeArray {
	static int[] tree;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int length = (int) Math.pow(2, V + 1);
		tree = new int[length];// 최대 배열 크기for (int i = 0; i < V - 1; i++) {
			int parent = sc.nextInt();
			int child = sc.nextInt();
			if (tree[1] == 0)
				tree[1] = parent;
			for (int p = 1; p < length; p++) {
				if (tree[p] == parent) {
					if (tree[2 * p] == 0)
						tree[2 * p] = child;
					else
						tree[2 * p + 1] = child;

					break;
				}
			}
		}// 이진트리 배열로 만들기
		System.out.println(Arrays.toString(tree));

		preOrder(1);// 전위순회
		System.out.println();
		inOrder(1);
		System.out.println();
		postOrder(1);

	}// mainstatic void preOrder(int i) {
		if (i < tree.length && tree[i] != 0) {
			System.out.print(tree[i] + " ");
			preOrder(i * 2);
			preOrder(i * 2 + 1);
		}
	}

	static void inOrder(int i) {
		if (i < tree.length && tree[i] != 0) {
			inOrder(i * 2);
			System.out.print(tree[i] + " ");
			inOrder(i * 2 + 1);
		}
	}

	static void postOrder(int i) {
		if (i < tree.length && tree[i] != 0) {
			postOrder(i * 2);
			postOrder(i * 2 + 1);
			System.out.print(tree[i] + " ");
		}
	}
}
```

### 2. 링크드 리스트

### 2-1. 노드

링크드 리스트를 활용하기 위해서 노드 클래스를 정의하였다.

노드는 자기 자신의 data와 왼쪽 자식(left) 오른쪽 자식(right)를 변수로 가진다.

```java
class MyNode {
	int data;
	MyNode left;
	MyNode right;

	public MyNode(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
}
```

### 2-2. 이진 트리의 검색

링크드 리스트는 배열과 달리 index로 자료를 관리하지 않기 때문에 부모 노드에 자식 노드를 추가하려면 부모 노드를 검색하는 메소드가 필요하다.

또한 기존의 이진 탐색 트리는 왼쪽 자식은 자기 자신보다 값이 작고, 오른쪽 자식은 자기 자신보다 값이 크지만, 크기를 보장하지 않는 경우의 검색은 방식이 다르다.

```java
	public MyNode search(MyNode curr, int key) {
		MyNode leftNode = null;
		MyNode rightNode = null;
		if (curr != null) {
			if (curr.data == key)
				return curr;
			if (curr.left != null)
				leftNode = search(curr.left, key);
			if (curr.right != null)
				rightNode = search(curr.right, key);
		}

		if (leftNode != null)
			return leftNode;
		else if (rightNode != null)
			return rightNode;
		return null;
	}
```

leftNode와 rightNode 중 하나만 parent가 될 수 있기 때문에, 각각 다른 변수로 지정해야 한다.

변수를 지정하지 않고 재귀함수를 실행하면 다른 자식에 찾고자 하는 key가 있음에도 null을 반환하는 경우가 있었다.

### 2-3. 이진 트리의 삽입

```java
	public void insert(int parent, int child) {
		MyNode parentNode = search(root, parent);
		if (parentNode == null) {// root가 비어있을 때
			parentNode = new MyNode(parent);
			this.root = parentNode;
		}

		MyNode childNode = new MyNode(child);
		if (parentNode.left == null)
			parentNode.left = childNode;
		else
			parentNode.right = childNode;

	}
```

이진 트리에 값을 삽입하기 위해서 먼저 부모 노드를 search메소드를 통해 찾는다.

만약 node가 존재하지 않는다면 tree가 비어 있는 경우를 의미하기 때문에 새로운 루트 노드를 만들어주고 root를 이 노드로 설정한다.

그리고 childNode를 삽입하는 경우이니 새로 노드를 만들어준 후, 왼쪽 자식이 비어있다면 왼쪽에, 아니라면 오른쪽에 자식을 삽입한다.

### 2-4. 전위 순회, 중위 순회, 후위 순회

기본적인 순회의 알고리즘은 동일하지만 배열과 달리 index가 아니라 left, right라는 변수로 자식 노드를 순회할 수 있다.

**전위 순회**

```java
	public void preOrder(MyNode root) {
		if (root != null) {
			System.out.print(root.data + " ");
			preOrder(root.left);
			preOrder(root.right);
		}
	}
```

배열에서 index로 리프 노드를 확인했다면, 이 경우에는 root부터 시작해서 해당 값이 null이 아닐 때에만 재귀를 지속한다.

**중위 순회, 후위 순회**

```java
	public void inOrder(MyNode root) {
		if (root != null) {
			inOrder(root.left);
			System.out.print(root.data + " ");
			inOrder(root.right);
		}
	}

	public void postOrder(MyNode root) {
		if (root != null) {
			postOrder(root.left);
			postOrder(root.right);
			System.out.print(root.data + " ");
		}
	}
```

### 2-5. 전체 코드

```java
package tree;

import java.util.Scanner;

public class BinaryTreeLinkedList {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		MyBinaryTree tree = new MyBinaryTree();

		for (int i = 0; i < V - 1; i++) {
			int parent = sc.nextInt();
			int child = sc.nextInt();
			tree.insert(parent, child);
		}
		tree.preOrder(tree.root);
		System.out.println();
		tree.inOrder(tree.root);
		System.out.println();
		tree.postOrder(tree.root);

	}

}

class MyNode {
	int data;
	MyNode left;
	MyNode right;

	public MyNode(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
}

class MyBinaryTree {

	MyNode root;

	public MyBinaryTree() {
		this.root = null;
	}

	public void insert(int parent, int child) {
		MyNode parentNode = search(root, parent);
		if (parentNode == null) {// root가 비어있을 때
			parentNode = new MyNode(parent);
			this.root = parentNode;
		}

		MyNode childNode = new MyNode(child);
		if (parentNode.left == null)
			parentNode.left = childNode;
		else
			parentNode.right = childNode;

	}

	public MyNode search(MyNode curr, int key) {
		MyNode leftNode = null;
		MyNode rightNode = null;
		if (curr != null) {
			if (curr.data == key)
				return curr;
			if (curr.left != null)
				leftNode = search(curr.left, key);
			if (curr.right != null)
				rightNode = search(curr.right, key);
		}

		if (leftNode != null)
			return leftNode;
		else if (rightNode != null)
			return rightNode;
		return null;
	}

	public void preOrder(MyNode root) {
		if (root != null) {
			System.out.print(root.data + " ");
			preOrder(root.left);
			preOrder(root.right);
		}
	}

	public void inOrder(MyNode root) {
		if (root != null) {
			inOrder(root.left);
			System.out.print(root.data + " ");
			inOrder(root.right);
		}
	}

	public void postOrder(MyNode root) {
		if (root != null) {
			postOrder(root.left);
			postOrder(root.right);
			System.out.print(root.data + " ");
		}
	}

}
```

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

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%208.png)

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
    
    ![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%209.png)
    
    ![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2010.png)
    
    ![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2011.png)
    
    ![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2012.png)
    
    구조적으로 검색 알고리즘과 동일함
    
    ### 삭제
    
    case 1: 삭제할 노드(r)가 리프 노드
    
    r을 삭제
    
    ![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2013.png)
    
    리프 노드라서 그냥 삭제
    
    ![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2014.png)
    
    case 2: 삭제할 노드의 자식 노드가 1개인 경우
    
    r의 부모가 r의 자식을 직접 가리키도록 함
    
    ![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2015.png)
    
    10을 삭제하고 20이 5를 가리키도록 바꿈
    
    ![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2016.png)
    
    case 3: 삭제할 노드의 자식 노드가 2개인 경우
    
    r의 오른쪽 서브 트리의 최소 원소 노드 s를 찾는다.
    
    s를 r자리로 복사하고 s를 삭제한다.
    
    ![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2017.png)
    
    ![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2018.png)
    

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
postOrder(r)
	if (r!=null)
		postOrder(r.left)
		poserOrder(r.right)
		r.visited=true
```

# Heap 힙

## 우선순위 큐

우선순위를 가진 원소를 삽입할 수 있고, 우선순위가 가장 큰 원소를 빼내줄 수 있는 자료구조

## 힙

대표적인 우선순위 큐, 완전 이진 트리(Complete Binary Tree) 구조 사용

포화 이진 트리(Full Binary Tree): 루트부터 시작해 모든 노드가 정확히 자식 노드를 2개씩 가지면서 꽉 채워진 트리, 노드의 총 수는 $2^k$-1개

완전 이진 트리(Complete Binary Tree): 모든 노드가 자식 노드를 2개씩 가짐, 노드의 개수가 $2^k$-1보다 작을 때 왼쪽부터 차례대로 채운 것

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2019.png)

## 힙의 조건

1. 완전 이진 트리
2. 힙 특성: 모든 노드는 값을 갖고, 자식 노드(들) 값보다 크거나 같다. (최대 힙)

## 힙 배열

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2020.png)

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2021.png)

루트 노드부터 순서대로 배열에 담아 관리

## 삽입

A[8]에 원소 8 삽입

1. 배열의 맨 끝에 8을 삽입

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2022.png)

1. 8을 자신의 부모 3과 비교 → 8이 더 크므로 자리 교환

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2023.png)

1. 다시 자신의 부모 7과 비교 → 8이 더 크므로 자리 교환

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2024.png)

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

`buildHeap()`: [Θ](https://ko.wikipedia.org/wiki/%CE%98)(n)

`insert()`: O(log n) - 한 번의 percolateUp (트리의 높이)

`deleteMax()`: O(log n) - 한 번의 percolateDown

---

# B-트리 / B+트리

[https://velog.io/@emplam27/자료구조-그림으로-알아보는-B-Tree](https://velog.io/@emplam27/%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0-%EA%B7%B8%EB%A6%BC%EC%9C%BC%EB%A1%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EB%8A%94-B-Tree)

# B-트리

내장 색인: 메모리에 올려서 사용 (이진 검색 트리)

외장 색인: 메인 메모리 외부에 놓고 사용하는 색인 (B-트리)

색인의 규모가 클 경우 or 메인 메모리가 충분하지 않을 때 디스크에 두고 사용

디스크 접근 시간으로 인한 비효율을 최대한 줄여야 함

- **B-트리: 디스크 환경에서 사용하기 적합한 외장 다진 검색 트리**

**최대 *M*개의 자식**을 가질 수 있는 B트리=*M*차 B트리

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2025.png)

$key_{i-1} < T_i < key_i$

## 성질

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2026.png)

1. 루트를 제외한 모든 노드는 $\frac k 2$ ~k개의 키를 갖는다. 
    1. k: 한 블록이 수용할 수 있는 최대 키의 개수
    2. 예외: 루트 노드는 적어도 2개 이상의 자식을 가짐
2. 모든 리프 노드는 같은 깊이를 가진다.
3. 각 key들의 왼쪽 자식들은 항상 key보다 작은 값을, 오른쪽은 큰 값을 가진다.
4. 노드의 자료수가 **N**이면, 자식 수는 **N+1**이어야 함
    
    ex. 노드의 자료수(key)가 3개라면, 그 노드의 자식 수는 4개
    
    $key_1, key_2, key_3$
    
    $node_1 < key_1$
    
    $key_1 < node_2 < key_2$
    
    $key_2 < node_3 < key_3$
    
    $node_4 > key_3$
    
5. 각 노드의 자료는 **정렬**된 상태여야함
6. 입력 자료는 **중복**될 수 없음

![81DE1DD8-84D1-4BF1-819F-18C5076644E5.jpeg](Data%20Structure%200e5741057c5a4f4289107babd59da419/81DE1DD8-84D1-4BF1-819F-18C5076644E5.jpeg)

### 장점

- 삽입, 삭제 후에도 균형 트리 유지
- 효율적인 알고리즘 제공 (이진 트리보다 빠름)
- 저장 장치의 효율성
- 균등한 탐색 속도 보장 가능

### 단점

- 삽입/삭제 시 복잡한 연산

## 알고리즘

### 검색

기본적으로 이진 검색 트리의 검색과 같음

1. 루트 노드부터 시작
2. 노드의 여러 키 중 검색 키와 일치하는 것이 있는지 확인
3. $key_{i-1}<x<key_i$인 두 키 $key_{i-1}$과 $key_i$를 찾아 분기해야 할 자식 노드를 찾음
4. 자식으로 분기하고 나면 깊이만 하나 내려간 똑같은 검색 문제(다시 자식 노드를 찾음) → 재귀적 과정

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2027.png)

### 삽입

ex) 각 노드가 최대 5개의 키를 가질 수 있다고 가정

루트 노드를 제외하고 2~5개의 키를 가져야 한다.

1. x를 삽입할 리프 노드 r을 찾는다. (검색)
2. 노드 r에 공간의 여유가 있으면 키를 삽입하고 끝낸다. **(case 1)**
3. 노드 r에 여유가 없으면 형제 노드에 공간의 여유가 있는지 살펴본다. 
    1. 형제 노드에 공간의 여유가 있으면 키를 하나 넘기고 끝낸다. **(case 2)**
        
        ![F71E7351-1E16-471D-B785-DEDDAFFA7516.jpeg](Data%20Structure%200e5741057c5a4f4289107babd59da419/F71E7351-1E16-471D-B785-DEDDAFFA7516.jpeg)
        
    2. 형제 노드에 여유가 없으면 노드를 2개로 분리한다. 분리 작업은 부모 노드로 키를 하나 넘기는 작업을 포함한다. **(case 3)**
        
        ![61DF2746-7FA3-4F2E-B0FA-DE347E6B84A6.jpeg](Data%20Structure%200e5741057c5a4f4289107babd59da419/61DF2746-7FA3-4F2E-B0FA-DE347E6B84A6.jpeg)
        

### 삭제

1. x를 키로 갖고 있는 노드를 찾는다. (검색)
2. 리프노드인지 아닌지 확인
    1. 리프노드라면 바로 삭제 (case 1)
        
        ![30404C10-87A5-413C-8556-BA00CCE9CB0E.jpeg](Data%20Structure%200e5741057c5a4f4289107babd59da419/30404C10-87A5-413C-8556-BA00CCE9CB0E.jpeg)
        
    2. 리프노드가 아니라면 x의 직후 원소 r과 x를 교환한 후, 리프 노드 x 제거 (case 2)
        
        ![7731022E-111F-4D4F-A047-137FFD2B2626.jpeg](Data%20Structure%200e5741057c5a4f4289107babd59da419/7731022E-111F-4D4F-A047-137FFD2B2626.jpeg)
        
3. x를 제거한 후 노드에 언더플로우가 발생하면 해소한다. (case 3)

![8D6CA31D-7CC6-4469-AD8C-59592B26DBBE.jpeg](Data%20Structure%200e5741057c5a4f4289107babd59da419/8D6CA31D-7CC6-4469-AD8C-59592B26DBBE.jpeg)

### 작업 성능

d진 검색 트리가 균형을 잘 맞추면 높이가 $log_dn$에 근접

B-트리에서 임의의 노드가 최대 d개의 자식을 가질 수 있다면, 최소한 $\frac {d-1} 2$+1개에서 $\frac d 2$개의 자식을 가져야 함

→ 높이는 $O(log n)$

- 작업 수행시간은 디스크 접근 횟수를 기준으로 함

**검색**

$O(log n)$ (높이)

**삽입**

실패하는 검색을 한 번 수행($O(log n)$)

오버플로우가 최대한 발생하더라도 높이에 비례하는 시간($O(log n)$ 

→ $O(log n)$

**삭제**

삭제 원소 검색 + 직후 원소를 찾는 작업($O(log n)$

언더플로우 최대 ($O(log n)$) 

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

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2028.png)

한 노드당 최대 데이터: 3개 → 오버 플로우

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2029.png)

리프 노드 분열 → 5가 새로운 키가 됨

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2030.png)

cf. 리프 노드(데이터)가 아닌 키에서 분열할 때는 값 복사x 

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2031.png)

9, 13, 16, 18 블록: 오버플로우

그러나 data 노드가 아니기 때문에 일반 B-Tree처럼 분열

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2032.png)

---

### 삭제

1. 9 삭제

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2033.png)

1. 9는 data인 동시에 key → 그러나 key는 다른 값을 찾는 데에 필요하기 때문에 data(리프 노드)만 삭제!

9를 삭제하면 underflow → 병합

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2034.png)

1. 이때 브랜치노드에 있던 9는 key 였고, (3,4,6) 블럭과 (9) 블럭이 병합되었기 때문에 더이상 키의 기능x → 삭제 

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2035.png)

1. 9가 없어져서 브랜치 노드에 underflow → 병합

![Untitled](Data%20Structure%200e5741057c5a4f4289107babd59da419/Untitled%2036.png)

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