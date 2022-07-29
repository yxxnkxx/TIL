# Java

---

<details>
<summary>자바 가상 머신</summary>
<div markdown="1">

# 자바 가상 머신(JVM, Java virtual machine)

자바 바이트코드를 실행할 수 있는 주체

자바 바이트코드는 플랫폼에 독립적이며 모든 JVM은 자바 가상 머신 규격에 정의된대로 자바 바이트코드를 실행

컴파일: .java → .class

- JRE?: 실행 시 필요한 것

-JDK?: Java Delevopment Kit, JRE + 개발에 필요한 것

-IDE: 통합 개발 환경

- **0718**

</div>
</details>


<details>
<summary>print</summary>
<div markdown="1">


# 출력

```java
System.out.printf("%d \n", 10); //정수 (10진수)
System.out.printf("%o \n", 10); //정수 (8진수)
System.out.printf("%x \n", 10); //정수 (16진수)
System.out.printf("%X \n", 10); //정수 (16진수)

System.out.printf("%4d\n", 10); // 4칸을 확보한 뒤 오른쪽부터 차지
System.out.printf("%-4d\n", 10); // 4칸을 확보한 뒤 왼쪽부터 차지
System.out.printf("%04d\n", 10); // 4칸을 확보한 뒤 오른쪽부터 차지, 빈칸 0을 채운다

System.out.printf("%f\n", 10.1); // 실수 출력
System.out.printf("%.2f\n", 10.1); // 실수 (소수점 둘째자리까지)
```

0719



## printf

```java
		for (int i = 8; i < 13; i++) {
			System.out.printf("%3d", i);
		}

		System.out.println();

		float f = 23.123f;
		System.out.printf("%10.2f", f);
		System.out.println();
```

**0721**

숫자 출력시 언더바 포함해도 똑같이 출력 - 자리수 표현

</div>
</details>

<details>
<summary>변수</summary>
<div markdown="1">


# 변수

- PascalCase : 주로 클래스 이름 작명
- camelCase: 변수명, 함수
- snake_case: Python, 상수→대문자_대문자
- kebab-case: 하이픈, HTML, CSS /turn

정수형 기본: int, 실수형 기본: double

## 형 변환(Type Casting)

- 자료형의 크기 비교

byte < short/char < int < long < float < double

long: 8byte, float: 4byte → 왜 float가 더 큼?: **자료형의 표현범위**가 float가 더 크다

- 데이터 형변환

묵시적: Implicit Casting

범위가 넓은 데이터 형에 좁은 데이터 형을 대입하는 것

ex) `byte b = 100; int i = b;`

명시적: Explicit Casting

범위가 좁은 데이터 형에 넓은 데이터 형을 대입하는 것

형 변환 연산자 사용 - `(타입) 값;`

ex) `int i = 100; byte b = i;` (X)

`byte b = (byte) i;` (O)


2100000000

-294967296 → 정수에 담을 수 없어서 오버플로우


</div>
</details>

<details>
<summary>연산자</summary>
<div markdown="1">


# 연산자

## 단항 연산자

비트 연산자

`~`: 2진수로 표현했을 때, 0은 1로, 1은 0으로 바꿈

전위형: `++i` 하고 연산

후위형: `i--` 연산 후 

정수와 정수의 연산 = 정수

정수와 실수의 연산 = 실수

## 산술 연산자

```java
System.out.println(a / (double) b); //1.6666666666666667
System.out.println((double) a / b); //1.6666666666666667
System.out.println((double) (a / b)); //1.0
```

- 논리 연산자

: 효율적인 연산 가능

`A && B` 

A가 거짓: B를 확인하지 않고 거짓으로 판단

- 삼항 연산자

조건식? 식1: 식2

참: 식1, 거짓: 식2

```java
int num = (int)(Math.random()*10)+1;
		
System.out.println("내가 뭐라고 했더라???");
System.out.println(num % 2 == 0 ? "짝" : "홀");
```

---

</div>
</details>

<details>
<summary>Loop</summary>
<div markdown="1">




# loop

## Break & continue

이중 for 문을 빠져나올 때 out으로 표시해줌

```java
package java06.practice;

public class BreakContinue {
	public static void main(String[] args) {
		out: for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (i == 2 && j == 2)
					break out;
				System.out.println(i + "," + j);
			}
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (i == 2 && j == 2)
					break;
				System.out.println(i + "," + j);
			}
		}

	}
}
```

## do while

```java
do {
	반복 수행할 문장;
} while (조건식);
```

</div>
</details>

<details>
<summary>배열</summary>
<div markdown="1">


# 배열

참조형 data type (String)

JVM 메모리 영역

스택 - 힙

참조형: String, 배열 → heap에 주소 값을 갖고 있음, 주소 1을 stack에 저장하여 힙의 값을 참조함

`new`로 생성: stack에 생성됨

```java
String str1 = "서울"; //literal
String str2 = new String("서울"); //object 객체
String str3 = str2;
```

`==`로 비교할 때, `true` 나올 수도 있음

![Untitled](/img/0718java1.png)

### 배열의 생성과 초기화

## 배열의 생성과 초기화

`자료형[] 배열이름 = {값1, 값2, 값3, 값4}` 선언과 동시에 초기화

`배열이름 = new 자료형[] {값1, 값2, 값3, 값4}` 배열 생성 및 값 초기화

`배열이름 = new 자료형[길이];` 배열 생성 (자료형의 초기값으로 초기화)

