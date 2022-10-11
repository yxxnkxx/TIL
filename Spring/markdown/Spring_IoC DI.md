# IoC와 DI

# 제어 역전(IoC)

일반적인 자바 개발: 객체 선언 > 의존성 생성 > 객체에서 제공

일련의 작업들을 개발자가 직접 제어함

**제어 역전:** 사용할 객체를 직접 생성하지 않고 객체의 생명주기 관리를 외부에 위임

‘외부’: 스프링 컨테이너(Spring Container)

## 스프링에서 제어 역전

Bean: 스프링에서의 객체

Container: Bean의 인스턴스화 조립, 관리의 역할, 사용 소멸에 대한 처리 담당

1. 객체 생성
2. 의존성 객체 주입: 제어권을 **스프링에게 위임하여 스프링이 만들어놓은 객체를 주입**
3. 의존성 객체 메소드 호출

스프링 컨테이너가 객체를 만드는 시기: 컨테이너가 만들어질 때 필요한 객체를 모두 생성

- cf.  `lazy-init`: getBean을 할 때마다 객체가 생성

기본: **싱글톤 패턴 `scope:"singleton"`**

- cf. `scope="prototype"` getBean할 때마다 새로운 객체가 생성

### 스프링에서 객체 생성 방식

1. Bean 태그
    
    ```xml
    <bean class="spring.Person" id="person"></bean>
    <bean class="spring.Movie" id="movie"></bean>
    ```
    
    ```java
    public class MovieTest {
    
    	public static void main(String[] args) {
    		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
    		Movie movie = context.getBean("movie", Movie.class);
    		Person person = context.getBean("person", Person.class);
    
    	}
    }
    ```
    
2. component-scan
    
    ```xml
    <context:component-scan base-package="spring"/> <!-- 패키지 이름 -->
    ```
    
    ```java
    package spring; // 해당 패키지에 있는 파일
    
    import org.springframework.stereotype.Component;
    
    @Component // annotation을 통해 자동으로 component로 인식해서 bean 객체 생성
    public class Action implements Movie {
    	@Override
    	public String getInfo() {
    		// TODO Auto-generated method stub
    		return "액션";
    	}
    }
    ```
    

# 의존성 주입(DI)

의존성: ClassA 객체가 어떤 일을 처리하기 위해 ClassB 객체의 도움을 받아야만 일을 처리할 수 있는 경우 ‘ClassA는 ClassB에 의존한다.’

**의존성 주입**: 사용 객체에 대하여 직접 의존성을 생성하는 것이 아니라 외부 컨테이너가 생성한 객체를 주입받아 사용하는 방식

## 스프링에서의 의존성 주입

### 1. 생성자를 통한 의존성 주입

```java
public class DIController {

	MyService myService;

	@Autowired
	public DIController(MyService myService) {
		this.myService = myService;
	}
}
```

### 2. 필드 객체 선언을 통한 의존성 주입

```java
public class DIController {
	@AutoWired
	MyService myService;

	public DIController(MyService myService) {
		this.myService = myService;
	}
}
```

### 3. setter 메서드를 통한 의존성 주입 (설정자 주입)

```java
public class DIController {

	MyService myService;

	public DIController(MyService myService) {
		this.myService = myService;
	}

	@Autowired
	public void serMyService(MyService myService) {
		this.myService = myService;
}
```

*스프링 공식 문서 권장 = 생성자를 통한 의존성 주비

→ 레퍼런스 객체 없이는 객체를 초기화할 수 없음

의존 관계 없이 객체 생성x → 더 엄격한 방식

---

[https://velog.io/@gillog/Spring-DIDependency-Injection](https://velog.io/@gillog/Spring-DIDependency-Injection)

장정우 - 스프링 부트 핵심 가이드 (위키북스)