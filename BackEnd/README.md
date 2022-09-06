# BackEnd


# Servlet & JSP

<details>
<summary>Servlet</summary>
<div markdown="1">

# Servlet

<h2> 참고</h2>

URL(Uniform Resource Locator) 웹 상의 자원을 참조하기 위한 웹 주소

웹 페이지: 웹 브라우저를 통해 보여지는 화면

웹 서버: 클라이언트 요청에 맞는 응답(웹 페이지)를 제공

웹 어플리케이션: 웹 서버를 기반으로 실행되는 응용 소프트웨어

웹 어플리케이션 서버(WAS): 요청이 오면 알맞은 프로그램을 실행하여 응답 만들고 제공하는 서버

- URL

[http://localhost:8080/Back01_Hello/HelloServlet](http://localhost:8080/Back01_Hello/HelloServlet)

localhost=내 컴퓨터 주소

8080=포트 번호

Back01_Hello: context path

HelloServlet: 들어온 요청

## Servlet

자바를 사용하여 웹페이지를 동적으로 생성하는 서버측 프로그램

웹 서버의 성능을 향상하기 위해 사용되는 자바 클래스의 일종

웹 기반 응용 프로그램을 구축하기 위한 구성 요소 기반의 플랫폼 독립적 방법을 제공

대화형 웹 응용 프로그램을 구축하는 데 널리 사용

**JSP와 비슷하지만, JSP는 HTML 문서 안에 Java 코드를 포함, 서블릿은 자바 코드 안에 HTML을 포함 → 서블릿은 .java 파일**

`@WebServlet("/HelloServlet")` HelloServlet이라는 요청이 들어오면 해당 클래스를 실행하게 만든다.

없으면 url이 mapping이 되지 않음

### 서블릿 생명주기

서블릿 인스턴스: 서블릿이 포함된 웹컨테이너에 의해 제어됨

서블릿 인스턴스가 존재하지 않으면 다음과 같은 작업을 수행

1. 서블릿 클래스 로드
2. 서블릿 클래스 인스턴스 생성
3. 서블릿 인스턴스 초기화
4. 웹 컨테이너에 의한 서비스 메서드 호출
5. destroy 메서드를 호출하여 서블릿 종료

서비스 메서드는 요청이 들어올 때마다 호출됨

### Servlet 작성

HttpServlet을 상속하는 클래스는 다음 중 적어도 하나의 메서드를 재정의해야 한다

`doGet()` - HTTP GET 요청 처리시 작성

`doPost()` - HTTP POST 요청 처리시 작성

```java
public class LifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	int initCnt = 1;
	int doGetCnt = 1;
	int destroyCnt = 1;

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메소드 호출: " + initCnt++);
	}

	@Override
	public void destroy() {
		System.out.println("destroy 메소드 호출: " + destroyCnt++);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doget 메소드 호출: " + doGetCnt++);
	}

}
```

`initCnt` = 처음 시작될 때

`doGetCnt` = 새로고침, 호출할 때

`destroy` = 서버 종료


## GET방식과 POST 방식

  
[https://cocoon1787.tistory.com/526](https://cocoon1787.tistory.com/526%5D)

[https://mommoo.tistory.com/60](https://mommoo.tistory.com/60)

### HTTP

  
웹상에서 클라이언트와 서버 간에 데이터를 주고받을 수 있는 프로토콜  
클라이언트가 HTTP 프로토콜을 통해 서버에 요청을 보내면 서버는 요청에 맞는 응답을 클라이언트에게 전송

###   GET 방식

클라이언트가 서버로 데이터를 요청하기 위해 사용되는 Method

ex. [http://localhost:3000/login?id=admin&pw=1234](http://localhost:3000/login?id=admin&pw=1234)

Body부분은 비어 있고 Content-Type 헤더 필드(헤더에 Body의 콘텐츠 타입 명시) 적지 않음

URL 뒤에 쿼리 스트링을 붙이고, HTTP 패킷의 헤더에 포함해서 서버에 데이터 요청

-   주소에 데이터 노출 (변수=값)

#### 특징

-   간단한 데이터 요청할 때 적합(게시판의 게시물, 목록 조회)
-   캐싱이 가능 - 속도가 빠르다
-   POST보다 상대적으로 속도가 빠름
-   브라우저 히스토리에 기록이 남음
-   ?뒤에서부터 데이터를 표현: 보안에 취약

### POST 방식

클라이언트가 서버로 데이터를 전송해 리소스를 추가하거나 생성하기 위해 사용되는 Method

#### 특징

-   Body에 담아 데이터를 전송
-   요청 헤더의 Content-Type에 콘텐츠 타입 명시
    -   application/x-www-form-urlencoded : key-value 쌍, 구분자=& (GET 방식과 유사)
    -   text/plain: 단순 text
    -   multipart/form-data: 주로 파일 전송, 바이너리 데이터 
-   GET 방식보다 보안에 좋음 (데이터가 URL에 노출x)
-   서버로 보내는 데이터의 양 제한X
-   캐싱 불가
-   요청받는 시간 제한 존재
-   브라우저 히스토리에 기록이 남지 않음
-   서버와 통신시 비동기적으로 작업
-   백그라운드에서 작업

### GET과 POST 비교

|   | GET | POST |
| --- | --- | --- |
| 주소에 데이터 노출 | O | X |
| 데이터 전송 방식 | URL을 통해 | Body에 데이터를 담음 |
| 요청 헤더 | Content-Type 명시 X | Content-Type 명시 O |
| 보안 | 상대적으로 취약 | 상대적으로 보안에 좋음 |
| 캐싱 | 가능 | 불가 |
| 브라우저 히스토리에 기록 | O | X |
| 데이터의 양 | 제한O | 제한X |


### *참고 URL 구성요소

https://www.google.com/search?q=ssafy

https - 프로토콜 (s=secure)

[www.google.com](http://www.google.com) - url

search - 경로

q=ssafy - Query String

</div>
</details>

<details>
<summary>JSP</summary> 
<div markdown="1">

# JSP

Servlet 표준을 기반으로 작성된 웹 어플리케이션 개발 언어

요청을 처리하고 응답을 구성하는 방법을 작성

정적 요소(HTML, XML 등)와 동적 요소(JSP 객체)를 모두 포함하는 텍스트 기반 문서 (JSP 페이지)를 개발하기 위한 언어

서버측 객체에 접근하기 위한 표현 언어

**HTML 코드 안에 자바 코드를 삽입할 수 있음**


## JSP 구성 요소

- 지시자(Directive): JSP 페이지에 대한 설정 정보를 지정
- 스크립트 요소: 스크립트릿(Scriptlet), 표현식(Expression), 선언부(Declaratiohn)
    
    JSP에서 문서의 내용을 동적으로 생성하기 위해서 사용
    
- JSP 기본객체: 요청 및 응답 관련 정보를 얻거나, 응답 결과를 만들기 위해서 사용
- 표현언어 (Expression Language): JSP를 좀 더 간결하게 작성하기 위해 사용
- Action Tag와 JSTL: 자주 사용하는 기능을 모아 미리 정의하여 Tag 형태로 작성
    
    JSP에서 자바 코드를 쉽게 작성할 수 있도록
    

### 지시자

`<%@ ~~ %>`

JSP에 대한 설정 정보 또는 JSP 페이지에 다른 문서를 포함할 때 사용

웹 컨테이너(톰캣)가 JSP를 번역하고 실행하는 방법을 서술

- page, include, taglib와 같은 디렉티브
- page 지시자
    - JSP 페이지 실행 매개변수를 제어
    - 출력 처리, 오류 처리등의 내용을 포함
    - 주요 속성: language, contentType, import, session, pageEncoding, errorPage, autoFlush 등
- include 지시자

반복적으로 사용되는 부분(header, footer)을 별도로 작성: 페이지 내에 삽입하는 반복되는 코드의 재작성을 줄일 수 있음

### 스크립트 요소

JSP 페이지 내에서 프로그래밍에서 로직을 수행하는 데 사용

- 선언부(Declaration)

멤버변수 선언이나 메서드를 선언하는 영역

형식: `<%! 스크립팅 언어 선언 %>`

`!`가 없으면 서비스라는 메서드 안에 새로운 메서드를 정의하는 것이어서 불가능

ex

```java
<%! 
	int global = 1;
	int num1 = 10, num2 = 20;

	public int add(int a, int b) {
		return a+b;
	}
%>
```

<%! = 클래스 영역

<% = 메서드 영역

→ `!`를 붙여서 선언부임을 표시해야 함

- 스크립트릿(Scriptlet)

스크립트 언어로 작성된 코드 조각을 포함하는 데 사용, 자바 코드 넣는 곳

형식

```java
<%
	scripting=language-statements
%>
```

[참고]

```java
<!-- HTML 주석 -->
<%-- JSP 주석, F12에서 출력되지 않음 --%>
```

- 표현식

문자열로 변환된 값을 출력결과에 포함시킬 때 사용

형식 `<%= scripting-language-expression %>`

**세미콜론을 쓰지 않음** → 해당 값이 파라미터로 쓰이기 때문에 세미콜론을 쓰면 안됨

```java
<%= request.getParameter("name")%>	
```

### JSP 기본 객체 영역

- Page: 하나의 **페이지 정보**를 담고 있는 영역, 페이지가 바뀌면 새로운 객체가 생성됨
- Request: 하나의 **요청을 처리**할 때 사용되는 영역, 응답이 완료되면 사라진다
- session: 하나의 **웹 브라우저**와 관련된 영역, 로그인 정보 등을 저장한다.
- application : **웹 어플리케이션**이 시작되면 종료될 때까지 유지됨

**메서드**

`setAttribute(String name, Object value)` - key-value 형태로 각 영역에 데이터를 저장

`getAttributes(String name)` - 현재 객체에서 인자로 받은 이름으로 설정된 값을 반환

### JSP 페이지 이동

요청을 받아서 화면을 변경하는 방법

- **포워드 방식**

요청이 들어오면 요청을 받은 JSP 또는 Servlet이 직접 응답을 작성하지 않고 **요청을 서버 내부에서 전달**하여 해당 요청을 처리하게 하는 방식

```java
RequestDispatcher dispatcher  = request.getRequestDispatcher("이동할 페이지");
dispatcher.forward(request, response);
```

request, response 객체가 전달되어 사용되기 때문에 **객체가 사라지지 않는다.**

브라우저에는 **최초 요청된 주소**가 표시

- **리다이렉트 방식**

요청이 들어오면 내부 로직 실행 후, 브라우저의 URL을 변경하도록 하여 새로운 요청을 생성함으로써 페이지를 이동

```java
response.sendRedirect("location");
```

브라우저가 새로운 요청을 만들어 내기 때문에 최초 요청 주소와 다른 요청주소가 화면에 보인다. **(URL이 바뀐다)**

regist → main → reuslt

`sendRedirect`: 요청을 지워버림! (**request, response가 사라진다)** → session에 담아두거나 다른 방식 사용

---

**Web-INF** 안에 있는 jsp는 주소로 접근x

```java
Origin 서버가 대상 리소스를 위한 현재의 representation을 찾지 못했거나, 그것이 존재하는지를 밝히려 하지 않습니다.
```

외부에서 접근할 수 없게 막아놓은 비공개 폴더

```java
request.getRequestDispatcher("/WEB-INF/list.jsp").forward(request, response)
```

내부에서는 접근할 수 있음

---


</div>
</details>