- boolean: false
- char: ‘\u0000’ (공백문자)
- byte, short, int: 0
- long: 0L
- float: 0.0f
- double: 0.0
- 참조형 변수: null (아무것도 참조하지 않음)

타입 Date → dateArr, `Date[] dateArr;` 기본형 = null

기본자료형 제외하고 모두 null (참조형 변수)

`int[] tmp = new int[5];`

`arr = tmp` → 복사

```java
int[] points = new int[3];
points[1] = 'A'; //ASCII 65
```

배열 선언: `int[] points`

배열 생성: `new int[3];`

참조 값 할당: `points = new int[3];`

![C30A4B83-ADC5-4A24-93AF-F976D6AA8693.jpeg](/img/0718java2.jpeg)

## 2차원 배열

- 1차원 배열들의 배열: 모두 크기(길이)가 같을 필요 없음!

`new 배열유형[1차원 배열개수][1차원 배열의 크기]`

`new 배열유형[1차원 배열개수][];` 1차원 배열은 null

```java
int a = 10;
int[] arr = new int [4];

int [][] arr2 = new int[2][];
arr2[0] = new int[3];
arr2[1] = new int[3];
arr2[1][1] = 100;
```


![53B607C6-59D9-4BAC-B246-3A00293CFEF5.jpeg](/img/0718java3.jpeg)

### 2차원 배열 탐색

|  | 0 | 1 | 2 | 3 | 4 |
| --- | --- | --- | --- | --- | --- |
| 0 |  |  |  |  |  |
| 1 |  | r-1, c-1 | r-1, c | r-1, c+1 |  |
| 2 |  | r, c-1 | r, c | r, c+1 |  |
| 3 |  | r+1, c-1 | r+1, c | r+1, c+1 |  |
| 4 |  |  |  |  |  |
- 사방 탐색

```java
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 5; c++) {
				int sum = arr[r][c];
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dr[d];
					// 경계처리
					if (nr >= 0 && nr < 5 && nc >=0 && nc <5) {
						sum += arr[nr][nc];
						}
					}
				}
			}
```

- 2차원 배열 출력

`System.out.println(Arrays.deepToString(arr));`

`ToString`: 배열의 참조값

</div>
</details>

<details>
<summary>Math.random()</summary>
<div markdown="1">

# Math.random()

```java
(int)Math.random());
```

0보다 크거나 같고 1보다 작은 실수 → int : 0

0 ≤ x < 1 : 0

0 ≤ 3*x < 3 : 0, 1, 2

1≤ 3*x+ 1 < 4 : 1, 2, 3

- a부터 b까지의 정수 중에서 임의의 난수

a ≤ x < b+1

0 ≤ x - a < b+1-a

0 ≤ (x - a) / (b+1-a) < 1

(x - a) / (b+1-a) = Math.random()

`(int) Math.random() * (b-a+1) + a`




</div>
</details>

<details>
<summary>입출력</summary>
<div markdown="1">

# 입출력

sc.next() - 공백, \n으로 구분

sc.nextLine() - 한 줄씩 입력

**0726**

## BufferedReader

```java
public class InputTest1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 
	}
}
```

InputStreamReader : Byte → char : 문자열로 입력 받아줌

## Scanner와 BufferedReader 비교

| Scanner | BufferedReader |
| --- | --- |
| 자동 형 변환 | 문자열 |
| 약간 느리다 | 상대적으로 빠르다 |

```java
BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
String str = in.readLine();
StringTokenizer st = new StringTokenizer(str); // 공백 단위로 잘라줌
StringTokenizer st2 = new StringTokenizer(str, ","); // 원하는 delim 단위로 잘라줌
```

StringTokenizer: 공백을 단위로 잘라줌

## Scanner

```java
Scanner sc = new Scanner(System.in);
String str = sc.next(); // 공백을 기준으로 구분O
System.out.println(str);
String str2 = sc.nextLine(); // 한줄씩 구분
System.out.println(str2);
```

```java
sc.useDelimiter(",|\r\n");

while (sc.hasNext()) {
			System.out.println(sc.next());
}
```

## I/O와 Stream

I/O: 데이터의 입력과 출력

데이터는 한쪽에서 주고 한쪽에서 받는 구조

- 노드: 입력과 출력의 끝단
- 스트림: 두 노드를 연결하고 데이터를 전송할 수 있는 개념
- 스트림은 단방향으로만 통신이 가능, 하나의 스트림으로 입력과 출력을 같이 처리할 수 X

|  | byte | Char |
| --- | --- | --- |
| 입력 | InputStream | Reader |
| 출력 | OuputStream | Writer |

### 보조 스트림

Filter Stream, Processing Stream

다른 스트림에 부가적인 기능을 제공하는 스트림

ex. 문자 set 변환, Buffering, 기본 데이터 형의 전송, 객체 입출력

- InputStreamReader, OutputStreamWriter : byte 스트림을 char 스트림으로 변환
- BufferedInputStream, BufferedOutputStream, Buffered Reader, BufferedWriter : 버퍼링을 통한 속도 향상
- ObjectInputStream, ObjectOutputStream: 객체 전송

생성: 이전 스트림을 생성자의 파라미터에 연결 

---

### 객체 직렬화 (Serialization)
    
    객체를 저장하거나 네트워크로 전송하기 위해 연속적인 데이터로 변환하는 것
    
    반대의 경우는 역 직렬화
    

