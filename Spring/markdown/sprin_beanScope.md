# 9. 빈 스코프

## 빈 스코프란?

스프링 빈은 스프링 컨테이너의 시작과 함께 생성되어 스프링 컨테이너가 종료될 때까지 유지

스프링 빈이 **싱글톤 스코프**로 생성되기 때문

스프링은 다음과 같은 스코프 지원

**“싱글톤**”: 기본 스코프, 스크링 컨테이너의 시작과 종료까지 유지되는 가장 넓은 범위의 스코프

**“프로토타입”**: 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입까지만 관여하고 더는 관리하지 않는 *매우 짧은 범위의 스코프*

**“웹 관련 스코프”**

- request: 웹 요청이 들어오고 나갈 때 유지되는 스코프
- session: 웹 세션이 생성되고 종료될 때까지 유지되는 스코프
- application: 웹의 서블릿 컨텍스트와 같은 범위로 유지되는 스코프

## 프로토타입 스코프

싱글톤 스코프와 달리 스프링 컨테이너에 조회하면 항상 새로운 인스턴스를 생성해 반환

**스프링 컨테이너는 프로토타입 빈을 생성하고 의존관계 주입, 초기화까지만 처리**

스프링 컨테이너는 클라이언트에 빈을 반환한 후 프로토타입 빈을 관리하지 않음

빈을 관리할 책임은 클라이언트에 있다

**그래서 @PreDestroy 같은 종료 메서드가 호출되지 않는다**

- `@PreDestroy` 는 컨테이너에서 객체를 제거하기 전에 실행 → 빈을 생성하고 초기화까지만 관리하니까, 빈을 반환한 후에는 관심x

```java
		void prototypeBenaFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find prototypeBean1");
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);
        assertThat(bean1).isNotSameAs(bean2);
				ac.close();

    }
```

```java
find prototypeBean1
PrototypeBean.init
find prototypeBean2
PrototypeBean.init
bean1 = hello.core.scope.PrototypeTest$PrototypeBean@293bb8a5
bean2 = hello.core.scope.PrototypeTest$PrototypeBean@2416a51
```

getBean이 실행될 때마다 PrototypeBean 클래스가 시작됨 

**destroy 실행안됨** → 스프링 컨테이너가 생성, 의존관계 주입, 초기화까지만 관여

---

## 프로토타입 스코프 - 싱글톤 빈과 함께 사용시 문제점

`clientBean`: 싱글톤

`PrototypeBean`: 프로토타입 빈 - `clientBean`의 의존관계 자동 주입에 의해 프로토타입 빈이 생성

clientBean이 프로토타입 빈을 내부 필드에 보관

클라이언트 A가 로직 호출 → prorotypeBean count=1

클라이언트 B가 로직 호출 → prototypBean count = 2

**clientBean이 내부에 가지고 있는 프로토타입 빈은 이미 과거에 주입이 끝난 빈 → 사용할 때마다 새로 생성되지 않음**

만약 호출할 때마다 새로 프로토타입 빈을 생성하고 싶으면?

현재의 방식은 주입 시점에만 새로 생성, 사용할 때마다 새로 생성하는 걸 원함

참고: 여러 빈에서 같은 프로토타입 빈을 주입 받으면, 주입 받는 시점에 각각 새로운 프로토타입 빈이 생성됨

→ ex. ClientBean1, ClientBean2

---

## Provider로 문제 해결

의존관계를 외부에서 주입받지 않고 직접 필요한 의존관계를 찾는 것 → **DL (Dependency Lookup)** 의존관계 조회라고 한다

```java
@Autowired
private ObjectProvider<PrototypeBean> prototypeBeanProvider;
```

ObjectFactory, ObjectProvider: 

항상 새로운 프로토타입 빈이 생성됨

ObjectProvider의 getObject(): **스프링 컨테이너를 통해 해당 빈을 찾아서 반환**

**Provider의 기능**

빈을 찾아오는 것!!

### JSR-330 Provider

gradle에 라이브러리 추가

`implementation 'javax.inject:javax.inject:1'`

provider.get() 을 통해 새로운 프로토타입 빈이 생성

자바 표준, 기능이 단순

별도의 라이브러리 필요

스프링이 아닌 다른 컨테이너에서도 사용 가능

---

## 웹 스코프

웹 환경에서만 동작

프로토타입과 다르게 스프링이 해당 스코프의 종료시점까지 관리 → 종료 메서드가 호출됨

**웹 스코프 종류**

- requests: HTTP 요청 하나가 들어오고 나갈 때까지 유지되는 스코프, 각 HTTP 요청마다 별도의 빈 인스턴스가 생성되고 관리
- session: HTTP Session과 동일한 생명주기를 가지는 스코프
- application: 서블릿 컨텍스트(ServletContext)와 동일한 생명주기를 가지는 스코프
- websocket: 웹 소켓과 동일한 생명주기를 가지는 스코프

## request 스코프 예제 만들기

build.gradle에 web 라이브러리 추가

