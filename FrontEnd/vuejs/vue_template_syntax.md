# Vue Template Syntax

## Template Syntax

Vue.js는 렌더링된 DOM을 기본 Vue 인스턴스의 데이터에 선언적으로 바인딩할 수 있는 HTML 기반 템플릿 구문 사용


### MVVM

- Model : JS Object

- Vew: DOM

- View Model: 뷰 인스턴스 → 인스턴스 생성

### 보간법 Interpolation

보간: 변수에 할당된 값이 문자열에 들어가 대체되어 출력

Text(문자열)

데이터 바인딩의 가장 기본 형태 “Mustache” 구문: (이중 중괄호)을 사용 -> 텍스트 보관

Mustache 태그는 데이터 객체의 msg 속성 값으로 대체 (해당 값이 변경되면 갱신)


### Interpolation (보간법) - Raw HTML

이중 중괄호는 일반 텍스트로 해석

실제 HTML을 출력하기 위해 `v-html` 디렉티브 사용

**신뢰할 수 있는 콘텐츠에서만 HTML 보간을 사용할 것

```jsx
    <p>{{msg}}</p>
    <!-- v-html-->
    <p v-html="msg"></p>
```

`v-text`: directive, 일반적으로 텍스트를 쓰면서도 변화가 일어나게 하는 directive

### JavaScript 표현식 사용

Vue.js는 모든 데이터 바인딩 내에서 JavaScript 표현식의 모든 기능을 지원

제한사항: 조건문은 작동하지 않음, 구문(ex. var a = 1)은 작동하지 않음

각 바인딩에 하나의 단일표현식만 포함될 수 있음

---

## Directive(디렉티브)

v-접두사가 있는 특수 속성

속성값은 단일 JavaScript 표현식이 됨 (v-for 예외)

역할: 표현식의 값이 변경될 때 사이드 이펙트를 **반응적으로 DOM에 적용**

### v-once

v-once 디렉티브를 사용하여 데이터 변경 시 업데이트되지 않는 일회성 보간을 수행

```jsx
<span v-once> 변경하지 않는다 {{ msg }}</span>
```

F12에서 변경해도 `v-once` 태그는 변하지 않음

### v-text

엘리먼트의 textContent를 업데이트

일부를 갱신한다면 {{ }}를 사용

일부 갱신? ex. {{name}} 님 안녕하세요

### v-bind

HTML 요소의 속성에 Vue 상태 데이터를 값으로 할당

→ 태그의 속성을 **동적으로 변경**

Object 형태로 사용하면 value가 true인 key가 class 바인딩 값으로 할당

약어 제공: v-bind:href ===`:href`

```jsx
<body>
  <div id="app">
    <div v-bind:id="idValue">v-bind</div>
  </div>

  <script>
    const app = new Vue({
      el: '#app',
      data() {
        return {
          idValue: 'test-id',
        };
      },
    });

  </script>
```

→ v-bind 연습의 태그는 test-id가 됨


```jsx
<div id="app">

    <button v-bind:[key]="btnId">버튼</button>

  <script>
    const app = new Vue({
      el: '#app',
      data() {
        return {
          key: 'class',
          btnId:'test-btn',
        };
      },
    });
```
key, btnId가 바인딩됨

```html
<button class="test-btn">버튼</button>
```

### v-model

cf. v-bind: **단방향 바인딩**(한쪽에서만 변화시킬 수 있음)

HTML form 요소의 input 엘리먼트 또는 컴포넌트에 **양방향 바인딩** 처리

ex. input 태그에서 input 값을 변경 <-> F12 Vue 탭에서 값 변경

서로 동기화

form 엘리먼트 초기 ‘value’와 ‘checked’, ‘selected’ 속성을 무시함

```jsx
<input type="text" value="초기값" v-model="msg"/> // 초기값 무시됨
```

### v-show

조건에 따라 엘리먼트를 화면에 표시

**항상 렌더링**되고 DOM(HTML)에 남아있음

단순히 엘리먼트에 display CSS 속성을 토글하는 것 (`display:none`)


```jsx
<div id="app">
    <h1 v-show="ok">Hello</h1>
  </div>
```

```jsx
<h1 style="display: none;">Hello</h1> // ok가 false일 때
```

눈에 보이지는 않지만 태그 자체가 남아 있음 → 렌더링은 되어 있다


### v-if, v-else-if, v-else

표현식 값의 참 거짓을 기반으로 엘리먼트를 조건부 렌더링

엘리먼트 및 포함된 디렉티브/컴포넌트는 토글하는동안 삭제되고 다시 작성됨


```jsx
<div id="app">
   
    <div>입력 : <input type="number" v-model="num" /></div>
    <div>
      v-if test :
      <span v-if="num < 10">1</span>
      <span v-else-if="num < 20">2</span>
      <span v-else-if="num < 30">3</span>
      <span v-else>4</span>
    </div>
  </div>

```

태그가 num 값에 따라 지워지고 나타나는 것이 반복됨

show: 항상 렌더링 → display:none 여부만 바뀜

if : **조건부 렌더링** → 조건에 따라 태그가 아예 사라지고 등장


### v-for

소스 데이터를 기반으로 요소 또는 템플릿 블록을 여러 번 렌더링


디렉티브의 값은 반복되는 현재 엘리먼트에 대한 별칭을 제공하기 위해 alias in expression 사용

또는, 인덱스(아니면 객체의 경우 키)의 별칭 사용 가능



```jsx
  <span v-for="i in 10">{{ i }}</span>
```

```jsx
		<ul>
          <li v-for="name in names">{{name}}</li>
    </ul>
```

```jsx
    <li v-for="(name, index) in names">{{index+1}}:{{name}}</li>

```

(item 별칭, index) 순서

```jsx
    <li v-for="human in humans">{{human.name}} : {{human.age}}세</li>
```

```jsx
<div v-for="(val,name,index) in object"></div>
```

val = **value**, name : **key**, index: **index**

v-for는 v-if보다 높은 우선순위를 가짐

### v-on

엘리먼트에 이벤트 리스너를 연결

이벤트 유형은 전달인자로 표시

약어 @

- v-on:click === @click

```jsx
<div id="app">
    <h2>{{count}}</h2>
    <button v-on:click="count+=1"> 증가</button>
    <button v-on:click="plus()">증가2</button>
    <button @click="plus">누르면 증가3</button>
  </div>
```

### v-cloak

Vue Instance가 준비될 때까지 Mustache 바인딩을 숨기는 데 사용

[v-cloak] { display : none } 과 같은 CSS 규칙과 함게 사용

Vue Instance가 준비되면 v-cloak는 제거됨

ex. 시간 제한이나 조건을 걸고 값이 들어올 때까지 null 값을 숨길 수 있음