조건

Serializable 인터페이스를 구현할 것

클래스의 모든 멤버가 Serializable 인터페이스를 구현해야 함

직렬화에서 제외하려는 멤버는 translent 선언

- serialVersionUID
    
    클래스의 변경 여부를 파악하기 위한 유일 키
    
    직렬화할 때의 UID와 역 직렬화할 때의 UID가 다를 경우 예외 발생
    
    직렬화되는 객체에 UID 가 설정되지 않았을 경우 컴파일러가 자동 생성
    
    직렬화되는 객체에 대해서 serialVersionUID 설정 권장
    

---


</div>
</details>


<details>
<summary>객체지향 프로그래밍</summary>
<div markdown="1">

# 객체지향 프로그래밍


객체: 하나의 역할을 수행하는 ‘메소드와 변수(데이터)’의 묶음

## 특징

- Abstraction (추상화)
- Polymorphism (다형성)
- Inheritance (상속)
- Encapsulation (캡슐화)

</div>
</details>

<details>
<summary>클래스</summary>
<div markdown="1">


# 클래스

하나의 변수에 다양한 정보 묶기

Person class의 크기? → stack 영역이 아니라 heap 영역에 생성하고 주소를 저장

관련 있는 변수와 함수를 묶어서 만든 사용자정의 <자료형>

: 객체를 생성하는 틀

클래스를 통해 생성된 객체 = 인스턴스, 클래스≠인스턴스

## 구성

속성(Attribute) - 필드/멤버 변수

동작(behavior) - 메소드

생성자(Constructor): 인스턴스를 생성할 때 호출 메소드

```java
[접근제한자] [활용제한자] class 클래스명 {
		속성 정의 (필드)
		기능 정의 (메소드)
		생성자
}
```

접근제한자: public / default

활용제한자: final / abstract

## 변수

- **클래스 변수(class variable)**

클래스 영역에 선언 (static 키워드)

 `static int personCount;` (초기화가 자동 진행, 자료형의 기본값으로 초기화)

생성시기: 클래스가 메모리에 올라 갔을 때

소멸시기: 프로그램 종료시

객체 생성 무관, `(클래스 이름)._____`

모든 인스턴스가 공유

- **인스턴스 변수(instance variable)**

클래스 영역 선언

`int age; String name;` (초기화 자동 진행)

생성시기: 인스턴스가 생성되었을 때 (new)

소멸시기: G.C (garbage collection) 더 이상 변수를 참조하지 않을 때

인스턴스 별로 생성

- **지역 변수(local variable)**

클래스 영역 이외 (메서드, 생성자 … 등)

생성시기: 선언되었을 때

소멸시기: 중괄호를 벗어나면 소멸

사용하기 전 초기화 꼭 필요

외부 접근 X



```java
static int personCount;

System.out.println(Person.personCount);
System.out.println(p1.personCount); //올바른 접근x
```

## 메소드

- 객체가 할 수 있는 행동을 정의
- 어떤 작업을 수행하는 명령문의 집합에 이름을 붙여 놓은 것

```java
[접근제한자] [활용제한자] 반환값 메소드이름([매개변수들]) {
		행위 기술
}
```

접근제한자: public / protected / default / private

활용제한자: static / final / abstract / synchronized

- 메소드 호출

호출한 메소드가 선언되어 있는 클래스를 접근

클래스 객체.메소드 이름으로 호출

```java
Person p = new Person();
p.info();
```

- 매개변수(parameter): 메소드에서 사용하는 것
- 인자(Argument): 호출하는 쪽에서 전달하는 것



파라미터 전달 시 묵시적 형 변환o

파라미터보다 큰 변수를 인자로 할 수 없음

### 메소드 오버로딩

이름이 같고 매개변수가 다른 메소드를 여러 개 정의하는 것

중복 코드에 대한 효율적 관리 가능

파라미터의 개수 또는 순서, 타입이 달라야 할 것 (파라미터 이름만 다른 것은 X)

리턴 타입이 다른 것은 의미X

## 생성자

인스턴스가 생성될 때 최초 한 번 수행되는 함수

- new 키워드와 함께 호출
- 클래스를 생성할 때 반드시 하나의 생성자 호출 (생성자는 여러개 가능)
- 필드의 초기화, 객체 생성 시 실행되어야 할 작업 작성
- 클래스와 이름이 동일
- 반환 타입이 없다 (void 작성X)

### 종류

**기본(디폴트) 생성자**

클래스 내에 생성자가 하나도 정의되어 있지 않을 경우 JVM이 자동으로 제공

형태: 매개변수가 없는 형태, 클래스명() {}

**파라미터가 있는 생성자**

생성자의 목적이 필드 초기화 `String name = "Yang";`

생성자 호출 시 값을 넘겨주어야 함

해당 생성자를 작성하면 JVM이 기본 생성자를 추가하지 않음

```java
class Dog {
	String name;
	int age;

	Dog (String n, int a) {
		name = n;
		age = a;
	}
	//기본 생성자 추가x

Dog d1 = new Dog(); //기본 생성자 -> 실행 불가

```

생성자 오버로딩을 지원

## this

참조 변수로써 객체 자신을 가리킴

this를 이용하여 자신의 멤버 접근 가능

지역변수와 필드의 이름이 동일할 경우 필드임을 식별

**객체**에 대한 참조이므로 static 영역에서 this 사용 불가

- 활용

`this.멤버변수`

