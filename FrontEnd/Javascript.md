# Javascript

## 0715

### Javascript / vscode

Live Server: 실시간 웹페이지 보여줌.

<h1>기본 문법</h1>
<details>
<summary>기본 문법</summary> 
<div markdown="1">


**0803**



HTML 자바스크립트 사용

`<script> </script>` 태그를 사용

문서 내 위치의 제약이 없음 

보통  `</body>` 태그 바로 위에 옴

```html
<script>
      console.log(2);
</script>
```

콘솔 로그

- **외부스크립트 참조하기**

.js 확장자를 가진 파일을 생성

html 문서에서 `<script src="외부파일의 위치"></script>`

```html
	<script src="js02.js">
      console.log("실행되지 않는 부분");
    </script>
    <script>
      console.log("파일 호출과 별도의 태그를 만들어서 실행");
    </script>
```

외부스크립트를 참조하는 구간에 있는 태그는 실행x

- **주석**

```html
      // 한줄 주석
      /*
        여러줄 주석
      */
```

자바와 유사

## 변수

가리키는 값에 대한 타입을 나타냄

var, let, const 키워드를 이용하여 변수를 선언

var: 중복 선언 가능

undefined: 변수에 아무 값도 없어서 타입을 알 수 없는 경우

동적 타입: 대입되는 값에 따라 용도가 변경

문자, $, _로 시작, 대소문자 구분, 예약어 사용X

### var