```java
No thread-bound request found: Are you referring to request attributes outside of an actual web request, or processing a request outside of the originally receiving thread? If you are actually operating within a web request and still receive this message, your code is probably running outside of DispatcherServlet: In this case, use RequestContextListener or RequestContextFilter to expose the current request.


Error creating bean with name 'myLogger': Scope 'request' is not active for the
current thread; consider defining a scoped proxy for this bean if you intend to
refer to it from a singleton;
```

logger는 http request가 생성되고 소멸될 때까지가 생명주기, 스프링 어플리케이션이 실행되어도 request 스코프 빈은 생성되지 않았기 때문에(아직 http request가 들어오지 않음) 스프링 컨테이너에서 찾을 수 없음

해결책: provider

나는 왜 여기서 provider를 쓰면 가능한지 이해가 잘 안 됐다.. 그래서 질문을 찾아봤다

**provider?** 

[https://www.inflearn.com/questions/179312](https://www.inflearn.com/questions/179312)

ObjectProvider는 프로토타입을 생성해주는 것이 아니라, **빈을 찾아오는 역할**

따라서 해당 시점에 `applicationContext.getBean("myLogger")`을 해서 빈을 찾아온다

만약 찾은 빈이 싱글톤 스코프라면 기존에 싱글톤으로 생성되어 있는 빈을 반환

그런데 myLogger가 request 스코프이기 때문에 request 스코프에 빈이 없으면 새로 생성하고, 이미 있으면 있는 빈을 반환한다

→ 스프링 컨테이너가 실행되고 request 요청이 들어오지 않아도 provider가 요청이 들어오면 myLogger 빈을 찾아오기 때문에 오류가 나지 않는 것

```java
[24fc8286-c625-415b-bdb9-a69952e60227] request scope bean create: hello.core.common.MyLogger@22830869
[24fc8286-c625-415b-bdb9-a69952e60227] [http://localhost:8080/log-demo] controller test
[24fc8286-c625-415b-bdb9-a69952e60227] [http://localhost:8080/log-demo] service id = testId
[24fc8286-c625-415b-bdb9-a69952e60227] request scope bean close: hello.core.common.MyLogger@22830869
```

request scope의 생성, 소멸

[b7aef91e-d1f4-4558-abe2-0b847559b006] request scope bean create: hello.core.common.MyLogger@42c13350

[a7c1f568-ebb9-4b4a-a04b-18129eb09ef9] request scope bean create: hello.core.common.MyLogger@5fcce353

[b7aef91e-d1f4-4558-abe2-0b847559b006] [[http://localhost:8080/log-demo](http://localhost:8080/log-demo)] controller test

[b7aef91e-d1f4-4558-abe2-0b847559b006] [[http://localhost:8080/log-demo](http://localhost:8080/log-demo)] service id = testId

[b7aef91e-d1f4-4558-abe2-0b847559b006] request scope bean close: hello.core.common.MyLogger@42c13350

[a7c1f568-ebb9-4b4a-a04b-18129eb09ef9] [[http://localhost:8080/log-demo](http://localhost:8080/log-demo)] controller test

[a7c1f568-ebb9-4b4a-a04b-18129eb09ef9] [[http://localhost:8080/log-demo](http://localhost:8080/log-demo)] service id = testId

[a7c1f568-ebb9-4b4a-a04b-18129eb09ef9] request scope bean close: hello.core.common.MyLogger@5fcce353

**각각의 생명주기에 따라 생성-소멸되는 것을 확인할 수 있음**

`ObjectProvider.getObject()` 를 `LogDemoController`, `LogDemoService` 각각 한 번씩 따로호출해도 **같은 HTTP 요청**이면 같은 스프링 빈 반환

---

## 스코프와 프록시

```java
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {
```

MyLogger의 가짜 프록시 클래스를 만들어두고 HTTP request와 상관없이 가짜 프록시 클래스를 다른 빈에 미리 주입

```java
myLogger = class hello.core.common.MyLogger$$EnhancerBySpringCGLIB$$b4100ae8
```

CGLIB 라이브러리로 내 클래스를 상속받은 가짜 프록시 객체를 만들어서 주입

→ 순수한 MyLogger 클래스가 아니라 `MyLogger$$EnhancerBySpringCGLIB$$b4100ae8`

가짜 프록시 객체는 요청이 오면 그때 내부에서 진짜 빈을 요청하는 위임 로직이 들어있다

클라이언트가 myLogger.logic() 호출 → 가짜 프록시 객체의 메서드를 호출한 것 → 가짜 프록시 객체가 진짜 myLogger.logic() 호출

**동작 정리**

CGLIB이라는 라이브러리로 가짜 프록시 객체를 만들어줌

가짜 프록시 객체는 실제 요청이 오면 그때 내부에서 실제 빈을 요청하는 위임 로직이 들어있음

가짜 프록시 객체는 request scope와는 관계가 없다

프록시 객체 덕분에 클라이언트는 싱글톤 빈을 사용하듯이 request scope를 사용할 수 있다

Provider나 프록시나 핵심 아이디어 = **진짜 객체 조회를 꼭 필요한 시점까지 지연처리**

애노테이션 설정 변경만으로 원본 객체를 프록시 객체로 대체할 수 있다

**주의점**

- 싱글톤과 다르게 동작하기 때문에 주의
- 필요한 곳에만 최소화해서 사용