`this ( [인자값] );` : 생성자 호출

this 생성자 호출 시 제한 사항

- 생성자 내에서만 호출 가능
- 생성자 내에서 첫번째 구문에 위치해야 함
    
    ```java
    public EX2_Fruit() {
    		fruitNo = 1003;
    		this(1004, "낑깡"); // 첫번째 구문이여야 함!
    	}
    출처: https://choicode.tistory.com/24 [드럼치는 코린이의 개발 공간:티스토리]
    ```
    

```java
class Dog {
	String name;
	int age;
	Dog ( ) {
		this("초코"); 
	}
	Dog ( String name ) {
	}
```

`this("초코")` → `Dog (String name)` 호출

</div>
</details>

<details>
<summary>JVM 메모리 구조</summary>
<div markdown="1">


# JVM 메모리 구조

GC(Garbage Collection)가 메모리 관리

- GC

Heap 영역(class 영역 포함)에 생성된 메모리 관리 담당

더 이상 사용되지 않는 객체들을 점검하여 제거

자동적 실행 / CPU가 한가 or 메모리 부족

JVM에서 실행

힙 영역: new — 로 인스턴스 생성

스택 영역: 블럭 영역에 있는 지역변수도 포함



## static

1. 로딩 시점
    - static: 클래스 로딩시
    - non-static: 객체 생성시
2. 메모리 상의 차이
    - static: 클래스 당 하나의 메모리 공간만 할당
    - non-static: 인스턴스 당 메모리가 별도로 할당
3. 문법적 특징
    - static: 클래스 이름으로 접근
    - non-static: 객체 생성 후 접근
4. **static 영역에서는 non-static 영역을 직접 접근이 불가능**
5. **non-static 영역에서는 static 영역에 대한 접근이 가능**
    
    ```java
    public class Main {
    	String str = "문장"; //인스턴스 변수 -> new Main()이 만들어져야 사용 가능
    	public static void main(String[] args) {
    		System.out.println(str);
    	}
    
    public class Main {
    	static String str = "문장"; //이미 메모리에 올라가 있음
    
    	public void print() {
    		System.out.println(str);
    	}
    ```
    

</div>
</details>

<details>
<summary>패키지</summary>
<div markdown="1">


# 패키지

프로그램의 많은 클래스를 관리

클래스와 관련 있는 인터페이스들을 모아두기 위한 이름 공간

구분: . (folder.subfolder)


---

- 다른 패키지에 있는 클래스 사용: import 필요

ex. 입력 Scanner → `import java.util.Scanner;`

- 해당 패키지 안의 모든 class 포함 → `imoprt java.util.*;`
    
    패키지 안의 하위 패키지 포함x → `java.util.function` 포함X
    
- 다른 패키지 안의 같은 이름의 클래스 동시에 import X
    
    → main 내에서 풀패키지명을 작성하여 사용
    
- java.lang: 자동으로 포함

</div>
</details>

<details>
<summary>캡슐화</summary>
<div markdown="1">

# 캡슐화

객체의 속성과 행위를 하나로 묶고

실제 구현 내용 일부를 외부에 감추어 은닉

## **접근 제한자**

클래스, 멤버 변수, 멤버 메서드 등의 선언부에서 접근 허용 범위를 지정하는 역할의 키워드

- **public**: 모든 위치에서 접근 가능
- **protected**: 같은 패키지에서 접근 가능, 다른 패키지 접근 불가능
    
    단, 다른 패키지의 클래스와 상속관계가 있을 경우 접근 가능
    
- **(default)**: 같은 패키지에서만 접근 허용
- **private:** 자신 클래스에서만 접근 허용

그 외 제한자

- static: 클래스 레벨의 요소 설정
- final: 요소를 더 이상 수정할 수 없게 함
- abstract: 추상 메서드 및 추상 클래스 작성

클래스 사용 가능: public, default

내부클래스, 멤버변수, 메소드: public, protected, default, private

| 수식어 |  내부 | 동일 패키지 | (다른 패키지 내의) 하위 클래스 | 다른 클래스 |
| --- | --- | --- | --- | --- |
| private | O |  |  |  |
| (default) | O | O |  |  |
| protected | O | O | O |  |
| public | O | O | O | O |

## 접근자(getter) / 설정자(setter)

```java
public class Person {
	String name;
	private int age; // 나이는 0~100까지

	public void setAge(int age) {
		
		if (age >= 0 && age <= 100) {
			this.age = age;
		} else {
			// 에러처리
		}	
	}

	public int getAge() {
		return this.age;
	}

```

getter, setter 사용 → 안전하고 신뢰성 있는 코드

</div>
</details>

<details>
<summary>싱글턴 패턴</summary>
<div markdown="1">

# 싱글턴 패턴

생성자가 여러 차례 호출되더라도 실제로 생성되는 객체는 하나이고, 최초 생성 이후에 호출된 생성자는 최초의 생성자가 생성한 객체를 리턴

```java
public class Manager {
	private static Manager = manager = new Manager();
	
	private Manager() {}

	public static Manager getManager() {
		return manager;
	}
```

기본 생성자를 `private`으로 → 새로 생성X

`getManager()` 메서드는 인스턴스 생성과 상관없이 호출할 수 있어야 하기 때문에 반드시 **static**

**0721**

여러 클래스에서 동시에 공유할 때

ex. 게임 - score

cf. `static class` 차이: Singleton=한 번이라도 객체를 생성 → 인스턴스