- 재선언 가능, 재할당 가능
- ES6 이전에 변수 선언시 사용
- 호이스팅(Hoisting) 특성이 있음
    - 자바스크립트 함수는 실행되기 전에 함수 안에 필요한 변수값들을 모두 모아서 유효 범위의 최상단에 선언한다.
    [https://gmlwjd9405.github.io/2019/04/22/javascript-hoisting.html](https://gmlwjd9405.github.io/2019/04/22/javascript-hoisting.html)
    
    ```jsx
    console.log(major) // undefined 에러가 나지 않음
    var major="정치";
    console.log(major);
    /////////////////////////////실제 동작
    var major; // hoisting
    console.log(major)
    major="정치";
    console.log(major)
    ```
    
- 함수 스코프


 예약어 사용x

 대소문자 구분
 
 문자, $, _ 로 시작 (숫자는 안됨)
 
 기본 카멜케이스 
 
 클래스, 생성자 -> 파스칼케이스
 
 상수 대문자 스네이크 케이스


### let

- 재선언 불가, 재할당 가능
- 블록 스코프 (≠var: 함수 스코프)
    
    ```jsx
    let job = "teacher";
    {
      let job = "student";
    }
    console.log(job)
    // teacher
    ```
    
    - [https://velog.io/@fromzoo/함수스코프-vs-블록스코프](https://velog.io/@fromzoo/%ED%95%A8%EC%88%98%EC%8A%A4%EC%BD%94%ED%94%84-vs-%EB%B8%94%EB%A1%9D%EC%8A%A4%EC%BD%94%ED%94%84)
    - i는 for 블록 안에서만 유효함
    
    ```jsx
    function  loop() { 
    	for (let i = 0; i < 5; i++) { 
    		console.log(i); 
    	} 
    	console.log('final', i); 
    } 
    loop(); /* ReferenceError: i is not defined */
    ```
    
    - cf. 함수 스코프
    
    ```jsx
    function loop() {
    	for(var i = 0; i < 5; i++) {
    		console.log(i);
    	}
    	console.log('final',i);
    }
    loop();
    /*
    	0
    	1
    	2
    	3
    	4
    	final 5
    */
    ```
    
- 호이스팅 X

### const

- 재선언 불가, 재 할당 불가
- 블록 스코프
- 대문자 SNAKE_CASE 사용
- 선언 시 값을 할당해야 함
    - 값을 재할당할 수 없기 때문에
- 상수로 사용

---

### undefined

- 변수에 값이 대입되지 않은 상태

```jsx
var name;
console.log(name);
```

## 데이터 타입(Data Type)

- 기본 데이터 타입(primitive): String, Number, Boolean, null, undefined
- 객체 타입(Reference) : Object - function, array 등

### typeof - 변수의 자료형 검사

- typeof 데이터
- typeof (데이터)
- typeof의 결과는 문자열 반환
- null의 데이터 타입은 null이 아닌 object (설계 실수)
- funcion은 기능을 가진 객체

### 동적 데이터 타입

다양한 값의 대입이 가능

### 숫자형 (Number)

정수와 실수로 나누어 구분하지 않음 (부동소수점 형식)

실수에 대해서는 정확한 연산x

일반적인 숫자 외 특수 숫자 포함(Infinity, NaN)

e를 활용하여 거듭제곱 표현 가능

### 문자열 (String)

- “ “로 감싼다
- ‘ ‘로 감싼다
- ` `(backtick)로 감싼다 -> Template Literal(ES6)
    - 여러 줄 입력이 가능 - 공백, 줄 넘김 유지
    - 문자열 내 ${변수명}을 이용하여 변수와 문자열을 결합
- UTF-16형식

emoji [https://apps.timwhitlock.info/emoji/tables/unicode#emoji-modal](https://apps.timwhitlock.info/emoji/tables/unicode#emoji-modal)

문자열 접근

```jsx
'문자열'.length
'문자열'[1]
'문자열'.charAt(1)
'문자열'[100] // undefined
'문자열'.charAt(100) // undefined
```

**연산**

문자열 + 숫자 → 문자열

그 이외의 연산 → 숫자

```jsx
// 하나라도 문자열이면 문자열로 +
// 2+2+'1' ->  '41'
console.log(1 + "20"); // 120
console.log("1" + "20"); // 120
console.log("1" + 20); // 120
console.log("100" - 8); // 92
console.log("100" * 8); // 800

console.log(1+"2"+2) //122
console.log("1"+2+2) // 122
```

형변환

```jsx
Number('123n') // NaN
Number(' 0123 ') // 123
Number(false) // 0
Number(undefined) // NaN
Number(null) // 0

String()
```

### Boolean

**자바스크립트 false**

- null, undefined, 0, ‘’ (빈 문자열), NaN → false
- 나머지는 true로 인식

```jsx
console.log("!!0", !!0);
console.log("!!''", !!"");
console.log("!!null", !!null);
console.log("!!undefined", !!undefined);
console.log("!!NaN", !!NaN);
// 모두 false
Boolean()

console.log(!!"0"); // true 빈 문자열이 아님

var id;
if (id) {
  console.log("id가 값이 있는 경우임...");
} else {
  console.log("id가 값이 없는 경우임...");
}
// else 출력 -> undefined
```

`!! = Boolean()`

### 연산자

+(덧셈), 단 항 사용시 Number()와 동일한 역할 `+"0"`

-, *, /, %, =, += -=, ++, — 

**(거듭제곱)

- 일치 연산자

값과 타입이 일치하는지 체크 `===, !==`

```jsx
var i = 100;
var j = "100";

// 비교시 암묵적 타입 변환을 통해 타입을 일치시킨 후 비교
console.log("i == j", i == j); // true 값만 확인
console.log("i === j", i === j); // false 값과 타입 확인
```

## 제어문

```jsx
for(let i = 0 ; i<10; i++ ){
  console.log(i)
}

let j = 0
while( j < 10) {
  j++;
}
do {

}while(j < 10)


// for in
// 객체의 속성들을 순회할 때 사용
// 배열도 순회 가능하지만 인덱스 순으로 순회한다는 보장이 없음
let student = {
  name: "이름",
  age: 20,
  hobby: ["취미1", "취미2"],
  "favorite singer": "가수",
};

for(key in student){
  console.log(key)
  console.log(student[`${key}`])
}
// name
// 이름
// age
// 20
// hobby
// (2) ['취미1', '취미2']
// favorite singer
// 가수 

// for of
// 반복 가능한(iterable) 객체를 순회하며 값을 꺼낼 때 사용
// Array, Map, Set, String 등
let food = ['김밥', '사이다']
for(val of food){
  console.log(val)
}
```

## 배열

배열의 생성: [] 또는 Array()

크기는 동적으로 변경

크기가 지정되어 있지 않은 경우에도 데이터의 입력 가능

배열의 길이는 가장 큰 인덱스+1

배열은 여러가지의 데이터 타입을 하나의 배열에 입력할 수 있음

push 함수를 통해 데이터 추가

```jsx
var arr1 = [];
var arr2 = new Array();

arr1[0] = 10;
arr1[2] = 30;
console.log(arr1[0], arr1[1], arr1[2]);  // 10 undefined 30
console.log(arr1.length);  // 3

arr1[3] = "문자열";
arr1[4] = {};
arr1[5] = [1, 2, 3];
arr1[6] = true;
arr1["msg"] = "메세지";
console.log(arr1);

arr1.push("추가"); // 배열 마지막 추가
arr1.pop() // 배열 마지막 제거
arr1.unshift("추가") // 배열 가장 앞 추가
arr1.shift() // 배열 가장 앞 제거
arr1.includes() //특정값 있는지 판별
arr1.indexOf() // 인덱스 반환 없으면 -1
arr1.join(구분자) // 구분자로 연결하여 반환 생략시 쉼표 기본
```


</div>
</details>

---

<h1>객체</h1>
<details>
<summary>객체</summary> 
<div markdown="1">

# 객체

문자열로 이름을 붙인 값들의 집합체 (Key : Value) (json)

key는 문자열

객체에 저장하는 값=프로퍼티(Property)

prototype이라는 특별한 프로퍼티를 가지고 있음

## 객체 만들기

객체 리터럴 이용: {}

Object 생성자 이용: `new Object()`

생성자 함수 이용 `function Member() {}`

```jsx
let member1 = { id: "aa", email: "aa@a.com" };
function Member(id, email) {
  this.id = id;
  this.email = email;
}

// this는 나를 부른 것! -> member2
let member2 = new Member("aa", "aa@a.com");
```

### 객체 생성 시 프로퍼티 추가

```jsx
let student = {
  name: "이름",
  age: 20,
  hobby: ["취미1", "취미1"],
  "favorite singer": "가수", 
// 띄어쓰기, 특수문자가 있으면 알아서 key를 인식x -> 따옴표 필요함
};

console.log(student.name);
console.log(student[age]); // 에러
console.log(student.hobby);
console.log(student["favorite singer"]); // .으로 접근x

```

### 프로퍼티 추가 생성 삭제

```jsx
// 프로퍼티 생성
var member = {};
member["id"] = "lee";
member.name = "이름";

// 동적인 프로퍼티 추가
member.email = "lee@email.com";
console.log(member);

// 프로퍼티 수정
member["id"] = "id2";  
member.email = "id2@a.com";  

// 프로퍼티 제거
let member = { id: "id", email: "id@a.com" };
delete member.id;
console.log(member);

```

함수 안에서의 this는 함수를 호출한 객체

```jsx
//함수안에서 this는 함수를 호출한 객체이다.

var m1 = {name: "이름1"};
var m2 = {name: "이름2"};
function msg () {
    console.log(this);
    console.log(this.name + "입니다.");
}
m1.msg = msg; //msg라는 key를 가진 function을 정의함 this.name:이름1
m2.msg = msg; // this.name = 이름2
m1.msg(); // 
m2.msg();
```

## JSON

JavaScript Object Notation

자바스크립트를 토대로 개발

여러 프로그래밍 언어에서 사용할 수 있는 독립형 언어

웹 클라이언트와 웹 서버 간 데이터 교환에 사용

웹 브라우저 비동기 처리에 사용되는 AJAX의 데이터 교환 형식으로 널리 알려짐


### Key, Value의 쌍으로 표현

- {”key” : value, … }
- key는 “”로 묶어서 표현
- value는 String일 경우 “”로 묶어서 표현

### JSON 내장 메서드

`JSON.parse()` : JSON 문자열 → 자바스크립트 객체

`JSON.stringify()`: 자바스크립트 객체 → JSON 문자열

# 함수

## 특징

- 객체 타입으로 값처럼 사용이 가능
- 함수를 변수에 대입하거나 매개변수로 넘길 수 있음
- 배열의 요소에 넣거나 객체의 프로퍼티로 설정이 가능
- 매개변수의 개수가 일치하지 않아도 호출이 가능
- JavaScript의 함수는 일급 객체(First-class-citizen)에 해당
    - 변수에 할당 가능
    - 함수의 매개변수로 전달 가능
    - 함수의 반환 값으로 사용 가능

## 함수 만들기

- 함수 선언식

`function 함수명() {함수 내용}`

- 함수 표현식

`let 함수명 = function() {함수 내용}`

### 함수 선언식

- 함수의 이름과 함께 정의하는 방식
- 함수의 이름
- 매개변수
- 내용
- 호이스팅o

```jsx
func( ); // 호이스팅됨 -> 문제x
function func( ) {
    console.log('선언식');
}
// 반환값이 없음 -> 콘솔에 undefined 
```

### 함수 표현식

- 익명 함수로 정의 가능
- 매개변수
- 내용

```jsx
func( ); // 호이스팅x -> 문제o
let func = function ( ) {
    console.log('표현식');
};
```

var로 선언해도 안 됨

```jsx
var func; // 호이스팅?
func() // 함수가 아니라고 인식함!
func = ~~;
```

### 선언식 vs 표현식

- 선언식: 호이스팅의 영향을 받아 함수 선언 이전에 호출 가능
- 표현식: 함수는 선언 이전에 호출이 불가능

## 함수의 리턴

함수의 실행 결과로 함수를 반환할 수 있음

리턴값이 없을 경우 undefined 반환

```jsx
function func() {
  return function (num1, num2) {
    return num1 + num2;
  };
}
function func2() {}

let callFn = func();
let result = callFn(100, 200);
console.log(result);

console.log(func2()); // undefined 출력

let callFn2 = func; // 함수
callFn2()(100,200) // 300 반환
```

## 함수의 호출

정의된 함수를 호출 시 함수를 값으로 넘길 수 있음

```jsx
function func(callFn) {
  callFn("hello");
}
function fn(msg) {
  console.log(msg);
}

func(fn("hello")); // error 
func(fn); // hello
```

## 함수 매개변수

호출 시 매개변수의 영향을 받지 않음

arguments라는 함수 내부의 프로퍼티를 이용하여 매개변수의 처리가 가능

오버로딩 개념X

기본 인자(default arguments)를 사용할 수 있음

```jsx
function fn1(num) {
  console.log("fn1", num);
}
fn1();
fn1(100);
fn1(100, 100);

//
function fn2() {
  console.log(arguments.length);
  for (let i = 0; i < arguments.length; i++) {
    console.log(arguments[i]);
  }
}
fn2(1);
fn2(1, 10, 100);

//
function fn() {
  console.log(1);
}
function fn() {
  console.log(2);
}
function fn(num) {
  console.log(num);
}
fn();
fn(1);

//

function hello(name = "이름") {
  console.log(name + "님 안녕하세요.");
}
hello(); // 이름님 안녕하세요.
hello("이름2"); // 이름2님 안녕하세요.
```

## 화살표 함수

ES6에서 추가

함수를 심플하게 정의

`(매개변수) => {명령어}`

작성순서

1. function 키워드 삭제
2. () 안에 함수가 사용할 파라미터 이름 작성
3. 화살표 ( ⇒ )를 붙인다.
4. {}를 작성하고 블록 안에 함수가 실행할 코드 작성

매개변수가 하나일 경우 ()를 생략할 수 있음 (하나도 없으면 생략할 수 없음)

실행 문장이 하나일 경우 {}을 생략할 수 있음

실행되는 하나의 문장이 return문일 경우 return 키워드를 생략해야 한다


</div>
</details>


<h1>DOM</h1>
<details>
<summary>DOM</summary> 
<div markdown="1">



# DOM

Document Object Model

## window 제공 함수

- alert
- confirm
    - 확인, 취소
- prompt
    - 입력
- open
- parseInt, parseFloat
- setTimeout, clearTimeout
- setInterval, clearInterval

XML, HTML 문서의 각 항목을 계층으로 표현하여 생성, 변형, 삭제할 수 있도록 돕는 인터페이스

DOM=문서 요소 집합을 트리 형태의 계층 구조로 HTML 표현

HTML 문서의 요소를 제어하기 위해 지원

상단의 document 노드를 통해 접근 가능

```jsx
document.body.style.background = "red";
setTimeout(() => (document.body.style.background = ""), 3000); 
// 3초 후 원상태로 복구하기
```

## 문서 접근 방식 이해

- getElementById(string) // 1개만 (id는 유일함)
- querySelector(css selector) // 1개만
- querySelectorAll(css selector) // 몽땅

### getElementById

```jsx
var ele = document.getElementById("a");
// 태그를 그대로 가져옴
ele.style.color = "green";

```

### querySelector (css selector)

```jsx
var ele = document.querySelector("#a");
// css selector라서 #a로 가져와야 함
// id는 id로 한정되어 있어서 # 없어도 ok
```

```jsx
var ele = document.querySelector("div");
// 여러개면 위에서부터 한 개만 가져옴
```

- “#—”: id
- “.—”: class
- “[name=’c’]: 속성 선택자

### querySelectorAll

quereSelector와 동일하지만 해당하는 모두를 가져옴

를 배열처럼 사용

## 문서 조작 방식

### createElement(name), append(string | node)

엘리먼트 생성

```jsx
var ele = document.createElement("img");
// <img> 태그 생성
```

추가할 기존 엘리먼트 접근

```jsx
var parent = document.getElementById("list");
// <div id ="list">
```

엘리먼트 추가

```jsx
parent.append(ele);
// <div id="list>
//		<img>
// </div
```

### setAttribute(name, value)

속성을 세팅

```jsx
var ele = document.createElement("img");
ele.setAttribute("src", "./images/cake.jpg")
ele.setAttribute("width", 200);
```

- 사용자 정의 속성은 접근할 수 없음

```jsx
ele.msg="test" // 불가능
```

### innerHTML을 이용한 요소내용 변경

조작할 엘리먼트 접근

```jsx
var list = document.getElementById("list");
list.innerHTML="<img src='./images/cake.jpg' width='200' height='150'/>";
```

# 이벤트

웹 페이지에서 여러 종류의 상호작용이 있을 때마다 이벤트가 발생

JavaScript를 사용하여 DOM에서 발생하는 이벤트를 감지하고 대응하는 작업을 수행할 수 있음

## 이벤트 처리 방식

### 고전 이벤트 처리 방식

- 인라인 이벤트 설정 → 엘리먼트에 직접 지정
- 설정하려는 이벤트를 정하고 on이벤트종류 의 형식으로 지정

```html
<button onclick="alert('누르지마세요')">누르지마시오.</button>
<button onclick="doSometing()">함수 실행</button>
<button id="btn">버튼</button>
<script>
    function doSometing () {
        let sum = 0
        for(let i = 0 ; i < 10; i++){
            sum += i;
        }
        console.log(sum)
    }
    let btn = document.querySelector("#btn")
    btn.onclick = doAction;
    bton.onclick = doAction(); // 바로 수행해버림

    function doAction(){
        alert("경고")
    }
</script>
```

- 엘리먼트에서 이벤트를 직접 설정하지 않고 스크립트에서 이벤트 설정
- 이벤트 요소.addEventListener(이벤트 타입, 이벤트리스너, [option]);
    - 이벤트 타입이 발생하면 이벤트리스너(액션) 발생

preventDefault()

- 어떤 이벤트를 명시적으로 처리하지 않은 경우, 해당 이벤트에 대한 [사용자 에이전트](https://developer.mozilla.org/ko/docs/Glossary/User_agent)
의 기본 동작을 실행하지 않도록 지정

```jsx
let a = document.querySelector("a")
    a.addEventListener("click", function (event){
      event.preventDefault() // 링크로 이동x
      console.log(event)
    })
```

</div>
</details>

<h1>Web Storage</h1>
<details>
<summary>Web Storage</summary> 
<div markdown="1">


# Web Storage

브라우저 내에 저장

모바일에서도 동작

## 로컬 스토리지

값은 반드시 문자열로 저장

로컬 스토리지: 브라우저가 종료되어도 데이터는 살아있다.

세션 스토리지: 브라우저가 종료되면 데이터가 사라짐

</div>
</details>

<h1>AJAX</h1>
<details>
<summary>AJAX</summary> 
<div markdown="1">

# AJAX

Asynchronous JavaScript and SML

비동기 방식의 자바스크립트 XML

직관적이고 자연스러운 사용자 상호액션 방식

기존 클릭이 필요하지 않음

화면의 일부분만 변경

기존 → 서버 요청, 대기, 전체화면 새로고침

AJAX → 업데이트가 필요한 부분만 변경

## 동작 방식

classic model: 서버에 요청한 데이터가 도착할 때까지 클라이언트는 대기(동기적 통신)

AJAX: 서버에 요청한 데이터가 도착할 동안 클라이언트는 멈추지 않고 동작 (비동기적 통신)

## XMLHttpRequest

자바스크립트 객체

대부분의 브라우저에서 지원

표준 HTTP 방식(GET/POST)으로 서버와 통신

<b> GET방식과 POST 방식 </b>
<details>
<summary>GET방식과 POST 방식</summary> 
<div markdown="1">

[https://cocoon1787.tistory.com/526]
### HTTP
- 웹상에서 클라이언트와 서버 간에 데이터를 주고받을 수 있는 프로토콜
- 클라이언트가 HTTP 프로토콜을 통해 서버에 요청을 보내면 서버는 요청에 맞는 응답을 클라이언트에게 전송

### GET 방식
클라이언트가 서버로 데이터를 요청하기 위해 사용되는 Method

ex. http://localhost:3000/login?id=admin&pw=1234

Body부분은 비어 있고 Content-Type 헤더 필드(헤더에 Body의 콘텐츠 타입 명시) 적지 않음

URL 뒤에 쿼리 스트링을 붙이고, HTTP 패킷의 헤더에 포함해서 서버에 데이터 요청



- 특징

간단한 데이터 요청할 때 적합(게시판의 게시물, 목록 조회)

캐싱이 가능 - 속도가 빠르다

POST보다 상대적으로 속도가 빠름

브라우저 히스토리에 기록이 남음

?뒤에서부터 데이터를 표현: 보안에 취약

### POST 방식

클라이언트가 서버로 데이터를 전송해 리소스를 추가하거나 생성하기 위해 사용되는 Method



- 특징

Body에 담아 데이터를 전송

요청 헤더의 Content-Type에 콘텐츠 타입 명시

GET 방식보다 보안에 좋음 (데이터가 URL에 노출x)

서버로 보내는 데이터의 양 제한X

캐싱 불가

요청받는 시간 제한 존재

브라우저 히스토리에 기록이 남지 않음


</div>
</details>




서버와 통신시 비동기적으로 작업

백그라운드에서 작업

### Methods

- open (”HTTP method”, “URL”, sync/async)
    - 요청의 초기화 작업
    - GET / POST 지정 (HTTP method)
        - GET: 주소 URL, 길이의 제한
        - POST: 노출x. 길이 제한x
    - 서버 URL 지정
    - 동기 / 비동기 설정
- send(content)
    - GET 방식은 URL에 필요 정보를 추가하기 때문에 null 적용
    - POST 방식에서 파라미터 설정 처리
- onreadystatechange
    - 서버에서 응답이 도착했을 때 호출될 콜백함수 지정
    - 콜백함수는 상태가 변경될 때 마다 호출
- readystate
    - 0~4
- status
    - 서버 처리 결과 상태 코드
    - 200 → OK 요청 성고
    - 404 → Not Found (페이지를 못 찾을 경우)
    - 500 → Sercer Error (서버에서 결과 생성시 오류 발생)

**0804**

1. Live server에 요청: GET 요청
2. Ajax 버튼을 클릭 → 서버에 요청(request) → 서버에서 처리 (response)


```jsx
<script>
    let xhr; //껍데기 준비

    //1. 클라이언트에서 요청이 발생해야한다.
    document.querySelector("#get-data").addEventListener("click", () => {
      // AJAX 요청과 응답 처리를 진행할 XMLHttpRequest 객체 생성자 함수 호출
      xhr = new XMLHttpRequest();
      // xhr의 상태가 바뀔 때 마다 호출할 콜백함수를 등록하겠다.
      xhr.onreadystatechange = responseMsg;

      //서버에 요청을 보내기
      //open("요청방식", "URL(어디다가 어떤 요청을 보낼건지)", ["비동기방식의 여부"])
      xhr.open("GET", "./data/hello.txt", true)

      //요청보내기
      xhr.send()

    });

    // AJAX 요청에 대한 응답이 왔을때 사용할 콜백함수 
    function responseMsg() {
      //서버의 응답이 완벽하게 끝났을때
      if (xhr.readyState == 4) {
        //서버에서 오류가 없이 정상적으로 처리가 됬을때
        if (xhr.status == 200) {
          // console.log(xhr)
          document.querySelector("#msg-view").innerHTML = xhr.responseText;
        }
        else {
          console.log("정상적으로 데이터를 수신하지 못했다.")
        }
      }
    }

  </script>
```

`xhr.readystate` 가 1~4까지 바뀜, 4가 되었을 때 동작

콜백: 이 이벤트가 일어날 때마다 호출

**0830**

### 참고

### <참고> 자바스크립트=싱글 스레드

Call Stack: 이벤트 처리

즉시 처리하지 못하는 이벤트들을 Web API로 보내서 처리

처리된 이벤트들은 처리된 순서대로 Task Queue에 저장

Call Stack이 공백이 되면 Event Loop가 대기 줄에서 가장 오래된 이벤트를 Call Stack으로 보냄

### 순차적인 비동기 처리학

Web API로 들어오는 순서x, 어떤 이벤트가 먼저 처리되느냐가 중요

1. Async Callbacks
    
    백그라운드에서 실행을 시작할 함수를 호출할 때 인자로 지정
    
2. Promis-Style
    
    Modern Web APIs에서의 새로운 코드 스타일
    
    XMLHttpRequest 객체를 사용하는 구조보다 조금 더 현대적인 버전
    

```jsx

    const URL = 'https://jsonplaceholder.typicode.com/todos/1'

    //AJAX 통신

    const xhr = new XMLHttpRequest();

    xhr.open('GET', URL);
    xhr.send(); //요청 보내기

    const todo = xhr.response; //싱글스레드여서 AJAX 요청을 보내놓고 응답을 기다리지 않음
		console.log(todo); // 아무것도 찍히지 않음

```

- 콜백함수 정하기

/code

```jsx
		const URL = 'https://jsonplaceholder.typicode.com/todos/1'

    //AJAX 통신을 하겠다.

    const xhr = new XMLHttpRequest();

    xhr.open('GET', URL);
    xhr.send(); //요청 보내기

	// xhr.onreadystatechange = function() {
    //   if(xhr.readyState == xhr.DONE){
    //     if(xhr.status == 200 ){
    //       const todo = xhr.response
    //       console.log(todo)
    //     }
    //   }
    // }

    xhr.onload = function () {
      if (xhr.status == 200) {
        const todo = xhr.response
        console.log(todo)
      }
    }
```

</div>
</details>

<h1>CallBack 함수와 Promise</h1>

[https://bigtop.tistory.com/35]

[https://joshua1988.github.io/web-development/javascript/javascript-asynchronous-operation/]

<details>
<summary>비동기 처리</summary> 
<div markdown="1">

## 비동기 처리

특정 코드의 연산이 끝날 때까지 코드의 실행을 멈추지 않고 다음 코드를 먼저 실행

```jsx
function asyncTest(duration) {
  setTimeout(() => {
    // 이 작업을
    console.log(duration)
  }, duration); // duration 경과하면 수행
}

asyncTest(2000);
asyncTest(1000);

//1000
//2000
```

setTimeout 함수는 duration이 경과하면 작업을 수행하는 함수이다.

asyncTest의 2000이 먼저 수행되었지만, 비동기적인 자바 스크립트에서는 함수 두개가 동시에 실행되기 때문에 1000이 먼저 완료되어 console에 1000, 2000 순서로 출력된다.


</div>
</details>

<details>
<summary>Callback 함수</summary> 
<div markdown="1">

## Callback 함수

콜백 함수 = 다른 함수에 매개변수로 넘겨준 함수

매개변수로 넘겨받은 함수는 나중에 호출(call back)한다.

비동기 처리 방식의 문제를 해결할 수 있음

매개변수로 넘겨받은 함수를 특정 작업이 완료된 후에 실행할 수 있다.

- 예시

callBackTest의 매개변수 `callBack`과 `errorCallBack`을 함수로 정의함

`check`가 `true`일 경우 `callBack` 함수 호출, `false`일 경우 `errorCallBack` 함수 호출

```jsx
function callBackTest(check, callBack, errorCallBack) {
  if (check===true) {
    callBack("true");
  } else {
    errorCallBack("error")
  }
}

callBackTest(false, 
  //callBack
  (msg) => {
    console.log(`callback: ${msg}`);
  }, (error) => {
    console.log(`error: ${error}`);
  })
```

</div>
</details>


<details>
<summary>Promise</summary> 
<div markdown="1">


## Promise


자바스크립트 비동기 처리에 사용되는 객체

비동기 작업을 마치 **동기 작업**처럼 값을 반환해서 사용하는 형태

미래의 완료 또는 실패와 그 결과 값을 나타냄

```jsx
new Promise((resolve, reject)=>{
  if (조건) {
		resolve();
	} else if (조건) {
		reject();
	}
});
```

Promise 객체를 만들어서 사용할 수 있다.

## Method

어떤 작업이 성공할 경우 `resolve()`를, 실패할 경우 `reject()`를 실행하도록 만든다.

```jsx
Promise
	.then((msg) => {
})
	.catch((error) => {
})
  .finally(() => {
})
```

`then`은 resolve가 실행되었을 때, 즉 이행 상태일 때 실행되는 함수이다.

`catch`는 reject가 실행되었을 때, 즉 실패 상태일 때 실행되는 함수이다.

`finally`는 결과와 상관없이 무조건 실행되는 함수이다.

### 콜백 함수를 Promise로 바꾸기

```jsx
function PromiseTest(check) {
  return new Promise((resolve, reject)=>{
    if (check===true) {
      resolve("true");
    } else {
      reject("false");
    }
  })
}

PromiseTest(true)
.then((msg) => {
  console.log(`resolved: ${msg}`)
})
.catch((msg)=> {
  console.log(`rejected: ${msg}`)
})

// then 실행
// resolved: true
```

위 함수는 Promise 객체를 반환하는 함수이다.

`check`가 `true`일 경우 fulfill(resolve), 아닌 경우에는 reject 한다.

fulfill일 경우 `then()`에서 resolved와 msg를 출력하고, reject일 경우 `catch()`에서 rejected와 msg를 출력한다.


## axios

브라우저, node.js에서 사용할 수 있는 Promise 기반 HTTP 클라이언트 라이버릴

Vue에서 권고

### 특징

- 브라우저를 위해 XMLHttpRequests 생성
- node.js를 위해 http 요청 생성
- Promise API 지원
- 요청 및 응답 인터셉트
- 요청 및 응답 데이터 변환
- 요청 취소
- JSON 데이터 자동 변환
- XSRF를 막기 위한 클라이언트 사이드 지원

</div>
</details>