---

ex) `Math.____` : 객체 생성 필요X

- static class는 static 변수, static 메소드만 O

```java

public class ScoreManager {

	private static ScoreManager scoreManager = new ScoreManager();
	int score;

	// Singleton = 생성자 private
	private ScoreManager() {
		// 이 class 내에서만 호출
	}

	public static ScoreManager getInstance() {
		// 왜 static으로?
		// getInstance() 메서드는 인스턴스 생성과 상관없이 호출할 수 있어야 하기 때문이다.
		return scoreManager;
	}

	public int getScore() {
		return this.score;
	}

	public void addScore() {
		this.score += 1;

	}

}

//////////////////////
public int getScore() {
		this.test = 1; // static 
		return this.score;
	}
// non static 메소드 static 변수 가능, 그러나 추천X
	
	public static int test() {
		this.score = 1;
	}
// static 메소드 내에서 non-static 변수 불가능
```

생성자 = static X

`getInstance` = static O, static 변수

`getScore, addScore` = static X

static한 메소드에서 non static한 변수를 쓸 수 x

| Singleton | static class |
| --- | --- |
| 인스턴스 | 클래스 |
| 생성자O | 생성자X |

</div>
</details>

<details>
<summary>상속</summary>
<div markdown="1">

# 상속

1. 확장성, 재사용성: 부모의 생성자와 초기화 블록은 상속x
2. 클래스 선언 시 extends 키워드를 명시: 자바는 다중 상속X
3. 관계
    - 부모(상위, Super) 클래스 : Person
    - 자식(하위, Sub) 클래스 : Student
4. 자식 클래스는 부모 클래스의 멤버변수, 메소드를 자신의 것처럼 사용할 수 있다
5. Object 클래스는 모든 클래스의 조상 클래스
    
    -별도의 extends 선언이 없는 클래스는 `extends Object`가 생략
    
6. super 키워드
    
    super를 통해 조상 클래스의 생성자 호출
    
    생성자 호출 순서: **부모 생성자 호출 → 자식(호출된 클래스) 생성자 호출**
    

```java
public class Person {
	String name;
	int age;

	public Person() {
		System.out.prinln("Person 기본 생성자");
	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
}

public class Student extends Person {
	String major;

	public Student() {
		super(); // 부모의 기본생성자 호출
		System.out.println("Student 기본 생성자");
	}
}
```

자식 class의 생성자에 `super()` : 부모의 기본 생성자 호출

Person class → extends Object: super() 호출

- 부모의 기본생성자가 만들어지지 않았으면 오류 O **매개변수가 동일해야 함**

부모의 메서드도 super로 호출 가능

1. 오버라이딩 (재정의, overriding)
    
    `@override`-Annotation: 해당 메서드가 재작성되었음을 알려줌
    
    장점: 잘 override가 되었는지 알려줌 
    
    상위 클래스에 선언된 메서드를 자식 클래스에서 재정의하는 것
    
    **메서드의 이름, 반환형, 매개변수(타입, 개수, 순서)** 동일해야 한다.
    
    하위 클래스의 접근 제어자 범위가 상위 클래스보다 크거나 같아야 함
    
    : 부모가 public, 자식이 private (X)
    
    : 부모가 private, 자식이 public (O)
    
    조상보다 더 큰 예외를 던질 수 없음
    

부모의 속성이 private → 자식 class에서 접근할 수 없음: getter, setter를 통해 접근 

## Object 클래스

가장 최상위 클래스로 모든 클래스의 조상

Object의 멤버는 모든 클래스의 멤버

- `equals(), getClass(), hashCode(), notify(), notifyAll()`

---

- toString 메서드

객체를 문자열로 변경하는 메서드

- equals 메서드

두 객체가 같은지를 비교하는 메서드

String을 equals로 비교 → 객체 주소가 아니라 내용 비교로 override

비교하고 싶은 대상으로 메서드 override 가능

```java
@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
```

## final

해당 선언이 최종 상태, 결코 수저오딜 수 없음

final 클래스 : 상속 금지

final 메소드 : overriding 금지

final 변수 : 더 이상 값을 바꿀 수 없음-상수화, 전부 대문자로



---

</div>
</details>

<details>
<summary>다형성</summary>
<div markdown="1">



**0725**

# 다형성 Polymorphism

Animal > Person > Student 

학생 객체 → 사람 관점 → 학생 관점 (O)

사람 객체 → 사람 관점 → 학생 관점 (X)

```java
Person s2 = new Student(); // 조상 클래스의 타입(Person)으로 자식 클래스 객체(Student)를 참조
//		s2.study(); // 오류남.. Person으로 보고 있음

Student st = new Person(); // 참조 불가능
```

상속 관계에 있을 때 조상 클래스의 타입으로 자식 클래스 객체를 참조할 수 있다.

## 활용

1. 다른 타입의 객체를 다루는 배열

```java
Person[] persons = new Person[3];
persons[0] = new Person();
persons[1] = new Student();
persons[2] = new Student();
```

1. 매개변수의 다형성

메서드가 호출되기 위해서는 메서드 이륵뫄 파라미터가 맞아야 함

ex. `public void println(Person p)`

조상을 파라미터로 처리한다면 객체의 타입에 따라 메서드를 만들 필요가 없다

API에서 파라미터를 `Object`로 받는다는 것은 모든 객체를 처리한다는 말

필요시 하위 클래스에서 오버라이딩 필요

### 다형성과 참조형 객체의 형 변환

- 메모리에 있는 것과 사용할 수 있는 것의 차이

`Person p = new Student("김", 20, "수학");`

new Student

Object - equals(), toString()

Person - name, eat()

Student - major, study()

`Person p` → `p.study()` 사용 불가

→ 메모리에 있더라도 **참조하는 변수의 타입**에 따라 접근할 수 있는 내용이 제한됨

```java
Person p2 = new Person();
Student st2 = (Student) p2;
st2.study();
```

무늬만 Student인 Person → 메모리의 객체는 study() 없음

`instanceof` 연산자: 실제 메모리에 있는 객체가 특정 클래스의 타입인지 boolean으로 리턴

### 참조 변수의 레벨에 따른 객체의 멤버 연결

```java

class SuperClass {
	String x = "super";

	public void method() {
		System.out.println("super class method");
	}

}

class SubClass extends SuperClass {
	String x = "sub";

	@Override
	public void method() {
		System.out.println("sub class method");
	}
}

public class MemberBindingTest {
	public static void main(String[] args) {
		SubClass subClass = new SubClass();
		System.out.println(subClass.x); //sub
		subClass.method(); //sub class method
		

		SuperClass superClass = subClass;
		System.out.println(superClass.x); // super
		superClass.method(); //sub class method

	}
}
```

상속 관계에서 객체의 멤버 변수가 중복될 때

- 참조 변수의 타입에 따라 연결이 달라짐

상속 관계에서 객체의 메서드가 중복될 때 (**메서드가 override**되었을 때)

- **무조건 자식 클래스의 메서드가 호출**됨 → virtual method invocation: 동적 바인딩
- 최대한 메모리에 생성된 실제 객체에 최적화된 메서드가 동작
    
    

</div>
</details>

<details>
<summary>추상클래스</summary>
<div markdown="1">


# 추상클래스

- 부모 클래스 중에서 일부를 구현하지 않은 것
- 추상 메소드 포함 → 추상 클래스
- 상속 받는 자식 클래스에서 (객체를 생성하려면) 반드시 abstract method를 구현해야 함
- 객체를 생성할 수 없음
- 부모클래스에서 해당 메소드를 구현할 필요가 없을 때 / 부모클래스로 객체를 생성할 필요가 없을 때
    
    

## 필요성

조상 클래스에 있는 메서드 중, 각 자손 클래스에서 override가 예정되어 있는 메서드를 구현하지 않고 정의만 함

## 정의

자손 클래스에서 반드시 재정의해서 사용되기 때문에 조상의 구현이 무의미한 메서드를 가진 클래스

메서드의 선언부만 남기고 구현부는 ;(세미콜론)으로 대체

구현부가 없으므로 `abstract` 키워드를 메서드 선언부에 추가

객체를 생성할 수 없는 클래스라는 의미로 클래스 선언부에 `abstract`를 추가

### 익명 클래스

추상 클래스로 객체를 생성하는 방법
Person 클래스는 추상 클래스

```java
// 익명 클래스 문법으로 1회 한정으로 구현하고 인스턴스를 만들 수 있게 해준다
		Person s2 = new Person() {
			@Override
			public void eat() {
				System.out.println("먹는다.");
			}
		};
```

abstract 클래스도 배열은 가능함 `Person[]`

## 특징

상속 전용 클래스

클래스에 구현부가 없는 메서드가 있으므로 객체를 생성할 수 없음

상위 클래스 타입으로 자식을 참조할 수는 있음

```java
Person p1 = new Person(); // 생성 불가
Person p2 = new Student(); // 참조 가능
```

조상 클래스에서 상속 받은 abstract 메서드를 재정의 하지 않은 경우, 클래스 내부에 abstract 메서드가 있으므로 자식 클래스는 abstract 클래스가 되어야 함

**→ abstract 메서드를 정의하거나, abstract 클래스가 됨**

## 장점

- 구현의 강제를 통해 프로그램의 안정성 향상

부모가 구현하고 싶은 내용이 없다고 해서 구현 자체를 빼버리면 동적 바인딩에 의해 자식의 오버라이딩된 함수가 실행되는 기회를 없앰

→ abstract 메서드를 만들어 놓으면 자식 클래스는 해당 클래스를 무조건 구현해야 하는 의무를 가짐


</div>
</details>

<details>
<summary>인터페이스</summary>
<div markdown="1">


# 인터페이스

클래스가 갖추어야 할 요건을 정의해놓은 것

..able 이라는 이름이 많이 붙음

장점: 구현한 인터페이스에 따라 어떤 기능이 있는지 유추 가능

대규모 프로젝트를 할 때 협업하기에 용이함

인터페이스는 상속에서 자유로움

완벽히 추상화된 객체: 모든 메서드가 abstract 형태

## 특징

1. interface 키워드를 이용하여 선언
2. 선언되는 변수는 모두 **상수**로 적용
3. 선언되는 메소드는 모두 **추상 메소드**로 적용
4. 인터페이스끼리 extends를 이용하여 상속 가능 (다중 상속가능, 구현부가 없음)
    
    ```java
    interface MyInterface2 {
    
    }
    
    interface MyInterface4 extends MyInterface2, MyInterface {
    	
    }
    ```
    
5. 객체 생성이 불가능 (추상클래스 동일한 특성)

6. 클래스가 인터페이스를 상속할 경우에는 extends 키워드가 아니라 `implements`(구현) 키워드를 이용

7. 인터페이스를 상속받는 하위클래스는 추상 메소드를 반드시 오버라이딩(재정의) 해야 한다
    
    (구현하지 않을 경우 abstract 클래스로 표시해야 함)
    
8. 인터페이스 다형성 적용

```java
IPerson p2 = new Student(); // 추상 클래스처럼 인터페이스를 참조 가능, 다형성 성질O
p2.method() // IPerson에 정의된 추상 메소드 -> Student에서 구현한 메소드가 호출됨
```

## 필요성

- 구현의 강제로 표준화 처리 (abstract 메서드 사용)
- 인터페이스를 통한 간접적인 클래스 사용으로 손쉬운 모듈 교체 지원
- 서로 상속의 관계가 없는 클래스들에게 인터페이스를 통한 관계 부여: 다형성 확장
- 모듈 간 독립적 프로그래밍 가능


---


</div>
</details>

<details>
<summary>제네릭 Generics</summary>
<div markdown="1">


**0727**

# 제네릭 Generics

다양한 타입의 객체를 다루는 메서드, 컬렉션 클래스에서 컴파일 시에 타입 체크

- 미리 사용할 타입을 명시 → 형 변환을 하지 않아도 됨
- 객체의 타입에 대한 안전성 향상 및 형 변환의 번거로움 감소

## 표현

클래스또는 인터페이스 선언 시 `<>`에 타입 파라미터 표시

타입 파라미터

- T : reference Type
- E : Element
- K : Key
- V : Value

## 객체 생성

변수 쪽과 생성 쪽의 타입은 반드시 같아야 함

```java
Class_Name<String> generic = new Class_Name<String>(); 
Class_Name<String> generic2 = new Class_Name<>(); // 생성 쪽에 쓰지 않으면 알아서 같은 타입으로
```

## Type parameter의 제한

필요에 따라 구체적인 타입 제한 필요

type parameter 선언 뒤 extends와 함께 상위 타입 명시

```java
class NumberBox <T extends Number> {
	public void addSomes (T... ts ) { // 가변인자, 매개변수가 몇 개 들어오는지 모를 때
		double d = 0;
		for (T t : ts ) {
			d += t .doubleValue
		}
	System.out.println("총 합은 : "+ d);
	}
}
```

인터페이스로 제한할 경우도 extends로 사용

클래스와 함께 인터페이스 제약 조건을 이용할 경우 `&`으로 연결

```java
class TypeRestric2<T extends Number & Cloneable & Comparable<String>>{}
```

## 와일드 카드

generic type에서 구체적인 타입 대신 사용, 제네릭의 실용성을 더해줌

1. `Generic type<?>` 타입에 제한이 없음
2. `Generic type<? extends T>` T 또는 T를 상속받은 타입들만 사용 가능
3. `Generic type<? super T>` T 또는 T의 조상 타입만 사용 가능

---


</div>
</details>

<details>
<summary>Collection Framework</summary>
<div markdown="1">


# Collection Framework

객체들을 한곳에 모아 놓고 편리하게 사용할 수 있는 환경 제공

정적 자료구조 (Static structure)

- 고정된 크기의 자료구조
- 배열

동적 자료구조(Dynamic structure)

- 요소의 개수에 따라 자료구조의 크기가 동적으로 증가하거나 감소
- 리스트, 스택, 큐

## **핵심 interface**

### List

- 순서가 있고, 중복을 허용 (배열과 유사)
- 구현 클래스: ArrayList, LinkedList

내부적으로 배열을 이용하여 데이터를 관리

배열과 다르게 크기가 유동적으로 변함 (동적 자료구조)

배열을 다루는 것과 유사하게 사용 가능

```java
public interface List<E> extends Collection<E>
```

Collection interface를 상속

### **배열과 ArrayLis**t

- 배열의 장점
    - 가장 기본적인 형태의 자료 구조, 간단하며 사용이 쉬움
    - 접근 속도가 빠름
- 배열의 단점
    - 크기를 변경할 수 없어 추가 데이터를 위해 새로운 배열을 만들고 복사 해야 함
    - 비순차적 데이터의 추가, 삭제에 많은 시간이 걸림

→ ArrayList도 배열을 사용하여, 배열의 장/단점을 그대로 가져감

### Set

특징: 순서가 없고, 중복을 허용하지 않음 // 인덱스가 없음

장점: 빠른 속도, 효율적인 중복 데이터 제거 수단

단점: 단순 집합의 개념으로 정렬하려면 별도의 처리가 필요하다.

구현 클래스: HashSet, TreeSet

### Map

특징: Key(키)와 Value(값)를 하나의 Entry로 묶어서 데이터 관리, 순서는 없으며 키에 대한 중복X

장점: 빠른 속도

구현 클래스: HashMap, TreeMap

## 정렬

요소를 특정 기준에 대한 내림차순 또는 오름차순으로 배치하는 것

순서를 가지는 Collection들만 정렬 가능

- List 계열
- Set에서는 SortedSet의 자식 객체
- map에서는 SortedMap의 자식 객체(Key 기준)

Collections의 sort()를 이용한 정렬

- `sort(List<T> list)`
- 객체가 Comparable을 구현하고 있는 경우 내장 알고리즘을 통해 정렬

### Comparable / Comparator

**Comparable Interface**

```java
public interface Comparable<T> {
	public int compareTo(T o);
}
```


Comparator의 활용

- 객체가 Comparable을 구현하고 있지 않거나 사용자 정의 알고리즘을 정렬하려는 경우

`sort(List<T> list, Comparator<? Super T> c)`

---

</div>
</details>

<details>
<summary>예외처리</summary>
<div markdown="1">


# 예외처리

- 에러와 예외

어떤 원인에 의해 오동작하거나 비정상적으로 종료되는 경우

**Error**

메모리 부족, stack overflow와 같이 일단 발생하면 복구할 수 없는 상황

프로그램의 비정상적 종료를 막을 수 없음 → 디버깅 필요

**Exception**

읽으려는 파일이 없거나, 네트워크 연결이 안 되는 등 수습될 수 있는 비교적 상태가 약한 것들

프로그램 코드에 의해 수습가능

**예외처리란?**

- 예외 발생 시 프로그램의 비정상 종료를 막고 정상적인 실행 상태를 유지하는 것
- 예외의 감지 및 예외 발생 시 동작할 코드 작성 필요

## 예외 클래스의 계층

- Checked exception
    
    예외에 대한 대처 코드가 없으면 컴파일이 진행되지 않음
    
- Unchecked exception (RuntimeException의 하위 클래스)
    
    예외에 대한 대처 코드가 없더라도 컴파일은 진행됨
    

## 예외 처리 키워드

### try-catch

```java
try {
// 예외가 발생할 수 있는 코드 
} catch (Exception e) {
// 예외가 발생했을 때 처리할 코드
}
```
    

## try ~ catch 문에서의 흐름

1. try 블록에서 예외가 발생
2. JVM이 해당 Exception 클래스의 객체 생성 후 던짐(throw): `throw new XXException()`
3. 던져진 exception을 처리할 수 있는 catch 블록에서 받은 후 처리 (적당한 catch 블록을 만나지 못하면 예외처리는 실패)
4. 정상적으로 처리되면 try-catch 블록을 벗어나 다음 문장 진행

- try 블록에서 어떤 예외도 발생하지 않는 경우: catch문을 거치지 않고 블록의 다음 흐름 문장 실행

## 다중 exception handling

try 블록에서 여러 종류의 예외가 발생한 경우

하나의 try 블록에 여러 개의 catch 블록 추가 가능 (예외 종류 별로)

**유의사항**

- JVM이 던진 예외는 catch 문장을 찾을 때는 다형성이 적용
    - Exception > XXException 상속 관계
    - 상속 타입의 예외가 먼저 선언되는 경우 뒤에 등장하는 catch 블록은 동작x
    - 상속 관계가 없는 경우는 무관
    - 상속 관계에서는 작은 범위(자식)에서 큰 범위(조상)순으로 정의

Exception은 모든 예외 class의 조상 → Exception이 다 처리할 수 있음

```java
try {
			System.out.println(nums[2]);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (ArithmeticException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Array~~와 arithmetic~~은 상속 관계가 아니라 상관X
		// Exception은 가장 마지막에 정의 (모든 Exception의 조상)
```

## try ~ catch ~finally 구문

finally는 예외 발생 여부와 상관 없이 **언제나** 실행

중간에 return을 만나는 경우도 finally 블록을 먼저 수행 후 return 실행

finally : 주로 자원을 반납/반환할 때 사용, 항상 실행되어야 하는 기능

try-catch 블록 밖에다가 써도 되지만, try 구문 안에 return이 있으면 실행되지 않을 수 있고 통일성이 없어 보임

## throw 키워드를 통한 처리 위임

method에서 처리해야 할 하나 이상의 예외를 호출한 곳으로 전달 (처리 위임)

예외가 없어지는 것이 아니라 단순히 전달됨

예외를 전달받은 메서드는 다시 예외 처리의 책임 발생

---

Checked Exception - 반드시 `try ~ catch` 또는 `throws` 필요, 필요한 곳에서 try~catch 처리

UnChecked Exception - `throws`하지 않아도 전달 O, 결국은 try~catch로 처리해야 함 → 실수 가능성 높음

### 메서드 재정의와 throws

메서드 재정의시 조상 클래스가 던지는 예외보다 부모 예외를 던질 수 없다.

## 로그 분석과 예외의 추적

Throwable의 printStackTrace: 메서드 호출 스택 정보 조회 가능: 최초 호출 메서드에서부터 예외 발생 메서드까지의 스택 정보 출력

### 꼭 확인해야할 정보

예외 종류

예외 원인: 예외 객체의 메세지

디버깅 출발점: 어디서 발생?

직접 작성한 코드를 디버깅 대상으로 삼을 것

참조하는 라이브러리는 과감히 건너 뛰기

## 사용자 정의 예외

API에 정의된 exception을 제외하고 필요에 따라 사용자 정의 예외 클래스 작성

대부분 Exception 또는 RuntimeExcepton 클래스를 상속받아 작성

- checked Exception 활용: 명시적 예외 처리 또는 throw 필요
    
    (코드는 복잡하지만 오류 발생 가능성 down)
    
- runtime Exception 활용: 묵시적 예외 처리 가능
    
    (코드는 간결하지만 예외 처리, 누락 가능성 발생)
    

장점

- 객체의 활용: 필요한 추가 정보, 기능 활용
- 코드의 재사용: 동일한 상황에서 예외 객체 재사용 가능
- throws 메커니즘의 이용: 중간 호출 단계에서 return 불필요
